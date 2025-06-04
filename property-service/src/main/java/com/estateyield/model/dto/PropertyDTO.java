package com.estateyield.model.dto;

import com.estateyield.model.enums.PropertyType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PropertyDTO {
    private Long propertyId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Property type is required")
    private PropertyType propertyType;

    @Positive(message = "Size must be positive")
    private Integer sizeSqft;

    private String description;

    @NotNull(message = "Purchase price is required")
    @Positive(message = "Purchase price must be positive")
    private BigDecimal purchasePrice;

    @NotNull(message = "Purchase date is required")
    private LocalDate purchaseDate;

    @NotNull(message = "Current market value is required")
    @Positive(message = "Current market value must be positive")
    private BigDecimal currentMarketValue;

    @NotNull(message = "Address is required")
    private PropertyAddressDTO address;
}
