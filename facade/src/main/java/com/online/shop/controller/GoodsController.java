package com.online.shop.controller;

import com.online.shop.dto.request.update.GoodsUpdateDTO;
import com.online.shop.dto.response.UserResponseDTO;
import com.online.shop.dto.request.creation.GoodsCreationDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.service.GoodsFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST-контроллер для управления с товарами интернет-магазина
 */
@RestController
@RequestMapping(ApiPath.API_BASE + "/goods")
@Tag(name = "Товары", description = "Управление товарами интернет-магазина")
public class GoodsController {

    @Autowired
    private GoodsFacadeService goodsFacadeService;

    /**
     * Обработчик GET запроса для получения списка товаров
     *
     * @return {@link List} список товаров {@link GoodsResponseDTO}
     */
    @GetMapping
    @Operation(summary = "Получение всех товаров",
            description = "Позволяет получить список всех товаров интернет-магазина")
    public List<GoodsResponseDTO> getAllGoods() {
        return goodsFacadeService.findAll();
    }

    /**
     * Обработчик GET запроса для получения информации о товаре по его {@code id}
     *
     * @param id идентификатор товара {@link UUID}
     * @return DTO {@link GoodsResponseDTO}, содержащий информацию о товаре
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получение товара по ID", description = "Позволяет получить товар по его ID")
    public GoodsResponseDTO getGoods(@PathVariable UUID id) {
        return goodsFacadeService.findById(id);
    }

    /**
     * Обработчик GET запроса для получения товаров по наименованию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} список, содержащий товары {@link GoodsResponseDTO} с наименованием {@code name}
     */
    @GetMapping(value = "/name", params = "name")
    @Operation(summary = "Получение товара по наименованию",
            description = "Позволяет получить товар по его наименованию")
    public List<GoodsResponseDTO> getGoodsByName(@RequestParam String name) {
        return goodsFacadeService.findAllGoodsByName(name);
    }

    /**
     * Обработчик POST запроса для добавления нового товара
     *
     * @param goodsCreationDTO DTO {@link GoodsCreationDTO}, содержащая информацию для добавления нового товара
     * @return DTO {@link GoodsResponseDTO}, содержащий информацию о новом товаре
     */
    @PostMapping
    @Operation(summary = "Добавление нового товара в интернет-магазин",
            description = "Позволяет добавить новый товар в интернет-магазин")
    public GoodsResponseDTO addNewGoods(@Valid @RequestBody GoodsCreationDTO goodsCreationDTO) {
        return goodsFacadeService.addNew(goodsCreationDTO);
    }

//    /**
//     * Обработчик POST запроса для добавления товара в корзину пользователя
//     *
//     * @param goodsId идентификатор товара {@link UUID}
//     * @param userId идентификатор пользователя {@link UUID}
//     * @return {@link List} список товаров {@link GoodsResponseDTO} в корзине пользователя
//     */
//    @PostMapping(value = "/{goodsId}", params = "userId")
//    @Operation(summary = "Добавление товара в корзину пользователя",
//            description = "Позволяет добавить товар в корзину пользователя")
//    public List<GoodsResponseDTO> addGoodsToUserCart(@PathVariable UUID goodsId, @RequestParam UUID userId) {
//        return goodsFacadeService.addGoodsToUserCart(goodsId, userId);
//    }

    /**
     * Обработчик PUT запроса для обновления информации о товаре
     *
     * @param id             идентификатор товара {@link UUID}
     * @param goodsUpdateDTO DTO {@link GoodsUpdateDTO}, содержащий новую информацию о товаре
     * @return DTO {@link UserResponseDTO} с обновленной информацией о товаре
     */
    @PutMapping("/{id}")
    @Operation(summary = "Изменение информации о товаре",
            description = "Позволяет изменить информацию о товаре")
    public GoodsResponseDTO updateGoods(@PathVariable UUID id, @Valid @RequestBody GoodsUpdateDTO goodsUpdateDTO) {
        return goodsFacadeService.update(id, goodsUpdateDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления товара
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление товара из интернет-магазина",
            description = "Позволяет удалить товар из интернет-магазина по его ID")
    public InformationDTO deleteGoods(@PathVariable UUID id) {
        return goodsFacadeService.deleteById(id);
    }

//    /**
//     * Обработчик DELETE запроса для удаления товара из корзины пользователя
//     *
//     * @param goodsId идентификатор товара {@link UUID}
//     * @param userId идентификатор пользователя {@link UUID}
//     * @return {@link InformationDTO} с сообщением о результате
//     */
//    @DeleteMapping(value = "/{goodsId}", params = "userId")
//    @Operation(summary = "Удаление товара из корзины пользователя",
//            description = "Позволяет удалить товар из корзины пользователя")
//    public InformationDTO removeGoodsFromUserCart(@PathVariable UUID goodsId, @RequestParam UUID userId) {
//        return goodsFacadeService.removeGoodsFromUserCart(goodsId, userId);
//    }

}
