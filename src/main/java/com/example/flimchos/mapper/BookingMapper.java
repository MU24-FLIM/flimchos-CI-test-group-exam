package com.example.flimchos.mapper;

import com.example.flimchos.dto.BookingCreationDTO;
import com.example.flimchos.dto.BookingDTO;
import com.example.flimchos.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(source = "guest.name", target = "guestName")
    @Mapping(source = "restaurant.city", target = "restaurantCity")
    @Mapping(source = "booking.id", target = "id")
    BookingDTO bookingToBookingDTO(Booking booking);

    Booking bookingCreationDTOToBooking(BookingCreationDTO bookingCreationDTO);
}
