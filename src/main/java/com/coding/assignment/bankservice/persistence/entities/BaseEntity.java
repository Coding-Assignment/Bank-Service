package com.coding.assignment.bankservice.persistence.entities;


import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @Type(type = "pg-uuid")
    private UUID id;

    @NotNull
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    @NotNull
    @Column(name = "is_deleted", nullable = false, columnDefinition = "bool default false")
    private boolean isDeleted;

    public BaseEntity() {
        this.id = UUID.randomUUID();
    }
}
