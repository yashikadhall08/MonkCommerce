package com.example.demo.controller;

import com.example.demo.model.Coupon;
import com.example.demo.repository.CouponRepo;
import com.example.demo.response.CouponResponse;
import com.example.demo.service.CouponService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    CouponRepo couponRepo;

    @Autowired
    CouponService couponService;

    @PostMapping(value = "/post",  produces = "application/json")
    @ResponseBody
    public ResponseEntity<Coupon> addCoupons(@RequestBody Coupon coupon) {
        log.info("coupon {}" , coupon.toString());
        couponService.saveCoupons(coupon);
        return ResponseEntity.status(200).body(coupon);
    }

    @GetMapping(value = "/get" ,  produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Coupon>> getCoupon() throws JsonProcessingException {
        return ResponseEntity.status(200).body(couponService.getCoupons());
    }
}
