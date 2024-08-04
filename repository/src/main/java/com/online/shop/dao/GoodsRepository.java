package com.online.shop.dao;

import com.online.shop.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, UUID> {

    /**
     * Выборка товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return {@link Optional} - контейнер, содержащий товар {@link Goods} по указанному {@code id}
     */
    @Query(value = "SELECT g FROM Goods g JOIN FETCH g.goodsCategory WHERE g.id = :id")
    Optional<Goods> findGoodsById(@Param("id") UUID id);

    /**
     * Выборка всех товаров
     *
     * @return {@link List} - список всех товаров {@link Goods}
     */
    @Query(value = "SELECT g FROM Goods g JOIN FETCH g.goodsCategory")
    List<Goods> findAllGoods();

    /**
     * Выборка товаров по названию
     *
     * @param name наименование товара {@link String}
     * @return {@link List} - список товаров {@link Goods} по указанному наименованию {@code name}
     */
    @Query(value = "SELECT g FROM Goods g JOIN FETCH g.goodsCategory WHERE g.name = :name")
    List<Goods> findAllGoodsByName(@Param("name") String name);

    /**
     * Удаление товара по id
     *
     * @param id идентификатор товара {@link UUID}
     * @return количество удалённых строк {@link int}
     */
    @Modifying
    @Query(value = "DELETE FROM Goods g WHERE g.id = :id")
    int deleteGoodsById(@Param("id") UUID id);

}
