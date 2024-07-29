package com.online.shop.mapper;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для {@link Customer} и {@link CustomerDTO}
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> toCustomerDTOList(List<Customer> customers);
    List<Customer> toCustomerList(List<CustomerDTO> customerDTOS);

}
