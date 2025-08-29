package myl.codeio.hello.controller;

import lombok.RequiredArgsConstructor;
import myl.codeio.hello.model.user;
import myl.codeio.hello.repository.UserRepository;
import myl.codeio.hello.service.userService;
import myl.codeio.hello.utils.Jwtutils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final userService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Jwtutils jwtutils;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body){
        String email= body.get("email");
        String password=passwordEncoder.encode(body.get("password"));


        if(userRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email already exists",HttpStatus.CONFLICT);
        }
        userService.createuser(user.builder().email(email).password(password).build());
        return new ResponseEntity<>("successfully registered",HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
        String email= body.get("email");
        String password=body.get("password");

        var userOptional=userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("user not registered",HttpStatus.UNAUTHORIZED);
        }
        user User=userOptional.get();
        if(!passwordEncoder.matches(password,User.getPassword())){
            return new ResponseEntity<>("Invalid User",HttpStatus.UNAUTHORIZED);
        }
        String token= jwtutils.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));


    }
}
