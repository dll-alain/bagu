package com.ali.shali.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author shali
 * @Date 2023/10/10 11:46
 * @PackageName:com.ali.shali.mapstruct
 * @ClassName: UserPo
 * @Description: TODO
 * @Version 1.0
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPo {
    private Long id;
    private Date gmtCreate;
    private Date createTime;
    private Long buyerId;
    private Long age;
    private String userNick;
    private String userVerified;

}
