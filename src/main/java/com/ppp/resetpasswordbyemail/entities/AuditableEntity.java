package com.ppp.resetpasswordbyemail.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import static jakarta.persistence.TemporalType.TIMESTAMP;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class AuditableEntity<T> {

    @CreatedBy
    @Column(nullable = false, updatable = false)
    protected T createdBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(nullable = false, updatable = false)
    protected Date createdAt;

    @LastModifiedBy
    @Column(nullable = true, insertable = false)
    protected T  editedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(nullable = true, insertable = false)
    protected Date updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    public boolean isDeleted;

}
