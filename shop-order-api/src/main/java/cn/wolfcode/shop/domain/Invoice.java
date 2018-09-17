package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Invoice extends BaseDomain {
    private int invoiceType;//发票类型
    private int invoiceHead;//发票开头  个人 or 单位
    private Long userId;//用户id
    private Long orderId;//订单id
    private int invoiceContent;//发票内容
    private String invoicePhone;//发票收票人电话
    private String invoiceEmail;//发票收票人邮箱
}
