package com.estateyield.repository;

import com.estateyield.model.entity.PropertyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyAddressRepository extends JpaRepository<PropertyAddress, Long> {
}
