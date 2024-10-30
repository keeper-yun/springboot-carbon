package com.yun.carbon.controller;


import com.yun.carbon.entity.Factory;
import com.yun.carbon.mapper.FactoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    private FactoryMapper factoryMapper;

    @GetMapping("/findAll")
    public List<Factory> findAll(){
        return factoryMapper.findAll();
    }

}
