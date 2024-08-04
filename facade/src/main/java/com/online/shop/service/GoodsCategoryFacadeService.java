package com.online.shop.service;

import com.online.shop.dto.GoodsCategoryDTO;
import com.online.shop.entity.GoodsCategory;

import java.util.List;
import java.util.UUID;

public interface GoodsCategoryFacadeService {

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор категории товара {@link UUID}
     * @return {@link GoodsCategoryDTO} - категория товаров по указанному {@code id}
     */
    GoodsCategoryDTO findGoodsCategoryById(UUID id);

    /**
     * Выборка всех категорий товаров
     *
     * @return {@link List} - список всех категорий товаров {@link GoodsCategoryDTO}
     */
    List<GoodsCategoryDTO> findAllGoodsCategory();

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategoryDTO} - категория товаров по указанному наименованию {@code name}
     */
    GoodsCategoryDTO findGoodsCategoryByName(String name);

    /**
     * Добавление новой категории товаров в БД
     *
     * @param category сущность Категория товаров {@link GoodsCategory}
     * @return DTO Категория товаров {@link GoodsCategoryDTO}
     */
    GoodsCategoryDTO addNewGoodsCategory(GoodsCategory category);

    /**
     * Обновление категории товаров в БД
     *
     * @param id          идентификатор категории товаров {@link UUID}
     * @param categoryDTO DTO Категория товаров {@link GoodsCategoryDTO} с изменёнными полями
     * @return обновлённый DTO Категория товаров {@link GoodsCategoryDTO}
     */
    GoodsCategoryDTO updateGoodsCategory(UUID id, GoodsCategoryDTO categoryDTO);

    /**
     * Удаление категории товара по id
     * @param id идентификатор категории товара {@link UUID}
     */
    void deleteGoodsCategoryById(UUID id);

}
