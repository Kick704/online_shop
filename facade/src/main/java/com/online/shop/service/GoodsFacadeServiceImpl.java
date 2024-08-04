package com.online.shop.service;

import com.online.shop.dto.GoodsDTO;
import com.online.shop.entity.Customer;
import com.online.shop.entity.Goods;
import com.online.shop.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GoodsFacadeServiceImpl implements GoodsFacadeService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link GoodsDTO} - товар по указанному {@code id}
     */
    @Override
    @Transactional(readOnly = true)
    public GoodsDTO findGoodsById(UUID id) {
        return goodsMapper.toDTO(goodsService.findGoodsById(id));
    }

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link GoodsDTO}
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsDTO> findAllGoods() {
        return goodsMapper.toDTOList(goodsService.findAllGoods());
    }

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров {@link GoodsDTO} по указанному наименованию {@code name}
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsDTO> findAllGoodsByName(String name) {
        return goodsMapper.toDTOList(goodsService.findAllGoodsByName(name));
    }

    /**
     * Добавление нового товара в БД
     *
     * @param goods сущность Товар {@link Goods}
     * @return DTO Товар {@link GoodsDTO}
     */
    @Override
    @Transactional
    public GoodsDTO addNewGoods(Goods goods) {
        Goods newGoods = Goods.Builder
                .newBuilder()
                .name(goods.getName())
                .goodsCategory(goods.getGoodsCategory())
                .price(goods.getPrice())
                .count(goods.getCount())
                .discount(goods.getDiscount())
                .build();
        goodsService.saveGoods(newGoods);
        return goodsMapper.toDTO(newGoods);
    }

    /**
     * Обновление товара в БД
     *
     * @param id       идентификатор товара {@link UUID}
     * @param goodsDTO DTO Товар {@link GoodsDTO} с изменёнными полями
     * @return обновлённый DTO Товар {@link GoodsDTO}
     */
    @Override
    @Transactional
    public GoodsDTO updateGoods(UUID id, GoodsDTO goodsDTO) {
        Goods goods = goodsService.findGoodsById(id);
        goodsMapper.updateEntityFromDto(goodsDTO, goods);
        goodsService.saveGoods(goods);
        return goodsMapper.toDTO(goods);
    }

    /**
     * Добавление товара в корзину покупателя
     *
     * @param goodsId    идентификатор товара {@link UUID}
     * @param customerId идентификатор покупателя {@link UUID}
     * @return {@link List} - список товаров {@link GoodsDTO} в корзине покупателя
     */
    @Override
    @Transactional
    public List<GoodsDTO> addGoodsToCustomerCart(UUID goodsId, UUID customerId) {
        Goods goods = goodsService.findGoodsById(goodsId);
        Customer customer = customerService.findCustomerById(customerId);
        customer.getGoodsInCart().add(goods);
        customerService.saveCustomer(customer);
        return goodsMapper.toDTOList(customer.getGoodsInCart());
    }

    /**
     * Удаление товара по id
     *
     * @param id идентификатор товара {@link UUID}
     */
    @Override
    @Transactional
    public void deleteGoodsById(UUID id) {
        goodsService.deleteGoodsById(id);
    }

    /**
     * Удаление товара из корзины покупателя
     *
     * @param goodsId    идентификатор товара {@link UUID}
     * @param customerId идентификатор покупателя {@link UUID}
     */
    @Override
    public void removeGoodsFromCustomerCart(UUID goodsId, UUID customerId) {
        Goods goods = goodsService.findGoodsById(goodsId);
        Customer customer = customerService.findCustomerById(customerId);
        if (customer.getGoodsInCart().isEmpty()) {
            throw new IllegalArgumentException("Корзина покупателя с ID: " + goodsId + " пуста");
        }
        if (!customer.getGoodsInCart().remove(goods)) {
            throw new IllegalStateException("Не удалось удалить товар с ID: " + goodsId +
                    " из корзины покупателя с ID: ");
        }
        customerService.saveCustomer(customer);
        // Пока нет кастомных исключений и, возможно, необходимо перенести всю логику в сервисы и не создавать новую в
        // фасад-сервисах
    }
}
