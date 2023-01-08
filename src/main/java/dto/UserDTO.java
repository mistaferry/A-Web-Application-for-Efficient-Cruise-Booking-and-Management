package dto;

import model.entity.Cruise;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;
import java.util.Set;

@Builder
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String login;
    private String password;
    private String firstName;
    private String surname;
    private int roleId;
    private boolean blocked;
//    private Set<Cruise> cruises;

}
