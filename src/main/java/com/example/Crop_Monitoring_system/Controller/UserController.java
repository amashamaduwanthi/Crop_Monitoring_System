package com.example.Crop_Monitoring_system.Controller;


import com.example.Crop_Monitoring_system.Secure.JWTAuthResponse;
import com.example.Crop_Monitoring_system.Secure.SignIn;
import com.example.Crop_Monitoring_system.Secure.SignUp;
import com.example.Crop_Monitoring_system.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(origins = "http://localhost:63342") // Replace with your frontend URL
public class UserController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<JWTAuthResponse> saveUser(@RequestBody SignUp signUp) {
        return ResponseEntity.ok(authenticationService.signUp(signUp));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn) {
        return ResponseEntity.ok(authenticationService.signIn(signIn));
    }
}

