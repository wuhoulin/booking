package com.microservice.skeleton.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.microservice.skeleton.user.domain.Request.ReservationApprovalRequest;
import com.microservice.skeleton.user.domain.Request.ReservationRequest;
import com.microservice.skeleton.user.domain.Response.ReservationResponse;
import com.microservice.skeleton.user.domain.Response.RoomReservationStatusResponse;
import com.microservice.skeleton.user.domain.entity.Reservation;


import java.time.LocalDate;
import java.util.List;

public interface ReservationService extends IService<Reservation> {

    ReservationResponse createReservation(ReservationRequest request);

    RoomReservationStatusResponse getRoomReservationStatus(Integer roomId, LocalDate date);
//
//    ReservationResponse updateReservation(Integer id, ReservationRequest request);
//
//    void deleteReservation(Integer id);
//
//    ReservationResponse getReservationById(Integer id);
//
//    ReservationResponse getReservationByNo(String reservationNo);
//
//    List<ReservationResponse> getAllReservations();
//
//    Page<ReservationResponse> getAllReservations(Page<Reservation> page);
//
//    List<ReservationResponse> getReservationsByUser(Integer userId);
//
//    Page<ReservationResponse> getReservationsByUser(Integer userId, Page<Reservation> page);
//
//    List<ReservationResponse> getReservationsByRoom(Integer roomId);
//
//    List<ReservationResponse> getReservationsByRoomAndDate(Integer roomId, LocalDate date);
//
//    List<ReservationResponse> getReservationsByStatus(Integer status);
//
//    Page<ReservationResponse> getReservationsByStatus(Integer status, Page<Reservation> page);
//
//    ReservationResponse cancelReservation(Integer id);
//
//    ReservationResponse completeReservation(Integer id);
//
//    ReservationResponse approveReservation(ReservationApprovalRequest request);
//
//    ReservationVO getReservationDetail(Integer id);
}
