package com.example.AEPB;

import com.example.AEPB.exception.CarNotExistException;
import com.example.AEPB.exception.CarParkedException;
import com.example.AEPB.exception.Codes;
import com.example.AEPB.exception.NoCarException;
import com.example.AEPB.exception.NoSpaceException;

import java.util.List;

public class ParkingManager extends ParkingBoy{

    public ParkingManager(List<ParkingLot> parkingLots){
        super(parkingLots);
    }
    @Override
    public Ticket park(Car car) {
        if (used < total) {
            ParkingLot targetParkingLot = this.parkingLots.stream().filter(parkingLot -> parkingLot.getSpace() > 0).findFirst().get();
            return getTicket(car, targetParkingLot);
        } else {
            throw new NoSpaceException(Codes.CODE_NO_SPACE);
        }
    }
}
