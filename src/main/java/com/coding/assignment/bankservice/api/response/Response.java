package com.coding.assignment.bankservice.api.response;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class Response<T> implements Serializable {
    public boolean ok;
    public T data;

    public Response(T data, boolean ok) {
        this.setOk(ok);
        this.data = data;
    }
    
    public Response(T data) {
        this.setOk(true);
        this.data = data;
    }
}
