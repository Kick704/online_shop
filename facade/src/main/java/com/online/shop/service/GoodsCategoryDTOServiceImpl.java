package com.online.shop.service;

import com.online.shop.dto.GoodsCategoryDTO;
import com.online.shop.mapper.GoodsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GoodsCategoryDTOServiceImpl implements GoodsCategoryDTOService {

    @Autowired
    private GoodsCategoryService categoryService;

    @Autowired
    private GoodsCategoryMapper categoryMapper;

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link GoodsCategoryDTO} - категория товаров по указанному {@code id}, или {@code null},
     * если такой категории нет
     */
    @Transactional(readOnly = true)
    @Override
    public GoodsCategoryDTO findGoodsCategoryById(UUID id) {
        return categoryMapper.toGoodsCategoryDTO(categoryService.findGoodsCategoryById(id));
    }

    /**
     * Выборка всех категорий товаров
     *
     * @return {@link List} - список всех категорий товаров, или {@code null}, если категорий еще нет
     */
    @Transactional(readOnly = true)
    @Override
    public List<GoodsCategoryDTO> findAllGoodsCategory() {
        return categoryMapper.toGoodsCategoryDTOList(categoryService.findAllGoodsCategory());
    }

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategoryDTO} - категория товаров по указанному наименованию, или {@code null},
     * если такой категории нет
     */
    @Transactional(readOnly = true)
    @Override
    public GoodsCategoryDTO findGoodsCategoryByName(String name) {
        return categoryMapper.toGoodsCategoryDTO(categoryService.findGoodsCategoryByName(name));
    }

    /**
     * Удаление категории товара по id
     *
     * @param id идентификатор категории товара {@link UUID}
     */
    @Transactional
    @Override
    public void deleteGoodsCategoryById(UUID id) {
        categoryService.deleteGoodsCategoryById(id);
    }

}
