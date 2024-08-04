package com.online.shop.service;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.dto.GoodsDTO;
import com.online.shop.entity.Customer;
import com.online.shop.mapper.CustomerMapper;
import com.online.shop.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerFacadeServiceImpl implements CustomerFacadeService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * Выборка покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link CustomerDTO} - покупатель по указанному {@code id}
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerDTO findCustomerById(UUID id) {
        return customerMapper.toDTO(customerService.findCustomerById(id));
    }

    /**
     * Выборка всех покупателей
     *
     * @return {@link List} - список всех покупателей {@link CustomerDTO}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findAllCustomers() {
        return customerMapper.toDTOList(customerService.findAllCustomers());
    }

    /**
     * Выборка всех товаров в корзине покупателя
     *
     * @return {@link List} - список всех товаров {@link GoodsDTO} в корзине покупателя
     */
    @Override
    public List<GoodsDTO> findAllGoodsInCustomerCart(UUID id) {
        return goodsMapper.toDTOList(customerService.findAllGoodsInCustomerCart(id));
    }

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей {@link CustomerDTO} по указанному состоянию аккаунта
     * {@code enabled}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> findAllCustomersByEnabled(boolean enabled) {
        return customerMapper.toDTOList(customerService.findAllCustomersByEnabled(enabled));
    }

    /**
     * Добавление нового покупателя в БД
     *
     * @param customer сущность Покупатель {@link CustomerDTO}
     */
    @Override
    @Transactional
    public CustomerDTO addNewCustomer(Customer customer) {
        Customer newCustomer = Customer.Builder.
                newBuilder().
                surname(customer.getSurname()).
                firstname(customer.getFirstname()).
                patronymic(customer.getPatronymic()).
                phoneNumber(customer.getPhoneNumber()).
                email(customer.getEmail()).
                password(customer.getPassword()).
                build();
        customerService.saveCustomer(newCustomer);
        return customerMapper.toDTO(newCustomer);
    }

    /**
     * Обновление покупателя в БД
     *
     * @param id идентификатор покупателя {@link UUID}
     * @param customerDTO DTO Покупатель {@link CustomerDTO} с изменёнными полями
     * @return обновлённый DTO Покупатель {@link CustomerDTO}
     */
    @Override
    @Transactional
    public CustomerDTO updateCustomer(UUID id, CustomerDTO customerDTO) {
        Customer customer = customerService.findCustomerById(id);
        customerMapper.updateEntityFromDto(customerDTO, customer);
        customerService.saveCustomer(customer);
        return customerMapper.toDTO(customer);
    }

    /**
     * Удаление покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     */
    @Override
    @Transactional
    public void deleteCustomerById(UUID id) {
        customerService.deleteCustomerById(id);
    }

}
