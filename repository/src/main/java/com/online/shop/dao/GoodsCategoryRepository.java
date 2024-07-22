package com.online.shop.dao;

import com.online.shop.entity.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, UUID> {

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link GoodsCategory} - категория товаров по указанному {@code id}, или {@code null},
     * если такой категории нет
     */
    @Query(value = "SELECT gc FROM GoodsCategory gc WHERE gc.id = id")
    GoodsCategory findGoodsCategoryById(@Param("id") UUID id);

    /**
     * Выборка всех категорий товаров
     * @return {@link List} - список всех категорий товаров, или {@code null}, если категорий еще нет
     */
    @Query(value = "SELECT gс FROM GoodsCategory gс")
    List<GoodsCategory> findAllGoodsCategory();

    /**
     * Выборка категории товаров по названию
     *
     * @param name наименование категории товаров {@link String}
     * @return {@link GoodsCategory} - категория товаров по указанному наименованию, или {@code null},
     * если такой категории нет
     */
    @Query(value = "SELECT gc FROM GoodsCategory gc WHERE gc.name = :name")
    GoodsCategory findGoodsCategoryByName(@Param("name") String name);

    /**
     * Удаление категории товара по id
     * @param id идентификатор категории товара {@link UUID}
     */
    @Query(value = "DELETE gc FROM GoodsCategory gc WHERE gc.id = :id")
    void deleteGoodsCategoryById(@Param("id") UUID id);

}
