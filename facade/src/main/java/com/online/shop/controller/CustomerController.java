package com.online.shop.controller;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.service.CustomerDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerDTOService customerDTOService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerDTOService.findAllCustomers();
    }

}
