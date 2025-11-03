package com.insurance.application.controllers;


import com.insurance.application.data.repositories.CustomerRepository;
import com.insurance.application.data.repositories.InsuranceRepository;
import com.insurance.application.models.Customer;
import com.insurance.application.models.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class HomeController {

    private final CustomerRepository customerRepository;
    private final InsuranceRepository insuranceRepository;

    @Autowired
    public HomeController(CustomerRepository customerRepository,
                          InsuranceRepository insuranceRepository) {
        this.customerRepository = customerRepository;
        this.insuranceRepository = insuranceRepository;
    }

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customerList", customerRepository.findAll());
        return "pages/home/index";
    }

    @GetMapping("/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "pages/home/new-customer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }

    @GetMapping("/detail/{id}")
    public String customerDetail(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));

        List<Insurance> insuranceList = insuranceRepository.findByCustomerId(id);

        model.addAttribute("customer", customer);
        model.addAttribute("insuranceList", insuranceList);

        return "pages/home/customer-detail";
    }

    @GetMapping("/insurance/new/{customerId}")
    public String showAddInsuranceForm(@PathVariable Long customerId, Model model) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + customerId));

        model.addAttribute("customer", customer);
        model.addAttribute("insurance", new Insurance());
        return "pages/home/new-insurance";
    }

    @PostMapping("/insurance/save/{customerId}")
    public String saveInsurance(@PathVariable Long customerId,
                                @ModelAttribute Insurance insurance) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + customerId));

        insurance.setCustomer(customer);
        insuranceRepository.save(insurance);

        return "redirect:/customers/detail/" + customerId;
    }

    // Delete insurance
    @GetMapping("/insurance/delete/{id}")
    public String deleteInsurance(@PathVariable Long id) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid insurance Id:" + id));
        Long customerId = insurance.getCustomer().getId();
        insuranceRepository.delete(insurance);
        return "redirect:/customers/detail/" + customerId;
    }

    // Show edit form
    @GetMapping("/insurance/edit/{id}")
    public String showEditInsuranceForm(@PathVariable Long id, Model model) {
        Insurance insurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid insurance Id:" + id));
        model.addAttribute("insurance", insurance);
        model.addAttribute("customer", insurance.getCustomer());
        return "pages/home/new-insurance"; // reuse the same form
    }

    // Save edited insurance
    @PostMapping("/insurance/update/{id}")
    public String updateInsurance(@PathVariable Long id, @ModelAttribute Insurance insurance) {
        Insurance existingInsurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid insurance Id:" + id));

        existingInsurance.setType(insurance.getType());
        existingInsurance.setAmount(insurance.getAmount());
        existingInsurance.setSubject(insurance.getSubject());
        existingInsurance.setValidFrom(insurance.getValidFrom());
        existingInsurance.setValidTo(insurance.getValidTo());

        insuranceRepository.save(existingInsurance);
        return "redirect:/customers/detail/" + existingInsurance.getCustomer().getId();
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));

        model.addAttribute("customer", customer);
        return "pages/home/new-customer"; // reuse the same form
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));

        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setStreet(updatedCustomer.getStreet());
        customer.setCity(updatedCustomer.getCity());
        customer.setPostNumber(updatedCustomer.getPostNumber());

        customerRepository.save(customer);

        return "redirect:/customers/detail/" + id;
    }


}



















