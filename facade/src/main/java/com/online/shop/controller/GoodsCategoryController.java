package com.online.shop.controller;

import com.online.shop.dto.request.creation.GoodsCategoryCreationDTO;
import com.online.shop.dto.request.update.GoodsCategoryUpdateDTO;
import com.online.shop.dto.response.GoodsCategoryResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.service.GoodsCategoryFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST-контроллер для управления с категориями товаров интернет-магазина
 */
@RestController
@RequestMapping(ApiPath.API_BASE + "/goods-category")
@Tag(name = "Категории товаров", description = "Управление категориями товаров интернет-магазина")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryFacadeService categoryFacadeService;

    /**
     * Обработчик GET запроса для получения списка категорий товаров
     *
     * @return {@link List} список категорий товаров {@link GoodsCategoryResponseDTO}
     */
    @GetMapping
    @Operation(summary = "Получение всех категорий товаров",
            description = "Позволяет получить список всех категорий товаров интернет-магазина")
    public List<GoodsCategoryResponseDTO> getAllGoodsCategories() {
        return categoryFacadeService.findAll();
    }

    /**
     * Обработчик GET запроса для получения информации о категории товаров по его {@code id}
     *
     * @param id идентификатор категории товаров {@link UUID}
     * @return DTO {@link GoodsCategoryResponseDTO}, содержащий информацию о категории товаров
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получение категории товаров по ID",
            description = "Позволяет получить категорию товаров по его ID")
    public GoodsCategoryResponseDTO getGoodsCategory(@PathVariable UUID id) {
        return categoryFacadeService.findById(id);
    }

    /**
     * Обработчик GET запроса для получения информации о категории товаров по его названию {@code name}
     *
     * @param name название категории товаров {@link String}
     * @return DTO {@link GoodsCategoryResponseDTO}, содержащий информацию о категории товаров
     */
    @GetMapping(value = "/name", params = "name")
    @Operation(summary = "Получение категории товаров по названию",
            description = "Позволяет получить категорию товаров по его названию")
    public GoodsCategoryResponseDTO getGoodsCategoryByName(@RequestParam String name) {
        return categoryFacadeService.findByName(name);
    }

    /**
     * Обработчик POST запроса для создания категории товаров
     *
     * @param categoryCreationDTO DTO {@link GoodsCategoryCreationDTO}, содержащая информацию для создания
     * категории товаров
     * @return DTO {@link GoodsCategoryResponseDTO}, содержащий информацию о категории товаров
     */
    @PreAuthorize("hasAuthority('CREATE_GOODS_CATEGORY')")
    @PostMapping
    @Operation(summary = "Добавление категории товаров", description = "Позволяет добавить новую категорию товаров")
    public GoodsCategoryResponseDTO addNewGoodsCategory(@Valid @RequestBody GoodsCategoryCreationDTO categoryCreationDTO) {
        return categoryFacadeService.addNew(categoryCreationDTO);
    }

    /**
     * Обработчик PUT запроса для обновления информации о категории товаров
     *
     * @param id идентификатор категории товара {@link UUID}
     * @param categoryUpdateDTO DTO {@link GoodsCategoryUpdateDTO}, содержащий новую информацию о категории товаров
     * @return DTO {@link GoodsCategoryResponseDTO} с обновленной информацией о категории товаров
     */
    @PreAuthorize("hasAuthority('EDIT_GOODS_CATEGORY')")
    @PutMapping("/{id}")
    @Operation(summary = "Изменение информации о категории товаров",
            description = "Позволяет изменить информацию о категории товаров")
    public GoodsCategoryResponseDTO updateGoodsCategory(@PathVariable UUID id,
                                                        @Valid @RequestBody GoodsCategoryUpdateDTO categoryUpdateDTO) {
        return categoryFacadeService.update(id, categoryUpdateDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления категории товаров
     *
     * @param id идентификатор категории товаров {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @PreAuthorize("hasAuthority('DELETE_GOODS_CATEGORY')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление категории товаров", description = "Позволяет удалить категорию товаров по его ID")
    public InformationDTO deleteGoodsCategory(@PathVariable UUID id) {
        return categoryFacadeService.deleteById(id);
    }

}
