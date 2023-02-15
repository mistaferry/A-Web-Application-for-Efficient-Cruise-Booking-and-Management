package dto;

import lombok.Builder;
import lombok.Data;
import model.entity.City;
import model.entity.Staff;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Builder
@Data
public class ShipDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private int numberOfPorts;
    private List<Staff> staff;
    private List<City> route;
    private int capacity;
}
