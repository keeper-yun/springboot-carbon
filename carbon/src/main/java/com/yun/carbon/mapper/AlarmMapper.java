package com.yun.carbon.mapper;

import com.yun.carbon.dto.AlarmDto;
import com.yun.carbon.entity.Alarm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmMapper extends JpaRepository<Alarm,Integer> {

    @Query("SELECT new com.yun.carbon.dto.AlarmDto(a.recordId, f.factoryName, f.location, f.phone, a.timestamp) " +
            "FROM Alarm a JOIN Factory f ON a.factoryId = f.factoryId")
    List<AlarmDto> findAlarm();
}
