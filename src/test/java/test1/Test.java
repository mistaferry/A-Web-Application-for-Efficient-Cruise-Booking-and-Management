package test1;

import com.google.protobuf.ServiceException;
import dao.CruiseDao;
import dao.UserDao;
import dto.CruiseDTO;
import dto.UserDTO;
import exceptions.DAOException;
import model.entity.Cruise;
import model.entity.User;
import services.GeneralService;
import services.impl.GeneralServiceImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test {
    CruiseDao cruiseDao = mock(CruiseDao.class);
    UserDao userDao = mock(UserDao.class);

    GeneralService generalService = new GeneralServiceImpl(userDao, cruiseDao);

    @org.junit.jupiter.api.Test
    void testViewUsers() throws DAOException, ServiceException, SQLException {
        List<User> users = new ArrayList<>();
        List<UserDTO> userDTOs = new ArrayList<>();
        users.add(getTestUser1());
        users.add(getTestUser2());
        userDTOs.add(getTestUserDTO1());
        userDTOs.add(getTestUserDTO2());
        when(userDao.getAll()).thenReturn(users);
        System.out.println(userDao.getAll());
        System.out.println(generalService.viewCatalog());
        assertIterableEquals(userDTOs, generalService.viewCatalog());
    }

    private UserDTO getTestUserDTO1() {
        return UserDTO.builder()
                .login("1")
                .password("1")
                .firstName("1")
                .surname("1")
                .roleId(1)
                .blocked(false)
                .build();

    }

    private UserDTO getTestUserDTO2() {
        return UserDTO.builder()
                .login("2")
                .password("2")
                .firstName("2")
                .surname("2")
                .roleId(2)
                .blocked(false)
                .build();

    }

    private User getTestUser1() {
        return User.builder()
                .login("1")
                .password("1")
                .firstName("1")
                .surname("1")
                .roleId(1)
                .blocked(false)
                .build();

    }

    private User getTestUser2() {
        return User.builder()
                .login("2")
                .password("2")
                .firstName("2")
                .surname("2")
                .roleId(2)
                .blocked(false)
                .build();

    }
}
