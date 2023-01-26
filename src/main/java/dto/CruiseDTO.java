package dto;

import lombok.Builder;
import lombok.Data;
import model.entity.City;
import model.entity.Ship;

import java.io.Serializable;
import java.sql.Date;

@Builder
@Data
public class CruiseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private Ship ship;
    private int duration;
    private double price;
    private Date startDate;
    private int number_of_register_people;
}
