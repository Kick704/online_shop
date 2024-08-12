package com.online.shop.service;

import com.online.shop.dao.GoodsCategoryRepository;
import com.online.shop.entity.GoodsCategory;
import com.online.shop.exception.NotFoundEntityException;
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
     * @return {@link GoodsCategory} - категория товаров по указанному {@code id}, или выбрасывается исключение
     * {@link NotFoundEntityException}, если такой категории нет
     */
    @Override
    public GoodsCategory findGoodsCategoryById(UUID id) {
        return categoryRepository.findGoodsCategoryById(id)
                .orElseThrow(() -> new NotFoundEntityException("Категория товаров с ID: " + id + " не найдена"));
    }

    /**
     * Выборка всех категорий товаров
     *
     * @return {@link List} - список всех категорий товаров {@link GoodsCategory}, или выбрасывается исключение
     * {@link NotFoundEntityException}, если категорий ещё нет
     */
    @Override
    public List<GoodsCategory> findAllGoodsCategory() {
        List<GoodsCategory> categories = categoryRepository.findAllGoodsCategory();
        if (categories.isEmpty()) {
            throw new NotFoundEntityException("Ни одна категория товаров не найдена в БД");
        }
        return categories;
    }

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategory} - категория товаров по указанному наименованию {@code name}, или выбрасывается
     * исключение {@link NotFoundEntityException}, если такой категории нет
     */
    @Override
    public GoodsCategory findGoodsCategoryByName(String name) {
        return categoryRepository.findGoodsCategoryByName(name)
                .orElseThrow(() ->
                        new NotFoundEntityException("Категория товаров с наименованием: " + name + " не найдена"));
    }

    /**
     * Добавление/обновление категории товаров в БД
     *
     * @param category сущность Категория товаров {@link GoodsCategory}
     */
    @Override
    public void saveGoodsCategory(GoodsCategory category) {
        categoryRepository.save(category);
    }

    /**
     * Удаление категории товара по id
     * <p> Может выбросить исключение {@link NotFoundEntityException}, если категория товаров {@link GoodsCategory}
     * не была удалёна
     *
     * @param id идентификатор категории товара {@link UUID}
     */
    @Override
    public void deleteGoodsCategoryById(UUID id) {
        if (categoryRepository.deleteGoodsCategoryById(id) == 0) {
            throw new NotFoundEntityException("Категория товаров с ID: " + id + " не найдена и/или не может быть удалена");
        }
    }
}
