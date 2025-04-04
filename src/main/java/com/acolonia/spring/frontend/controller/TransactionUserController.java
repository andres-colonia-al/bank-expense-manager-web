package com.acolonia.spring.frontend.controller;

import com.acolonia.spring.frontend.model.TransactionDto;
import com.acolonia.spring.frontend.model.UserDto;
import com.acolonia.spring.frontend.model.enums.RoleCategory;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class TransactionUserController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8080/transaction";

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        UserDto authenticatedUser = (UserDto) session.getAttribute("user");

        if (authenticatedUser == null) {
            return "redirect:/auth/login";
        }

        Long userId = authenticatedUser.getIdUser();
        String url = BASE_URL + "/usuario/" + userId;

        ResponseEntity<List> response = restTemplate.exchange(
                url, HttpMethod.GET, null, List.class );

        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("transactions", response.getBody());
        } else {
            model.addAttribute("transactions", List.of());
        }

        model.addAttribute("userId", userId);
        model.addAttribute("categories", Arrays.asList(RoleCategory.values()));

        return "user/dashboard";
    }

    @PostMapping("/addTransaction")
    public ModelAndView addTransaction(@RequestParam("nameTransaction") String nameTransaction,
                                       @RequestParam("descriptionTransaction") String descriptionTransaction,
                                       @RequestParam("amountTransaction") String amountTransaction,
                                       @RequestParam("category") String category,
                                       HttpSession session) {

        UserDto authenticatedUser = (UserDto) session.getAttribute("user");

        ModelAndView modelAndView = new ModelAndView();

        TransactionDto transactionDto = TransactionDto.builder()
                .nameTransaction(nameTransaction)
                .descriptionTransaction(descriptionTransaction)
                .amountTransaction(Double.parseDouble(amountTransaction))
                .category(category)
                .idUser(authenticatedUser.getIdUser())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TransactionDto> requestEntity = new HttpEntity<>(transactionDto, headers);
        try {
            ResponseEntity<TransactionDto> response = restTemplate.exchange(
                    BASE_URL,
                    HttpMethod.POST,
                    requestEntity,
                    TransactionDto.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                modelAndView.setViewName("redirect:/user/dashboard");
            }

        } catch (Exception e) {
            System.err.println("Error al comunicarse con el backend: " + e.getMessage());

            modelAndView.setViewName("redirect:/user/dashboard");
            modelAndView.addObject("error", true);
            modelAndView.addObject("errorMessage", "Error al ingresar la nueva transaccion");
        }

        return modelAndView;
    }

    @PostMapping("/deleteTransaction")
    public String deleteTransaction(@RequestParam("idTransaction") Long idTransaction) {

        String url = BASE_URL + "/" + idTransaction;
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);

        return "redirect:/user/dashboard";
    }

}
