package com.online.shop.service;

import com.online.shop.dto.request.creation.GoodsCreationDTO;
import com.online.shop.dto.request.update.GoodsUpdateDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.entity.Goods;
import com.online.shop.entity.GoodsCategory;
import com.online.shop.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Фасад-сервис для управления DTO на основе сущности {@link Goods}
 */
@Service
public class GoodsFacadeServiceImpl implements GoodsFacadeService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsCategoryService categoryService;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link GoodsResponseDTO} - товар по указанному {@code id}
     */
    @Override
    @Transactional(readOnly = true)
    public GoodsResponseDTO findById(UUID id) {
        return goodsMapper.toDTO(goodsService.findById(id));
    }

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link GoodsResponseDTO}
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsResponseDTO> findAll() {
        return goodsMapper.toDTOList(goodsService.findAll());
    }

    /**
     * Выборка товаров по названию
     *
     * @param name название товара {@link String}
     * @return {@link List} - список товаров {@link GoodsResponseDTO} по указанному названию {@code name}
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsResponseDTO> findAllByName(String name) {
        return goodsMapper.toDTOList(goodsService.findAllByName(name));
    }

    /**
     * Добавление нового товара
     *
     * @param goodsCreationDTO DTO новый Товар {@link GoodsCreationDTO}
     * @return DTO Товар {@link GoodsResponseDTO}
     */
    @Override
    @Transactional
    public GoodsResponseDTO addNew(GoodsCreationDTO goodsCreationDTO) {
        GoodsCategory category = categoryService.findById(goodsCreationDTO.getGoodsCategoryId());
        Goods newGoods = goodsMapper.toEntity(goodsCreationDTO);
        newGoods.setGoodsCategory(category);
        goodsService.save(newGoods);
        return goodsMapper.toDTO(newGoods);
    }

    /**
     * Обновление существующего товара
     *
     * @param id идентификатор товара {@link UUID}
     * @param goodsUpdateDTO DTO Товар {@link GoodsUpdateDTO} с изменёнными полями
     * @return обновлённый DTO Товар {@link GoodsResponseDTO}
     */
    @Override
    @Transactional
    public GoodsResponseDTO update(UUID id, GoodsUpdateDTO goodsUpdateDTO) {
        Goods goods = goodsService.findById(id);
        goodsMapper.updateEntityFromDto(goodsUpdateDTO, goods);
        goodsService.save(goods);
        return goodsMapper.toDTO(goods);
    }

    /**
     * Удаление товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @Override
    @Transactional
    public InformationDTO deleteById(UUID id) {
        goodsService.deleteById(id);
        return new InformationDTO(String.format("Товар с ID %s удалён", id));
    }

}
