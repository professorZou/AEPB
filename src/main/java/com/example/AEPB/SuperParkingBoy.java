package com.example.AEPB;

import com.example.AEPB.exception.CarNotExistException;
import com.example.AEPB.exception.CarParkedException;
import com.example.AEPB.exception.Codes;
import com.example.AEPB.exception.NoCarException;
import com.example.AEPB.exception.NoSpaceException;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        if (used < total) {
            ParkingLot targetParkingLot = this.parkingLots.stream().max(getParkingLotComparator()).get();
            return getTicket(car, targetParkingLot);
        } else {
            throw new NoSpaceException(Codes.CODE_NO_SPACE);
        }
    }

    private Comparator<ParkingLot> getParkingLotComparator() {
        return (o1, o2) -> {
            double vacancyRate2 = (double) o2.getSpace() / o2.getTotal();
            double vacancyRate1 = (double) o1.getSpace() / o1.getTotal();
            if (vacancyRate2 - vacancyRate1 > 0) return -1;
            if (vacancyRate2 - vacancyRate1 < 0) return 1;
            return 0;
        };
    }
}
