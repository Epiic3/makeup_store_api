package com.makeupstore.controllers;

import com.makeupstore.dtos.userdtos.UpdateUserDto;
import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.UserEntity;
import com.makeupstore.response.ApiResponse;
import com.makeupstore.services.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            List<UserEntity> users = userService.getAllUsers();
            return ResponseEntity.ok(new ApiResponse("success", 200, users));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), 500, null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        try {
            UserEntity user = userService.getUserById(id);
            return ResponseEntity.ok(new ApiResponse("success", 200, user));
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), 404, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), 500, null));
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserDto updateUserDto, @PathVariable Long id) {
        try {
            UserEntity user = userService.updateUser(updateUserDto, id);
            return ResponseEntity.ok(new ApiResponse("success", 200, user));
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), 404, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), 500, null));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponse("success", 200, null));
        } catch(ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), 404, null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), 500, null));
        }
    }
}
