package com.online.shop.dao;

import com.online.shop.entity.GoodsCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Long> {

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор заказа {@link int}
     * @return {@link GoodsCategory} - категория товаров по указанному {@code id}, или {@code null},
     * если такой категории нет
     */
    @Query(value = "SELECT gc FROM GoodsCategory gc WHERE gc.id = id")
    GoodsCategory findGoodsCategoryById(@Param("id") int id);

    /**
     * Выборка всех категорий товаров с сортировкой
     *
     * @param sort критерий сортировки {@link Sort}
     * @return {@link List} - список всех категорий товаров, или {@code null}, если категорий еще нет
     */
    @Query(value = "SELECT gс FROM GoodsCategory gс")
    List<GoodsCategory> findAllGoodsCategory(Sort sort);

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
     * @param id идентификатор категории товара {@link int}
     */
    @Query(value = "DELETE gc FROM GoodsCategory gc WHERE gc.id = :id")
    void deleteGoodsCategoryById(@Param("id") int id);

}
