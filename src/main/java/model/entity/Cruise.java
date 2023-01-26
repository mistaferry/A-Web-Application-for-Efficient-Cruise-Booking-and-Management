package model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Cruise extends Entity {
    private static final long serialVersionUID = 1L;
    private Ship ship;
    private int duration;
    private double price;
    private Date startDate;
    private int number_of_register_people;

    public Cruise() {
    }

    public Cruise(Ship ship) {
        this.ship = ship;
    }

    public Cruise(Ship ship, int duration, Date startDate) {
        this.ship = ship;
        this.duration = duration;
        this.startDate = startDate;
    }

    public Cruise(Ship ship, int duration, double price, Date startDate, int number_of_register_people) {
        this.ship = ship;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.number_of_register_people = number_of_register_people;
    }

    public Cruise(long id, Ship ship, int duration, double price, Date startDate, int number_of_register_people) {
        super(id);
        this.ship = ship;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.number_of_register_people = number_of_register_people;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship shipId) {
        this.ship = shipId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getNumber_of_register_people() {
        return number_of_register_people;
    }

    public void setNumber_of_register_people(int number_of_register_people) {
        this.number_of_register_people = number_of_register_people;
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }
}
