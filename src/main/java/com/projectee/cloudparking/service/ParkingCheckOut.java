package com.projectee.cloudparking.service;

import com.projectee.cloudparking.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckOut {

    public static final int ONE_HOUR = 60;
    public static final int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;
    public static final double ONE_HOUR_VALUE = 5.00;
    public static final double ADDITIONAL_PER_HOUR_VALUE = 2.00;
    public static final double DAY_VALUE = 20.00;

    public static Double getBill(Parking parking){
        return getBill(parking.getEntryDate(),parking.getExitDate());
    }

    private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        long minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);

        if(minutes <= ONE_HOUR){
            return ONE_HOUR_VALUE;
        }
        if(minutes < TWENTY_FOUR_HOUR){
            int hours = (int) (minutes/ ONE_HOUR);
            return ONE_HOUR_VALUE + ADDITIONAL_PER_HOUR_VALUE*hours;
        }
        int days = (int) (minutes/TWENTY_FOUR_HOUR);
        return DAY_VALUE*days;

    }
}
