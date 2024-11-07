package com.online.shop.dao;

import com.online.shop.entity.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для управления сущностью {@link GoodsCategory} между приложением и БД
 */
@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, UUID> {

    /**
     * Выборка категории товаров по id
     *
     * @param id идентификатор заказа {@link UUID}
     * @return {@link Optional} - контейнер, содержащий категорию товаров {@link GoodsCategory} по указанному {@code id}
     */
    @Query(value = "SELECT gc FROM GoodsCategory gc WHERE gc.id = :id")
    Optional<GoodsCategory> findGoodsCategoryById(@Param("id") UUID id);

    /**
     * Выборка всех категорий товаров
     *
     * @return {@link List} - список всех категорий товаров {@link GoodsCategory}
     */
    @Query(value = "SELECT gс FROM GoodsCategory gс")
    List<GoodsCategory> findAllGoodsCategories();

    /**
     * Выборка категории товаров по названию
     *
     * @param name название категории товаров {@link String}
     * @return {@link Optional} - контейнер, содержащий категорию товаров {@link GoodsCategory} по указанному
     * названию {@code name}
     */
    @Query(value = "SELECT gc FROM GoodsCategory gc WHERE gc.name = :name")
    Optional<GoodsCategory> findGoodsCategoryByName(@Param("name") String name);

    /**
     * Удаление категории товара по id
     *
     * @param id идентификатор категории товара {@link UUID}
     * @return количество удалённых строк {@link int}
     */
    @Modifying
    @Query(value = "DELETE FROM GoodsCategory gc WHERE gc.id = :id")
    int deleteGoodsCategoryById(@Param("id") UUID id);

    /**
     * Проверка существования категории товаров в БД с указанным названием
     *
     * @param name название категории товаров
     * @return результат проверки
     */
    boolean existsByName(String name);

}
