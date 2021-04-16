package com.waracle.cakemanager;


import com.waracle.cakemanager.model.CakeEntity;
import com.waracle.cakemanager.model.CakeRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


/*
To initialize the application db after the spring context has been loaded
 */
@Component
public class EventListenerBean {

    @Value( "${url.init}" )
    private String initUrl;


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CakeRepository cakeRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {


        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
                MediaType.TEXT_PLAIN));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);


        ResponseEntity<CakeEntity[]> responseEntity =
                restTemplate.getForEntity(initUrl, CakeEntity[].class);


        Arrays.stream(responseEntity.getBody()).forEach(
                it-> {
                    CakeEntity cakeEntity = new CakeEntity();
                    cakeEntity.setDescription(getValueIfNotBlank(it.getDescription()));
                    cakeEntity.setImage(getValueIfNotBlank(it.getImage()));
                    cakeEntity.setTitle(getValueIfNotBlank(it.getTitle()));

                    cakeRepository.save(cakeEntity);
                });


    }

    private String getValueIfNotBlank(String value) {
        return Strings.isNotBlank(value) ? value : "";
    }
}