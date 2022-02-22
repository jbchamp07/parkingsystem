package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * The type Fare calculator service.
 */
public class FareCalculatorService {

    /**
     * The TicketDAO.
     */
    private final TicketDAO ticketDAO;

    /**
     * Instantiates a new Fare calculator service.
     *
     * @param tDAO the ticket dao
     */
    public FareCalculatorService(final TicketDAO tDAO) {
        this.ticketDAO = tDAO;
    }

    /**
     * Calculate fare.
     *
     * @param ticket the ticket
     */
    public void calculateFare(final Ticket ticket) {
        if ((ticket.getOutTime() == null)
               || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:"
                    + ticket.getOutTime().toString());
        }
        final int halfHourInMinutes = 30;
        final int anHourInMinutes = 60;
        final double rationReduction = 0.95;
        //int inHour = ticket.getInTime().getHours();
        //int outHour = ticket.getOutTime().getHours();

    //TOD: Some tests are failing here. Need to check if this logic is correct
        LocalDateTime ldtIn = LocalDateTime.ofInstant(ticket.getInTime()
                        .toInstant(),
                ZoneId.systemDefault());
        LocalDateTime ldtOut = LocalDateTime.ofInstant(ticket.getOutTime()
                        .toInstant(),
                ZoneId.systemDefault());
        int differenceInMinutes = (int) ChronoUnit.MINUTES.between(ldtIn,
                ldtOut);
        double duration = 0;
        if (differenceInMinutes <= halfHourInMinutes) {
            duration = 0;
        } else {
            int differenceInDays = (int) ChronoUnit.DAYS.between(ldtIn,
                    ldtOut);
            int differenceInHeures = 0;
            if (differenceInMinutes / anHourInMinutes > 0) {
                differenceInHeures = differenceInMinutes / anHourInMinutes;
            }
            if (differenceInMinutes == anHourInMinutes) {
                differenceInMinutes = 0;
            }
            duration = differenceInHeures
                    + ((double) differenceInMinutes / anHourInMinutes)
                    - (differenceInHeures * differenceInDays);

        }
        boolean ticketState = this.ticketDAO.testTicket(ticket);
        if (ticketState) {
            duration = duration * rationReduction;

        }
        switch (ticket.getParkingSpot().getParkingType()) {
            case CAR:
                ticket.setPrice(BigDecimal.valueOf(duration * Fare.CAR_RATE_PER_HOUR).setScale(2, RoundingMode.HALF_UP).doubleValue());
                //ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
                break;
            case BIKE:
                ticket.setPrice(BigDecimal.valueOf(duration * Fare.BIKE_RATE_PER_HOUR).setScale(2, RoundingMode.HALF_UP).doubleValue());
                //ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
                break;
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }
}
