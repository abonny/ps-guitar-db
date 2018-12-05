package com.guitar.db.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Model;
import com.guitar.db.model.ModelType;

public interface ModelJpaRepository extends JpaRepository<Model, Long> {
    
    List<Model> findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal lower, BigDecimal upper);

    /**
     * This one is extra cool/hard.
     * 
     * This is looking at the ModelType Object then returning 
     * all who match its "Name" property.
     * 
     * @param types
     * @return
     */
    List<Model> findByModelTypeNameIn(List<String> types);
    
}
