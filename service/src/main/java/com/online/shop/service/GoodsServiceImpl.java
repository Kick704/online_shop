package com.online.shop.service;

import com.online.shop.dao.GoodsRepository;
import com.online.shop.entity.Goods;
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
     * @return {@link Goods} - товар по указанному {@code id}, или {@code null}, если такого товара нет
     */
    @Override
    public Goods findGoodsById(UUID id) {
        return goodsRepository.findGoodsById(id);
    }

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров, или {@code null}, если товаров еще нет
     */
    @Override
    public List<Goods> findAllGoods() {
        return goodsRepository.findAllGoods();
    }

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров по указанному наименованию, или {@code null}, если такого товара нет
     */
    @Override
    public List<Goods> findAllGoodsByName(String name) {
        return goodsRepository.findAllGoodsByName(name);
    }

    /**
     * Удаление товара по id
     *
     * @param id идентификатор товара {@link UUID}
     */
    @Override
    public void deleteGoodsById(UUID id) {
        goodsRepository.deleteGoodsById(id);
    }
}
