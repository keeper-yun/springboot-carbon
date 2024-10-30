package com.yun.carbon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AlarmDto {

    private Integer recordId;
    private String factoryName;
    private String location;
    private String phone;
    private Date timestamp;

}
