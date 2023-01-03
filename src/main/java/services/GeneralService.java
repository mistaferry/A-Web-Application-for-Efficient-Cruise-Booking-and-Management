package services;

import com.google.protobuf.ServiceException;
import dto.UserDTO;

public interface GeneralService {

    UserDTO signIn(String login, String password) throws ServiceException;


}
