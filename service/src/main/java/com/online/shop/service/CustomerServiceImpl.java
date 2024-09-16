package com.online.shop.service;

import com.online.shop.dao.CustomerRepository;
import com.online.shop.entity.Customer;
import com.online.shop.entity.Goods;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Реализация интерфейса для управления сущностью {@link Customer} на сервисном слое
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Выборка покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link Customer} - покупатель по указанному {@code id}, или выбрасывается исключение
     * {@link CommonRuntimeException}, если такого покупателя нет
     */
    @Override
    public Customer findById(UUID id) {
        return customerRepository.findCustomerById(id)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Покупатель с ID: %s не найден", id))
                );
    }

    /**
     * Выборка всех покупателей
     *
     * @return {@link List} - список всех покупателей {@link Customer}, или выбрасывается исключение
     * {@link CommonRuntimeException}, если покупателей ещё нет
     */
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAllCustomers();
        if (customers.isEmpty()) {
            throw new CommonRuntimeException(ErrorCode.ENTITY_NOT_FOUND, "Ни один покупатель не найден в БД");
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
        Customer customer = findById(id);
        List<Goods> goodsInCart = customer.getGoodsInCart();
        if (goodsInCart.isEmpty()) {
            throw new CommonRuntimeException(
                    ErrorCode.EMPTY_CART,
                    String.format("Корзина покупателя c ID: %s пуста", id)
            );
        }
        return goodsInCart;
    }

    /**
     * Получение общей стоимости товаров в корзине покупателя
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return Общая стоимость товаров в корзине покупателя
     */
    @Override
    public double getAmountOfGoodsInCustomerCart(UUID id) {
        List<Goods> goodsInCart = findAllGoodsInCustomerCart(id);
        return goodsInCart.stream().mapToDouble(Goods::getPrice).sum();
    }

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей {@link Customer} по указанному состоянию аккаунта, или
     * выбрасывается исключение {@link CommonRuntimeException}, если таких покупателей нет
     */
    @Override
    public List<Customer> findAllCustomersByEnabled(boolean enabled) {
        List<Customer> customers = customerRepository.findAllCustomersByEnabled(enabled);
        if (customers.isEmpty()) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Ни один покупатель не найден по указанному состоянию аккаунта: %s", enabled)
            );
        }
        return customers;
    }

    /**
     * Добавление/обновление покупателя в БД
     * <p> Может выбросить исключение {@link CommonRuntimeException},
     * если сущность ссылается на null или не проходит проверку уникальности
     *
     * @param customer сущность Покупатель {@link Customer}
     */
    @Override
    public void save(Customer customer) {

        if (customer == null) {
            throw new CommonRuntimeException(
                    ErrorCode.OBJECT_REFERENCE_IS_NULL,
                    "Customer: предан пустой объект для сохранения"
            );
        }

        if (customerRepository.existsByPhoneNumber(customer.getPhoneNumber())) {
            throw new CommonRuntimeException(
                    ErrorCode.UNIQUE_CONSTRAINT_VIOLATION,
                    "Покупатель с таким номером телефона уже зарегистрирован"
            );
        }

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CommonRuntimeException(
                    ErrorCode.UNIQUE_CONSTRAINT_VIOLATION,
                    "Покупатель с таким email уже зарегистрирован"
            );
        }

        customerRepository.save(customer);

    }

    /**
     * Удаление покупателя по id
     * <p> Может выбросить исключение {@link CommonRuntimeException}, если покупатель {@link Customer} не был удалён
     *
     * @param id идентификатор покупателя {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (customerRepository.deleteCustomerById(id) == 0) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Покупатель с ID: %s не найден или не может быть удалён", id)
            );
        }
    }
}
