package cn.wolfcode.shop.qo;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductQueryObject extends QueryObject {

    private String keyword;

    public void setKeyword(String keyword){
        if(keyword != null && keyword.trim().length() > 0){
            this.keyword = "%"+keyword+"%";
        }
    }

}
