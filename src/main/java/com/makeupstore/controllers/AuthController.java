package com.makeupstore.controllers;

import com.makeupstore.dtos.authdtos.GetAuthUser;
import com.makeupstore.dtos.authdtos.LoginUserDto;
import com.makeupstore.dtos.authdtos.SignupUserDto;
import com.makeupstore.exceptions.AlreadyExistsException;
import com.makeupstore.models.UserEntity;
import com.makeupstore.response.ApiResponse;
import com.makeupstore.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginUserDto user) {
        try {
            //Si se quisisera enviar el jwt a traves de una cookie se tendria que extraer el jwt del authUser y seguir con este proceso

            //Crear una cookie
            //Cookie cookie = new Cookie("JWT", jwtToken);
            //cookie.setHttpOnly(true); // No accesible desde JavaScript
            //cookie.setSecure(true); // Solo se enviará por HTTPS
            //cookie.setPath("/"); // La cookie estará disponible en todo el dominio
            //cookie.setMaxAge(86400); // Tiempo de vida de la cookie (en segundos)

            //Agregar la cookie a la respuesta
            //response.addCookie(cookie);

            GetAuthUser authUser = authService.login(user);
            return ResponseEntity.ok(new ApiResponse("Login successful", 200, authUser));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Incorrect email or password, try again", 400, null));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignupUserDto newUser) {
        try {
            UserEntity user = authService.singup(newUser);
            return ResponseEntity.ok(new ApiResponse("success", 200, user));
        } catch(AlreadyExistsException e) {
            return ResponseEntity.status(500).body(new ApiResponse(e.getMessage(), 500,null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), 500, null));
        }
    }
}
