package com.example.AEPB;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private int total;
    private int space;
    private List<Boolean> status;
    private Map<String, String> carMap;

    public ParkingLot(int total) {
        this.total = total;
        this.space = total;
        this.status = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            status.add(false);
        }
        this.carMap = new HashMap<>();
    }

    public int getTotal() {
        return total;
    }

    public int getSpace() {
        return space;
    }

    public List<Boolean> getStatus() {
        return status;
    }

    public Map<String, String> getCarMap() {
        return carMap;
    }

    public void setSpace(int space) {
        this.space = space;
    }

}