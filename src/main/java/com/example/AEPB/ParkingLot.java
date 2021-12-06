package com.example.AEPB;

import com.example.AEPB.exception.CarNotExistException;
import com.example.AEPB.exception.Codes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private int total;
    private int used;
    private List<Boolean> status;
    private Map<String, Integer> carMap;

    public ParkingLot() {
        this.total = 100;
        this.used = 0;
        this.status = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            status.add(false);
        }
        this.carMap = new HashMap<>();
    }

    public int getTotal() {
        return total;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public String park(Car car) {
        if (used < total) {
            used++;
            int target = 0;
            while (target < total) {
                if (status.get(target) == false) {
                    status.set(target,true);
                    break;
                }
                target++;
            }
            carMap.put(car.getId(), target);
            return "park success at " + getPosition(target);
        }
        return "no space available, park fail";
    }

    private String getPosition(int position) {
        String area;
        switch (position / 20) {
            case 0:
                area = "A";
                break;
            case 1:
                area = "B";
                break;
            case 2:
                area = "C";
                break;
            case 3:
                area = "D";
                break;
            case 4:
                area = "E";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return area + (position % 20 + 1);
    }

    public String leave(String carId) {
        if (carMap.containsKey(carId)) {
            status.set(carMap.get(carId),false);
            carMap.remove(carId);
            used--;
            return "byebye";
        }
        throw new CarNotExistException(Codes.CODE_CAR_NOT_EXIST);
    }

    public void setParkedFrom1ToN(int N) {
        for (int i = 0; i < N; i++) {
            status.set(i,true);
        }
    }
}