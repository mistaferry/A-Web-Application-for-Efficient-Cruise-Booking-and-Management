package model.entity;

import java.sql.Date;

public class Cruise extends Entity{
    private static final long serialVersionUID = 1L;
    private int shipId;
    private int duration;
    private Date startDate;
    private int numberOfPorts;
    private String startPort;
    private String endPort;
    private boolean paid;

    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }

    public String getStartPort() {
        return startPort;
    }

    public void setStartPort(String startPort) {
        this.startPort = startPort;
    }

    public String getEndPort() {
        return endPort;
    }

    public void setEndPort(String endPort) {
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
