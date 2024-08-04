package com.online.shop.service;

import com.online.shop.dao.GoodsRepository;
import com.online.shop.entity.Customer;
import com.online.shop.entity.Goods;
import com.online.shop.exception.NoEntitiesFoundException;
import com.online.shop.exception.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link Goods} - товар по указанному {@code id}, или выбрасывается исключение
     * {@link NoSuchEntityException}, если такого товара нет
     */
    @Override
    public Goods findGoodsById(UUID id) {
        return goodsRepository.findGoodsById(id)
                .orElseThrow(() -> new NoSuchEntityException("Товар с ID: " + id + " не найден"));
    }

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link Goods}, или выбрасывается исключение
     * {@link NoEntitiesFoundException}, если товаров ещё нет
     */
    @Override
    public List<Goods> findAllGoods() {
        List<Goods> goods = goodsRepository.findAllGoods();
        if (goods.isEmpty()) {
            throw new NoEntitiesFoundException("Ни один товар не найден в БД");
        }
        return goods;
    }

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров {@link Goods} по указанному наименованию {@code name}, или выбрасывается
     * исключение {@link NoEntitiesFoundException}, если таких товаров нет
     */
    @Override
    public List<Goods> findAllGoodsByName(String name) {
        List<Goods> goods = goodsRepository.findAllGoodsByName(name);
        if (goods.isEmpty()) {
            throw new NoEntitiesFoundException("Ни один товар не найден по наименованию " + name);
        }
        return goods;
    }

    /**
     * Добавление/обновление товара в БД
     *
     * @param goods сущность Товар {@link Goods}
     */
    @Override
    public void saveGoods(Goods goods) {
        goodsRepository.save(goods);
    }

    /**
     * Удаление товара по id
     * <p> Может выбросить исключение {@link NoSuchEntityException}, если товар {@link Goods} не был удалён
     *
     * @param id идентификатор товара {@link UUID}
     */
    @Override
    public void deleteGoodsById(UUID id) {
        if (goodsRepository.deleteGoodsById(id) == 0) {
            throw new NoSuchEntityException("Товар с ID: " + id + " не найден и/или не может быть удалён");
        }
    }
}
