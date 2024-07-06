package service;

import entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(long id);
    void saveCustomer(Customer customer);
    void deleteCustomer(Customer customer);
}
