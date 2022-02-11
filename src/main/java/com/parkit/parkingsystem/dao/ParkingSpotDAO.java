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

/**
 * The type Parking spot dao.
 */
public class ParkingSpotDAO {

    /**
     * The Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger("ParkingSpotDAO");

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
     * Get next available slot int.
     *
     * @param pkType the parking type
     * @return the int
     */
    public int getNextAvailableSlot(final ParkingType pkType) {
        Connection con = null;
        int result = -1;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(DBConstants.GET_NEXT_PARKING_SPOT);
            ps.setString(1, pkType.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
        } catch (Exception ex) {
            LOGGER.error("Error fetching next available slot", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
        }
        return result;
    }

    /**
     * Update parking boolean.
     *
     * @param pkSpot the parking spot
     * @return the boolean
     */
    public boolean updateParking(final ParkingSpot pkSpot) {
        //update the availability fo that parking slot
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(DBConstants.UPDATE_PARKING_SPOT);
            ps.setBoolean(1, pkSpot.isAvailable());
            ps.setInt(2, pkSpot.getId());
            int updateRowCount = ps.executeUpdate();
            dataBaseConfig.closePreparedStatement(ps);
            return (updateRowCount == 1);
        } catch (Exception ex) {
            LOGGER.error("Error updating parking info", ex);
            return false;
        } finally {
            dataBaseConfig.closeConnection(con);
        }
    }

    /**
     * Verify parking spot boolean.
     *
     * @param ticket the ticket
     * @return the boolean
     */
//verify slot
    public boolean verifyParkingSpot(final Ticket ticket) {
        Connection con = null;
        boolean result = true;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            con = dataBaseConfig.getConnection();
            ps = con.prepareStatement(DBConstants.VERIFY_PARKING_SPOT);
            ps.setInt(1, ticket.getParkingSpot().getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                result =  rs.getBoolean("AVAILABLE");
            }

        } catch (Exception ex) {
            LOGGER.error("Error updating parking info", ex);
            return false;
        } finally {
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
            dataBaseConfig.closeConnection(con);
        }
        return result;
    }

}
