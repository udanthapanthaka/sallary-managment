package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.UserDTO;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repository.UserRepository;
import lk.ijse.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);

        UserDTO newUserDTO = modelMapper.map(user, UserDTO.class);
        return newUserDTO;
    }

    @Override
    public UserDTO loginUser(String username, String password) {
       User user= userRepository.findByUsername(username);

       UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        System.out.println("1 eka "+userDTO);

       if (user.getPassword().equals(password)) {
           System.out.println(userDTO);
           return userDTO;
       }
       return null;

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user =userRepository.save(modelMapper.map(userDTO, User.class));

        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }
}
