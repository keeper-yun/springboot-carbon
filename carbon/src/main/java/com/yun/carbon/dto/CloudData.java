package com.yun.carbon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CloudData {

    private List<ListData> list;

    public List<ListData> getList() {
        return list;
    }

    public void setList(List<ListData> list) {
        this.list = list;
    }
}
