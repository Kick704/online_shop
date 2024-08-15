package com.online.shop.mapper;

import com.online.shop.dto.request.AbstractRequestDTO;
import com.online.shop.dto.response.CustomerResponseDTO;
import com.online.shop.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Маппер для сущности {@link Customer} в DTO и обратно
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper extends BaseMapper<Customer, CustomerResponseDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return ResponseDTO
     */
    @Override
    CustomerResponseDTO toDTO(Customer entity);

    /**
     * Маппинг из DTO в сущность
     *
     * @param dto RequestDTO
     * @return сущность
     */
    @Override
    Customer toEntity(AbstractRequestDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param customers список сущностей
     * @return список ResponseDTO
     */
    @Override
    List<CustomerResponseDTO> toDTOList(List<Customer> customers);

    /**
     * Маппинг из списка DTO в список сущностей
     *
     * @param dtos список RequestDTO
     * @return список сущностей
     */
    @Override
    List<Customer> toEntityList(List<AbstractRequestDTO> dtos);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    void updateEntityFromDto(AbstractRequestDTO dto, @MappingTarget Customer entity);

}
