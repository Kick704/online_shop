package com.online.shop.controller;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.dto.OrderDTO;
import com.online.shop.entity.Order;
import com.online.shop.enums.OrderStatus;
import com.online.shop.service.OrderFacadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/orders")
@Tag(name = "Заказы", description = "Управление заказами интернет-магазина")
public class OrderController {

    @Autowired
    private OrderFacadeService orderFacadeService;

    /**
     * Обработчик GET запроса для получения списка заказов
     *
     * @return {@link List} список заказов {@link OrderDTO}
     */
    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderFacadeService.findAllOrders();
    }

    /**
     * Обработчик GET запроса для получения информации о заказе по его {@code id}
     *
     * @param id идентификатор заказа {@link UUID}
     * @return DTO {@link OrderDTO}, содержащий информацию о заказе
     */
    @GetMapping("/{id}")
    public OrderDTO getOrders(@PathVariable UUID id) {
        return orderFacadeService.findOrderById(id);
    }

    /**
     * Обработчик GET запроса для получения заказов с указанным статусом
     *
     * @param status статус заказа {@link OrderStatus}
     * @return {@link List} список, содержащий заказы {@link OrderDTO} с указанным статусом {@code status}
     */
    @GetMapping(value = "/status", params = "status")
    public List<OrderDTO> getOrdersByStatus(@RequestParam OrderStatus status) {
        return orderFacadeService.findAllOrdersByStatus(status);
    }

    /**
     * Обработчик POST запроса для создания заказа
     *
     * @param order сущность {@link Order}, содержащая информацию для создания заказа
     * @return DTO {@link OrderDTO}, содержащий информацию о заказе
     */
    @PostMapping
    public OrderDTO addNewOrder(@RequestBody Order order) {
        return orderFacadeService.addNewOrder(order);
    }

    /**
     * Обработчик PUT запроса для обновления информации о заказе
     *
     * @param id идентификатор заказа {@link UUID}
     * @param orderDTO DTO {@link OrderDTO}, содержащий новую информацию о заказе
     * @return DTO {@link OrderDTO} с обновленной информацией о заказе
     */
    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable UUID id, @RequestBody OrderDTO orderDTO) {
        return orderFacadeService.updateOrder(id, orderDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления заказа
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link String} с информацией об успешном удалении
     */
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable UUID id) {
        orderFacadeService.deleteOrderById(id);
        return "Заказ с ID: " + id + " удалён";
    }

}
