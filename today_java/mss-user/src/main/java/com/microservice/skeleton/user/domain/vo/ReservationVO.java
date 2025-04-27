package com.microservice.skeleton.user.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel("预约记录视图对象")

public class ReservationVO {
    @ApiModelProperty("预约ID")
    private Integer id;

    @ApiModelProperty("预约编号")
    private String reservationNo;

    @ApiModelProperty("教室ID")
    private Integer roomId;

    @ApiModelProperty("教室名称")
    private String roomName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("预约日期")
    private LocalDate reservationDate;

    @ApiModelProperty("活动名称")
    private String activityName;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("状态：0-待审核，1-已通过，2-已拒绝，3-已取消，4-已完成")
    private Integer status;

    @ApiModelProperty("状态描述")
    private String statusDesc;

    @ApiModelProperty("参与人数")
    private Integer attendees;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
}
