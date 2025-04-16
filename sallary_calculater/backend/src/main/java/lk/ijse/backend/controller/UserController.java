package lk.ijse.backend.controller;


import lk.ijse.backend.dto.UserDTO;
import lk.ijse.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // Register method
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.registerUser(userDTO);
        if (user != null) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(400).body("Registration failed");
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
//        UserDTO user = userService.loginUser(username, password);
//        if (user != null) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
//        UserDTO user = userService.loginUser(username, password);
//        if (user != null) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        UserDTO user = userService.loginUser(username, password);
        return (user != null)
                ? ResponseEntity.ok("Login successful")
                : ResponseEntity.status(401).body("Invalid username/password");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.updateUser(userDTO);

        if (user != null) {
            return ResponseEntity.ok("User updated successfully");
        }else {
            return ResponseEntity.status(400).body("Update failed");
        }
    }

}
