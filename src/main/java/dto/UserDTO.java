package dto;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

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
