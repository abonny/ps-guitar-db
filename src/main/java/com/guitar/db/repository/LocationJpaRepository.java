package com.guitar.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Location;

public interface LocationJpaRepository extends JpaRepository<Location, Long> {
    
    List<Location> findByStateLike(String stateName);
    
    List<Location> findByStateNotLike(String stateName);
    
    List<Location> findByStateNot(String stateName);
    
    List<Location> findByStateOrCountry(String stateName, String countryName);
    
    List<Location> findByStateAndCountry(String stateName, String countryName);
    
    List<Location> findByStateStartingWithIgnoreCase(String stateName);

}
