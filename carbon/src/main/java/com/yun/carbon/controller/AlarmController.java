package com.yun.carbon.controller;

import com.yun.carbon.dto.AlarmDto;
import com.yun.carbon.mapper.AlarmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alarm")
public class AlarmController {

    @Autowired
    private AlarmMapper alarmMapper;

    @GetMapping("/selectRecord")
    public List<AlarmDto> findRecord(){

        return alarmMapper.findAlarm();

    }

}
