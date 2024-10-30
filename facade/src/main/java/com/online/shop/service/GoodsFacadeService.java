package com.online.shop.service;

import com.online.shop.dto.request.creation.GoodsCreationDTO;
import com.online.shop.dto.request.update.GoodsUpdateDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.entity.Goods;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

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

    /**
     * Добавление товара по его id в корзину авторизованного пользователя
     *
     * @param id идентификатор товара {@link UUID}
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return {@link InformationDTO} с сообщением о результате
     */
    InformationDTO addToCart(UUID id, int quantity, Principal principal);

    /**
     * Добавление нового товара
     *
     * @param goodsCreationDTO DTO новый Товар {@link GoodsCreationDTO}
     * @return DTO Товар {@link GoodsResponseDTO}
     */
    GoodsResponseDTO addNew(GoodsCreationDTO goodsCreationDTO);

}
