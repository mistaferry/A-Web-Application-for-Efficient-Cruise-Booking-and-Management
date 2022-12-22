package model.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction extends Entity {
    private static final long serialVersionUID = 1L;
    private Date timestamp;
    private int cruiseId;
    private Double amount;
    private boolean completed;
    private String description;

    public Transaction(Date timestamp, int cruise, Double amount, boolean completed, String description) {
        this.timestamp = timestamp;
        this.cruiseId = cruise;
        this.amount = amount;
        this.completed = completed;
        this.description = description;
    }

    public Transaction() {

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getCruise() {
        return cruiseId;
    }

    public void setCruise(int cruise) {
        this.cruiseId = cruise;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getCruise() == that.getCruise()
                && isCompleted() == that.isCompleted()
                && Objects.equals(getTimestamp(), that.getTimestamp())
                && Objects.equals(getAmount(), that.getAmount())
                && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimestamp(), getCruise(), getAmount(), isCompleted(), getDescription());
    }
}
