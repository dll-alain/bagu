package com.ali.shali.mapstruct;

import lombok.Builder;

import java.util.Date;

/**
 * @Author shali
 * @Date 2023/10/10 11:46
 * @PackageName:com.ali.shali.mapstruct
 * @ClassName: Demo01
 * @Description: TODO
 * @Version 1.0
 */
public class Demo01 {

    public static void testNormal() {
        System.out.println("-----------testNormal-----start------");
        UserPo userPo = UserPo.builder().id(1L).gmtCreate(new Date()).buyerId(666L).userNick("测试mapstruct").userVerified("ok").age(18L).build();
        System.out.println("1234" + userPo);
        UserEntity userEntity = IMapper.I_MAPPER.po2Entity(userPo);
        System.out.println(userEntity);
        System.out.println("-----------testNormal-----ent------");
    }

    public static void main(String[] args) {
        testNormal();
    }
}
