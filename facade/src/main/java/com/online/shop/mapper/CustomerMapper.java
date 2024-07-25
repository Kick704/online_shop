package com.online.shop.mapper;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);

}
