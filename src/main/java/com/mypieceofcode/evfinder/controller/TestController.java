package com.mypieceofcode.evfinder.controller;


import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.recommender.EventRecommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    EventRecommendation eventRecommendation;

    @PostMapping(value = "/test")
    public void checkConverter(){
        User user = new User();
        user.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":3,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":2,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");

        Event pilkaNozna = new Event();
        pilkaNozna.setName("Pilka Nożna");
        pilkaNozna.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":6,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");

        Event mieszane = new Event();
        mieszane.setName("Rozne");
        mieszane.setProfile("{\"25\":6,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":6,\"8\":0,\"27\":0,\"6\":6,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":6,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");

        Event koncertMEtallica = new Event();
        koncertMEtallica.setName("Koncert Metal");
        koncertMEtallica.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":6,\"30\":0,\"18\":0}\t");

        Event dlaDzieci = new Event();
        dlaDzieci.setName("Dla dzieci + Edukacja");
        dlaDzieci.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":6,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":6,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");

        List<Event> events = new ArrayList<>();
        events.add(pilkaNozna);
        events.add(koncertMEtallica);
        events.add(dlaDzieci);
        events.add(mieszane);

        eventRecommendation.recommend(user, events);
    }
}
