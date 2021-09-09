package me.greeenly.userservice.dto;

import lombok.Data;
import me.greeenly.userservice.vo.ResponseOrder;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createAt;

    // 중간단계 클래스로 이동될때, 사용할 예정
    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
