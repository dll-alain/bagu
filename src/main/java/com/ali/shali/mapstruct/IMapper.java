package com.ali.shali.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author shali
 * @Date 2023/10/10 11:50
 * @PackageName:com.ali.shali.mapstruct
 * @ClassName: IMapper
 * @Description: 接口中有一个常量和一个方法，常量的值是接口的实现类，这个实现类是Mapstruct默认帮我们实现的
 * @Version 1.0
 */
@Mapper
public interface IMapper {
    IMapper I_MAPPER = Mappers.getMapper(IMapper.class);


    @Mapping(target = "userNickDiff", source = "userNick")
    UserEntity po2Entity(UserPo userPo);

}
