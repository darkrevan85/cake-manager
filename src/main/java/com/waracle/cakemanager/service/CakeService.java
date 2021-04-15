package com.waracle.cakemanager.service;


import com.google.common.collect.Lists;
import com.waracle.cakemanager.model.CakeEntity;
import com.waracle.cakemanager.model.CakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class CakeService {

    Logger logger = LoggerFactory.getLogger(CakeService.class);

    @Autowired
    private CakeRepository cakeRepository;


    public void createCake(CakeEntity cakeEntity){
         cakeRepository.save(cakeEntity);
    }

    public void deleteCake(CakeEntity cakeEntity){
        cakeRepository.delete(cakeEntity);
    }

    public List<CakeEntity> findAll(){
        return Lists.newArrayList(cakeRepository.findAll());
    }


}
