package com.online.shop.service;

import com.online.shop.dao.GoodsCategoryRepository;
import com.online.shop.entity.GoodsCategory;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления сущностью {@link GoodsCategory}
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryRepository categoryRepository;

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link GoodsCategory} - категория товаров по указанному {@code id}
     */
    @Override
    public GoodsCategory findById(UUID id) {
        return categoryRepository.findGoodsCategoryById(id)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Категория товаров с ID %s не найдена", id))
                );
    }

    /**
     * Выборка всех категорий товаров
     *
     * @return {@link List} - список всех категорий товаров {@link GoodsCategory}
     */
    @Override
    public List<GoodsCategory> findAll() {
        List<GoodsCategory> categories = categoryRepository.findAllGoodsCategories();
        if (categories.isEmpty()) {
            throw new CommonRuntimeException(ErrorCode.ENTITY_NOT_FOUND, "Ни одна категория товаров не найдена в БД");
        }
        return categories;
    }

    /**
     * Выборка категории товаров по названию
     *
     * @param name название категории товаров {@link String}
     * @return {@link GoodsCategory} - категория товаров по указанному названию {@code name}
     */
    @Override
    public GoodsCategory findByName(String name) {
        return categoryRepository.findGoodsCategoryByName(name)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Категория товаров с названием '%s' не найдена", name))
                );
    }

    /**
     * Добавление/обновление категории товаров в БД
     *
     * @param category сущность Категория товаров {@link GoodsCategory}
     */
    @Override
    public void save(GoodsCategory category) {
        if (category == null) {
            throw new CommonRuntimeException(
                    ErrorCode.OBJECT_REFERENCE_IS_NULL,
                    "GoodsCategory: предан пустой объект для сохранения"
            );
        }
        categoryRepository.save(category);
    }

    /**
     * Удаление категории товара по id
     *
     * @param id идентификатор категории товара {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (categoryRepository.deleteGoodsCategoryById(id) == 0) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Категория товаров с ID %s не найдена или не может быть удалена", id)
            );
        }
    }

    /**
     * Проверка названия категории товаров на уникальность в БД
     *
     * @param name название категории товаров
     */
    @Override
    public void validateNameUniqueness(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new CommonRuntimeException(
                    ErrorCode.UNIQUE_CONSTRAINT_VIOLATION,
                    String.format("Категория товаров с названием '%s' уже существует", name)
            );
        }
    }

}
