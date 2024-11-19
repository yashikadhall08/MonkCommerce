package com.example.demo.service;

import com.example.demo.entity.CouponEntity;
import com.example.demo.model.Coupon;
import com.example.demo.repository.CouponRepo;
import com.example.demo.response.CouponResponse;
import com.example.demo.utils.MapperUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CouponService {

    @Autowired
    private CouponRepo couponRepo;

    public void saveCoupons(Coupon coupon) {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setId(coupon.getId());
        couponEntity.setType(coupon.getType());
        couponEntity.setDetails(coupon.getDetails().toString());
        log.info("CouponEntity {}" , couponEntity);
        couponRepo.save(couponEntity);
    }

    public List<Coupon> getCoupons() throws JsonProcessingException {
        List<CouponEntity> couponEntityList = couponRepo.findAll();
        List<Coupon> allCoupons = new ArrayList<>();
        for(CouponEntity couponEntity : couponEntityList) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            String respData = objectMapper.writeValueAsString(couponEntity.getDetails());
            Map respMap = objectMapper.readValue(respData, Map.class);

            Coupon coupon = new Coupon(couponEntity.getId(), couponEntity.getType(), respMap);
            allCoupons.add(coupon);
        }

        return allCoupons;
    }
}
