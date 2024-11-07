package com.online.shop.service;

import com.online.shop.dto.request.creation.GoodsCategoryCreationDTO;
import com.online.shop.dto.request.update.GoodsCategoryUpdateDTO;
import com.online.shop.dto.response.GoodsCategoryResponseDTO;
import com.online.shop.entity.GoodsCategory;

/**
 * Фасад-сервис для управления DTO на основе сущности {@link GoodsCategory}
 */
public interface GoodsCategoryFacadeService
        extends BaseFacadeService<GoodsCategoryCreationDTO, GoodsCategoryUpdateDTO, GoodsCategoryResponseDTO> {

    /**
     * Выборка категории товаров по названию
     *
     * @param name название категории товаров {@link String}
     * @return {@link GoodsCategoryResponseDTO} - категория товаров по указанному названию {@code name}
     */
    GoodsCategoryResponseDTO findByName(String name);

}
