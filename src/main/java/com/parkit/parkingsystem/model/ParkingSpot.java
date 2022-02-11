/**
 * Info about this package doing something for package-info.java file.
 */
package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;

/**
 * The type Parking spot.
 */
public class ParkingSpot {
    /**
     * The number.
     */
    private int number;
    /**
     * The ParkingType.
     */
    private ParkingType parkingType;
    /**
     * The isAvailable.
     */
    private boolean isAvailable;

    /**
     * Instantiates a new Parking spot.
     *
     * @param nbr      the number
     * @param pkType the parking type
     * @param isAv the is available
     */
    public ParkingSpot(final int nbr, final ParkingType pkType,
                       final boolean isAv) {
        this.number = nbr;
        this.parkingType = pkType;
        this.isAvailable = isAv;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return number;
    }

    /**
     * Sets id.
     *
     * @param nbr the number
     */
    public void setId(final int nbr) {
        this.number = nbr;
    }

    /**
     * Gets parking type.
     *
     * @return the parking type
     */
    public ParkingType getParkingType() {
        return parkingType;
    }

    /**
     * Sets parking type.
     *
     * @param pkType the parking type
     */
    public void setParkingType(final ParkingType pkType) {
        this.parkingType = pkType;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
    public void setAvailable(final boolean available) {
        isAvailable = available;
    }

    /**
     * equals.
     *
     * @param o the Object,
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }
        ParkingSpot that = (ParkingSpot) o;
        return number == that.number;
    }

    /**
     * hashCode.
     *return number
     */
    @Override
    public int hashCode() {
        return number;
    }
}
