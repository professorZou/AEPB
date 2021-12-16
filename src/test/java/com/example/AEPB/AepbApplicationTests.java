package com.example.AEPB;

import com.example.AEPB.exception.CarNotExistException;
import com.example.AEPB.exception.CarParkedException;
import com.example.AEPB.exception.Codes;
import com.example.AEPB.exception.NoCarException;
import com.example.AEPB.exception.NoSpaceException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingBoyTest {

    //HappyPath
    @Test
    void should_park_and_leave_successfully_when_generalParkingBoy_park_car_given_parkSpace_available() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        Car car3 = new Car("陕A00003");
        GeneralParkingBoy generalParkingBoy = new GeneralParkingBoy(List.of(new ParkingLot(100), new ParkingLot(100)));
        generalParkingBoy.park(car1);
        generalParkingBoy.park(car2);
        Ticket ticket = generalParkingBoy.park(car3);
        String leaveResult = generalParkingBoy.leave(ticket);
        assertEquals("陕A00003", ticket.getCarId());
        assertEquals(1, ticket.getDistrict());
        assertEquals(3, ticket.getNumber());
        assertEquals("byebye", leaveResult);
    }

    @Test
    void should_park_and_leave_successfully_when_smartParkingBoy_park_car_given_parkSpace_available() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        Car car3 = new Car("陕A00003");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(10), new ParkingLot(100)));
        smartParkingBoy.park(car1);
        smartParkingBoy.park(car2);
        Ticket ticket = smartParkingBoy.park(car3);
        String leaveResult = smartParkingBoy.leave(ticket);
        assertEquals(2, ticket.getDistrict());
        assertEquals(3, ticket.getNumber());
        assertEquals("byebye", leaveResult);
    }

    @Test
    void should_park_successfully_when_superParkingBoy_park_car_given_parkSpace_available() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        Car car3 = new Car("陕A00003");
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(new ParkingLot(10), new ParkingLot(100)));
        superParkingBoy.park(car1);
        superParkingBoy.park(car2);
        Ticket ticket = superParkingBoy.park(car3);
        assertEquals(2, ticket.getDistrict());
        assertEquals(2, ticket.getNumber());
    }

    @Test
    void should_park_successfully_when_manager_park_car_given_parkSpace_available() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        Car car3 = new Car("陕A00003");
        ParkingManager parkingManager = new ParkingManager(List.of(new ParkingLot(2), new ParkingLot(100)));
        parkingManager.park(car1);
        parkingManager.park(car2);
        Ticket ticket = parkingManager.park(car3);
        String leaveResult = parkingManager.leave(ticket);
        assertEquals("陕A00003", ticket.getCarId());
        assertEquals(2, ticket.getDistrict());
        assertEquals(1, ticket.getNumber());
        assertEquals("byebye", leaveResult);
    }

    //SadPath
    @Test
    void should_park_failed_when_generalParkingBoy_park_car_given_no_parkSpace_available() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        Car car3 = new Car("陕A00003");
        GeneralParkingBoy generalParkingBoy = new GeneralParkingBoy(List.of(new ParkingLot(1), new ParkingLot(1)));
        generalParkingBoy.park(car1);
        generalParkingBoy.park(car2);
        NoSpaceException exception = assertThrows(NoSpaceException.class, () -> generalParkingBoy.park(car3));
        assertEquals(Codes.CODE_NO_SPACE, exception.getMessage());
    }

    @Test
    void should_park_failed_when_smartParkingBoy_park_car_given_no_parkSpace_available() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        Car car3 = new Car("陕A00003");
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1), new ParkingLot(1)));
        smartParkingBoy.park(car1);
        smartParkingBoy.park(car2);
        NoSpaceException exception = assertThrows(NoSpaceException.class, () -> smartParkingBoy.park(car3));
        assertEquals(Codes.CODE_NO_SPACE, exception.getMessage());
    }

    @Test
    void should_park_failed_when_superParkingBoy_park_car_given_no_parkSpace_available() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        Car car3 = new Car("陕A00003");
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(new ParkingLot(1), new ParkingLot(1)));
        superParkingBoy.park(car1);
        superParkingBoy.park(car2);
        NoSpaceException exception = assertThrows(NoSpaceException.class, () -> superParkingBoy.park(car3));
        assertEquals(Codes.CODE_NO_SPACE, exception.getMessage());
    }

    @Test
    void should_park_failed_when_parkingManager_park_car_given_no_parkSpace_available() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        Car car3 = new Car("陕A00003");
        ParkingManager parkingManager = new ParkingManager(List.of(new ParkingLot(1), new ParkingLot(1)));
        parkingManager.park(car1);
        parkingManager.park(car2);
        NoSpaceException exception = assertThrows(NoSpaceException.class, () -> parkingManager.park(car3));
        assertEquals(Codes.CODE_NO_SPACE, exception.getMessage());
    }

    //exception
    @Test
    void should_throw_exception_when_leave_given_car_not_found() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        ParkingManager parkingManager = new ParkingManager(List.of(new ParkingLot(1), new ParkingLot(1)));
        parkingManager.park(car1);
        parkingManager.park(car2);
        Ticket ticket = Ticket.builder().carId("陕A00003").district(1).build();
        assertThrows(CarNotExistException.class, () -> parkingManager.leave(ticket));
    }

    @Test
    void should_throw_exception_when_park_a_car_twice() {
        Car car1 = new Car("陕A00001");
        Car car2 = new Car("陕A00002");
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(new ParkingLot(10)));
        superParkingBoy.park(car1);
        superParkingBoy.park(car2);
        assertThrows(CarParkedException.class, () -> superParkingBoy.park(car1));
    }

    @Test
    void should_throw_exception_when_leave_a_car_given_no_car_parked() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(new ParkingLot(10)));
        Ticket ticket = Ticket.builder().carId("陕A00001").build();
        assertThrows(NoCarException.class, () -> smartParkingBoy.leave(ticket));
    }
}
