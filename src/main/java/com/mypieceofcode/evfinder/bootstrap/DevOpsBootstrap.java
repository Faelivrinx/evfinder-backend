package com.mypieceofcode.evfinder.bootstrap;

import com.mypieceofcode.evfinder.domain.Comment;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.domain.security.Role;
import com.mypieceofcode.evfinder.domain.security.UserRole;
import com.mypieceofcode.evfinder.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sun.applet.AppletListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DevOpsBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createEvents();
    }

    private void createEvents() {
        User user = new User();
        user.setUsername("Damian");
        user.setDisplayName("Damian");
        user.setProfile("{\"25\":3,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":4,\"8\":0,\"27\":2,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":5,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        userRepository.save(user);

        List<User> users = new ArrayList<>();
        users.add(user);


        Event event1 = new Event();
        event1.setName("Św. Barbara w sztuce i legendzie");
        event1.setDescription("Z okazji Barbórki zapraszamy do obejrzenia obrazów i rzeźb przedstawiających świętą Barbarę. Jej wizerunki powstały w różnych epokach i wykonano je różnymi technikami.");
        event1.setAddress("Muzeum Śląskie, ul. T. Dobrowolskiego 1, zbiórka przy kasach");
        event1.setDate(1512468L);
        event1.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":6,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event1.setLatitude(50.263162);
        event1.setLongitude(19.0311411);
        eventRepository.save(event1);


        Event event2 = new Event();
        event2.setName("Spotkania z archeologią. Archeobotanika - blaski i cienie");
        event2.setDescription("Dział Archeologii Muzeum Śląskiego zaprasza na cykl wykładów, które poprowadzą badacze oraz naukowcy." +
                "Wykład poświęcony będzie zagadnieniom związanym z archeobotaniką, nauką zajmującą się badaniem pozostałości roślinnych odnajdywanych na stanowiskach archeologicznych. Opowiemy również o kulisach pracy archeobotanika.Prowadzenie: dr Joanna Abramów, Pracownia ARCHBOT");
        event2.setAddress("Muzeum Śląskie, ul. Dobrowolskiego 1 / poziom -3 / sala A");
        event2.setDate(1512662L);
        event2.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":6,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event2.setLatitude(50.263779);
        event2.setLongitude(19.028871);
        eventRepository.save(event2);


        Event event3 = new Event();
        event3.setName("Hey");
        event3.setDescription("Przez te 25 lat wydaliśmy 18 płyt, zagraliśmy niezliczoną liczbę koncertów praktycznie w każdym zakątku Polski. Odwiedziliśmy trzy kontynenty, choć jeden niekoniecznie muzycznie. Liczby piosenek nawet nie zliczymy, gdyż kończą się nam palce w zespole.\n" +
                "\n" +
                "Chcielibyśmy to wszystko jakoś podsumować, spiąć w klamrę, zebrać w dwa tomy. To przecież dzięki Wam mieliśmy siłę i ochotę na stworzenie tego wszystkiego, co udało nam się nagrać i zaśpiewać. Jak świętować, to tylko z Wami.\n" +
                "\n" +
                "FAYRANT to siedem koncertów w największych halach koncertowych w kraju. Koncertów wyjątkowych, wspominkowych, przekrojowych a przede wszystkim emocjonalnych. Będą goście, będą hity, będą rzeczy zapomniane, a specjalnie na tę imprezę wyciągnięte z dna szuflady. Będą również niespodzianki i cała masa dobrej zabawy, czyli wszystko to, czego nie może zabraknąć na dobrej imprezie urodzinowej.\n" +
                "\n" +
                "Do zobaczenia!");
        event3.setAddress("Katowice Spodek!");
        event3.setDate(1512759L);
        event3.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":6,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event3.setLatitude(50.265711);
        event3.setLongitude(19.0228923);
//        event3.setUsers(users);
        eventRepository.save(event3);


        Event event4 = new Event();
        event4.setName("12. Silesian Jazz Festival");
        event4.setDescription("Trzy jazzowe wieczory, każdy z aż dwoma koncertami w programie – tak w skrócie zapowiada się 12. edycja Silesian Jazz Festivalu. Jego program będzie, jak zwykle, bardzo zróżnicowany stylistycznie i ukierunkowany na współczesną, polską i europejską muzykę jazzową. Spotkamy się z nią w sali koncertowej Miasta Ogrodów, w klubach, ale też w nowej dla festiwalu przestrzeni Muzeum Śląskiego.\n" +
                "\n" +
                "Niezmiennie częścią festiwalu będzie rozstrzygniecie Międzynarodowego Konkursu na Kompozycję Jazzową oraz koncert, podczas którego po raz pierwszy wykonane zostaną nagrodzone utwory. 4. edycja konkursu przynosi zmiany. Kompozytorzy mają do dyspozycji formy muzyczne od sola do sekstetu na określony skład instrumentalny. Jak co roku wręczona zostanie statuetka Ambasadora Jazzu.\n" +
                "\n" +
                "Pierwszego wieczoru dominować będą tria. Najpierw katowickie RGG, wzmocnione przez dwóch gości z zagranicy - Verneri Pohjola, okrzykniętego „najlepszym fińskim trębaczem jazzowym młodego pokolenia” oraz Samuela Blasera, szwajcarskiego puzonistę porównywanego do legendarnego Alberta Mangelsdorffa. Drugim triem będzie zespół Marka Napiórkowskiego, znane też jako KonKubiNap od nazwisk trójki muzyków.\n" +
                "\n" +
                "Największy skład tegorocznego Silesian Jazz Festivalu pojawi się na scenie Miasta Ogrodów 9 grudnia na koncercie „Tribute to Akwarium” Piotra Wojtasika. To współczesny elektro-akustyczny jazz reprezentujący nurt modalny z elementami muzyki etnicznej. Wcześniej poznamy laureatów Konkursu na Kompozycję Jazzową.\n" +
                "\n" +
                "Komu będzie mało wrażeń, jazzowe after party w klubie Absurdalna zapewni Kuba Więcek Trio.\n" +
                "\n" +
                "Finał 10 grudnia zapowiada się najbardziej międzynarodowo. Przed solowym recitale Marcina Maseckiego w Muzeum Śląskim spotkanie z projektem Hearth: Rasmussen/ Rave/ Santos Silva/ Draksler. To jedyne w swoim rodzaju spotkanie muzyków z Norwegii, Holandii, Portugalii i Słowenii.\n" +
                "\n" +
                "\n" +
                "Program 12. Silesian Jazz Festivalu\n" +
                "\n" +
                "8 grudnia (piątek), sala koncertowa Miasta Ogrodów, początek koncertów o godz. 18\n" +
                "\n" +
                "RGG „Contemporary Sonus” feat. Verneri Pohjola & Samuel Blaser\n" +
                "/Łukasz Ojdana, Maciej Garbowski, Krzysztof Gradziuk/\n" +
                "\n" +
                "Marek Napiórkowski Trio „KonKubiNap”\n" +
                "/Marek Napiórkowski, Robert Kubiszyn, Cezary Konrad/\n" +
                "\n" +
                "9 grudnia (sobota), sala koncertowa Miasta Ogrodów, początek koncertów o godz. 18\n" +
                "\n" +
                "4. Międzynarodowy Konkurs na Kompozycję Jazzową\n" +
                "ogłoszenie wyników, wręczenie nagród, koncert zwycięskich kompozycji\n" +
                "\n" +
                "Tribute to Akwarium\n" +
                "/Eric Allen, Marek Kądziela, Joris Teepe, Piotr Wojtasik, Kuba Marciniak, Marcin Kaletka, Sebastian Sołdrzyński, Dominik Wania, Paweł Tomaszewski, Jakub Miarczyński, Igor Pietraszewski, Magdalena Zawartko, Anna Maria Mbayo/\n" +
                "\n" +
                "Kuba Więcek Trio (Absurdalna), godz. 21\n" +
                "/Jakub Więcek, Michał Barański, Łukasz Żyta/\n" +
                "\n" +
                "10 grudnia (niedziela), Muzeum Śląskie (Strefa Kultury), początek koncertów o godz. 17\n" +
                "\n" +
                "Hearth: Rasmussen/ Rave/ Santos Silva/ Draksler\n" +
                "/ Mette Rasmussen, Ada Rave, Susana Santos Silva, Kaja Draksler\n" +
                "\n" +
                "Marcin Masecki");
        event4.setAddress("Katowice Miasto Ogrodów");
        event4.setDate(1512752L);
        event4.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":6,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event4.setLatitude(50.262774);
        event4.setLongitude(19.025127);
        event4.setUsers(users);
        eventRepository.save(event4);


//        Event event5 = new Event();
//        event5.setName("Św. Barbara w sztuce i legendzie");
//        event5.setDescription("Z okazji Barbórki zapraszamy do obejrzenia obrazów i rzeźb przedstawiających świętą Barbarę. Jej wizerunki powstały w różnych epokach i wykonano je różnymi technikami.");
//        event5.setAddress("Muzeum Śląskie, ul. T. Dobrowolskiego 1, zbiórka przy kasach");
//        event5.setDate(432432L);
//
//        Event event7 = new Event();
//        event7.setName("Św. Barbara w sztuce i legendzie");
//        event7.setDescription("Z okazji Barbórki zapraszamy do obejrzenia obrazów i rzeźb przedstawiających świętą Barbarę. Jej wizerunki powstały w różnych epokach i wykonano je różnymi technikami.");
//        event7.setAddress("Muzeum Śląskie, ul. T. Dobrowolskiego 1, zbiórka przy kasach");
//        event7.setDate(432432L);
//
//        Event event6 = new Event();
//        event6.setName("Św. Barbara w sztuce i legendzie");
//        event6.setDescription("Z okazji Barbórki zapraszamy do obejrzenia obrazów i rzeźb przedstawiających świętą Barbarę. Jej wizerunki powstały w różnych epokach i wykonano je różnymi technikami.");
//        event6.setAddress("Muzeum Śląskie, ul. T. Dobrowolskiego 1, zbiórka przy kasach");
//        event6.setDate(432432L);


        Comment comment = new Comment();
        comment.setRating(3);
        comment.setComment("Zapowiada się ciekawie! ;)");
        comment.setUser(user);
        comment.setEvent(event1);
        commentRepository.save(comment);


        Comment comment1 = new Comment();
        comment1.setRating(5);
        comment1.setComment("Hey, jak zawsze w dobrej formie!");
        comment1.setUser(user);
        comment1.setEvent(event3);
        commentRepository.save(comment1);


        Comment comment2 = new Comment();
        comment2.setRating(1);
        comment2.setComment("Nie polecam, słabo sie zapowiada!");
        comment2.setUser(user);
        comment2.setEvent(event4);
        commentRepository.save(comment2);


        Comment comment3 = new Comment();
        comment3.setRating(3);
        comment3.setComment("Zapowiada się ciekawie! ;)");
        comment3.setUser(user);
        comment3.setEvent(event1);
        commentRepository.save(comment3);

    }


}
