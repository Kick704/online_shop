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
 * Реализация интерфейса для управления сущностью {@link Goods} на сервисном слое
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link Goods} - товар по указанному {@code id}, или выбрасывается исключение
     * {@link CommonRuntimeException}, если такого товара нет
     */
    @Override
    public Goods findById(UUID id) {
        return goodsRepository.findGoodsById(id)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Товар с ID: %s не найден", id))
                );
    }

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link Goods}, или выбрасывается исключение
     * {@link CommonRuntimeException}, если товаров ещё нет
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
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров {@link Goods} по указанному наименованию {@code name}, или выбрасывается
     * исключение {@link CommonRuntimeException}, если таких товаров нет
     */
    @Override
    public List<Goods> findAllGoodsByName(String name) {
        List<Goods> goods = goodsRepository.findAllGoodsByName(name);
        if (goods.isEmpty()) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Ни один товар не найден по наименованию %s", name)
            );
        }
        return goods;
    }

    /**
     * Добавление/обновление товара в БД
     * <p> Может выбросить исключение {@link CommonRuntimeException}, если сущность ссылается на null
     *
     * @param goods сущность Товар {@link Goods}
     */
    @Override
    public void save(Goods goods) {
        if (goods == null) {
            throw new CommonRuntimeException(
                    ErrorCode.OBJECT_REFERENCE_IS_NULL,
                    "Сущность Goods не проинициализирована перед сохранением"
            );
        }
        goodsRepository.save(goods);
    }

    /**
     * Удаление товара по id
     * <p> Может выбросить исключение {@link CommonRuntimeException}, если товар {@link Goods} не был удалён
     *
     * @param id идентификатор товара {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (goodsRepository.deleteGoodsById(id) == 0) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Товар с ID: %s не найден или не может быть удалён", id)
            );
        }
    }
}
