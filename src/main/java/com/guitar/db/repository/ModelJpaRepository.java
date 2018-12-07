package com.guitar.db.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guitar.db.model.Model;

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
    
    /**
     * Notice the JPQL we have ":named" parameters instead of "?".
     * 
     * @return
     */
    @Query("select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
    List<Model> queryByPriceRangeAndWoodType(@Param("lowest") BigDecimal low, 
            @Param("highest") BigDecimal high, 
            @Param("wood") String would);
    
    @Query("select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType in :wood")
    List<Model> queryByPriceRangeAndWoodTypeSet(@Param("lowest") BigDecimal low, 
            @Param("highest") BigDecimal high, 
            @Param("wood") Set<String> woodSet);
    
    
    /**
     * This is auto-bound to the "named query" in {@link Model} because of naming conventions.
     * 
     * @param modelType
     * @return
     */
    List<Model> findAllModelsByType(@Param("name") String modelType);

}
