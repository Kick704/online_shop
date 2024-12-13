package com.online.shop.controller;

import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.dto.response.SelectedGoodsDTO;
import com.online.shop.service.GoodsFacadeService;
import com.online.shop.service.UserFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;
import java.util.UUID;

/**
 * REST-контроллер для управления корзиной пользователя интернет-магазина
 */
@RestController
@RequestMapping(ApiPath.API_BASE + "/cart")
@Tag(name = "Корзина", description = "Управление корзиной пользователя")
public class CartController {

    @Autowired
    private UserFacadeService userFacadeService;

    @Autowired
    private GoodsFacadeService goodsFacadeService;

    /**
     * Обработчик GET запроса для получения информации о корзине пользователя по его {@code id}
     *
     * @param id идентификатор пользователя {@link UUID}
     * @return {@link Set} список товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @GetMapping("/{id}")
    @Operation(summary = "Получение корзины пользователя",
            description = "Позволяет получить список товаров в корзине по ID пользователя")
    public Set<SelectedGoodsDTO> getUserCart(@PathVariable UUID id) {
        return userFacadeService.findAllGoodsInUserCart(id);
    }

    /**
     * Обработчик GET запроса для получения информации о корзине авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return {@link Set} список товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("/current-user")
    @Operation(summary = "Получение корзины текущего пользователя",
            description = "Позволяет получить список товаров в корзине авторизованного пользователя")
    public Set<SelectedGoodsDTO> getCurrentUserCart(Principal principal) {
        return userFacadeService.findAllGoodsInCurrentUserCart(principal);
    }

    /**
     * Обработчик POST запроса для добавления товара по его id в корзину авторизованного пользователя
     *
     * @param id идентификатор товара {@link UUID}
     * @param quantity количество товара
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return {@link Set} список товаров {@link GoodsResponseDTO} в корзине пользователя
     */
    @PreAuthorize("hasAuthority('ADD_TO_CART')")
    @PostMapping(value = "/add/{id}", params = "quantity")
    @Operation(summary = "Добавление товара в корзину",
            description = "Позволяет текущему пользователю добавить товар в корзину")
    public Set<SelectedGoodsDTO> addGoodsToCurrentUserCart(@PathVariable UUID id,
                                                           @RequestParam int quantity,
                                                           Principal principal) {
        return goodsFacadeService.addToCurrentUserCart(id, quantity, principal);
    }

    /**
     * Обработчик DELETE запроса для очистки корзины авторизованного пользователя
     *
     * @param principal информация об авторизованном пользователе {@link Principal}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @PreAuthorize("hasAuthority('REMOVE_FROM_CART')")
    @DeleteMapping("/current")
    @Operation(summary = "Очистка корзины текущего пользователя",
            description = "Позволяет текущему пользователю очистить корзину от товаров")
    public InformationDTO clearCurrentUserCart(Principal principal) {
        return userFacadeService.clearCurrentUserCart(principal);
    }

}
