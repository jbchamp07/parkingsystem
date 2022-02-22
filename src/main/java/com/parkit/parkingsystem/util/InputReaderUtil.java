package com.parkit.parkingsystem.util;

import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.time.Instant;
import java.util.Scanner;

/**
 * The type Input reader util.
 */
public class InputReaderUtil {

    /**
     * The Scanner.
     */
    private static Scanner scan = new Scanner(System.in);
    /**
     * The Logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger("InputReaderUtil");

    /**
     * Read selection int.
     *
     * @return the int
     */
    public int readSelection() {
        try {
            int input = Integer.parseInt(scan.nextLine());
            return input;
        } catch (Exception e) {
            LOGGER.error("Error while reading user input from Shell", e);
            System.out.println("Error reading input. "
                    + "Please enter valid number for proceeding further");
            return -1;
        }
    }

    /**
     * Read vehicle registration number string.
     *
     * @return the string
     * @throws Exception the exception
     */
    public String readVehicleRegistrationNumber() throws Exception {
        try {
            String vehicleRegNumber = scan.nextLine();
            if (vehicleRegNumber == null
                    || vehicleRegNumber.trim().length() == 0) {
                throw new IllegalArgumentException("Invalid input provided");
            }
            TicketDAO ticketDAO = new TicketDAO();
            Ticket ticket = new Ticket();
            ticket.setVehicleRegNumber(vehicleRegNumber);
            ticket.setOutTime(Date.from(Instant.now()));
            boolean ticketState = ticketDAO.testTicket(ticket);
            if(ticketState){
                System.out.println("Welcome Back");
            }
            return vehicleRegNumber;
        } catch (Exception e) {
            LOGGER.error("Error while reading user input from Shell", e);
            System.out.println("Error reading input. "
                    + "Please enter a valid string for "
                    + "vehicle registration number");
            throw e;
        }
    }


}
