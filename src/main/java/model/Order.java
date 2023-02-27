package model;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class Order{
    private static final long serialVersionUID = 1L;
    Cruise cruise;
    long userId;
    Date dateOfRegistration;
    boolean paid;

    public Order() {
    }

    public Order(Cruise cruise, long userId, Date dateOfRegistration, boolean paid) {
        this.cruise = cruise;
        this.userId = userId;
        this.dateOfRegistration = dateOfRegistration;
        this.paid = paid;
    }

    public Cruise getCruise() {
        return cruise;
    }

    public void setCruise(Cruise cruise) {
        this.cruise = cruise;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
