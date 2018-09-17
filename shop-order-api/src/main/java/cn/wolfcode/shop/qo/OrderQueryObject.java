package cn.wolfcode.shop.qo;

import lombok.Setter;


public class OrderQueryObject extends QueryObject {


    @Setter
    private String keyword;

    public String getKeyword(){
        if(this.keyword != null && keyword.trim().length() > 0){
            return "%"+keyword+"%";
        }
        return null;
    }
}
