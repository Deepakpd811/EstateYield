package com.estateyield.mapper;

import com.estateyield.model.dto.PropertyAddressDTO;
import com.estateyield.model.dto.PropertyDTO;
import com.estateyield.model.entity.Property;
import com.estateyield.model.entity.PropertyAddress;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {

    public PropertyDTO toDTO(Property property) {
        if (property == null) return null;
        PropertyDTO dto = new PropertyDTO();
        dto.setPropertyId(property.getPropertyId());
        dto.setUserId(property.getUserId());
        dto.setPropertyType(property.getPropertyType());
        dto.setSizeSqft(property.getSizeSqft());
        dto.setDescription(property.getDescription());
        dto.setPurchasePrice(property.getPurchasePrice());
        dto.setPurchaseDate(property.getPurchaseDate());
        dto.setCurrentMarketValue(property.getCurrentMarketValue());
        dto.setAddress(toAddressDTO(property.getAddress()));
        return dto;
    }

    public Property toEntity(PropertyDTO dto) {
        if (dto == null) return null;
        Property property = new Property();
        property.setUserId(dto.getUserId());
        property.setPropertyType(dto.getPropertyType());
        property.setSizeSqft(dto.getSizeSqft());
        property.setDescription(dto.getDescription());
        property.setPurchasePrice(dto.getPurchasePrice());
        property.setPurchaseDate(dto.getPurchaseDate());
        property.setCurrentMarketValue(dto.getCurrentMarketValue());
        property.setAddress(toAddressEntity(dto.getAddress()));
        return property;
    }

    public PropertyAddressDTO toAddressDTO(PropertyAddress address) {
        if (address == null) return null;
        PropertyAddressDTO dto = new PropertyAddressDTO();
        dto.setAddressId(address.getAddressId());
        dto.setAddress(address.getAddress());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setCountry(address.getCountry());
        dto.setZipCode(address.getZipCode());
        return dto;
    }

    public PropertyAddress toAddressEntity(PropertyAddressDTO dto) {
        if (dto == null) return null;
        PropertyAddress address = new PropertyAddress();
        address.setAddressId(dto.getAddressId());
        address.setAddress(dto.getAddress());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setZipCode(dto.getZipCode());
        return address;
    }
}