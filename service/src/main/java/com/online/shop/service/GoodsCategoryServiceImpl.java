package com.online.shop.service;

import com.online.shop.dao.GoodsCategoryRepository;
import com.online.shop.entity.GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryRepository categoryRepository;

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link GoodsCategory} - категория товаров по указанному {@code id}, или {@code null},
     * если такой категории нет
     */
    @Override
    public GoodsCategory findGoodsCategoryById(UUID id) {
        return categoryRepository.findGoodsCategoryById(id);
    }

    /**
     * Выборка всех категорий товаров
     *
     * @return {@link List} - список всех категорий товаров, или {@code null}, если категорий еще нет
     */
    @Override
    public List<GoodsCategory> findAllGoodsCategory() {
        return categoryRepository.findAllGoodsCategory();
    }

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategory} - категория товаров по указанному наименованию, или {@code null},
     * если такой категории нет
     */
    @Override
    public GoodsCategory findGoodsCategoryByName(String name) {
        return categoryRepository.findGoodsCategoryByName(name);
    }

    /**
     * Удаление категории товара по id
     *
     * @param id идентификатор категории товара {@link UUID}
     */
    @Override
    public void deleteGoodsCategoryById(UUID id) {
        categoryRepository.deleteGoodsCategoryById(id);
    }
}
