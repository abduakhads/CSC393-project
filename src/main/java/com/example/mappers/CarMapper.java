package com.example.mappers;

import com.example.dto.CarDTO;
import com.example.models.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {


    @Mapping(source = "location.code", target = "locationCode") // location.code is automatically mapped to target locationCode
    CarDTO toDTO(Car car);

    List<CarDTO> toDTOList(List<Car> cars);
}