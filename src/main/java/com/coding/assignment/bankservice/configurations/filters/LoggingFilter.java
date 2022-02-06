package com.coding.assignment.bankservice.configurations.filters;

import com.coding.assignment.bankservice.configurations.LoggingConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@WebFilter
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    @Autowired
    private LoggingConfiguration loggingConfiguration;

    private Pattern requestMaskingPattern;

    private Pattern responseMaskingPattern;

    public LoggingFilter(@Value(value = "${log.masked.request}") String[] maskedRequestFields,
                         @Value(value = "${log.masked.response}") String[] maskedResponseFields) {
        String requestFields = String.join("|", List.of(maskedRequestFields));
        String responseFields = String.join("|", List.of(maskedResponseFields));
        this.requestMaskingPattern = Pattern.compile("(%s)\" *: *(\"([^\"]+)\")".formatted(requestFields));
        this.responseMaskingPattern = Pattern.compile("(%s)\" *: *(\"([^\"]+)\")".formatted(responseFields));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().contains("swagger") ||
                request.getServletPath().contains("api-docs") ||
                request.getServletPath().contains("actuator/health")) {
            filterChain.doFilter(request, response);
            return;
        }

        long startTime = System.currentTimeMillis();
        StringBuilder reqInfo = new StringBuilder()
                .append("REQUEST")
                .append("[")
                .append(request.getMethod())
                .append(" ").append((CharSequence) request.getRequestURL())
                .append(request.getQueryString() != null ? "?%s".formatted(request.getQueryString()) : "")
                .append("] ");

        String requestId = request.getHeader("x-request-id");
        if (requestId == null) {
            requestId = UUID.randomUUID().toString();
            response.addHeader("x-request-id", requestId);
            request.setAttribute("x-request-id", requestId);
        }

        reqInfo.append("ID[")
                .append(requestId)
                .append("] ");

        var requestWrapper = new ContentCachingRequestWrapper(request);
        var responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        long duration = System.currentTimeMillis() - startTime;

        reqInfo.append("DURATION[")
                .append(duration)
                .append("] STATUS[")
                .append(response.getStatus())
                .append("] ");

        addRequest(request, reqInfo, requestWrapper);
        if (!request.getServletPath().equals("/app/data"))
            addResponse(response, reqInfo, responseWrapper);

        responseWrapper.copyBodyToResponse();

        Object ex = request.getAttribute("ex");

        if (ex == null)
            log.info(reqInfo.toString());
        else
            log.info(reqInfo.toString(), ((Throwable) ex));
    }

    private void addRequest(HttpServletRequest request, StringBuilder reqInfo, ContentCachingRequestWrapper requestWrapper) {
        //if this condition is false, the logging filter will show the request body
        if (!loggingConfiguration.isRequest()) return;

        String requestBody = getContentAsString(requestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        requestBody = mask(requestBody, requestMaskingPattern);
        if (requestBody.length() > 0) {
            reqInfo.append("REQUEST_BODY: ")
                    .append(requestBody.replace("\r", "").replace(" ", ""));
        }
    }

    private void addResponse(HttpServletResponse response, StringBuilder reqInfo, ContentCachingResponseWrapper responseWrapper) {
        //if this condition is false, the logging filter will show the response body
        if (!loggingConfiguration.isResponse()) return;
        if (response == null) return;
        if (!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(response.getContentType())) return;

        String responseBody = getContentAsString(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());
        responseBody = mask(responseBody, responseMaskingPattern);
        reqInfo.append(" RESPONSE_BODY: ")
                .append(responseBody);
    }

    private String getContentAsString(byte[] buf, String charsetName) {
        if (buf == null || buf.length == 0)
            return "";
        try {
            return new String(buf, 0, buf.length, charsetName)
                    .replace("\n", "");
        } catch (UnsupportedEncodingException ex) {
            return "Unsupported Encoding";
        }
    }

    private String mask(String msg, Pattern pattern) {
        Matcher matcher = pattern.matcher(msg);
        StringBuilder buffer = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, "$1\": \"****\"");
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
