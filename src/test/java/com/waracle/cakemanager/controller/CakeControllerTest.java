package com.waracle.cakemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemanager.CakeManagerMavenWebappApplication;
import com.waracle.cakemanager.model.CakeEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * We'll start the whole context, but not the server. We'll mock the REST calls instead.
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = CakeManagerMavenWebappApplication.class)
class CakeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void cake() throws Exception {
        this.mockMvc.perform(get("/cakes"))
                .andExpect(status().isOk()).andExpect(content().json(
                "[{\"title\":\"Lemon cheesecake\",\"description\":\"\",\"image\":\"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"},{\"title\":\"victoria sponge\",\"description\":\"\",\"image\":\"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"},{\"title\":\"Carrot cake\",\"description\":\"\",\"image\":\"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"},{\"title\":\"Banana cake\",\"description\":\"\",\"image\":\"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"},{\"title\":\"Birthday cake\",\"description\":\"\",\"image\":\"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"},{\"title\":\"Lemon cheesecake\",\"description\":\"\",\"image\":\"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"},{\"title\":\"victoria sponge\",\"description\":\"\",\"image\":\"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"},{\"title\":\"Carrot cake\",\"description\":\"\",\"image\":\"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"},{\"title\":\"Banana cake\",\"description\":\"\",\"image\":\"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"},{\"title\":\"Birthday cake\",\"description\":\"\",\"image\":\"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"},{\"title\":\"Lemon cheesecake\",\"description\":\"\",\"image\":\"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"},{\"title\":\"victoria sponge\",\"description\":\"\",\"image\":\"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"},{\"title\":\"Carrot cake\",\"description\":\"\",\"image\":\"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"},{\"title\":\"Banana cake\",\"description\":\"\",\"image\":\"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"},{\"title\":\"Birthday cake\",\"description\":\"\",\"image\":\"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"},{\"title\":\"Lemon cheesecake\",\"description\":\"\",\"image\":\"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"},{\"title\":\"victoria sponge\",\"description\":\"\",\"image\":\"http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg\"},{\"title\":\"Carrot cake\",\"description\":\"\",\"image\":\"http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg\"},{\"title\":\"Banana cake\",\"description\":\"\",\"image\":\"http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg\"},{\"title\":\"Birthday cake\",\"description\":\"\",\"image\":\"http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg\"}]"));

    }

    @Test
    public void createCake() throws Exception {

        //given
        CakeEntity cakeEntity = new CakeEntity();
        cakeEntity.setDescription("desc");
        cakeEntity.setImage("image");
        cakeEntity.setTitle("title");

        //then
        this.mockMvc.perform( post("/cakes")
                .content(asJsonString(cakeEntity))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}