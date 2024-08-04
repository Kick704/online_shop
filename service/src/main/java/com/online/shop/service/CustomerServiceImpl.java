package com.online.shop.service;

import com.online.shop.dao.CustomerRepository;
import com.online.shop.entity.Customer;
import com.online.shop.entity.Goods;
import com.online.shop.exception.NoEntitiesFoundException;
import com.online.shop.exception.NoSuchEntityException;
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
     * @return {@link Customer} - покупатель по указанному {@code id}, или выбрасывается исключение
     * {@link NoSuchEntityException}, если такого покупателя нет
     */
    @Override
    public Customer findCustomerById(UUID id) {
        return customerRepository.findCustomerById(id)
                .orElseThrow(() -> new NoSuchEntityException("Покупатель с ID: " + id + " не найден"));
    }

    /**
     * Выборка всех покупателей
     *
     * @return {@link List} - список всех покупателей {@link Customer}, или выбрасывается исключение
     * {@link NoEntitiesFoundException}, если покупателей ещё нет
     */
    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customers = customerRepository.findAllCustomers();
        if (customers.isEmpty()) {
            throw new NoEntitiesFoundException("Ни один покупатель не найден в БД");
        }
        return customers;
    }

    /**
     * Выборка всех товаров в корзине покупателя
     *
     * @return {@link List} - список всех товаров {@link Goods} в корзине покупателя, или выбрасывается исключение
     * (пока в разработке)
     */
    @Override
    public List<Goods> findAllGoodsInCustomerCart(UUID id) {
        Customer customer = findCustomerById(id);
        List<Goods> goodsInCart = customer.getGoodsInCart();
        if (goodsInCart.isEmpty()) {
            throw new IllegalStateException("Корзина покупателя пуста");
        }
        return goodsInCart;
    }

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей {@link Customer} по указанному состоянию аккаунта, или
     * выбрасывается исключение {@link NoEntitiesFoundException}, если таких покупателей нет
     */
    @Override
    public List<Customer> findAllCustomersByEnabled(boolean enabled) {
        List<Customer> customers = customerRepository.findAllCustomersByEnabled(enabled);
        if (customers.isEmpty()) {
            throw new NoEntitiesFoundException("Ни один покупатель не найден по указанному состоянию аккаунта: "
                    + enabled);
        }
        return customers;
    }

    /**
     * Добавление/обновление покупателя в БД
     *
     * @param customer сущность Покупатель {@link Customer}
     */
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    /**
     * Удаление покупателя по id
     * <p> Может выбросить исключение {@link NoSuchEntityException}, если покупатель {@link Customer} не был удалён
     *
     * @param id идентификатор покупателя {@link UUID}
     */
    @Override
    public void deleteCustomerById(UUID id) {
        if (customerRepository.deleteCustomerById(id) == 0) {
            throw new NoSuchEntityException("Покупатель с ID: " + id + " не найден и/или не может быть удалён");
        }
    }
}
