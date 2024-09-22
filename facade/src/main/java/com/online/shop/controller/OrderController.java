package com.online.shop.controller;

import com.online.shop.dto.request.creation.OrderCreationDTO;
import com.online.shop.dto.request.update.OrderUpdateDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.dto.response.OrderResponseDTO;
import com.online.shop.enums.OrderStatus;
import com.online.shop.service.OrderFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST-контроллер для управления с заказами интернет-магазина
 */
@RestController
@RequestMapping(ApiPath.API_BASE + "/order")
@Tag(name = "Заказы", description = "Управление заказами интернет-магазина")
public class OrderController {

    @Autowired
    private OrderFacadeService orderFacadeService;

    /**
     * Обработчик GET запроса для получения списка заказов
     *
     * @return {@link List} список заказов {@link OrderResponseDTO}
     */
    @GetMapping
    @Operation(summary = "Получение всех заказов",
            description = "Позволяет получить список всех созданных заказов интернет-магазина")
    public List<OrderResponseDTO> getAllOrders() {
        return orderFacadeService.findAll();
    }

    /**
     * Обработчик GET запроса для получения информации о заказе по его {@code id}
     *
     * @param id идентификатор заказа {@link UUID}
     * @return DTO {@link OrderResponseDTO}, содержащий информацию о заказе
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получение заказа по ID", description = "Позволяет получить заказ по его ID")
    public OrderResponseDTO getOrder(@PathVariable UUID id) {
        return orderFacadeService.findById(id);
    }

    /**
     * Обработчик GET запроса для получения заказов с указанным статусом
     *
     * @param status статус заказа {@link OrderStatus}
     * @return {@link List} список, содержащий заказы {@link OrderResponseDTO} с указанным статусом {@code status}
     */
    @GetMapping(value = "/status", params = "status")
    @Operation(summary = "Получение заказа по статусу", description = "Позволяет получить заказ по текущему статусу")
    public List<OrderResponseDTO> getOrdersByStatus(@RequestParam OrderStatus status) {
        return orderFacadeService.findAllOrdersByStatus(status);
    }

    /**
     * Обработчик POST запроса для создания заказа
     *
     * @param orderCreationDTO DTO {@link OrderCreationDTO}, содержащая информацию для создания заказа
     * @return DTO {@link OrderResponseDTO}, содержащий информацию о заказе
     */
    @PostMapping
    @Operation(summary = "Создание заказа",
            description = "Позволяет создать заказ на основе корзины покупателя")
    public OrderResponseDTO addNewOrder(@Valid @RequestBody OrderCreationDTO orderCreationDTO) {
        return orderFacadeService.addNew(orderCreationDTO);
    }

    /**
     * Обработчик PUT запроса для обновления информации о заказе
     *
     * @param id идентификатор заказа {@link UUID}
     * @param orderUpdateDTO DTO {@link OrderUpdateDTO}, содержащий новую информацию о заказе
     * @return DTO {@link OrderResponseDTO} с обновленной информацией о заказе
     */
    @PutMapping("/{id}")
    @Operation(summary = "Изменение заказа",
            description = "Позволяет изменить данные заказа")
    public OrderResponseDTO updateOrder(@PathVariable UUID id, @Valid @RequestBody OrderUpdateDTO orderUpdateDTO) {
        return orderFacadeService.update(id, orderUpdateDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления заказа
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление заказа", description = "Позволяет удалить заказ по его ID")
    public InformationDTO deleteOrder(@PathVariable UUID id) {
        return orderFacadeService.deleteById(id);
    }

}
