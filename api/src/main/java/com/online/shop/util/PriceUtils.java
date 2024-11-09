package com.online.shop.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Утилитарный класс для работы с ценами на товары
 */
public class PriceUtils {

    private PriceUtils() {
    }

    /**
     * Форматирование стоимости с округлением до двух знаков
     *
     * @param value исходное значение стоимости
     * @return форматированное значение стоимости
     */
    public static double formatPrice(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * Вычисление стоимости товара с учётом установленной скидки
     *
     * @param price стоимость товара
     * @param discount скидка на товар
     * @return стоимость товара с учётом скидки
     */
    public static double getDiscountedPrice(double price, int discount) {
        return price * (1 - discount / 100.0);
    }

}
