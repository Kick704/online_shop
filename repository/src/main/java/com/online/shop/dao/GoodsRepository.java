package com.online.shop.dao;

import com.online.shop.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, UUID> {

    /**
     * Выборка товара по id
     * @param id идентификатор товара {@link UUID}
     * @return {@link Goods} - товар по указанному {@code id}, или {@code null}, если такого товара нет
     */
    @Query(value = "SELECT g FROM Goods g WHERE g.id = :id")
    Goods findGoodsById(@Param("id") UUID id);

    /**
     * Выборка всех товаров
     * @return {@link List} - список всех товаров, или {@code null}, если товаров еще нет
     */
    @Query(value = "SELECT g FROM Goods g")
    List<Goods> findAllGoods();

    /**
     * Выборка товаров по названию
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров по указанному наименованию, или {@code null}, если такого товара нет
     */
    @Query(value = "SELECT g FROM Goods g WHERE g.name = :name")
    List<Goods> findAllGoodsByName(@Param("name") String name);

    /**
     * Удаление товара по id
     * @param id идентификатор товара {@link UUID}
     */
    @Modifying
    @Query(value = "DELETE FROM Goods g WHERE g.id = :id")
    void deleteGoodsById(@Param("id") UUID id);

}
