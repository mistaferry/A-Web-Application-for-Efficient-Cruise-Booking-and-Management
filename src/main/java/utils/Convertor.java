package utils;

import dto.*;
import model.entity.Cruise;
import model.entity.User;

public final class Convertor {
    private Convertor() {}

    public static User convertDTOToUser(UserDTO userDTO) {
        return User.builder()
                .login(userDTO.getLogin())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .surname(userDTO.getSurname())
                .blocked(userDTO.isBlocked())
                .roleId(userDTO.getRoleId())
//                .cruises(userDTO.getCruises())
                .build();
    }

    public static UserDTO convertUserToDTO(User user) {
        return UserDTO.builder()
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
                .shipId(cruise.getShipId())
                .duration(cruise.getDuration())
                .startDate(cruise.getStartDate())
                .paid(cruise.isPaid())
                .numberOfPorts(cruise.getNumberOfPorts())
                .startPort(cruise.getStartPort())
                .endPort(cruise.getEndPort())
                .build();
    }
}
