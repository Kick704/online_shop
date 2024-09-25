package com.online.shop.controller;

import com.online.shop.dto.request.creation.CustomerCreationDTO;
import com.online.shop.dto.request.update.CustomerUpdateDTO;
import com.online.shop.dto.response.CustomerResponseDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.service.CustomerFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.online.shop.entity.CustomerRole.*;

/**
 * REST-контроллер для взаимодействия с покупателями интернет-магазина
 */
@RestController
@RequestMapping(ApiPath.API_BASE + "/customer")
@Tag(name = "Покупатели", description = "Взаимодействие с пользователями интернет-магазина")
public class CustomerController {

    @Autowired
    private CustomerFacadeService customerFacadeService;

    /**
     * Обработчик GET запроса для получения списка покупателей
     *
     * @return {@link List} список покупателей {@link CustomerResponseDTO}
     */
    @Secured(ADMIN)
    @GetMapping
    @Operation(summary = "Получение всех покупателей",
            description = "Позволяет получить список всех покупателей(клиентов) интернет-магазина")
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerFacadeService.findAll();
    }

    /**
     * Обработчик GET запроса для получения информации о покупателе по его {@code id}
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return DTO {@link CustomerResponseDTO}, содержащий информацию о покупателе
     */
    @Secured(ADMIN)
    @GetMapping("/{id}")
    @Operation(summary = "Получение покупателя по ID", description = "Позволяет получить покупателя по его ID")
    public CustomerResponseDTO getCustomer(@PathVariable UUID id) {
        return customerFacadeService.findById(id);
    }

    /**
     * Обработчик GET запроса для получения информации о корзине покупателе по его {@code id}
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link List} список товаров {@link GoodsResponseDTO} в корзине покупателя
     */
    @Secured(ADMIN)
    @GetMapping(value = "/cart", params = "id")
    @Operation(summary = "Получение корзины покупателя",
            description = "Позволяет получить список товаров в корзине по ID покупателя")
    public List<GoodsResponseDTO> getCustomerCart(@RequestParam UUID id) {
        return customerFacadeService.findAllGoodsInCustomerCart(id);
    }

    /**
     * Обработчик GET запроса для получения списка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} список покупателей {@link CustomerResponseDTO} по указанному состоянию аккаунта {@code enabled}
     */
    @Secured(ADMIN)
    @GetMapping(value = "/by-enabled", params = "enabled")
    @Operation(summary = "Получение всех покупателей по состоянию аккаунта",
            description = "Позволяет получить список всех активных/неактивных аккаунтов покупателей")
    public List<CustomerResponseDTO> getAllCustomersByEnabled(@RequestParam boolean enabled) {
        return customerFacadeService.findAllCustomersByEnabled(enabled);
    }


    @PostMapping
    @Operation(summary = "Регистрация покупателя",
            description = "Позволяет зарегистрировать нового покупателя")
    public CustomerResponseDTO addNewCustomer(@Valid @RequestBody CustomerCreationDTO customerCreationDTO) {
        return customerFacadeService.addNew(customerCreationDTO);
    }

    /**
     * Обработчик PUT запроса для обновления информации о покупателе
     *
     * @param id                идентификатор покупателя {@link UUID}
     * @param customerUpdateDTO DTO {@link CustomerUpdateDTO}, содержащий новую информацию о покупателе
     * @return DTO {@link CustomerResponseDTO} с обновленной информацией о покупателе
     */
    @Secured(ADMIN)
    @PutMapping("/{id}")
    @Operation(summary = "Изменение информации о покупателе", description = "Позволяет изменить данные покупателя")
    public CustomerResponseDTO updateCustomer(@PathVariable UUID id,
                                              @Valid @RequestBody CustomerUpdateDTO customerUpdateDTO) {
        return customerFacadeService.update(id, customerUpdateDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления покупателя
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @Secured(ADMIN)
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление покупателя", description = "Позволяет удалить покупателя по его ID")
    public InformationDTO deleteCustomer(@PathVariable UUID id) {
        return customerFacadeService.deleteById(id);
    }


}
