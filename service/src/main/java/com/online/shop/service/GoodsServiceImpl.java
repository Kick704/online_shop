package com.online.shop.service;

import com.online.shop.dao.GoodsRepository;
import com.online.shop.entity.Goods;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления сущностью {@link Goods}
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link Goods} - товар по указанному {@code id}
     */
    @Override
    public Goods findById(UUID id) {
        return goodsRepository.findGoodsById(id)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Товар с ID %s не найден", id))
                );
    }

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link Goods}
     */
    @Override
    public List<Goods> findAll() {
        List<Goods> goods = goodsRepository.findAllGoods();
        if (goods.isEmpty()) {
            throw new CommonRuntimeException(ErrorCode.ENTITY_NOT_FOUND, "Ни один товар не найден в БД");
        }
        return goods;
    }

    /**
     * Выборка товаров по названию
     *
     * @param name название товара {@link String}
     * @return {@link List} - список товаров {@link Goods} по указанному названию {@code name}
     */
    @Override
    public List<Goods> findAllByName(String name) {
        List<Goods> goods = goodsRepository.findAllGoodsByName(name);
        if (goods.isEmpty()) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Ни один товар не найден по названию '%s'", name)
            );
        }
        return goods;
    }

    /**
     * Добавление/обновление товара в БД
     *
     * @param goods сущность Товар {@link Goods}
     */
    @Override
    public void save(Goods goods) {
        if (goods == null) {
            throw new CommonRuntimeException(
                    ErrorCode.OBJECT_REFERENCE_IS_NULL,
                    "Goods: предан пустой объект для сохранения"
            );
        }
        goodsRepository.save(goods);
    }

    /**
     * Удаление товара по id
     *
     * @param id идентификатор товара {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (goodsRepository.deleteGoodsById(id) == 0) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Товар с ID %s не найден или не может быть удалён", id)
            );
        }
    }
}
