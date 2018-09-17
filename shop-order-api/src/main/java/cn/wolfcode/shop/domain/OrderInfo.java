package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter@Getter
public class OrderInfo extends BaseDomain {
    private String orderSn;//发票编号
    private Long userId;//用户id
    private BigDecimal orderAmount;//订单总金额
    private int payType;//支付类型
    private int orderStatus;//订单状态
    private int flowStatus;//物流状态
    private int payStatus;//支付状态
    private Date orderTime;//下单时间
    private String phone;//订单电话
    private String consignee;//订单联系人
    private String country;//国家
    private String province;//身份
    private String city;//城市
    private String district;//区
    private String address;//详细地址
    private String zipcode;//右边
    private List<OrderProduct> orderProductList;//订单商品集合

    //付款方式,0为余额付款，1为在线付款，2为货到付款
    public String getPayTypeStr(){
        switch (this.payType){
            case 0:
                return "余额付款";
            case 1:
                return "在线付款";
            case 2:
                return "货到付款";
            default:
                return "余额付款";
        }
    }

    /**
     * 订单状态：0为未确认，1为已确认，2,为已完成，3为取消
     * 订单物流状态：0为未发货，1为已发货，2为确认收货，3为退货
     * 订单支付状态：0为未付款，1为付款
     * @return
     */
    public String getStatus(){
        //先判断是支付方式是什么
        if(payType == 2){
            return judge();
        }else{
            //判断付款状态是什么
            if(payStatus == 0){
                return "未付款";
            }else{
                return judge();
            }
        }

    }

    /**
     * 判断订单状态方法
     * @return
     */
    private String judge() {
        //判断订单状态
        if(orderStatus == 0){
            return "未确认";
        }else if(orderStatus == 1){
            if(flowStatus == 1){
                return "已发货";
            }else {
                return "已确认";
            }
        }else if(orderStatus == 2){
            if(flowStatus == 3){
                return "退货中";
            }else if (flowStatus == 4){
                return "已退货";
            }else{
                return "已完成";
            }
        }else {
            return "取消";
        }
    }

    /**
     * 订单状态：0为未确认，1为已确认，2,为已完成，3为取消
     * @return
     */
    public String getOrderStatusStr(){
        switch (this.orderStatus){
            case 0:
                return "未确认";
            case 1:
                return "已确认";
            case 2:
                return "以完成";
            case 3:
                return "取消";
            default:
                return "未确认";
        }
    }

    /**
     * 订单物流状态：0为未发货，1为已发货，2为确认收货，3为退货
     * @return
     */
    public String getFlowStatusStr(){
        switch (this.flowStatus){
            case 0:
                return "未发货";
            case 1:
                return "已发货";
            case 2:
                return "确认收货";
            case 3:
                return "退货中";
            case 4:
                return "已退货";
            default:
                return "未发货";
        }
    }

    /**
     * 订单支付状态：0为未付款，1为付款
     * @return
     */
    public String getPayStatusStr(){
        switch (this.payStatus){
            case 0:
                return "未付款";
            case 1:
                return "付款";
            default:
                return "未付款";
        }
    }
}