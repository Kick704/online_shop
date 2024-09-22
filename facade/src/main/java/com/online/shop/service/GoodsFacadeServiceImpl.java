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
 * Фасад-сервис слоя представления для управления DTO на основе сущности {@link Goods}
 */
@Service
public class GoodsFacadeServiceImpl implements GoodsFacadeService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CustomerService customerService;

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
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров {@link GoodsResponseDTO} по указанному наименованию {@code name}
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsResponseDTO> findAllGoodsByName(String name) {
        return goodsMapper.toDTOList(goodsService.findAllGoodsByName(name));
    }

    /**
     * Добавление нового товара в БД
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
     * Обновление товара в БД
     *
     * @param id             идентификатор товара {@link UUID}
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

//    /**
//     * Добавление товара в корзину покупателя
//     *
//     * @param goodsId    идентификатор товара {@link UUID}
//     * @param customerId идентификатор покупателя {@link UUID}
//     * @return {@link List} - список товаров {@link GoodsResponseDTO} в корзине покупателя
//     */
//    @Override
//    @Transactional
//    public List<GoodsResponseDTO> addGoodsToCustomerCart(UUID goodsId, UUID customerId) {
//        Goods goods = goodsService.findById(goodsId);
//        Customer customer = customerService.findById(customerId);
//        customer.getGoodsInCart().add(goods);
//        customerService.save(customer);
//        return goodsMapper.toDTOList(customer.getGoodsInCart());
//    }

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
        return new InformationDTO(String.format("Товар с ID: %s удалён", id));
    }

//    /**
//     * Удаление товара из корзины покупателя
//     *
//     * @param goodsId    идентификатор товара {@link UUID}
//     * @param customerId идентификатор покупателя {@link UUID}
//     * @return {@link InformationDTO} с сообщением о результате
//     */
//    @Override
//    public InformationDTO removeGoodsFromCustomerCart(UUID goodsId, UUID customerId) {
//
//        ////////////////////////////////f
//        p
//        Goods goods = goodsService.findById(goodsId);
//        Customer customer = customerService.findById(customerId);
//        if (customer.getGoodsInCart().isEmpty()) {
//            throw new IllegalArgumentException("Корзина покупателя с ID: " + goodsId + " пуста");
//        }
//        if (!customer.getGoodsInCart().remove(goods)) {
//            throw new IllegalStateException("Не удалось удалить товар с ID: " + goodsId +
//                    " из корзины покупателя с ID: " + customerId);
//        }
//        customerService.save(customer);
//        return new InformationDTO("Товар с ID: " + goodsId + " удалён из корзины покупателя с ID: " + customerId);
//        // Пока нет кастомных исключений и, возможно, необходимо перенести всю логику в сервисы и не создавать новую в
//        // фасад-сервисах
//    }

}
