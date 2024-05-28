package com.example.teammate.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class})
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;

    @LastModifiedDate
    @Column(name ="moddate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate modDate;
}
