package com.parkit.parkingsystem;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TicketDAOTest {

    private static TicketDAO ticketDAO = new TicketDAO();
    private static Ticket ticket = new Ticket();
    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static DataBasePrepareService dataBasePrepareService = new DataBasePrepareService();

    @BeforeEach
    private void setUpPerTest() {;
        ticketDAO.setDataBaseConfig(dataBaseTestConfig);
        dataBasePrepareService.clearDataBaseEntries();
    }

    @Test
    public void saveTicketTest(){
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setPrice(5);
        ticket.setParkingSpot(new ParkingSpot(1,ParkingType.CAR,true));
        ticket.setInTime(new Date());
        ticket.setOutTime(new Date());
        ticketDAO.saveTicket(ticket);
        assertNotNull(ticketDAO.getTicket("ABCDEF"));
    }

    @Test
    public void getTicketTest() {
        saveTicketTest();
        ticket = ticketDAO.getTicket("ABCDEF");
        assertEquals(1,ticket.getId());

    }

    @Test
    public void updateTicketTest() {
        saveTicketTest();
        ticket.setId(1);
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setPrice(10);
        ticket.setParkingSpot(new ParkingSpot(1,ParkingType.CAR,true));
        ticket.setInTime(new Date());
        ticket.setOutTime(new Date());
        ticketDAO.updateTicket(ticket);
        assertEquals(10,ticketDAO.getTicket("ABCDEF").getPrice());
    }

    @Test
    public void testTicketTestOk() {
        saveTicketTest();
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setOutTime(new Date());
        assertEquals(true,ticketDAO.testTicket(ticket));
    }

    @Test
    public void testTicketTestNotOk() {
        ticket.setVehicleRegNumber("ABCDEF");
        assertEquals(false,ticketDAO.testTicket(ticket));
    }

    @Test
    public void doesTicketExistTestOk() {
        saveTicketTest();
        ticket.setId(1);
        ticket.setVehicleRegNumber("ABCDEF");
        assertEquals(true,ticketDAO.doesTicketExist(ticket));
    }

    @Test
    public void doesTicketExistTestNotOk() {
        ticket.setId(1);
        ticket.setVehicleRegNumber("ABCDEF");
        assertEquals(false,ticketDAO.doesTicketExist(ticket));
    }

}
