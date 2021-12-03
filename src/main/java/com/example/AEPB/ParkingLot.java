package com.example.AEPB;

import com.example.AEPB.exception.CarNotExistException;
import com.example.AEPB.exception.Codes;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {

    private int total;
    private int used;
    private Set<String> carSet;

    public ParkingLot() {
        this.total = 100;
        this.used = 0;
        this.carSet = new HashSet<>();
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getTotal() {
        return total;
    }

    public String park(Car car) {
        if (used < total) {
            used++;
            carSet.add(car.getId());
            return "park success";
        }
        return "park fail";
    }

    public String leave(String carId) {
        if (carSet.contains(carId)) {
            carSet.remove(carId);
            used--;
            return "byebye";
        }
        throw new CarNotExistException(Codes.CODE_CAR_NOT_EXIST);
    }
}