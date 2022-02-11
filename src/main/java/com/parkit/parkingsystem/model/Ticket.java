package com.parkit.parkingsystem.model;

import java.util.Date;

/**
 * The type Ticket.
 */
public class Ticket {
    /**
     * The id.
     */
    private int id;
    /**
     * The parkingSpot.
     */
    private ParkingSpot parkingSpot;
    /**
     * The vehicleRegNumber.
     */
    private String vehicleRegNumber;
    /**
     * The price.
     */
    private double price;
    /**
     * The Date inTime.
     */
    private Date inTime;
    /**
     * The Date outTime.
     */
    private Date outTime;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param idParam the id
     */
    public void setId(final int idParam) {
        this.id = idParam;
    }

    /**
     * Gets parking spot.
     *
     * @return the parking spot
     */
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    /**
     * Sets parking spot.
     *
     * @param pkSpot the parking spot
     */
    public void setParkingSpot(final ParkingSpot pkSpot) {
        this.parkingSpot = pkSpot;
    }

    /**
     * Gets vehicle reg number.
     *
     * @return the vehicle reg number
     */
    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    /**
     * Sets vehicle reg number.
     *
     * @param vRegNbr the vehicle reg number
     */
    public void setVehicleRegNumber(final String vRegNbr) {
        this.vehicleRegNumber = vRegNbr;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param priceParam the price
     */
    public void setPrice(final double priceParam) {
        this.price = priceParam;
    }

    /**
     * Gets in time.
     *
     * @return the in time
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * Sets in time.
     *
     * @param inTimeParam the in time
     */
    public void setInTime(final Date inTimeParam) {
        this.inTime = inTimeParam;
    }

    /**
     * Gets out time.
     *
     * @return the out time
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * Sets out time.
     *
     * @param outTimeParam the out time
     */
    public void setOutTime(final Date outTimeParam) {
        this.outTime = outTimeParam;
    }
}
