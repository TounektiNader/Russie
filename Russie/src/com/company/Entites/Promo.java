/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Entites;
import java.util.Date;
/**
 *
 * @author 21650
 */
public class Promo {
    private String coupon;
    private int promotion;
    private String expiration;

    public Promo() {
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
    
    public Promo(String coupon, int promotion, String expiration) {
        this.coupon = coupon;
        this.promotion = promotion;
        this.expiration = expiration;
    }
    
}

