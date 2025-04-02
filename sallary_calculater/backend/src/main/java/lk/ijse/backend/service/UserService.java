package lk.ijse.backend.service;

import lk.ijse.backend.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO loginUser(String username, String password);

    UserDTO updateUser(UserDTO userDTO);

    boolean deleteUser(String id);
}
