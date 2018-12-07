package com.guitar.db;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guitar.db.model.Manufacturer;
import com.guitar.db.repository.ManufacturerJpaRepository;
import com.guitar.db.repository.ManufacturerRepository;

@ContextConfiguration(locations={"classpath:com/guitar/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ManufacturerPersistenceTests {
	@Autowired
	private ManufacturerRepository manufacturerRepository;
    
	@Autowired
    private ManufacturerJpaRepository manufacturerJpaRepository;

	@Test
	public void testGetManufacturersFoundedBeforeDate() throws Exception {
		List<Manufacturer> mans = manufacturerRepository.getManufacturersFoundedBeforeDate(new Date());
		assertEquals(2, mans.size());
	}

	@Test
	public void testGetManufactureByName() throws Exception {
		Manufacturer m = manufacturerRepository.getManufacturerByName("Fender");
		assertEquals("Fender Musical Instruments Corporation", m.getName());
	}

	@Test
	public void testGetManufacturersThatSellModelsOfType() throws Exception {
		List<Manufacturer> mans = manufacturerRepository.getManufacturersThatSellModelsOfType("Semi-Hollow Body Electric");
		assertEquals(1, mans.size());
	}
    @Test
    public void testGetManufacturersThatSellModelsOfTypeJpaVersion() throws Exception {
        List<Manufacturer> mans = manufacturerRepository.getManufacturersThatSellModelsOfTypeJpaVersion("Semi-Hollow Body Electric");
        assertEquals(1, mans.size());
    }
	
	@Test
	public void testActiveTrue() throws Exception {
	    List<Manufacturer> activeManufacturers = manufacturerJpaRepository.findByActiveTrue();
	    Manufacturer aManufacturer = activeManufacturers.get(0);
	    String fender = aManufacturer.getName();
	    assertEquals("Fender Musical Instruments Corporation", fender);
	}
	
    @Test
    public void testInactive() throws Exception {
        List<Manufacturer> inActiveManufacturers = manufacturerJpaRepository.findByActiveFalse();
        Manufacturer aManufacturer = inActiveManufacturers.get(0);
        String gibson = aManufacturer.getName();
        assertEquals("Gibson Guitar Corporation", gibson);
    }
	
	
}
