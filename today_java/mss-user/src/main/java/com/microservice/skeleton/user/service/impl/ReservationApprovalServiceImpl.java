package com.microservice.skeleton.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microservice.skeleton.user.domain.entity.ReservationApproval;
import com.microservice.skeleton.user.mapper.ReservationApprovalMapper;
import com.microservice.skeleton.user.service.ReservationApprovalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationApprovalServiceImpl extends ServiceImpl<ReservationApprovalMapper, ReservationApproval> implements ReservationApprovalService {

    @Override
    public List<ReservationApproval> getApprovalsByReservation(Integer reservationId) {
        LambdaQueryWrapper<ReservationApproval> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReservationApproval::getReservationId, reservationId);
        return this.list(queryWrapper);
    }

    @Override
    public List<ReservationApproval> getApprovalsByAdmin(Integer adminId) {
        LambdaQueryWrapper<ReservationApproval> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReservationApproval::getAdminId, adminId);
        return this.list(queryWrapper);
    }
}
