package model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Cruise extends Entity{
    private static final long serialVersionUID = 1L;
    private int shipId;
    private int duration;
    private double price;
    private Date startDate;
    private int numberOfPorts;
    private City startPort;
    private City endPort;
    private boolean paid;

    public Cruise(int shipId, int duration, double price, Date startDate, int numberOfPorts, City startPort, City endPort, boolean paid) {
        this.shipId = shipId;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.numberOfPorts = numberOfPorts;
        this.startPort = startPort;
        this.endPort = endPort;
        this.paid = paid;
    }

    public Cruise(long id, int shipId, int duration, double price, Date startDate, int numberOfPorts, City startPort, City endPort, boolean paid) {
        super(id);
        this.shipId = shipId;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.numberOfPorts = numberOfPorts;
        this.startPort = startPort;
        this.endPort = endPort;
        this.paid = paid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }

    public City getStartPort() {
        return startPort;
    }

    public void setStartPort(City startPort) {
        this.startPort = startPort;
    }

    public City getEndPort() {
        return endPort;
    }

    public void setEndPort(City endPort) {
        this.endPort = endPort;
    }

    public Cruise() { }
    public Cruise(int shipId) {
        this.shipId = shipId;
    }

    public Cruise(int shipId, int duration, Date startDate, boolean paid) {
        this.shipId = shipId;
        this.duration = duration;
        this.startDate = startDate;
        this.paid = paid;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
