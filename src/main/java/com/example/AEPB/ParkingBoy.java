package com.example.AEPB;


import com.example.AEPB.exception.CarNotExistException;
import com.example.AEPB.exception.CarParkedException;
import com.example.AEPB.exception.Codes;
import com.example.AEPB.exception.NoCarException;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingBoy {
      protected List<ParkingLot> parkingLots;
      protected int total;
      protected int used;

      public ParkingBoy(List<ParkingLot> parkingLots) {
            this.parkingLots = new ArrayList<>();
            this.total = 0;
            this.used = 0;
            for (ParkingLot parkingLot : parkingLots) {
                  this.parkingLots.add(parkingLot);
                  this.total += parkingLot.getTotal();
            }
      }

      public abstract Ticket park(Car car);

      protected String leave(Ticket ticket){
            if(used<1){
                  throw new NoCarException(Codes.CODE_NO_CAR);
            }
            ParkingLot targetParkingLot = this.parkingLots.get(ticket.getDistrict() - 1);
            if(targetParkingLot.getCarMap().containsKey(ticket.getCarId())){
                  targetParkingLot.getCarMap().remove(ticket.getCarId());
                  targetParkingLot.getStatus().set(ticket.getNumber()-1,Boolean.FALSE);
                  targetParkingLot.setSpace(targetParkingLot.getSpace()+1);
                  used--;
                  return "byebye";
            }
            throw new CarNotExistException(Codes.CODE_CAR_NOT_EXIST);
      }

      protected Ticket getTicket(Car car, ParkingLot targetParkingLot) {
            if (targetParkingLot.getCarMap().containsKey(car.getId())) {
                  throw new CarParkedException(Codes.CODE_CAR_PARKED);
            }
            int district = parkingLots.indexOf(targetParkingLot);
            int index = targetParkingLot.getStatus().indexOf(Boolean.FALSE);
            targetParkingLot.getStatus().set(index, Boolean.TRUE);
            targetParkingLot.getCarMap().put(car.getId(), format(district, index));
            targetParkingLot.setSpace(targetParkingLot.getSpace() - 1);
            used++;
            return Ticket.builder().carId(car.getId()).district(district+1).number(index+1).build();
      }

      protected String format(int district, int index) {
            return String.format("District %d No.%d", district, index);
      }
}
