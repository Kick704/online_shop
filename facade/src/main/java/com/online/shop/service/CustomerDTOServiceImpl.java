package com.online.shop.service;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerDTOServiceImpl implements CustomerDTOService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * Выборка покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link CustomerDTO} - покупатель по указанному {@code id}, или {@code null}, если такого покупателя нет
     */

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findCustomerById(UUID id) {
        return customerMapper.toCustomerDTO(customerService.findCustomerById(id));
    }

    /**
     * Выборка всех покупателей
     *
     * @return {@link List} - список всех покупателей, или {@code null}, если покупателей еще нет
     */
    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomers() {
        return customerMapper.toCustomerDTOList(customerService.findAllCustomers());
    }

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей по указанному состоянию аккаунта, или {@code null},
     * если таких покупателей нет
     */

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomersByEnabled(boolean enabled) {
        return customerMapper.toCustomerDTOList(customerService.findAllCustomersByEnabled(enabled));
    }

    /**
     * Удаление покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     */
    @Transactional
    @Override
    public void deleteCustomerById(UUID id) {
        customerService.deleteCustomerById(id);
    }

}
