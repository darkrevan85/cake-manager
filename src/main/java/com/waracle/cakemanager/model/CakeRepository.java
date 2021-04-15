package com.waracle.cakemanager.model;

import org.springframework.data.repository.CrudRepository;


public interface CakeRepository extends CrudRepository<CakeEntity,Integer> {

     CakeEntity findByTitle(String title);

}
