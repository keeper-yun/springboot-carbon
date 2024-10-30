package com.yun.carbon.mapper;


import com.yun.carbon.entity.Factory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryMapper extends JpaRepository<Factory,Integer>{}

