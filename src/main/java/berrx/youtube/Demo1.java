package berrx.youtube;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class Demo1 {


    public static void main(String[] args) {

        List<User> users = List.of(
                new User("Anton", LocalDate.now().minusDays(5)),
                new User("Josh", LocalDate.now().minusDays(3)),
                new User("Jackal", LocalDate.now().minusDays(4)),
                new User("Kelly", LocalDate.now().minusDays(2)),
                new User("Dos", LocalDate.now().minusDays(3))
        );

        users
                .stream()
                .filter(u -> u.signInDate.plusDays(3).compareTo(LocalDate.now()) >= 0 )
                .sorted(Comparator
                        .comparingInt((User u) -> u.getName().length()).reversed())
                .collect(Collectors.toList())
                .forEach(System.out::println);


//        ThreadPoolExecutor;
//        Iterator
//        HashMap
    }

    @Data
    @AllArgsConstructor
    static class User {
        private String name;
        private LocalDate signInDate;
    }
}
