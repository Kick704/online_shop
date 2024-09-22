package com.online.shop.mapper;

import com.online.shop.dto.request.creation.CustomerCreationDTO;
import com.online.shop.dto.request.update.CustomerUpdateDTO;
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
public interface CustomerMapper extends BaseMapper<Customer,
        CustomerCreationDTO,
        CustomerUpdateDTO,
        CustomerResponseDTO> {

    /**
     * Маппинг из сущности в DTO
     *
     * @param entity сущность
     * @return ResponseDTO
     */
    @Override
    CustomerResponseDTO toDTO(Customer entity);

    /**
     * Маппинг из DTO в сущность для его создания
     *
     * @param dto RequestDTO
     * @return сущность
     */
    @Override
    Customer toEntity(CustomerCreationDTO dto);

    /**
     * Маппинг из списка сущностей в список DTO
     *
     * @param customers список сущностей
     * @return список ResponseDTO
     */
    @Override
    List<CustomerResponseDTO> toDTOList(List<Customer> customers);

    /**
     * Обновление сущности на основе DTO, игнорируя null поля и поле id
     *
     * @param dto    RequestDTO, проинициализированные поля которого будут обновлены в сущности
     * @param entity обновляемая сущность
     */
    @Override
    void updateEntityFromDto(CustomerUpdateDTO dto, @MappingTarget Customer entity);

}
