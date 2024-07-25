package com.online.shop.service;

import com.online.shop.dao.CustomerRepository;
import com.online.shop.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Выборка покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link Customer} - покупатель по указанному {@code id}, или {@code null}, если такого покупателя нет
     */
    @Override
    public Customer findCustomerById(UUID id) {
        return customerRepository.findCustomerById(id);
    }

    /**
     * Выборка всех покупателей
     *
     * @return {@link List} - список всех покупателей, или {@code null}, если покупателей еще нет
     */
    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей по указанному состоянию аккаунта, или {@code null},
     * если таких покупателей нет
     */
    @Override
    public List<Customer> findAllCustomersByEnabled(boolean enabled) {
        return customerRepository.findAllCustomersByEnabled(enabled);
    }

    /**
     * Удаление покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     */
    @Override
    public void deleteCustomerById(UUID id) {
        customerRepository.deleteCustomerById(id);
    }
}
