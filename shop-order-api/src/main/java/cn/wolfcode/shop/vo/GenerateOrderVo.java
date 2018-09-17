package cn.wolfcode.shop.vo;

import cn.wolfcode.shop.domain.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wolfcode on 2018/8/20.
 */
@Setter@Getter
public class GenerateOrderVo implements Serializable {
    /*{
        "userAddressId":44,
        "pay":{"payType":2},
        "productSkuIds":[147,148],
        "invoice":{"invoiceType":2,"invoiceHead":1,"invoiceContent":1,"invoicePhone":"13022004622","invoiceEmail":""}
    }
    {
	"userAddressId":1,
	"pay":{"payType":2},
	"carList":[
				{"id":"","skuId":98,"productNumber":2,"productName":"测试商品","productImg":""},
				{"id":"","skuId":99,"productNumber":1,"productName":"测试商品2","productImg":""}
			  ],
	"invoice":{"invoiceType":2,"invoiceHead":1,"invoiceContent":1,"invoicePhone":"13022004622","invoiceEmail":""}
    }
    */
    private Long userAddressId;
    private PayVo pay;
    private List<Long> productSkuIds;
    private Invoice invoice;

}
