package com.example.AEPB;

import com.example.AEPB.exception.CarNotExistException;
import com.example.AEPB.exception.Codes;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotTest {

	//HappyPath
	@Test
	void should_parking_successfully_at_n_plus_1_when_all_parked_from_1_to_n() {
		ParkingLot parkingLot = new ParkingLot();
		parkingLot.setParkedFrom1ToN(10);
		Car car = new Car("陕A00001");
		String parkResult = parkingLot.park(car);
		assertEquals("park success at A11", parkResult);
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
	void should_park_at_min_space_number_when_available() {
		ParkingLot parkingLot = new ParkingLot();
		parkingLot.setParkedFrom1ToN(10);
		Car car = new Car("陕A00001");
		parkingLot.park(car);
		Car anotherCar = new Car("陕A00002");
		parkingLot.park(anotherCar);
		parkingLot.leave("陕A00001");
		Car thirdCar = new Car("陕A00003");
		String parkResult = parkingLot.park(thirdCar);
		assertEquals("park success at A11", parkResult);
	}

	//SadPath
	@Test
	void should_parking_filed_when_invoking_park_given_parking_space_unavailable() {
		ParkingLot parkingLot = new ParkingLot();
		parkingLot.setUsed(parkingLot.getTotal());
		parkingLot.setParkedFrom1ToN(parkingLot.getTotal());
		Car car = new Car("陕A00002");
		String parkResult = parkingLot.park(car);
		assertEquals("no space available, park fail", parkResult);
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
