package com.mypieceofcode.evfinder.bootstrap;

import com.mypieceofcode.evfinder.domain.Comment;
import com.mypieceofcode.evfinder.domain.Event;
import com.mypieceofcode.evfinder.domain.Friend;
import com.mypieceofcode.evfinder.domain.User;
import com.mypieceofcode.evfinder.domain.security.Role;
import com.mypieceofcode.evfinder.domain.security.UserRole;
import com.mypieceofcode.evfinder.repository.*;
import com.mypieceofcode.evfinder.service.FriendService;
import com.sun.corba.se.impl.ior.FreezableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    @Autowired
    FriendService friendService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        User user = new User();
//        user.setPassword("$2a$12$qmpFIUrXNteGrv7nx6zkxu/SG4wve8ZVNsWMbKqERjnzj8Ha7K5KS");
//        user.setUsername("admin");
//        user.setDisplayName("Admin");
//        user.setProfile("{\"25\":3,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":4,\"8\":0,\"27\":2,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":5,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
//        userRepository.save(user);
//
//        List<User> users = createUsers();
//        List<Event> events = createEvents();
//        List<Comment> comments = createComments(users, events);
//        List<Friend> friends = makeFriends(users);
//
//        makeFriends(user);
//
//        for (Event event : events) {
//            if (new Random().nextInt(2) == 1) {
//                Comment comment = new Comment();
//                comment.setRating(1 + new Random().nextInt(6));
//                comment.setComment("<opinia>");
//                comment.setUser(user);
//                comment.setEvent(event);
//                comments.add(comment);
//                commentRepository.save(comment);
//            }
//        }

        create();

    }

    private void create() {
        User user = new User();
        user.setUsername("Monika");
        user.setDisplayName("Monika");
        user.setProfile("{\"25\":3,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":4,\"8\":0,\"27\":2,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":5,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        userRepository.save(user);

        User user1 = new User();
        user1.setApiToken("fdsafdsdsaga");
        user1.setUsername("Test");
        user1.setPassword("$2a$12$qmpFIUrXNteGrv7nx6zkxu/SG4wve8ZVNsWMbKqERjnzj8Ha7K5KS");
        user1.setDisplayName("Testowy User");
        user1.setProfile("{\"25\":3,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":4,\"8\":0,\"27\":2,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":5,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        userRepository.save(user1);

        User user2 = new User();
        user2.setApiToken("gdashfa");
        user2.setUsername("Adam");
        user2.setDisplayName("Adam");
        user2.setProfile("{\"25\":3,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":4,\"8\":0,\"27\":2,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":5,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        userRepository.save(user2);

        User user3 = new User();
        user3.setApiToken("fdafbbfadbd");
        user3.setUsername("Marek");
        user3.setDisplayName("Marek");
        user3.setProfile("{\"25\":3,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":4,\"8\":0,\"27\":2,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":5,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        userRepository.save(user3);

        User user4 = new User();
        user4.setApiToken("4324326432vdsa");
        user4.setUsername("Faelivrinx");
        user4.setDisplayName("Marian Kowalski");
        user4.setProfile("{\"25\":3,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":4,\"8\":0,\"27\":2,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":5,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        userRepository.save(user4);

        List<User> users = new ArrayList<>();
        users.add(user);


        Event event1 = new Event();
        event1.setName("Św. Barbara w sztuce i legendzie");
        event1.setDescription("Z okazji Barbórki zapraszamy do obejrzenia obrazów i rzeźb przedstawiających świętą Barbarę. Jej wizerunki powstały w różnych epokach i wykonano je różnymi technikami.");
        event1.setAddress("Muzeum Śląskie, ul. T. Dobrowolskiego 1, zbiórka przy kasach");
        event1.setDate(1522080000000L);
        event1.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":6,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event1.setLatitude(50.263162);
        event1.setLongitude(19.0311411);
        eventRepository.save(event1);


        Event event2 = new Event();
        event2.setName("Spotkania z archeologią. Archeobotanika - blaski i cienie");
        event2.setDescription("Dział Archeologii Muzeum Śląskiego zaprasza na cykl wykładów, które poprowadzą badacze oraz naukowcy." +
                "Wykład poświęcony będzie zagadnieniom związanym z archeobotaniką, nauką zajmującą się badaniem pozostałości roślinnych odnajdywanych na stanowiskach archeologicznych. Opowiemy również o kulisach pracy archeobotanika.Prowadzenie: dr Joanna Abramów, Pracownia ARCHBOT");
        event2.setAddress("Muzeum Śląskie, ul. Dobrowolskiego 1 / poziom -3 / sala A");
        event2.setDate(1522252800000L);
        event2.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":6,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event2.setLatitude(50.263779);
        event2.setLongitude(19.028871);
        eventRepository.save(event2);


        Event event3 = new Event();
        event3.setName("Hey");
        event3.setDescription("Przez te 25 lat wydaliśmy 18 płyt, zagraliśmy niezliczoną liczbę koncertów praktycznie w każdym zakątku Polski. Odwiedziliśmy trzy kontynenty, choć jeden niekoniecznie muzycznie. Liczby piosenek nawet nie zliczymy, gdyż kończą się nam palce w zespole.\n" +
                "\n" +
                "Chcielibyśmy to wszystko jakoś podsumować, spiąć w klamrę, zebrać w dwa tomy.");
        event3.setAddress("Katowice Spodek!");
        event3.setDate(1522094400000L);
        event3.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":6,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event3.setLatitude(50.260740);
        event3.setLongitude(19.013555);
//        event3.setUsers(users);
        eventRepository.save(event3);


        Event event4 = new Event();
        event4.setName("12. Silesian Jazz Festival");
        event4.setDescription("Trzy jazzowe wieczory, każdy z aż dwoma koncertami w programie – tak w skrócie zapowiada się 12. edycja Silesian Jazz Festivalu. Jego program będzie, jak zwykle, bardzo zróżnicowany stylistycznie i ukierunkowany na współczesną, polską i europejską muzykę jazzową. Spotkamy się z nią w sali koncertowej Miasta Ogrodów, w klubach, ale też w nowej dla festiwalu przestrzeni Muzeum Śląskiego.\n" +
                "\n" +
                "Niezmiennie częścią festiwalu będzie rozstrzygniecie Międzynarodowego Konkursu na Kompozycję Jazzową oraz koncert, podczas którego po raz pierwszy wykonane zostaną nagrodzone utwory. 4. edycja konkursu przynosi zmiany. Kompozytorzy mają do dyspozycji formy muzyczne od sola do sekstetu na określony skład instrumentalny. Jak co roku wręczona zostanie statuetka Ambasadora Jazzu.\n" +
                "\n" +
                "Pierwszego wieczoru dominować będą tria.");
        event4.setAddress("Katowice Miasto Ogrodów");
        event4.setDate(1522958400000L);
        event4.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":6,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event4.setLatitude(50.2528837);
        event4.setLongitude(19.0229935);
        event4.setUsers(users);
        eventRepository.save(event4);

        Event event5 = new Event();
        event5.setName("Katowice - pARTyzant koncert");
        event5.setDescription("pARTyzant cki koncert w Katowicach Piotrowicach - mateczniku gitarzysty . Niezwykłe miejsce wypełni się dźwiękami z nowej płyty \"Kenopsja\" i jak zwykle nie zabraknie niespodzianek ;) \n");
        event5.setAddress("Narodowa Orkiestra Symfoniczna Polskiego Radia w Katowicach, Plac Sejmu Śląskiego 2");
        event5.setDate(1520103600000L);
        event5.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":6,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event5.setLatitude(50.2301153);
        event5.setLongitude(18.9507141);
        event5.setUsers(users);
        eventRepository.save(event5);

        Event event6 = new Event();
        event6.setName("PRZYGODY TYMOTEUSZA MAJSTERKLEPKI");
        event6.setDescription("W ramach niedzielnych Podwieczorków Teatralnych w Teatrze Gry i Ludzie w najbliższą niedzielę będzie można obejrzeć spektakl pt. 'Tymoteusz Majsterklepka'.");
        event6.setAddress("Katowice");
        event6.setDate(1519923600000L);
        event6.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":6,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        event6.setLatitude(50.275538);
        event6.setLongitude(19.066814);
        event6.setUsers(users);
        eventRepository.save(event6);

        Event event7 = new Event();
        event7.setName("GKS Katowice vs. Skra Bełchatów");
        event7.setDescription("Zapraszamy na siatkarski mecz GKS Katowice vs. Skra Bełchatów.\n" +
                "Więcej informacji wkrótce");
        event7.setAddress("aleja Korfantego 35, 40-005 Katowice");
        event7.setLatitude(50.2660531);
        event7.setLongitude(19.0253699);
        event7.setDate(1520618400000L);
        event7.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":6,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        eventRepository.save(event7);

        Event event8 = new Event();
        event8.setName("Krzysztof Penderecki (Verdi's Messa da Requiem)");
        event8.setDescription("Koncert muzyki poważnej");
        event8.setAddress("Pl. Wojciecha Kilara 1");
        event8.setLatitude(50.263997);
        event8.setLongitude(19.027769);
        event8.setDate(1520190000000L);
        event8.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":6,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        eventRepository.save(event8);

        Event event9 = new Event();
        event9.setName("Ba rockowe ostatki!");
        event9.setDescription("Kwintet Śląskich Kameralistów zaprasza na \"Ba.. Rockowe Ostatki\"! W programie polskie prawykonanie Kwartetu smyczkowego F-dur Maurice\\'a Ravela w wersji na kwintet smyczkowy oraz muzyka z płyt \"ba.. Rockowo\" i \"Mecyje\":");
        event9.setAddress("Filharmonia Śląska im. Henryka Mikołaja Góreckiego");
        event9.setLatitude(50.260771);
        event9.setLongitude(19.013731);
        event9.setDate(1519758000000L);
        event9.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":6,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        eventRepository.save(event9);

        Event event10 = new Event();
        event10.setName("11. Katowicki Karnawał Komedii - dzień 6");
        event10.setDescription("Co roku, w czasie karnawału organizatorzy Katowickiego Karnawału Komedii proponują na scenach Teatru Korez i Teatru Śląskiego niepowtarzalny repertuar spektakli z całej Polski oraz imprez towarzyszących. Na przełomie stycznia i lutego do Katowic zjeżdżają znakomici aktorzy prezentujący najciekawsze tytuły spektakli sezonu oraz te, które widzowie pokochali w ostatnich latach.");
        event10.setAddress("Teatr Korez w Katowicach");
        event10.setLatitude(50.2528947);
        event10.setLongitude(19.023369);
        event10.setDate(1519844400000L);
        event10.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":0,\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":0,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":6,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        eventRepository.save(event10);

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

        //testowy
        Comment comment = new Comment();
        comment.setRating(1);
        comment.setComment("Nudy");
        comment.setUser(user1);
        comment.setEvent(event1);
        commentRepository.save(comment);

        Comment comment7 = new Comment();
        comment7.setRating(6);
        comment7.setComment("Tak średnio");
        comment7.setUser(user1);
        comment7.setEvent(event2);
        commentRepository.save(comment7);

        Comment comment17 = new Comment();
        comment17.setRating(3);
        comment17.setComment("Może być ciekawe");
        comment17.setUser(user1);
        comment17.setEvent(event3);
        commentRepository.save(comment17);

        Comment comment18 = new Comment();
        comment18.setRating(2);
        comment18.setComment("Omijać szerokim łukiem!");
        comment18.setUser(user1);
        comment18.setEvent(event6);
        commentRepository.save(comment18);


        Comment comment8 = new Comment();
        comment8.setRating(2);
        comment8.setComment("Warto zobaczyć");
        comment8.setUser(user1);
        comment8.setEvent(event4);
        commentRepository.save(comment8);

        //Adam

        Comment comment3 = new Comment();
        comment3.setRating(2);
        comment3.setComment("...");
        comment3.setUser(user2);
        comment3.setEvent(event1);
        commentRepository.save(comment3);

        Comment comment4 = new Comment();
        comment4.setRating(5);
        comment4.setComment("No bez przesady");
        comment4.setUser(user2);
        comment4.setEvent(event2);
        commentRepository.save(comment4);

        Comment comment5 = new Comment();
        comment5.setRating(2);
        comment5.setComment("Będę :)");
        comment5.setUser(user2);
        comment5.setEvent(event3);
        commentRepository.save(comment5);

        Comment comment6 = new Comment();
        comment6.setRating(4);
        comment6.setComment("Trzeba być!");
        comment6.setUser(user2);
        comment6.setEvent(event4);
        commentRepository.save(comment6);

        Comment comment20 = new Comment();
        comment20.setRating(2);
        comment20.setComment("Trzeba być!");
        comment20.setUser(user2);
        comment20.setEvent(event5);
        commentRepository.save(comment20);


        Comment comment21 = new Comment();
        comment21.setRating(4);
        comment21.setComment("Trzeba być!");
        comment21.setUser(user2);
        comment21.setEvent(event6);
        commentRepository.save(comment21);

        Comment comment30 = new Comment();
        comment30.setRating(4);
        comment30.setComment("Trzeba być!");
        comment30.setUser(user2);
        comment30.setEvent(event7);
        commentRepository.save(comment30);

        Comment comment40 = new Comment();
        comment40.setRating(2);
        comment40.setComment("Nie wydaje mi się to ciekawe :/");
        comment40.setUser(user2);
        comment40.setEvent(event8);
        commentRepository.save(comment40);

        Comment comment50 = new Comment();
        comment50.setRating(5);
        comment50.setComment("Rock n roll!");
        comment50.setUser(user2);
        comment50.setEvent(event9);
        commentRepository.save(comment50);


        ///Marek
        Comment comment9 = new Comment();
        comment9.setRating(4);
        comment9.setComment("Mieszane odczucia");
        comment9.setUser(user3);
        comment9.setEvent(event1);
        commentRepository.save(comment9);

        Comment comment10 = new Comment();
        comment10.setRating(2);
        comment10.setComment("Same...");
        comment10.setUser(user3);
        comment10.setEvent(event2);
        commentRepository.save(comment10);

        Comment comment11 = new Comment();
        comment11.setRating(6);
        comment11.setComment("Na plus");
        comment11.setUser(user3);
        comment11.setEvent(event3);
        commentRepository.save(comment11);

        Comment comment12 = new Comment();
        comment12.setRating(2);
        comment12.setComment("Trzeba!");
        comment12.setUser(user3);
        comment12.setEvent(event4);
        commentRepository.save(comment12);

        Comment comment15 = new Comment();
        comment15.setRating(2);
        comment15.setComment("Szkoda zachodu :/");
        comment15.setUser(user3);
        comment15.setEvent(event6);
        commentRepository.save(comment15);

        Comment comment16 = new Comment();
        comment16.setRating(6);
        comment16.setComment("Może coś z tego będzie");
        comment16.setUser(user3);
        comment16.setEvent(event5);
        commentRepository.save(comment16);

        Comment comment31 = new Comment();
        comment31.setRating(5);
        comment31.setComment("Dobry mecz");
        comment31.setUser(user3);
        comment31.setEvent(event7);
        commentRepository.save(comment31);

        Comment comment41 = new Comment();
        comment41.setRating(1);
        comment41.setComment("Nie warto iść :/");
        comment41.setUser(user3);
        comment41.setEvent(event8);
        commentRepository.save(comment41);

        Comment comment51 = new Comment();
        comment51.setRating(5);
        comment51.setComment("Dobry początek 2018! ;)");
        comment51.setUser(user3);
        comment51.setEvent(event9);
        commentRepository.save(comment51);

        //Monika
        Comment comment13 = new Comment();
        comment13.setRating(4);
        comment13.setComment("Eh meh...");
        comment13.setUser(user4);
        comment13.setEvent(event1);
        commentRepository.save(comment13);

        Comment comment14 = new Comment();
        comment14.setRating(5);
        comment14.setComment("+/- średnie");
        comment14.setUser(user4);
        comment14.setEvent(event2);
        commentRepository.save(comment14);

        Comment comment22 = new Comment();
        comment22.setRating(2);
        comment22.setComment("+/- średnie");
        comment22.setUser(user4);
        comment22.setEvent(event3);
        commentRepository.save(comment22);

        Comment comment23 = new Comment();
        comment23.setRating(2);
        comment23.setComment("+/- średnie");
        comment23.setUser(user4);
        comment23.setEvent(event4);
        commentRepository.save(comment23);

        Comment comment24 = new Comment();
        comment24.setRating(1);
        comment24.setComment("+/- średnie");
        comment24.setUser(user4);
        comment24.setEvent(event5);
        commentRepository.save(comment24);

        Comment comment25 = new Comment();
        comment25.setRating(2);
        comment25.setComment("+/- średnie");
        comment25.setUser(user4);
        comment25.setEvent(event6);
        commentRepository.save(comment25);

        Comment comment32 = new Comment();
        comment32.setRating(6);
        comment32.setComment("Będzie co oglądać");
        comment32.setUser(user4);
        comment32.setEvent(event7);
        commentRepository.save(comment32);

        Comment comment42 = new Comment();
        comment42.setRating(3);
        comment42.setComment("Srednio :/");
        comment42.setUser(user4);
        comment42.setEvent(event8);
        commentRepository.save(comment42);

        Comment comment52 = new Comment();
        comment52.setRating(5);
        comment52.setComment("Trzeba być!");
        comment52.setUser(user4);
        comment52.setEvent(event9);
        commentRepository.save(comment52);


        ///FRIENDS

        Friend friend = new Friend();
        friend.setUser(user1);
        friend.setUsername("Monika");
        friend.setName("Monika");
        friendService.save(friend);

        Friend friend1 = new Friend();
        friend1.setUser(user1);
        friend1.setUsername("Adam");
        friend1.setName("Adam");
        friendService.save(friend1);

        Friend friend2 = new Friend();
        friend2.setUser(user1);
        friend2.setUsername("Marek");
        friend2.setName("Marek");
        friendService.save(friend2);


    }

    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("testowy" + i);
            user.setDisplayName("Testowy User");
            user.setProfile("{\"25\":" + new Random().nextInt(6) + ",\"10\":" + new Random().nextInt(6) + ",\"5\":" + new Random().nextInt(6) + ",\"14\":" + new Random().nextInt(6) + ",\"2\":" + new Random().nextInt(6) + ",\"19\":" + new Random().nextInt(6) + ",\"13\":" + new Random().nextInt(6) + ",\"8\":" + new Random().nextInt(6) + ",\"27\":" + new Random().nextInt(6) + ",\"6\":" + new Random().nextInt(6) + ",\"16\":" + new Random().nextInt(6) + ",\"28\":" + new Random().nextInt(6) + ",\"7\":" + new Random().nextInt(6) + ",\"4\":" + new Random().nextInt(6) + ",\"20\":" + new Random().nextInt(6) + ",\"17\":" + new Random().nextInt(6) + ",\"3\":" + new Random().nextInt(6) + ",\"22\":" + new Random().nextInt(6) + ",\"1\":" + new Random().nextInt(6) + ",\"29\":" + new Random().nextInt(6) + ",\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
            users.add(user);
        }

        userRepository.save(users);

        return users;
    }


    private List<Event> createEvents() {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Event event6 = new Event();
            event6.setName("wydarzenie " + i);
            event6.setDescription("Testowe wydarzenie " + i);
            event6.setAddress("Adres");
            event6.setDate(15431439610000L);
            int i1 = new Random().nextInt(3);
            if (i1 == 0) {
                createSportProfile(event6);
            } else if (i1 == 1) {
                createMusicProfile(event6);
            } else {
                createCultureProfile(event6);
            }
            event6.setLatitude(50.262774);
            event6.setLongitude(19.025127);
            events.add(event6);
        }
        eventRepository.save(events);
        return events;
    }

    private List<Comment> createComments(List<User> users, List<Event> events) {
        List<Comment> comments = new ArrayList<>();
        for (User user : users) {
            for (Event event : events) {
                Comment comment = new Comment();
                comment.setRating(1 + new Random().nextInt(6));
                comment.setComment("<opinia>");
                comment.setUser(user);
                comment.setEvent(event);
                comments.add(comment);
            }
        }
        commentRepository.save(comments);

        return comments;
    }

    private List<Friend> makeFriends(List<User> users) {
        List<Friend> friends = new ArrayList<>();
        for (User user : users) {
            for (int i = 0; i < 10; i++) {
                Friend friend = new Friend();
                friend.setUser(user);
                friend.setName("testowy" + i);
                friend.setUsername("testowy" + i);
                if (user.getUsername().equals(friend.getUsername())) {

                } else {
                    friends.add(friend);
                    friendService.save(friend);
                }
            }
        }

        return friends;
    }

    Event createSportProfile(Event event) {
        event.setName("Sportowe " + event.getName());
        event.setProfile("{\"25\":0,\"10\":" + new Random().nextInt(6) + ",\"5\":" + new Random().nextInt(6) + ",\"14\":0,\"2\":" + new Random().nextInt(6) + ",\"19\":0,\"13\":0,\"8\":" + new Random().nextInt(6) + ",\"27\":0,\"6\":" + new Random().nextInt(6) + ",\"16\":0,\"28\":0,\"7\":" + new Random().nextInt(6) + ",\"4\":" + new Random().nextInt(6) + ",\"20\":0,\"17\":0,\"3\":" + new Random().nextInt(6) + ",\"22\":0,\"1\":" + new Random().nextInt(6) + ",\"29\":0,\"21\":0,\"11\":" + new Random().nextInt(6) + ",\"9\":" + new Random().nextInt(6) + ",\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        return event;
    }

    Event createMusicProfile(Event event) {
        event.setName("Muzyczne " + event.getName());
        event.setProfile("{\"25\":0,\"10\":0,\"5\":0,\"14\":" + new Random().nextInt(6) + ",\"2\":0,\"19\":" + new Random().nextInt(6) + ",\"13\":" + new Random().nextInt(6) + ",\"8\":0,\"27\":0,\"6\":0,\"16\":" + new Random().nextInt(6) + ",\"28\":0,\"7\":0,\"4\":0,\"20\":" + new Random().nextInt(6) + ",\"17\":" + new Random().nextInt(6) + ",\"3\":0,\"22\":" + new Random().nextInt(6) + ",\"1\":0,\"29\":0,\"21\":" + new Random().nextInt(6) + ",\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":" + new Random().nextInt(6) + ",\"30\":0,\"18\":" + new Random().nextInt(6) + "}\t");
        return event;
    }


    Event createCultureProfile(Event event) {
        event.setName("Kulturowe " + event.getName());
        event.setProfile("{\"25\":" + new Random().nextInt(6) + ",\"10\":0,\"5\":0,\"14\":0,\"2\":0,\"19\":0,\"13\":0,\"8\":0,\"27\":" + new Random().nextInt(6) + ",\"6\":0,\"16\":0,\"28\":0,\"7\":0,\"4\":0,\"20\":0,\"17\":0,\"3\":0,\"22\":6,\"1\":0,\"29\":0,\"21\":0,\"11\":0,\"9\":0,\"24\":0,\"26\":0,\"15\":0,\"30\":0,\"18\":0}\t");
        return event;
    }

    private List<Friend> makeFriends(User user) {
        List<Friend> friends = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Friend friend = new Friend();
                friend.setUser(user);
                friend.setName("testowy" + i);
                friend.setUsername("testowy" + i);
                if (user.getUsername().equals(friend.getUsername())) {
                } else {
                    friends.add(friend);
                    friendService.save(friend);
                }

        }

        return friends;
    }

}
