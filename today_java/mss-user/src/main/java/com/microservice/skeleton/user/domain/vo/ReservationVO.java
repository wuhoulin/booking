//package com.microservice.skeleton.user.domain.vo;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//@Data
//@ApiModel(description = "预约视图对象")
//public class ReservationVO {
//
//    @ApiModelProperty(value = "预约ID", example = "1")
//    private Integer id;
//
//    @ApiModelProperty(value = "预约编号", example = "R202305150001")
//    private String reservationNo;
//
//    @ApiModelProperty(value = "预约用户信息")
//    private UserVO user;
//
//    @ApiModelProperty(value = "预约教室信息")
//    private RoomVO room;
//
//    @ApiModelProperty(value = "预约日期", example = "2023-05-15")
//    private LocalDate reservationDate;
//
//    @ApiModelProperty(value = "预约时间段")
//    private TimePointVO TimePoint;
//
//    @ApiModelProperty(value = "预约用途", example = "举办学生会会议")
//    private String purpose;
//
//    @ApiModelProperty(value = "参与人数", example = "20")
//    private Integer attendees;
//
//    @ApiModelProperty(value = "状态：0-待审核，1-已通过，2-已拒绝，3-已取消，4-已完成", example = "0")
//    private Integer status;
//
//    @ApiModelProperty(value = "状态描述", example = "待审核")
//    private String statusDesc;
//
//    @ApiModelProperty(value = "备注", example = "需要准备投影仪和白板")
//    private String remark;
//
//    @ApiModelProperty(value = "创建时间", example = "2023-05-01T10:00:00")
//    private LocalDateTime createdAt;
//}
