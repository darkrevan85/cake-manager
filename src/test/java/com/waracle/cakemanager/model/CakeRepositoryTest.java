package com.waracle.cakemanager.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CakeRepositoryTest {

    @Autowired
    private CakeRepository cakeRepository;

    @Test
    public void testPersist(){

        // given
        CakeEntity cake = new CakeEntity();
        cake.setTitle("title");
        cake.setImage("image");
        cake.setDescription("description");
        cakeRepository.save(cake);

        // when
        CakeEntity found = cakeRepository.findByTitle(cake.getTitle());

        // then
        assertThat(found.getTitle())
                .isEqualTo(cake.getTitle());
    }


}