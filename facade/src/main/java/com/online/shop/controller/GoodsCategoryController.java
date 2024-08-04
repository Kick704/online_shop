package com.online.shop.controller;

import com.online.shop.dto.GoodsCategoryDTO;
import com.online.shop.entity.GoodsCategory;
import com.online.shop.service.GoodsCategoryFacadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/goods-categories")
@Tag(name = "Категории товаров", description = "Управление с категориями товаров интернет-магазина")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryFacadeService categoryFacadeService;

    /**
     * Обработчик GET запроса для получения списка категорий товаров
     *
     * @return {@link List} список категорий товаров {@link GoodsCategoryDTO}
     */
    @GetMapping
    public List<GoodsCategoryDTO> getAllGoodsCategories() {
        return categoryFacadeService.findAllGoodsCategory();
    }

    /**
     * Обработчик GET запроса для получения информации о категории товаров по его {@code id}
     *
     * @param id идентификатор категории товаров {@link UUID}
     * @return DTO {@link GoodsCategoryDTO}, содержащий информацию о категории товаров
     */
    @GetMapping("/{id}")
    public GoodsCategoryDTO getGoodsCategory(@PathVariable UUID id) {
        return categoryFacadeService.findGoodsCategoryById(id);
    }

    /**
     * Обработчик GET запроса для получения информации о категории товаров по его наименованию {@code name}
     *
     * @param name наименование категории товаров {@link String}
     * @return DTO {@link GoodsCategoryDTO}, содержащий информацию о категории товаров
     */
    @GetMapping(value = "/name", params = "name")
    public GoodsCategoryDTO getGoodsCategoryByName(@RequestParam String name) {
        return categoryFacadeService.findGoodsCategoryByName(name);
    }

    /**
     * Обработчик POST запроса для создания категории товаров
     *
     * @param category сущность {@link GoodsCategory}, содержащая информацию для создания категории товаров
     * @return DTO {@link GoodsCategoryDTO}, содержащий информацию о категории товаров
     */
    @PostMapping
    public GoodsCategoryDTO addNewGoodsCategory(@RequestBody GoodsCategory category) {
        return categoryFacadeService.addNewGoodsCategory(category);
    }

    /**
     * Обработчик PUT запроса для обновления информации о категории товаров
     *
     * @param id идентификатор категории товара {@link UUID}
     * @param categoryDTO DTO {@link GoodsCategoryDTO}, содержащий новую информацию о категории товаров
     * @return DTO {@link GoodsCategoryDTO} с обновленной информацией о категории товаров
     */
    @PutMapping("/{id}")
    public GoodsCategoryDTO updateGoodsCategory(@PathVariable UUID id, @RequestBody GoodsCategoryDTO categoryDTO) {
        return categoryFacadeService.updateGoodsCategory(id, categoryDTO);
    }

    /**
     * Обработчик DELETE запроса для удаления категории товаров
     *
     * @param id идентификатор категории товаров {@link UUID}
     * @return {@link String} с информацией об успешном удалении
     */
    @DeleteMapping("/{id}")
    public String deleteGoodsCategory(@PathVariable UUID id) {
        categoryFacadeService.deleteGoodsCategoryById(id);
        return "Категория товаров с ID: " + id + " удалена";
    }

}
