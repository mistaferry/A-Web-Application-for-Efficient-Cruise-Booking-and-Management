package dto;

import lombok.Builder;
import lombok.Data;
import model.Cruise;

import java.sql.Date;

@Builder
@Data
public class OrderDTO {
    private static final long serialVersionUID = 1L;
    Cruise cruise;
    long userId;
    Date dateOfRegistration;
    boolean paid;
}
