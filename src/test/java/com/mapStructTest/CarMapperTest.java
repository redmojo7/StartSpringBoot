package com.mapStructTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.strat.springboot.controller.mapStructs.Car;
import com.strat.springboot.controller.mapStructs.CarDto;
import com.strat.springboot.controller.mapStructs.CarType;
import com.strat.springboot.controller.mapStructs.CarMapper;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/8.
 */
public class CarMapperTest {
  
  
  // http://www.tianshouzhi.com/api/tutorials/mapstruct
  
  @Test
  public void shouldMapCarToDto() {
    //given
    Car car = new Car( "Morris", 5, CarType.SEDAN );
    
    //when
    CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );
    System.out.println(" carDto : " + carDto);
    
    //then
    assertThat( carDto ).isNotNull();
    assertThat( carDto.getMake() ).isEqualTo( "Morris" );
    assertThat( carDto.getSeatCount() ).isEqualTo( 5 );
    assertThat( carDto.getType() ).isEqualTo( "SEDAN" );
    

  }
}
