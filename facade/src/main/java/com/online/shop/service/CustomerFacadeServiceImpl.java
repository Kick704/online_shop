package com.online.shop.service;

import com.online.shop.dto.request.CustomerCreationDTO;
import com.online.shop.dto.request.CustomerUpdateDTO;
import com.online.shop.dto.response.CustomerResponseDTO;
import com.online.shop.dto.response.GoodsResponseDTO;
import com.online.shop.dto.response.InformationDTO;
import com.online.shop.entity.Customer;
import com.online.shop.mapper.CustomerMapper;
import com.online.shop.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Фасад-сервис слоя представления для управления DTO на основе сущности {@link Customer}
 */
@Service
public class CustomerFacadeServiceImpl implements CustomerFacadeService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * Выборка покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link CustomerResponseDTO} - покупатель по указанному {@code id}
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO findById(UUID id) {
        return customerMapper.toDTO(customerService.findById(id));
    }

    /**
     * Выборка всех покупателей
     *
     * @return {@link List} - список всех покупателей {@link CustomerResponseDTO}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> findAll() {
        return customerMapper.toDTOList(customerService.findAll());
    }

    /**
     * Выборка всех товаров в корзине покупателя
     *
     * @return {@link List} - список всех товаров {@link GoodsResponseDTO} в корзине покупателя
     */
    @Override
    @Transactional(readOnly = true)
    public List<GoodsResponseDTO> findAllGoodsInCustomerCart(UUID id) {
        return goodsMapper.toDTOList(customerService.findAllGoodsInCustomerCart(id));
    }

    /**
     * Выборка покупателей по состоянию(активен или заблокирован) аккаунта
     *
     * @param enabled состояние аккаунта {@link boolean}
     * @return {@link List} - список всех покупателей {@link CustomerResponseDTO} по указанному состоянию аккаунта
     * {@code enabled}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> findAllCustomersByEnabled(boolean enabled) {
        return customerMapper.toDTOList(customerService.findAllCustomersByEnabled(enabled));
    }

    /**
     * Добавление нового покупателя в БД
     *
     * @param customerCreationDTO DTO новый Покупатель {@link CustomerCreationDTO}
     * @return DTO Покупатель {@link CustomerResponseDTO}
     */
    @Override
    @Transactional
    public CustomerResponseDTO addNew(CustomerCreationDTO customerCreationDTO) {
        Customer newCustomer = customerMapper.toEntity(customerCreationDTO);
        customerService.save(newCustomer);
        return customerMapper.toDTO(newCustomer);
    }

    /**
     * Обновление покупателя в БД
     *
     * @param id                идентификатор покупателя {@link UUID}
     * @param customerUpdateDTO DTO Покупатель {@link CustomerUpdateDTO} с изменёнными полями
     * @return обновлённый DTO Покупатель {@link CustomerResponseDTO}
     */
    @Override
    @Transactional
    public CustomerResponseDTO update(UUID id, CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = customerService.findById(id);
        customerMapper.updateEntityFromDto(customerUpdateDTO, customer);
        customerService.save(customer);
        return customerMapper.toDTO(customer);
    }

    /**
     * Удаление покупателя по id
     *
     * @param id идентификатор покупателя {@link UUID}
     * @return {@link InformationDTO} с сообщением о результате
     */
    @Override
    @Transactional
    public InformationDTO deleteById(UUID id) {
        customerService.deleteById(id);
        return new InformationDTO(String.format("Покупатель с ID: %s удалён", id));
    }

}
