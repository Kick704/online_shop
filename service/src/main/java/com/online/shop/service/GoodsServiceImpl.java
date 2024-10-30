package com.online.shop.service;

import com.online.shop.dao.GoodsRepository;
import com.online.shop.entity.Goods;
import com.online.shop.entity.User;
import com.online.shop.exception_handling.CommonRuntimeException;
import com.online.shop.exception_handling.ErrorCode;
import com.online.shop.util.PriceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;

/**
 * Сервис для управления сущностью {@link Goods}
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private UserService userService;

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link Goods} - товар по указанному {@code id}
     */
    @Override
    public Goods findById(UUID id) {
        return goodsRepository.findGoodsById(id)
                .orElseThrow(() -> new CommonRuntimeException(
                        ErrorCode.ENTITY_NOT_FOUND,
                        String.format("Товар с ID %s не найден", id))
                );
    }

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link Goods}
     */
    @Override
    public List<Goods> findAll() {
        List<Goods> goods = goodsRepository.findAllGoods();
        if (goods.isEmpty()) {
            throw new CommonRuntimeException(ErrorCode.ENTITY_NOT_FOUND, "Ни один товар не найден в БД");
        }
        return goods;
    }

    /**
     * Выборка товаров по названию
     *
     * @param name название товара {@link String}
     * @return {@link List} - список товаров {@link Goods} по указанному названию {@code name}
     */
    @Override
    public List<Goods> findAllByName(String name) {
        List<Goods> goods = goodsRepository.findAllGoodsByName(name);
        if (goods.isEmpty()) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_NOT_FOUND,
                    String.format("Ни один товар не найден по названию '%s'", name)
            );
        }
        return goods;
    }

    /**
     * Добавление/обновление товара в БД
     *
     * @param goods сущность Товар {@link Goods}
     */
    @Override
    public void save(Goods goods) {
        if (goods == null) {
            throw new CommonRuntimeException(
                    ErrorCode.OBJECT_REFERENCE_IS_NULL,
                    "Goods: предан пустой объект для сохранения"
            );
        }
        goodsRepository.save(goods);
    }

    /**
     * Добавление товара в корзину текущего покупателя
     *
     * @param id идентификатор товара {@link UUID}
     * @param quantity количество товара для добавления
     * @param principal информация об авторизованном пользователе {@link Principal}
     */
    @Override
    @Transactional
    public void addToCart(UUID id, int quantity, Principal principal) {
        User user = userService.getCurrentUser(principal);
        Goods goods = findById(id);
        int goodsQuantityInCart = getGoodsQuantityInCart(user.getGoodsInCart(), goods);
        if (goods.getCount() < (goodsQuantityInCart + quantity)) {
            throw new CommonRuntimeException(
                    ErrorCode.INSUFFICIENT_STOCK,
                    String.format("Недостаточно товаров на складе: доступно %d, требуется %d",
                            goods.getCount() - goodsQuantityInCart,
                            quantity)
            );
        }
        List<Goods> goodsInCart = user.getGoodsInCart();
        goodsInCart.addAll(Collections.nCopies(quantity, goods));
        userService.update(user);
    }

    /**
     * Получение количества указанного товара в корзине покупателя
     *
     * @param goodsInCart список товаров в корзине {@link List}
     * @param requiredGoods искомый товар для расчета количества {@link Goods}
     * @return количество товара в корзине
     */
    private int getGoodsQuantityInCart(List<Goods> goodsInCart, Goods requiredGoods) {
        return Collections.frequency(goodsInCart, requiredGoods);
    }

    /**
     * Удаление товара по id
     *
     * @param id идентификатор товара {@link UUID}
     */
    @Override
    public void deleteById(UUID id) {
        if (goodsRepository.deleteGoodsById(id) == 0) {
            throw new CommonRuntimeException(
                    ErrorCode.ENTITY_DELETION_FAILED,
                    String.format("Товар с ID %s не найден или не может быть удалён", id)
            );
        }
    }

    /**
     * Получение общей стоимости товаров в корзине покупателя
     *
     * @param goodsList список товаров в корзине {@link List}
     * @return общая стоимость товаров в корзине
     */
    @Override
    public double getCartTotalPrice(List<Goods> goodsList) {
        return PriceUtils.formatPrice(goodsList.stream()
                .mapToDouble(goods -> PriceUtils.getDiscountedPrice(goods.getPrice(), goods.getDiscount()))
                .sum());
    }

    /**
     * Вычитание товаров на складе на основе списка приобретаемых покупателем
     *
     * @param goodsList список товаров для приобретения {@link List}
     */
    @Override
    @Transactional
    public void deductGoodsCount(List<Goods> goodsList) {
        goodsList.forEach(goods -> goods.setCount(goods.getCount() - 1));
        Set<Goods> goodsSet = new HashSet<>(goodsList);
        goodsSet.forEach(this::save);
    }

}
