package com.online.shop.service;

import com.online.shop.dto.request.creation.GoodsCreationDTO;
import com.online.shop.dto.request.update.GoodsUpdateDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.entity.Goods;

import java.util.List;

/**
 * Фасад-сервис для управления DTO на основе сущности {@link Goods}
 */
public interface GoodsFacadeService extends BaseFacadeService<GoodsCreationDTO, GoodsUpdateDTO, GoodsResponseDTO> {

    /**
     * Выборка товаров по названию
     *
     * @param name название товара {@link String}
     * @return {@link List} - список товаров {@link GoodsResponseDTO} по указанному названию {@code enabled}
     */
    List<GoodsResponseDTO> findAllByName(String name);

}
