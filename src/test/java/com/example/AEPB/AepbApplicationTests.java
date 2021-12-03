package com.example.AEPB;

import com.example.AEPB.exception.CarNotExistException;
import com.example.AEPB.exception.Codes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {

	@Test
	void should_parking_successfully_when_invoking_park_given_parking_space_available() {
		ParkingLot parkingLot = new ParkingLot();
		Car car = new Car("陕A00001");
		String parkResult = parkingLot.park(car);
		assertEquals("park success", parkResult);
	}

	@Test
	void should_leave_successfully_when_invoking_leave_given_car_exist() {
		ParkingLot parkingLot = new ParkingLot();
		Car car = new Car("陕A00001");
		parkingLot.park(car);
		String parkResult = parkingLot.leave("陕A00001");
		assertEquals("byebye", parkResult);
	}

	@Test
	void should_parking_filed_when_invoking_park_given_parking_space_unavailable() {
		ParkingLot parkingLot = new ParkingLot();
		parkingLot.setUsed(parkingLot.getTotal());
		Car car = new Car("陕A00002");
		String parkResult = parkingLot.park(car);
		assertEquals("park fail", parkResult);
	}

	@Test
	void should_throw_car_not_found_when_invoking_leave_given_car_not_exist() {
		ParkingLot parkingLot = new ParkingLot();
		Car car = new Car("陕A00001");
		parkingLot.park(car);
		CarNotExistException exception = assertThrows(CarNotExistException.class, () -> parkingLot.leave("陕A00002"));
		assertEquals(Codes.CODE_CAR_NOT_EXIST, exception.getMessage());
	}


}
