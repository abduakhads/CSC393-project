package com.example.mappers;

import com.example.dto.RentedCarDTO;
import com.example.dto.ReservationResponseDTO;
import com.example.models.Extra;
import com.example.models.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    // Response DTO
    @Mapping(source = "pickupLocation.code", target = "pickupLocationCode")
    @Mapping(source = "pickupLocation.name", target = "pickupLocationName")
    @Mapping(source = "dropoffLocation.code", target = "dropoffLocationCode")
    @Mapping(source = "dropoffLocation.name", target = "dropoffLocationName")
    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "member.name", target = "memberName")
    @Mapping(target = "totalAmount", source = ".", qualifiedByName = "calculateTotalAmount")
    ReservationResponseDTO toResponseDTO(Reservation reservation);

    // Map to Rented Car DTO
    @Mapping(source = "car.brand", target = "brand")
    @Mapping(source = "car.model", target = "model")
    @Mapping(source = "car.category", target = "category")
    @Mapping(source = "car.transmissionType", target = "transmissionType")
    @Mapping(source = "car.barcode", target = "barcode")
    @Mapping(source = "member.name", target = "memberName")
    @Mapping(source = "dropoffLocation.code", target = "dropoffLocationCode")
    @Mapping(target = "reservationDayCount", source = ".", qualifiedByName = "calculateDayCount")
    RentedCarDTO toRentedCarDTO(Reservation reservation);

    List<RentedCarDTO> toRentedCarDTOList(List<Reservation> reservations);


    // custom calc logic
    @Named("calculateTotalAmount")
    default BigDecimal calculateTotalAmount(Reservation res) {
        if (res.getPickupDateTime() == null || res.getDropoffDateTime() == null || res.getCar() == null) {
            return BigDecimal.ZERO;
        }

        // days
        long days = ChronoUnit.DAYS.between(res.getPickupDateTime(), res.getDropoffDateTime());
        if (days == 0) days = 1; // 1 day at least

        // car price
        BigDecimal dailyPrice = BigDecimal.valueOf(res.getCar().getDailyPrice());
        BigDecimal carTotal = dailyPrice.multiply(BigDecimal.valueOf(days));

        // extra price
        BigDecimal extrasTotal = BigDecimal.ZERO;
        if (res.getExtras() != null) {
            for (Extra extra : res.getExtras()) {
                if (extra.getPrice() != null) {
                    extrasTotal = extrasTotal.add(extra.getPrice());
                }
            }
        }

        return carTotal.add(extrasTotal);
    }

    @Named("calculateDayCount")
    default Long calculateDayCount(Reservation res) {
        if (res.getPickupDateTime() == null || res.getDropoffDateTime() == null) {
            return 0L;
        }
        long days = ChronoUnit.DAYS.between(res.getPickupDateTime(), res.getDropoffDateTime());
        return days == 0 ? 1L : days;
    }
}