package com.online.shop.service;

import com.online.shop.dto.request.GoodsCategoryCreationDTO;
import com.online.shop.dto.request.GoodsCategoryUpdateDTO;
import com.online.shop.dto.response.GoodsCategoryResponseDTO;
import com.online.shop.entity.GoodsCategory;

/**
 * Фасад-сервис слоя представления для управления DTO на основе сущности {@link GoodsCategory}
 */
public interface GoodsCategoryFacadeService
        extends BaseFacadeService<GoodsCategoryCreationDTO, GoodsCategoryUpdateDTO, GoodsCategoryResponseDTO> {

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategoryResponseDTO} - категория товаров по указанному наименованию {@code name}
     */
    GoodsCategoryResponseDTO findGoodsCategoryByName(String name);

}
