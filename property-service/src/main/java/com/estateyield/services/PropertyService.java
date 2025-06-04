package com.estateyield.services;

import com.estateyield.exception.PropertyException;
import com.estateyield.model.dto.PropertyDTO;
import com.estateyield.model.entity.Property;
import com.estateyield.mapper.PropertyMapper;
import com.estateyield.repository.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyRepository propertyRepository, PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    @Transactional
    public PropertyDTO addProperty(PropertyDTO propertyDTO) {
        if (propertyDTO.getUserId() == null || propertyDTO.getUserId() <= 0) {
            throw new PropertyException("Invalid user ID provided", "INVALID_USER_ID");
        }
        Property property = propertyMapper.toEntity(propertyDTO);
        Property savedProperty = propertyRepository.save(property);
        return propertyMapper.toDTO(savedProperty);
    }

    public PropertyDTO getPropertyById(Long id) {
        if (id == null || id <= 0) {
            throw new PropertyException("Invalid property ID provided", "INVALID_PROPERTY_ID");
        }
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));
        return propertyMapper.toDTO(property);
    }

    public List<PropertyDTO> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        if (properties.isEmpty()) {
            throw new PropertyException("No properties found", "NO_PROPERTIES_FOUND");
        }
        return properties.stream()
                .map(propertyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO) {
        if (id == null || id <= 0) {
            throw new PropertyException("Invalid property ID provided", "INVALID_PROPERTY_ID");
        }
        if (propertyDTO.getUserId() == null || propertyDTO.getUserId() <= 0) {
            throw new PropertyException("Invalid user ID provided", "INVALID_USER_ID");
        }
        Property existingProperty = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));

        Property updatedProperty = propertyMapper.toEntity(propertyDTO);
        updatedProperty.setPropertyId(id);
        updatedProperty.setCreatedAt(existingProperty.getCreatedAt());

        Property savedProperty = propertyRepository.save(updatedProperty);
        return propertyMapper.toDTO(savedProperty);
    }

    @Transactional
    public void deleteProperty(Long id) {
        if (id == null || id <= 0) {
            throw new PropertyException("Invalid property ID provided", "INVALID_PROPERTY_ID");
        }
        if (!propertyRepository.existsById(id)) {
            throw new EntityNotFoundException("Property not found with id: " + id);
        }
        propertyRepository.deleteById(id);
    }
}