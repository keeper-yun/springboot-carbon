package com.yun.carbon.service.impl;

import com.yun.carbon.dto.CloudResponse;
import com.yun.carbon.dto.ListData;
import com.yun.carbon.dto.MonitoringDataDTO;
import com.yun.carbon.entity.Alarm;
import com.yun.carbon.entity.MonitoringRecord;
import com.yun.carbon.mapper.AlarmMapper;
import com.yun.carbon.mapper.DeviceMapper;
import com.yun.carbon.mapper.MonitoringRecordMapper;
import com.yun.carbon.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    @Autowired
    private MonitoringRecordMapper monitoringRecordMapper;

    //10秒所有数据都要更新一次
//    @Scheduled(fixedRate = 10000)
//    public void fetchMonitoringData() {
//        List<MonitoringRecord> records = findAllRecords();
//        // 可以在这里进行处理或存储状态
//        System.out.println("Fetched monitoring records: " + records);
//    }
    @Scheduled(fixedRate = 10000)
    public void fetchMonitoringData() {
        List<MonitoringDataDTO> records = findAllRecords();
        // 可以在这里进行处理或存储状态
        System.out.println("(All)Fetched monitoring records: " + records);
    }

    // 定时每10秒读取一条最新数据
//    @Scheduled(fixedRate = 10000)
//    public void fetchMonitoringData2() {
//        Optional<MonitoringRecord> record = monitoringRecordMapper.findTopByOrderByTimestampDesc();
//        record.ifPresent(r -> {
//            // 在这里处理或存储状态
//            System.out.println("Fetched monitoring record: " + r);
//        });
//    }
    @Scheduled(fixedRate = 10000)
    public void fetchMonitoringData2() {
        List<MonitoringDataDTO> records = findLatestMonitoringDataForAllFactories();
        // 遍历每条记录并处理

            // 在这里处理或存储状态
        System.out.println("(Latest)Fetched monitoring record: " + records);
            // 例如，可以将其存储到某个数据结构中或进行其他操作

    }


//    @Override
//    public List<MonitoringRecord> findAllRecords() {
//        return monitoringRecordMapper.findAllMonitoringData();
//    }
    @Override
    public List<MonitoringDataDTO> findAllRecords() {
        return monitoringRecordMapper.findAllMonitoringData();
    }

    @Override
    public List<MonitoringDataDTO> findLatestMonitoringDataForAllFactories() {
        return monitoringRecordMapper.findLatestMonitoringDataForAllFactories();
    }

    @Override
    public List<MonitoringDataDTO> findMonitoringDataInRange(Date startDate, Date endDate) {
        return monitoringRecordMapper.findMonitoringDataInRange(startDate, endDate);
    }



    //云平台数据
    //历史数据
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String url = "https://iot-api.heclouds.com/thingmodel/query-device-property-history?product_id=TKDr9E8tk0&device_name=CO2_READ&identifier=PPM&start_time=1729289628000&end_time=1729302270752&limit=3";
//
//    @Scheduled(fixedRate = 10000) // 每10秒调用一次
//    public void fetchDataFromCloudHistory() {
//
//        Random random = new Random();
//        int num = random.nextInt(1,6);
//        double num2 = random.nextDouble(1.0,3.5);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "version=2022-05-01&res=userid%2F381488&et=1729343788&method=sha1&sign=yQtkz4kOdpbW4znrr%2ByQ9n0Mgcc%3D");
//        headers.set("Accept","application/json, text/plain, */*");
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        ResponseEntity<CloudResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, CloudResponse.class);
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            CloudResponse cloudResponse = response.getBody();
//            if (cloudResponse != null && cloudResponse.getData() != null) {
//                for (ListData data : cloudResponse.getData().getList()) {
//                    MonitoringRecord record = new MonitoringRecord();
//
//                    BigDecimal co2level = new BigDecimal(data.getValue());
//                    BigDecimal co2flow = new BigDecimal(num2);
//                    BigDecimal lou = new BigDecimal(1.18);
//                    BigDecimal co2output = co2level.multiply(co2flow.multiply(lou));
//                    BigDecimal x = new BigDecimal(1000000);
//                    co2output = co2output.divide(x, RoundingMode.HALF_UP);
//
//
//                    record.setCo2_Level(co2level); // 转换为 BigDecimal
//                    record.setTimestamp(new Date(data.getTime())); // 转换时间戳为 Date
//                    record.setFactoryId(num);
//                    record.setCo2_Flow(co2flow);
//                    record.setCo2_Output(co2output);
//
//                    //record.setCo2_Flow(new BigDecimal());
//                    monitoringRecordMapper.save(record);
//                }
//                System.out.println("Fetched and saved records: " + cloudResponse.getData().getList().size());
//            }
//        } else {
//            System.err.println("Failed to fetch data from cloud: " + response.getStatusCode());
//        }
//    }


    //实时数据
//    @Autowired
//    private  DeviceMapper deviceMapper;
//
//    @Autowired
//    private AlarmMapper alarmMapper;
//
//    int i = 0;
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final String url = "https://iot-api.heclouds.com/thingmodel/query-device-property?product_id=TKDr9E8tk0&device_name=CO2_READ";
//    private final String url2 = "https://iot-api.heclouds.com/thingmodel/query-device-property?product_id=TKDr9E8tk0&device_name=CO2_READ2";
//    private String posturl = "";
//    int a = 0;
//
//    @Scheduled(fixedRate = 5000) // 每10秒调用一次
//    public void fetchDataFromCloudHistory() {
//
//        Random random = new Random();
//        int num = random.nextInt(1,6);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "version=2022-05-01&res=userid%2F381488&et=1730117230&method=sha1&sign=wVI1uyZEDYjSfAEA%2FWzvq5OvmeQ%3D");
//        headers.set("Accept","application/json, text/plain, */*");
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
////        if(a%2 == 0){
////            posturl = url;
////        }else if(a%2 == 1){
////            posturl = url2;
////        }
//        ResponseEntity<CloudResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, CloudResponse.class);
//
//        if (response.getStatusCode().is2xxSuccessful()) {
//            CloudResponse cloudResponse = response.getBody();
//            if (cloudResponse != null && cloudResponse.getData() != null) {
//
//                MonitoringRecord record = new MonitoringRecord();
//
//                BigDecimal co2Value = null;
//                BigDecimal flowValue = null;
//
//                BigDecimal lou = new BigDecimal(1.183);
//                BigDecimal ppm = new BigDecimal(1000);
//
//
//                Date time = new Date();
//
//                for (ListData data : cloudResponse.getData()) {
//
//                    String identifier = data.getIdentifier();
//                    BigDecimal value = new BigDecimal(data.getValue());
//
//                    if (identifier.equals("PPM")) {
/////*  --->    --->  */    num = deviceMapper.findFactoryId(identifier);
////                        num = 2;
//
////                        if(a%2 == 0){
////                            num = 1;
////                        }else if(a%2 == 1){
////                            num = 2;
////                        }
//                        num = 2;
//                        co2Value = value;
//                    } else if (identifier.equals("speed")) {
//                        flowValue = value; // 提取流量值
//                        time = new Date(data.getTime());
//                    }
//
//
//                    if(co2Value != null && flowValue != null) {
//
//                        record.setCo2_Level(co2Value); // 转换为 BigDecimal
//                        record.setTimestamp(time); // 转换时间戳为 Date
//                        record.setFactoryId(num);
//                        record.setCo2_Flow(flowValue);
//                        record.setCo2_Output(co2Value.multiply(flowValue.multiply(lou)));
//
//                        monitoringRecordMapper.save(record);
//
//                        BigDecimal result = co2Value.subtract(ppm);
//
//                        if(result.compareTo(BigDecimal.ZERO) >= 0){
//
//                            Alarm alarm = new Alarm();
//
//                            alarm.setFactoryId(num);
//                            alarm.setTimestamp(time);
//                            alarm.setInfo("");
//                            alarmMapper.save(alarm);
//
//                        }
//
//                        co2Value = null;
//                        flowValue = null;
//
//                    }
//
//
//                }
//
//
//
//                System.out.println("Fetched and saved records: " + cloudResponse.getData().size());
//            }
//        } else {
//            System.err.println("Failed to fetch data from cloud: " + response.getStatusCode());
//        }
//        a++;
//
//    }





}