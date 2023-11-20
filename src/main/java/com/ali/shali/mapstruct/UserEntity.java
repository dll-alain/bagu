package com.ali.shali.mapstruct;

import lombok.Data;

import java.util.Date;

/**
 * @Author shali
 * @Date 2023/10/10 11:48
 * @PackageName:com.ali.shali.mapstruct
 * @ClassName: UserEntity
 * @Description: TODO
 * @Version 1.0
 */
@Data
public class UserEntity {
    private Long id;
    private Date gmtCreate;
    private Date createTime;
    private Long buyerId;
    private Long age;
    private String userNickDiff;
    private String userVerified;
}
