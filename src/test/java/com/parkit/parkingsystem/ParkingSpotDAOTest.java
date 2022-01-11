package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ParkingSpotDAOTest {

    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static DataBasePrepareService dataBasePrepareService = new DataBasePrepareService();
    private static ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
    private static ParkingSpot parkingSpot;

    @BeforeEach
    private void setUpPerTest() {;
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService.clearDataBaseEntries();
    }

    @Test
    public void getNextAvailableSlotTestCar(){
        assertEquals(1,parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR));
    }

    @Test
    public void getNextAvailableSlotTestBike(){
        assertEquals(4,parkingSpotDAO.getNextAvailableSlot(ParkingType.BIKE));
    }

    @Test
    public void updateParkingTestCar(){
        parkingSpot = new ParkingSpot(1,ParkingType.CAR,false);
        parkingSpotDAO.updateParking(parkingSpot);
        assertEquals(2,parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR));
    }

    @Test
    public void updateParkingTestBike(){
        parkingSpot = new ParkingSpot(4,ParkingType.BIKE,false);
        parkingSpotDAO.updateParking(parkingSpot);
        assertEquals(5,parkingSpotDAO.getNextAvailableSlot(ParkingType.BIKE));
    }

    @Test
    public void verifyParkingSpotTestCarOk(){
        Ticket ticket = new Ticket();
        parkingSpot = new ParkingSpot(1,ParkingType.CAR,true);
        parkingSpot.setId(1);
        ticket.setParkingSpot(parkingSpot);
        assertEquals(true,parkingSpotDAO.verifyParkingSpot(ticket));
    }


    @Test
    public void verifyParkingSpotTestBikeOk(){
        Ticket ticket = new Ticket();
        parkingSpot = new ParkingSpot(4,ParkingType.BIKE,true);
        parkingSpot.setId(4);
        ticket.setParkingSpot(parkingSpot);
        assertEquals(true,parkingSpotDAO.verifyParkingSpot(ticket));
    }


}
