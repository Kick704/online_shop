package com.online.shop.controller;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.dto.GoodsCreationDTO;
import com.online.shop.dto.GoodsDTO;
import com.online.shop.service.GoodsFacadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiPath.API_BASE + "/goods")
@Tag(name = "Товары", description = "Управление товарами интернет-магазина")
public class GoodsController {

    @Autowired
    private GoodsFacadeService goodsFacadeService;

    /**
     * Обработчик GET запроса для получения списка товаров
     *
     * @return {@link List} список товаров {@link GoodsDTO}
     */
    @GetMapping
    public List<GoodsDTO> getAllGoods() {
        return goodsFacadeService.findAllGoods();
    }

    /**
     * Обработчик GET запроса для получения информации о товаре по его {@code id}
     *
     * @param id идентификатор товара {@link UUID}
     * @return DTO {@link GoodsDTO}, содержащий информацию о товаре
     */
    @GetMapping("/{id}")
    public GoodsDTO getGoods(@PathVariable UUID id) {
        return goodsFacadeService.findGoodsById(id);
    }

    /**
     * Обработчик GET запроса для получения товаров по наименованию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} список, содержащий товары {@link GoodsDTO} с наименованием {@code name}
     */
    @GetMapping(value = "/name", params = "name")
    public List<GoodsDTO> getGoodsByName(@RequestParam String name) {
        return goodsFacadeService.findAllGoodsByName(name);
    }

    /**
     * Обработчик POST запроса для добавления нового товара
     *
     * @param goodsCreationDTO DTO {@link GoodsCreationDTO}, содержащая информацию для добавления нового товара
     * @return DTO {@link GoodsDTO}, содержащий информацию о новом товаре
     */
    @PostMapping
    public GoodsDTO addNewGoods(@RequestBody GoodsCreationDTO goodsCreationDTO) {
        return goodsFacadeService.addNewGoods(goodsCreationDTO);
    }

    /**
     * Обработчик POST запроса для добавления товара в корзину покупателя
     *
     * @param goodsId идентификатор товара {@link UUID}
     * @param customerId идентификатор покупателя {@link UUID}
     * @return {@link List} список товаров {@link GoodsDTO} в корзине покупателя
     */
    @PostMapping(value = "/{goodsId}", params = "customerId")
    public List<GoodsDTO> addGoodsToCustomerCart(@PathVariable UUID goodsId, @RequestParam UUID customerId) {
        return goodsFacadeService.addGoodsToCustomerCart(goodsId, customerId);
    }

    /**
     * Обработчик PUT запроса для обновления информации о товаре
     *
     * @param id идентификатор товара {@link UUID}
     * @param goodsDTO DTO {@link CustomerDTO}, содержащий новую информацию о товаре
     * @return DTO {@link CustomerDTO} с обновленной информацией о товаре
     */
    @PutMapping("/{id}")
    public GoodsDTO updateGoods(@PathVariable UUID id, @RequestBody GoodsDTO goodsDTO) {
        return goodsFacadeService.updateGoods(id, goodsDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления товара
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link String} с информацией об успешном удалении
     */
    @DeleteMapping("/{id}")
    public String deleteGoods(@PathVariable UUID id) {
        goodsFacadeService.deleteGoodsById(id);
        return "Товар с ID: " + id + " удалён";
    }

    /**
     * Обработчик DELETE запроса для удаления товара из корзины покупателя
     *
     * @param goodsId идентификатор товара {@link UUID}
     * @param customerId идентификатор покупателя {@link UUID}
     * @return {@link String} с информацией об успешном удалении
     */
    @DeleteMapping(value = "/{goodsId}", params = "customerId")
    public String removeGoodsFromCustomerCart(@PathVariable UUID goodsId, @RequestParam UUID customerId) {
        goodsFacadeService.removeGoodsFromCustomerCart(goodsId, customerId);
        return "Товар с ID: " + goodsId + " удалён из корзины покупателя с ID: " + customerId;
    }

}
