package me.greeenly.userservice.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {
    private String productId;
    private Integer quantity;
    private Integer unitPrice; // 단가
    private Integer totalPrice;
    private Date CreateAt;

    private String orderId;
}
