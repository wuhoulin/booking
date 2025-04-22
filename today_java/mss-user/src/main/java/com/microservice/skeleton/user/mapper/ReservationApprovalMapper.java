package com.microservice.skeleton.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.microservice.skeleton.user.domain.entity.ReservationApproval;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationApprovalMapper extends BaseMapper<ReservationApproval> {
}
