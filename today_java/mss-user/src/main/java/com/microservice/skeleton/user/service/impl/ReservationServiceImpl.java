package com.microservice.skeleton.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;

import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeSet;
import com.microservice.skeleton.user.domain.BusinessException;
import com.microservice.skeleton.user.domain.Request.ReservationRequest;
import com.microservice.skeleton.user.domain.Response.ReservationResponse;
import com.microservice.skeleton.user.domain.Response.RoomReservationStatusResponse;
import com.microservice.skeleton.user.domain.dto.TimeRangeDto;
import com.microservice.skeleton.user.domain.entity.Reservation;
import com.microservice.skeleton.user.domain.entity.Room;
import com.microservice.skeleton.user.domain.entity.TimePoint;
import com.microservice.skeleton.user.mapper.ReservationMapper;
import com.microservice.skeleton.user.service.ReservationService;
import com.microservice.skeleton.user.service.RoomService;
import com.microservice.skeleton.user.service.TimePointService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
//
//@Service
//public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation>
//        implements ReservationService {
//    @Autowired
//    private TimePointService timePointService;
//
//    @Autowired
//    private RoomService roomService;
//
//    // 使用 ConcurrentHashMap + 日期+教室ID 作为key
//    private final ConcurrentHashMap<String, TreeRangeSet<LocalTime>> roomReservations = new ConcurrentHashMap<>();
//    private final ReadWriteLock lock = new ReentrantReadWriteLock();
//
//    @Override
//    @Transactional
//    public ReservationResponse createReservation(ReservationRequest request) {
//        // 1. 获取时间点数据
//        List<TimePoint> timePoints = timePointService.listByIds(request.getTimePointIds());
//        if (timePoints.size() != 2) {
//            throw new BusinessException("时间点数据异常");
//        }
//
//        // 2. 自动排序时间点（确保 startTime < endTime）
//        timePoints.sort(Comparator.comparing(TimePoint::getPoint));
//        TimePoint startPoint = timePoints.get(0);
//        TimePoint endPoint = timePoints.get(1);
//
//        if (startPoint.getPoint().equals(endPoint.getPoint())) {
//            throw new BusinessException("开始时间和结束时间不能相同");
//        }
//
//        // 3. 检查时间冲突（使用写锁保证原子性）
//        lock.writeLock().lock();
//        try {
//            if (isTimeSlotConflict(request.getRoomId(), request.getReservationDate(),
//                    startPoint.getPoint(), endPoint.getPoint())) {
//                throw new BusinessException("该时间段已被预约");
//            }
//
//            // 4. 保存预约记录
//            Reservation reservation = new Reservation();
//            BeanUtils.copyProperties(request, reservation);
//            reservation.setStartTimeId(startPoint.getId());
//            reservation.setEndTimeId(endPoint.getId());
//            reservation.setStatus(0); // 待审核
//
//            // 设置其他字段
//            reservation.setActivityName(request.getActivityName());
//            reservation.setDepartment(request.getDepartment());
//            reservation.setUserName(request.getUserName());
//            reservation.setCollege(request.getCollege());
//            reservation.setMajor(request.getMajor());
//            reservation.setContact(request.getContact());
//            reservation.setNeedProjection(Boolean.TRUE.equals(request.getNeedProjection()));
//            reservation.setTeacherName(request.getTeacherName());
//            reservation.setTeacherContact(request.getTeacherContact());
//            reservation.setOtherRequirements(request.getOtherRequirements());
//            reservation.setUserCn(request.getUserCn());
//
//            // 生成预约编号
//            String timeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//            int randomNum = new Random().nextInt(900) + 100;
//            reservation.setReservationNo("R" + timeStr + randomNum);
//
//            save(reservation);
//
//            // 更新内存缓存
//            addReservationToMemory(request.getRoomId(), request.getReservationDate(),
//                    startPoint.getPoint(), endPoint.getPoint());
//
//            // 返回响应
//            ReservationResponse response = new ReservationResponse();
//            BeanUtils.copyProperties(reservation, response);
//            return response;
//        } finally {
//            lock.writeLock().unlock();
//        }
//    }
//
//    @Override
//    public RoomReservationStatusResponse getRoomReservationStatus(Integer roomId, LocalDate date) {
//        // 1. 获取教室基本信息
//        Room room = roomService.getById(roomId);
//        if (room == null) {
//            throw new BusinessException("教室不存在");
//        }
//
//        // 2. 构建响应对象
//        RoomReservationStatusResponse response = new RoomReservationStatusResponse();
//        response.setRoomId(roomId);
//        response.setRoomName(room.getName());
//        response.setDate(date);
//
//        // 3. 把 RangeSet<LocalTime> 转成 List<TimeRangeDto>
//        String key = roomId + "-" + date;
//        lock.readLock().lock();
//        try {
//            TreeRangeSet<LocalTime> rangeSet = roomReservations.get(key);
//            if (rangeSet != null) {
//                List<TimeRangeDto> list = rangeSet.asRanges().stream()
//                        .map(r -> {
//                            TimeRangeDto dto = new TimeRangeDto();
//                            dto.setStart(r.lowerEndpoint().toString());
//                            dto.setEnd(r.upperEndpoint().toString());
//                            return dto;
//                        })
//                        .collect(Collectors.toList());
//                response.setReservedTimeRanges(list);
//            }
//        } finally {
//            lock.readLock().unlock();
//        }
//        return response;
//    }
//
//    // 时间段冲突检查方法
//    private boolean isTimeSlotConflict(Integer roomId, LocalDate date, LocalTime start, LocalTime end) {
//        String key = roomId + "-" + date;
//        TreeRangeSet<LocalTime> rangeSet = roomReservations.get(key);
//        if (rangeSet != null) {
//            Range<LocalTime> newRange = Range.closedOpen(start, end);
//            return rangeSet.intersects(newRange);
//        }
//        return false;
//    }
//
//    private void addReservationToMemory(Integer roomId, LocalDate date, LocalTime start, LocalTime end) {
//        String key = roomId + "-" + date;
//        TreeRangeSet<LocalTime> rangeSet = roomReservations.computeIfAbsent(key, k -> TreeRangeSet.create());
//        rangeSet.add(Range.closedOpen(start, end));
//    }
//
//    // 可选：初始化时从数据库加载已有预约数据
//    @PostConstruct
//    public void initReservationCache() {
//        List<Reservation> reservations = list();
//        for (Reservation reservation : reservations) {
//            TimePoint startPoint = timePointService.getById(reservation.getStartTimeId());
//            TimePoint endPoint = timePointService.getById(reservation.getEndTimeId());
//            if (startPoint != null && endPoint != null) {
//                addReservationToMemory(reservation.getRoomId(),
//                        reservation.getReservationDate(),
//                        startPoint.getPoint(),
//                        endPoint.getPoint());
//            }
//        }
//    }
//}


@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation>
        implements ReservationService {
    @Autowired
    private TimePointService timePointService;

    @Autowired
    private RoomService roomService;

    @Override
    @Transactional
    public ReservationResponse createReservation(ReservationRequest request) {
        // 1. 获取时间点数据
        List<TimePoint> timePoints = timePointService.listByIds(request.getTimePointIds());
        if (timePoints.size() != 2) {
            throw new BusinessException("时间点数据异常");
        }

        // 2. 自动排序时间点（确保 startTime < endTime）
        timePoints.sort(Comparator.comparing(TimePoint::getPoint));
        TimePoint startPoint = timePoints.get(0);
        TimePoint endPoint = timePoints.get(1);

        if (startPoint.getPoint().equals(endPoint.getPoint())) {
            throw new BusinessException("开始时间和结束时间不能相同");
        }

        // 3. 检查时间冲突（直接查询数据库）
        if (hasTimeConflict(request.getRoomId(), request.getReservationDate(),
                startPoint.getPoint(), endPoint.getPoint())) {
            throw new BusinessException("该时间段已被预约");
        }

        // 4. 保存预约记录
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(request, reservation);
        reservation.setStartTimeId(startPoint.getId());
        reservation.setEndTimeId(endPoint.getId());
        reservation.setStatus(0); // 待审核

        // 设置其他字段
        reservation.setActivityName(request.getActivityName());
        reservation.setDepartment(request.getDepartment());
        reservation.setUserName(request.getUserName());
        reservation.setCollege(request.getCollege());
        reservation.setMajor(request.getMajor());
        reservation.setContact(request.getContact());
        reservation.setNeedProjection(Boolean.TRUE.equals(request.getNeedProjection()));
        reservation.setTeacherName(request.getTeacherName());
        reservation.setTeacherContact(request.getTeacherContact());
        reservation.setOtherRequirements(request.getOtherRequirements());
        reservation.setUserCn(request.getUserCn());

        // 生成预约编号
        String timeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        int randomNum = new Random().nextInt(900) + 100;
        reservation.setReservationNo("R" + timeStr + randomNum);

        save(reservation);

        // 返回响应
        ReservationResponse response = new ReservationResponse();
        BeanUtils.copyProperties(reservation, response);
        return response;
    }

    @Override
    public RoomReservationStatusResponse getRoomReservationStatus(Integer roomId, LocalDate date) {
        // 1. 获取教室基本信息
        Room room = roomService.getById(roomId);
        if (room == null) {
            throw new BusinessException("教室不存在");
        }

        // 2. 查询该教室当天的所有预约记录
        List<Reservation> reservations = lambdaQuery()
                .eq(Reservation::getRoomId, roomId)
                .eq(Reservation::getReservationDate, date)
                .list();

        // 3. 获取所有时间点并构建已预约时间段
        List<TimeRangeDto> reservedRanges = reservations.stream()
                .map(res -> {
                    TimePoint start = timePointService.getById(res.getStartTimeId());
                    TimePoint end = timePointService.getById(res.getEndTimeId());
                    if (start != null && end != null) {
                        TimeRangeDto dto = new TimeRangeDto();
                        dto.setStart(start.getPoint().toString());
                        dto.setEnd(end.getPoint().toString());
                        return dto;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 4. 构建响应对象
        RoomReservationStatusResponse response = new RoomReservationStatusResponse();
        response.setRoomId(roomId);
        response.setRoomName(room.getName());
        response.setDate(date);
        response.setReservedTimeRanges(reservedRanges);
        return response;
    }

    /**
     * 检查时间段是否冲突（直接查询数据库）
     */
    private boolean hasTimeConflict(Integer roomId, LocalDate date, LocalTime start, LocalTime end) {
        // 查询该教室当天的所有预约记录
        List<Reservation> reservations = lambdaQuery()
                .eq(Reservation::getRoomId, roomId)
                .eq(Reservation::getReservationDate, date)
                .list();

        // 检查是否有时间冲突
        for (Reservation res : reservations) {
            TimePoint resStart = timePointService.getById(res.getStartTimeId());
            TimePoint resEnd = timePointService.getById(res.getEndTimeId());

            if (resStart != null && resEnd != null) {
                // 检查时间段是否重叠
                if (!(end.compareTo(resStart.getPoint()) <= 0 || start.compareTo(resEnd.getPoint()) >= 0)) {
                    return true; // 有冲突
                }
            }
        }
        return false; // 无冲突
    }
}
