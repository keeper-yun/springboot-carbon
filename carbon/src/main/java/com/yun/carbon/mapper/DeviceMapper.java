package com.yun.carbon.mapper;

import com.yun.carbon.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperation;

@Mapper
public interface DeviceMapper {
    Integer findFactoryId(@Param("nameCo2") String nameCo2);

}
