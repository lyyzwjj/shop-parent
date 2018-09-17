package cn.wolfcode.shop.qo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter@Getter
public class QueryObject implements Serializable{

    //当前页
    protected Integer currentPage = 1;

    //每页显示的数据大小
    protected Integer pageSize = 5;

    public Integer getStart(){
        return (currentPage - 1) * pageSize;
    }
}
