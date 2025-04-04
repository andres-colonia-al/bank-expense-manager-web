package com.acolonia.spring.frontend.controller;

import com.acolonia.spring.frontend.model.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8080/user";

    @GetMapping("/login")
    public String showLoginform() {
        return "auth/login";
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);

        String url = BASE_URL + "/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDto> requestEntity = new HttpEntity<>(userDto, headers);

        try {
            ResponseEntity<UserDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    UserDto.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Almacenar los datos del usuario en la sesión
                UserDto authenticatedUser = response.getBody();
                session.setAttribute("user", authenticatedUser);
                session.setAttribute("isAuthenticated", true);

                modelAndView.setViewName("redirect:/user/dashboard");
            }

        } catch (Exception e) {
            System.err.println("Error al comunicarse con el backend: " + e.getMessage());

            modelAndView.setViewName("auth/login");
            modelAndView.addObject("error", true);
            modelAndView.addObject("errorMessage", "Credenciales inválidas");
        }

        return modelAndView;
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
