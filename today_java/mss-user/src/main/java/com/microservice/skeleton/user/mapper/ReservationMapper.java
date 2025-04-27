package com.microservice.skeleton.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.microservice.skeleton.user.domain.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {
    @Select("SELECT * FROM reservations " +
            "WHERE room_id = #{roomId} " +
            "AND reservation_date = #{date} " +
            "AND status IN (0, 1, 4)")
    List<Reservation> findByRoomIdAndDate(@Param("roomId") Integer roomId, @Param("date") LocalDate date);

    @Select("SELECT * FROM reservations WHERE user_id = #{userId} " +
            "AND (#{status} IS NULL OR status = #{status}) " +
            "ORDER BY reservation_date DESC, created_at DESC")
    List<Reservation> findByUserId(@Param("userId") String userId,
                                   @Param("status") Integer status);

    // 如果使用MyBatis-Plus
    @Select("SELECT * FROM reservations WHERE reservation_no = #{reservationNo}")
    Optional<Reservation> selectByReservationNo(@Param("reservationNo") String reservationNo);


    // 统计用户 N 次取消（status=3）中，取消时间在指定时刻之后的数量
    int countByUserIdAndStatusAndCancelTimeAfter(
            @Param("userId") String userId,
            @Param("status") Integer status,
            @Param("time") LocalDateTime time
    );

}
