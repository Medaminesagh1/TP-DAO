package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;

import monprojet.projections.populationParPays;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value="Select SUM(population) as population "
        + " From Country " 
        +" inner join CITY ON Country.id=CITY.country_id  "
        +" Where Country.id= :idCountry ",
        nativeQuery = true)
        public int populationPays(int idCountry);
    @Query(value=" Select Country.name as nom , SUM(population) as population "
        + " From Country "
        +" inner join CITY ON Country.id=CITY.country_id "
        +" Group By Country.name ",
        nativeQuery = true)
        public List<populationParPays> populationParPays();
           
}
