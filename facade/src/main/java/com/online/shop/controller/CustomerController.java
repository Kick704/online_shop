package com.online.shop.controller;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.dto.GoodsDTO;
import com.online.shop.entity.Customer;
import com.online.shop.service.CustomerFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customers")
@Tag(name = "Покупатели", description = "Взаимодействие с пользователями интернет-магазина")
public class CustomerController {

    @Autowired
    private CustomerFacadeService customerFacadeService;

    /**
     * Обработчик GET запроса для получения списка покупателей
     *
     * @return {@link List} список покупателей {@link CustomerDTO}
     */
    @GetMapping
    @Operation(summary = "Получить всех покупателей", description = "Позволяет получить список всех покупателей")
    public List<CustomerDTO> getAllCustomers() {
        return customerFacadeService.findAllCustomers();
    }

    /**
     * Обработчик GET запроса для получения информации о покупателе по его {@code id}
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return DTO {@link CustomerDTO}, содержащий информацию о покупателе
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получить покупателя", description = "Позволяет получить покупателей по ID")
    public CustomerDTO getCustomer(@PathVariable UUID id) {
        return customerFacadeService.findCustomerById(id);
    }

    /**
     * Обработчик GET запроса для получения информации о корзине покупателе по его {@code id}
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link List} список товаров {@link GoodsDTO} в корзине покупателя
     */
    @GetMapping(value = "/cart", params = "id")
    @Operation(summary = "Получить корзину покупателя",
            description = "Позволяет получить список товаров покупателя по ID")
    public List<GoodsDTO> getCustomerCart(@RequestParam UUID id) {
        return customerFacadeService.findAllGoodsInCustomerCart(id);
    }

    /**
     * Обработчик GET запроса для получения списка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} список покупателей {@link CustomerDTO} по указанному состоянию аккаунта {@code enabled}
     */
    @GetMapping(params = "enabled")
    @Operation(summary = "Получить всех покупателей по состоянию аккаунта",
            description = "Позволяет получить список всех активных/неактивных аккаунтов покупателей")
    public List<CustomerDTO> getAllCustomersByEnabled(@RequestParam boolean enabled) {
        return customerFacadeService.findAllCustomersByEnabled(enabled);
    }

    /**
     * Обработчик POST запроса для регистрации покупателя
     *
     * @param customer сущность {@link Customer}, содержащая информацию для регистрации покупателя
     * @return DTO {@link CustomerDTO}, содержащий информацию о покупателе
     */
    @PostMapping
    @Operation(summary = "Регистрация покупателя",
            description = "Позволяет пользователю зарегистрироваться в качестве покупателя")
    public CustomerDTO addNewCustomer(@RequestBody Customer customer) {
        return customerFacadeService.addNewCustomer(customer);
    }

    /**
     * Обработчик PUT запроса для обновления информации о покупателе
     *
     * @param id идентификатор покупателя {@link UUID}
     * @param customerDTO DTO {@link CustomerDTO}, содержащий новую информацию о покупателе
     * @return DTO {@link CustomerDTO} с обновленной информацией о покупателе
     */
    @PutMapping("/{id}")
    @Operation(summary = "Обновить информацию о покупателе", description = "Позволяет изменить данные покупателя")
    public CustomerDTO updateCustomer(@PathVariable UUID id, @RequestBody CustomerDTO customerDTO) {
        return customerFacadeService.updateCustomer(id, customerDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления покупателя
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link String} с информацией об успешном удалении
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить покупателя", description = "Позволяет удалить покупателя из БД")
    public String deleteCustomer(@PathVariable UUID id) {
        customerFacadeService.deleteCustomerById(id);
        return "Покупатель с ID: " + id + " удалён";
    }


}
