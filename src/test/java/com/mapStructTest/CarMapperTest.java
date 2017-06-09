package com.mapStructTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.strat.springboot.Controller.mapStructs.Car;
import com.strat.springboot.Controller.mapStructs.CarDto;
import com.strat.springboot.Controller.mapStructs.CarType;
import com.strat.springboot.Controller.mapStructs.CarMapper;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/8.
 */
public class CarMapperTest {
  
  @Test
  public void shouldMapCarToDto() {
    //given
    Car car = new Car( "Morris", 5, CarType.SEDAN );
    
    //when
    CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );
    //
    //then
    assertThat( carDto ).isNotNull();
    assertThat( carDto.getMake() ).isEqualTo( "Morris" );
    assertThat( carDto.getSeatCount() ).isEqualTo( 5 );
    assertThat( carDto.getType() ).isEqualTo( "SEDAN" );
  }
}
