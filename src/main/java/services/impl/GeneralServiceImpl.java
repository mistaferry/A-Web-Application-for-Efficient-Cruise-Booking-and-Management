package services.impl;

import com.google.protobuf.ServiceException;
import dao.UserDao;
import dto.CruiseDTO;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.User;
import services.GeneralService;
import utils.Convertor;

import java.util.List;
import java.util.Optional;

import static utils.Convertor.convertUserToDTO;

public class GeneralServiceImpl implements GeneralService {
    private final UserDao userDao;
    public GeneralServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDTO signIn(String login, String password) throws ServiceException {
        UserDTO userDTO;
        try{
            User user = (userDao.getByEmail(login)).get();
            if(user == null){
                throw new Exception("Login not exist");
            }
            userDTO = convertUserToDTO(user);
            return userDTO;
        } catch (Exception e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public List<CruiseDTO> viewCatalog(String login, String password) throws ServiceException {
        return null;
    }
}
