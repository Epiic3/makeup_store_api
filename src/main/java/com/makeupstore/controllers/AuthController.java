package com.makeupstore.controllers;

import com.makeupstore.exceptions.AlreadyExistsException;
import com.makeupstore.models.UserEntity;
import com.makeupstore.response.ApiResponse;
import com.makeupstore.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public ResponseEntity<ApiResponse> signup(@RequestBody UserEntity newUser) {
        try {
            UserEntity user = authService.singup(newUser);
            return ResponseEntity.ok(new ApiResponse("success", 200, user));
        } catch(AlreadyExistsException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), 500,null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse("error", 500, e));
        }
    }
}
