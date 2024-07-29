package com.online.shop.service;

import com.online.shop.dto.GoodsDTO;
import com.online.shop.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GoodsDTOServiceImpl implements GoodsDTOService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link GoodsDTO} - товар по указанному {@code id}, или {@code null}, если такого товара нет
     */
    @Transactional(readOnly = true)
    @Override
    public GoodsDTO findGoodsById(UUID id) {
        return goodsMapper.toGoodsDTO(goodsService.findGoodsById(id));
    }

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров, или {@code null}, если товаров еще нет
     */
    @Transactional(readOnly = true)
    @Override
    public List<GoodsDTO> findAllGoods() {
        return goodsMapper.toGoodsDTOList(goodsService.findAllGoods());
    }

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров по указанному наименованию, или {@code null}, если такого товара нет
     */
    @Transactional(readOnly = true)
    @Override
    public List<GoodsDTO> findAllGoodsByName(String name) {
        return goodsMapper.toGoodsDTOList(goodsService.findAllGoodsByName(name));
    }

    /**
     * Удаление товара по id
     *
     * @param id идентификатор товара {@link UUID}
     */
    @Transactional
    @Override
    public void deleteGoodsById(UUID id) {
        goodsService.deleteGoodsById(id);
    }

}
