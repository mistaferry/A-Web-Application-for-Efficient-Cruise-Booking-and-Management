package utils;

import dto.*;
import model.entity.Cruise;
import model.entity.User;

public final class Convertor {
    private Convertor() {}

    public static User convertDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setSurname(userDTO.getSurname());
        user.setBlocked(userDTO.isBlocked());
        user.setRoleId(userDTO.getRoleId());
        return user;
    }

    public static UserDTO convertUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .blocked(user.isBlocked())
                .roleId(user.getRoleId())
//                .cruises(userDTO.getCruises())
                .build();
    }

    public static CruiseDTO convertCruiseToDTO(Cruise cruise) {
        return CruiseDTO.builder()
                .id(cruise.getId())
                .ship(cruise.getShip())
                .duration(cruise.getDuration())
                .price(cruise.getPrice())
                .startDate(cruise.getStartDate())
                .paid(cruise.isPaid())
                .build();
    }
}
