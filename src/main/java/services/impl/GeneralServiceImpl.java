package services.impl;

import com.google.protobuf.ServiceException;
import dao.UserDao;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.User;
import services.GeneralService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public List<UserDTO> viewCatalog() throws ServiceException {
        List<UserDTO> DTOUserList = new ArrayList<>();
        try{
            List<User> userList = userDao.getAll();
            userList.forEach(user -> DTOUserList.add(convertUserToDTO(user)));
            return DTOUserList;
        } catch (DAOException | SQLException e) {
            throw  new ServiceException(e);
        }
    }
}
