package com.microservice.skeleton.user.domain.dto;

import lombok.Data;

@Data

public class TimeRangeDto {
    private String start;  // 格式 "08:00"
    private String end;    // 格式 "09:30"
    // + getter/setter
}
