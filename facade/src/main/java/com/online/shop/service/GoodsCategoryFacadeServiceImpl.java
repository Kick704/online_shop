package com.online.shop.service;

import com.online.shop.dto.request.creation.GoodsCategoryCreationDTO;
import com.online.shop.dto.request.update.GoodsCategoryUpdateDTO;
import com.online.shop.dto.response.GoodsCategoryResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.entity.GoodsCategory;
import com.online.shop.mapper.GoodsCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Фасад-сервис для управления DTO на основе сущности {@link GoodsCategory}
 */
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
     * @return {@link GoodsCategoryResponseDTO} - категория товаров по указанному {@code id}
     */
    @Override
    @Transactional(readOnly = true)
    public GoodsCategoryResponseDTO findById(UUID id) {
        return categoryMapper.toDTO(categoryService.findById(id));
    }

    /**
     * Выборка всех категорий товаров
     *
     * @return {@link List} - список всех категорий товаров {@link GoodsCategoryResponseDTO}
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsCategoryResponseDTO> findAll() {
        return categoryMapper.toDTOList(categoryService.findAll());
    }

    /**
     * Выборка категории товаров по названию
     *
     * @param name название категории товаров {@link String}
     * @return {@link GoodsCategoryResponseDTO} - категория товаров по указанному названию {@code name}
     */
    @Override
    @Transactional(readOnly = true)
    public GoodsCategoryResponseDTO findByName(String name) {
        return categoryMapper.toDTO(categoryService.findByName(name));
    }

    /**
     * Добавление новой категории товаров
     *
     * @param categoryCreationDTO DTO новая Категория товаров {@link GoodsCategoryCreationDTO}
     * @return DTO Категория товаров {@link GoodsCategoryResponseDTO}
     */
    @Override
    @Transactional
    public GoodsCategoryResponseDTO addNew(GoodsCategoryCreationDTO categoryCreationDTO) {
        GoodsCategory newCategory = categoryMapper.toEntity(categoryCreationDTO);
        categoryService.validateNameUniqueness(newCategory.getName());
        categoryService.save(newCategory);
        return categoryMapper.toDTO(newCategory);
    }

    /**
     * Обновление существующей категории товаров в БД
     *
     * @param id идентификатор категории товаров {@link UUID}
     * @param categoryUpdateDTO DTO Категория товаров {@link GoodsCategoryUpdateDTO} с изменёнными полями
     * @return обновлённый DTO Категория товаров {@link GoodsCategoryResponseDTO}
     */
    @Override
    @Transactional
    public GoodsCategoryResponseDTO update(UUID id, GoodsCategoryUpdateDTO categoryUpdateDTO) {
        GoodsCategory category = categoryService.findById(id);
        if (!category.getName().equals(categoryUpdateDTO.getName())) {
            categoryService.validateNameUniqueness(categoryUpdateDTO.getName());
        }
        categoryMapper.updateEntityFromDto(categoryUpdateDTO, category);
        categoryService.save(category);
        return categoryMapper.toDTO(category);
    }

    /**
     * Удаление категории товара по id
     *
     * @param id идентификатор категории товара {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @Override
    @Transactional
    public InformationDTO deleteById(UUID id) {
        categoryService.deleteById(id);
        return new InformationDTO(String.format("Категория товаров с ID %s удалена", id));
    }

}
