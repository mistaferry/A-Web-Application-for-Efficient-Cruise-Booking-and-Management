package dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Builder
@Data
public class CruiseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int shipId;
    private int duration;
    private Date startDate;
    private int numberOfPorts;
    private String startPort;
    private String endPort;
    private boolean paid;

}
