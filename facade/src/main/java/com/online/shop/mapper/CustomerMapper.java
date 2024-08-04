package com.online.shop.mapper;

import com.online.shop.dto.CustomerDTO;
import com.online.shop.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для {@link Customer} и {@link CustomerDTO}
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper extends BaseMapper<Customer, CustomerDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return DTO
     */
    @Override
    CustomerDTO toDTO(Customer entity);

    /**
     * Маппинг из DTO в сущность
     *
     * @param dto DTO
     * @return сущность
     */
    @Override
    Customer toEntity(CustomerDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param customers список сущностей
     * @return список DTO
     */
    @Override
    List<CustomerDTO> toDTOList(List<Customer> customers);

    /**
     * Маппинг из списка DTO в список сущностей
     *
     * @param customerDTOS список DTO
     * @return список сущностей
     */
    @Override
    List<Customer> toEntityList(List<CustomerDTO> customerDTOS);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    DTO, проинициализированные поля которого, кроме id, будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(CustomerDTO dto, @MappingTarget Customer entity);

}
