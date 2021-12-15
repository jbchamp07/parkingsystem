package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        int inHour = ticket.getInTime().getHours();
        int outHour = ticket.getOutTime().getHours();

        //TODO: Some tests are failing here. Need to check if this logic is correct
        LocalDateTime ldtIn = LocalDateTime.ofInstant(ticket.getInTime().toInstant(),
                ZoneId.systemDefault());
        LocalDateTime ldtOut = LocalDateTime.ofInstant(ticket.getOutTime().toInstant(),
                ZoneId.systemDefault());
        int differenceInMinutes = (int) ChronoUnit.MINUTES.between(ldtIn, ldtOut);
        int differenceInDays = (int) ChronoUnit.DAYS.between(ldtIn, ldtOut);
        int differenceInHeures = 0;
        if(differenceInMinutes / 60 > 0){
            differenceInHeures = differenceInMinutes / 60;
        }
        if(differenceInMinutes == 60){
            differenceInMinutes = 0;
        }
        double duration = differenceInHeures + ((double)differenceInMinutes/60) - (differenceInHeures * differenceInDays);

        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
                ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }
}