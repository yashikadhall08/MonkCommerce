package com.example.demo.entity;

import com.example.demo.utils.MapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "coupon")
@Getter
@Setter
public class CouponEntity {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DETAILS")
    @Lob
    private String details;

    @Override
    public String toString() {
       return MapperUtil.toJson(this);
    }
}
