package com.online.shop.service;

import com.online.shop.dao.GoodsRepository;
import com.online.shop.entity.Goods;
import com.online.shop.exception.NotFoundEntityException;
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
     * {@link NotFoundEntityException}, если такого товара нет
     */
    @Override
    public Goods findById(UUID id) {
        return goodsRepository.findGoodsById(id)
                .orElseThrow(() -> new NotFoundEntityException(
                        new StringBuilder("Товар с ID: ")
                                .append(id)
                                .append(" не найден")));
    }

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link Goods}, или выбрасывается исключение
     * {@link NotFoundEntityException}, если товаров ещё нет
     */
    @Override
    public List<Goods> findAll() {
        List<Goods> goods = goodsRepository.findAllGoods();
        if (goods.isEmpty()) {
            throw new NotFoundEntityException(
                    new StringBuilder("Ни один товар не найден в БД"));
        }
        return goods;
    }

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров {@link Goods} по указанному наименованию {@code name}, или выбрасывается
     * исключение {@link NotFoundEntityException}, если таких товаров нет
     */
    @Override
    public List<Goods> findAllGoodsByName(String name) {
        List<Goods> goods = goodsRepository.findAllGoodsByName(name);
        if (goods.isEmpty()) {
            throw new NotFoundEntityException(new StringBuilder("Ни один товар не найден по наименованию ")
                    .append(name));
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
        goodsRepository.save(goods);
    }

    /**
     * Удаление товара по id
     * <p> Может выбросить исключение {@link NotFoundEntityException}, если товар {@link Goods} не был удалён
     *
     * @param id идентификатор товара {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (goodsRepository.deleteGoodsById(id) == 0) {
            throw new NotFoundEntityException(
                    new StringBuilder("Товар с ID: ")
                            .append(id)
                            .append(" не найден или не может быть удалён"));
        }
    }
}
