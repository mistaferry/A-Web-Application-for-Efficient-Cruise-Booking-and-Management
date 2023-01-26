package model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class Order extends Entity{
    private static final long serialVersionUID = 1L;
    int cruiseId;
    int userId;
    Date dateOfRegistration;
    boolean paid;

    public Order(long id, int cruise, int user, Date dateOfRegistration, boolean paid) {
        super(id);
        this.cruiseId = cruise;
        this.userId = user;
        this.dateOfRegistration = dateOfRegistration;
        this.paid = paid;
    }

    public Order(int cruiseId, int userId, Date dateOfRegistration, boolean paid) {
        this.cruiseId = cruiseId;
        this.userId = userId;
        this.dateOfRegistration = dateOfRegistration;
        this.paid = paid;
    }

    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Override
    public long getId() {
        return super.getId();
    }
}
