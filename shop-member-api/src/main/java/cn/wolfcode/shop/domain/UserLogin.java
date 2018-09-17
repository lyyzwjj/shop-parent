package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin extends BaseDomain {
    public static int LOGIN_STATE_NORMAL = 0;
    public static int LOGIN_STATE_ERROR = 1;
    private String username;
    private String password;
    private int state = LOGIN_STATE_NORMAL;
}