package com.insurance.application.controllers;


import com.insurance.application.data.repositories.CustomerRepository;
import com.insurance.application.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class HomeController {

    private final CustomerRepository customerRepository;

    @Autowired
    public HomeController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Home page: list all customers
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customerList", customerRepository.findAll());
        return "pages/home/index"; // Thymeleaf template
    }

    // Show form to create a new customer
    @GetMapping("/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "pages/home/new-customer"; // Thymeleaf template
    }

    // Save a new customer
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    // Delete a customer by ID
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }
}























