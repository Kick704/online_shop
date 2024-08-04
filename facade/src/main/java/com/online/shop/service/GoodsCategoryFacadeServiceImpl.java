package com.online.shop.service;

import com.online.shop.dto.GoodsCategoryDTO;
import com.online.shop.entity.GoodsCategory;
import com.online.shop.mapper.GoodsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GoodsCategoryFacadeServiceImpl implements GoodsCategoryFacadeService {

    @Autowired
    private GoodsCategoryService categoryService;

    @Autowired
    private GoodsCategoryMapper categoryMapper;

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link GoodsCategoryDTO} - категория товаров по указанному {@code id}
     */
    @Override
    @Transactional(readOnly = true)
    public GoodsCategoryDTO findGoodsCategoryById(UUID id) {
        return categoryMapper.toDTO(categoryService.findGoodsCategoryById(id));
    }

    /**
     * Выборка всех категорий товаров
     *
     * @return {@link List} - список всех категорий товаров {@link GoodsCategoryDTO}
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsCategoryDTO> findAllGoodsCategory() {
        return categoryMapper.toDTOList(categoryService.findAllGoodsCategory());
    }

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategoryDTO} - категория товаров по указанному наименованию {@code name}
     */
    @Override
    @Transactional(readOnly = true)
    public GoodsCategoryDTO findGoodsCategoryByName(String name) {
        return categoryMapper.toDTO(categoryService.findGoodsCategoryByName(name));
    }

    /**
     * Добавление новой категории товаров в БД
     *
     * @param category сущность Категория товаров {@link GoodsCategory}
     * @return DTO Категория товаров {@link GoodsCategoryDTO}
     */
    @Override
    @Transactional
    public GoodsCategoryDTO addNewGoodsCategory(GoodsCategory category) {
        GoodsCategory newCategory = GoodsCategory.Builder
                .newBuilder()
                .categoryName(category.getCategoryName())
                .build();
        categoryService.saveGoodsCategory(newCategory);
        return categoryMapper.toDTO(newCategory);
    }

    /**
     * Обновление категории товаров в БД
     *
     * @param id          идентификатор категории товаров {@link UUID}
     * @param categoryDTO DTO Категория товаров {@link GoodsCategoryDTO} с изменёнными полями
     * @return обновлённый DTO Категория товаров {@link GoodsCategoryDTO}
     */
    @Override
    @Transactional
    public GoodsCategoryDTO updateGoodsCategory(UUID id, GoodsCategoryDTO categoryDTO) {
        GoodsCategory category = categoryService.findGoodsCategoryById(id);
        categoryMapper.updateEntityFromDto(categoryDTO, category);
        categoryService.saveGoodsCategory(category);
        return categoryMapper.toDTO(category);
    }

    /**
     * Удаление категории товара по id
     *
     * @param id идентификатор категории товара {@link UUID}
     */
    @Override
    @Transactional
    public void deleteGoodsCategoryById(UUID id) {
        categoryService.deleteGoodsCategoryById(id);
    }

}
