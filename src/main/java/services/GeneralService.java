package services;

import com.google.protobuf.ServiceException;
import dao.CruiseDao;
import dto.CruiseDTO;
import dto.UserDTO;

import java.util.List;

public interface GeneralService {

    UserDTO signIn(String login, String password) throws ServiceException;

    List<UserDTO> viewCatalog() throws ServiceException;

    List<CruiseDTO> viewCruiseCatalog() throws ServiceException;
}
