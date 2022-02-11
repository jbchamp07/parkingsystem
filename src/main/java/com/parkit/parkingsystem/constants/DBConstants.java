package com.parkit.parkingsystem.constants;

/**
 * The type Db constants.
 */
public abstract class DBConstants {
    /**
     * The constant GET_NEXT_PARKING_SPOT.
     */
    public static final String GET_NEXT_PARKING_SPOT = "select min(PARKING_NUM"
            + "BER) from parking where AVAILABLE = true and TYPE = ?";
    /**
     * The constant UPDATE_PARKING_SPOT.
     */
    public static final String UPDATE_PARKING_SPOT = "update parking "
            + "set available = ? where PARKING_NUMBER = ?";
    /**
     * The constant VERIFY_PARKING_SPOT.
     */
    public static final String VERIFY_PARKING_SPOT = "SELECT AVAILABLE from "
            + "parking p, ticket t where p.PARKING_NUMBER = ?";

    /**
     * The constant SAVE_TICKET.
     */
    public static final String SAVE_TICKET = "insert into ticket(PARKING_NUMBE"
            + "R, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)"
            + " values(?,?,?,?,?)";
    /**
     * The constant UPDATE_TICKET.
     */
    public static final String UPDATE_TICKET = "update ticket set PRICE=?,"
            + " OUT_TIME=? where ID=?";
    /**
     * The constant GET_TICKET.
     */
    public static final String GET_TICKET = "select t.PARKING_NUMBER, "
            + "t.ID, t.PRICE, t.IN_TIME, t.OUT_TIME, p.TYPE from ticket t"
            + ",parking p where p.parking_number = t.parking_number"
            + " and t.VEHICLE_REG_NUMBER=? order by t.IN_TIME DESC limit 1";
    /**
     * The constant TEST_TICKET.
     */
    public static final String TEST_TICKET = "SELECT VEHICLE_REG_NUMBER FROM "
            + "`ticket` WHERE ticket.VEHICLE_REG_NUMBER = ? "
            + "and ticket.OUT_TIME is not null";
    /**
     * The constant EXIST_TICKET.
     */
    public static final String EXIST_TICKET = "SELECT VEHICLE_REG_NUMBER"
            + " FROM `ticket` WHERE ticket.VEHICLE_REG_NUMBER = ? "
            + "and ticket.ID = ?;";
}
