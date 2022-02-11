package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;


/**
 * The type Ticket dao.
 */
public class TicketDAO {

    /**
     * The Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger("TicketDAO");

    /**
     * Gets data base config.
     *
     * @return the data base config
     */
    public DataBaseConfig getDataBaseConfig() {
        return dataBaseConfig;
    }

    /**
     * Sets data base config.
     *
     * @param dtbConf the data base config
     */
    public void setDataBaseConfig(final DataBaseConfig dtbConf) {
        this.dataBaseConfig = dtbConf;
    }

    /**
     * The Data base config.
     */
    private DataBaseConfig dataBaseConfig = new DataBaseConfig();

    /**
     * Save ticket boolean.
     *
     * @param ticket the ticket
     * @return the boolean
     */
    public boolean saveTicket(final Ticket ticket) {
        Connection con = null;
        final int collumn3 = 3;
        final int collumn4 = 4;
        final int collumn5 = 5;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(DBConstants.SAVE_TICKET);
            //ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
            //ps.setInt(1,ticket.getId());
            ps.setInt(1, ticket.getParkingSpot().getId());
            ps.setString(2, ticket.getVehicleRegNumber());
            ps.setDouble(collumn3, ticket.getPrice());
            ps.setTimestamp(collumn4,
                    new Timestamp(ticket.getInTime().getTime()));
            ps.setTimestamp(collumn5, (ticket.getOutTime() == null)
                    ? null : (new Timestamp(ticket.getOutTime().getTime())));
            return ps.execute();
        } catch (Exception ex) {
            LOGGER.error("Error fetching next available slot", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            return false;
        }
    }

    /**
     * Gets ticket.
     *
     * @param vehicleRegNumber the vehicle reg number
     * @return the ticket
     */
    public Ticket getTicket(final String vehicleRegNumber) {
        Connection con = null;
        Ticket ticket = null;
        final int column6 = 6;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(DBConstants.GET_TICKET);
            //ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
            ps.setString(1, vehicleRegNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ticket = new Ticket();
                ParkingSpot parkingSpot =
                        new ParkingSpot(rs.getInt(1),
                                ParkingType.valueOf(rs.getString(column6)),
                                false);
                ticket.setParkingSpot(parkingSpot);
                ticket.setId(rs.getInt(2));
                ticket.setVehicleRegNumber(vehicleRegNumber);
                ticket.setPrice(rs.getDouble("PRICE"));
                ticket.setInTime(rs.getTimestamp("IN_TIME"));
                ticket.setOutTime(rs.getTimestamp("OUT_TIME"));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        } catch (Exception ex) {
            LOGGER.error("Error fetching next available slot", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
            return ticket;
        }
    }

    /**
     * Update ticket boolean.
     *
     * @param ticket the ticket
     * @return the boolean
     */
    public boolean updateTicket(final Ticket ticket) {
        Connection con = null;
        final int column3 = 3;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(DBConstants.UPDATE_TICKET);
            ps.setDouble(1, ticket.getPrice());
            ps.setTimestamp(2,
                    new Timestamp(ticket.getOutTime().getTime()));
            ps.setInt(column3, ticket.getId());
            ps.execute();
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error saving ticket info", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
        }
        return false;
    }

    /**
     * Test ticket boolean.
     *
     * @param ticket the ticket
     * @return the boolean
     */
    public boolean testTicket(final Ticket ticket) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(DBConstants.TEST_TICKET);
            ps.setString(1, ticket.getVehicleRegNumber());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                return true;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Does ticket exist boolean.
     *
     * @param ticket the ticket
     * @return the boolean
     */
    public boolean doesTicketExist(final Ticket ticket) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(DBConstants.EXIST_TICKET);
            ps.setString(1, ticket.getVehicleRegNumber());
            ps.setInt(2, ticket.getId());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
