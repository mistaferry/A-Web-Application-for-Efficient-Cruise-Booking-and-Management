package dto;

import lombok.Builder;
import lombok.Data;
import model.entity.City;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Builder
@Data
public class CruiseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private int shipId;
    private int duration;
    private double price;
    private Date startDate;
    private int numberOfPorts;
    private City startPort;
    private City endPort;
    private boolean paid;

}
