package com.acolonia.spring.frontend.controller;

import com.acolonia.spring.frontend.model.TransactionDto;
import com.acolonia.spring.frontend.model.UserDto;
import com.acolonia.spring.frontend.model.enums.Department;
import com.acolonia.spring.frontend.model.enums.RoleCategory;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TransactionAdminController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "http://localhost:8080/transaction";

    @GetMapping("/dashboard")
    public ModelAndView showAdminDashboard( HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("admin/dashboard");
        modelAndView.addObject("categories", Arrays.asList(RoleCategory.values()));
        modelAndView.addObject("departments", Arrays.asList(Department.values()));
        UserDto authenticatedUser = (UserDto) session.getAttribute("user");

        if (authenticatedUser == null) {
            modelAndView.setViewName("redirect:/auth/login");
        }

        String url = BASE_URL + "/all";
        ResponseEntity<TransactionDto[]> response = restTemplate.getForEntity(url, TransactionDto[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            List<TransactionDto> transactions = Arrays.asList(response.getBody());
            modelAndView.addObject("transactions", transactions);
        }

        return modelAndView;
    }

    @GetMapping("/filter/category")
    public ModelAndView filterByCategory(@RequestParam("category") String category) {
        ModelAndView modelAndView = new ModelAndView("admin/dashboard");
        modelAndView.addObject("categories", Arrays.asList(RoleCategory.values()));
        modelAndView.addObject("departments", Arrays.asList(Department.values()));

        String url = BASE_URL + "/category/" + category;
        ResponseEntity<TransactionDto[]> response = restTemplate.getForEntity(url, TransactionDto[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            List<TransactionDto> transactions = Arrays.asList(response.getBody());
            modelAndView.addObject("transactions", transactions);
        }

        return modelAndView;
    }

    @GetMapping("/filter/department")
    public ModelAndView filterByDepartment(@RequestParam("deptId") Long deptId) {
        ModelAndView modelAndView = new ModelAndView("admin/dashboard");
        modelAndView.addObject("categories", Arrays.asList(RoleCategory.values()));
        modelAndView.addObject("departments", Arrays.asList(Department.values()));


        String url = BASE_URL + "/departments/" + deptId;
        ResponseEntity<TransactionDto[]> response = restTemplate.getForEntity(url, TransactionDto[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            List<TransactionDto> transactions = Arrays.asList(response.getBody());
            modelAndView.addObject("transactions", transactions);
        }

        return modelAndView;
    }

    @PostMapping("/deleteTransaction")
    public String deleteTransaction(@RequestParam("idTransaction") Long idTransaction) {

        String url = BASE_URL + "/" + idTransaction;
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);

        return "redirect:/admin/dashboard";
    }
}
