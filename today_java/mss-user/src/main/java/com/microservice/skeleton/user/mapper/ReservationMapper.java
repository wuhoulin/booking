package com.microservice.skeleton.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.microservice.skeleton.user.domain.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {
    @Select("SELECT * FROM reservations " +
            "WHERE room_id = #{roomId} " +
            "AND reservation_date = #{date} " +
            "AND status IN (0, 1, 4)")
    List<Reservation> findByRoomIdAndDate(@Param("roomId") Integer roomId, @Param("date") LocalDate date);

//    @Select("SELECT * FROM reservations WHERE reservation_no = #{reservationNo}")
//    Reservation findByReservationNo(@Param("reservationNo") String reservationNo);
//
//    @Select("SELECT COUNT(*) FROM reservations WHERE room_id = #{roomId} AND reservation_date = #{date} AND time_slot_id = #{TimePointId} AND status IN (0, 1, 4)")
//    Integer countConflictReservation(@Param("roomId") Integer roomId, @Param("date") LocalDate date, @Param("TimePointId") Integer TimePointId);
//
//    @Select("SELECT r.*, u.username as user_name, u.real_name, rm.name as room_name, c.name as community_name, " +
//           "ts.start_time, ts.end_time FROM reservations r " +
//           "LEFT JOIN users u ON r.user_id = u.id " +
//           "LEFT JOIN rooms rm ON r.room_id = rm.id " +
//           "LEFT JOIN communities c ON rm.community_id = c.id " +
//           "LEFT JOIN time_slots ts ON r.time_slot_id = ts.id " +
//           "WHERE r.id = #{id}")
//    ReservationVO getReservationDetail(@Param("id") Integer id);
//
//    @Select("SELECT r.*, u.username as user_name, u.real_name, rm.name as room_name, c.name as community_name, " +
//           "ts.start_time, ts.end_time FROM reservations r " +
//           "LEFT JOIN users u ON r.user_id = u.id " +
//           "LEFT JOIN rooms rm ON r.room_id = rm.id " +
//           "LEFT JOIN communities c ON rm.community_id = c.id " +
//           "LEFT JOIN time_slots ts ON r.time_slot_id = ts.id " +
//           "WHERE r.user_id = #{userId}")
//    IPage<ReservationVO> getReservationsByUserPage(Page<ReservationVO> page, @Param("userId") Integer userId);
}
