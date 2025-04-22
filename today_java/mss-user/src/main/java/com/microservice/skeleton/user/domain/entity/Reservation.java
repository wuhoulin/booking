package com.microservice.skeleton.user.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("reservations")
@ApiModel(description = "预约信息")
public class Reservation {

    @ApiModelProperty("预约ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("预约编号")
    @TableField("reservation_no")
    private String reservationNo;

    @ApiModelProperty("预约教室ID")
    @TableField("room_id")
    private Integer roomId;

    @ApiModelProperty("预约日期")
    @TableField("reservation_date")
    private LocalDate reservationDate;

    @ApiModelProperty("开始时间点ID")
    @TableField("start_time_id")
    private Integer startTimeId;

    @ApiModelProperty("结束时间点ID")
    @TableField("end_time_id")
    private Integer endTimeId;

    @ApiModelProperty("活动名称或用途")
    @TableField("activity_name")
    private String activityName;

    @ApiModelProperty("申请部门")
    @TableField("department")
    private String department;

    @ApiModelProperty("是否需要多媒体投屏")
    @TableField("need_projection")
    private Boolean needProjection;

    @ApiModelProperty("申请人姓名") // ✅ 修改描述
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("单点登录 CN") // ✅ 新增字段
    @TableField("user_cn")
    private String userCn;

    @ApiModelProperty("所属学院")
    @TableField("college")
    private String college;

    @ApiModelProperty("年级专业")
    @TableField("major")
    private String major;

    @ApiModelProperty("联系方式")
    @TableField("contact")
    private String contact;

    @ApiModelProperty("指导老师姓名（如有）")
    @TableField("teacher_name")
    private String teacherName;

    @ApiModelProperty("指导老师联系方式（如有）")
    @TableField("teacher_contact")
    private String teacherContact;

    @ApiModelProperty("其他需求（如有）")
    @TableField("other_requirements")
    private String otherRequirements;

    @ApiModelProperty("参与人数")
    @TableField("attendees")
    private Integer attendees;

    @ApiModelProperty("预约状态：0-待审核，1-已通过，2-已拒绝，3-已取消，4-已完成")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("备注（管理员填写）")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
