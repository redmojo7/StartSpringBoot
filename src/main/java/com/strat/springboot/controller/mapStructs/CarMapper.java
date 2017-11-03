package com.strat.springboot.controller.mapStructs;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * Created by Administrator on 2017/6/8.
 */
@Mapper(componentModel = "spring")
public interface CarMapper {
  CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
  
  @Mapping(source = "numberOfSeats", target = "seatCount")
  CarDto carToCarDto(Car car);
  
  void updateCarFromDto(CarDto carDto, @MappingTarget Car car);
  
}