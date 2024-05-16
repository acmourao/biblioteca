package com.pucpr.exercicio.controller;

import com.pucpr.exercicio.entity.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("exercicio")
public class ExercicioController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/helloAdmin")
    public String helloAdmin() {
        return "hello admin";
    }

    @GetMapping("/dates")
    public String getDateDifference(@RequestParam String startDate,
                                    @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        long daysDifference = ChronoUnit.DAYS.between(start, end);
        long weeksDifference = ChronoUnit.WEEKS.between(start, end);
        long monthsDifference = ChronoUnit.MONTHS.between(start, end);

        return "{" +
                "\"days\": " + daysDifference + ", " +
                "\"weeks\": " + weeksDifference + ", " +
                "\"months\": " + monthsDifference +
                "}";
    }

    @GetMapping("/sort")
    public String getNumbers(@RequestParam String numbers){
        String[] numbersArray = numbers.split(",");
        List<Integer> numbersInt = Arrays.stream(numbersArray).
                map(Integer::parseInt).toList();
        List<Integer> sorted = numbersInt.stream().sorted().toList();
        List<Integer> sortedReversed = numbersInt.stream().sorted((a, b) -> b - a).toList();
        List<Integer> evenNumbers = numbersInt.stream().filter(n -> n % 2 == 0).toList();

        return "{" +
                "\"sorted\": " + sorted + ", \n" +
                "\"sortedReversed\": " + sortedReversed + ", \n" +
                "\"evenNumbers\": " + evenNumbers + " \n" +
                        "}";
    }
    @GetMapping("/mimimi")
    public String getMimimi(@RequestParam String text){
        String[] vowels = {"a", "e", "o", "u"};
        String mimimi = text;
        for (String vowel : vowels) {
            mimimi = mimimi.replaceAll(vowel, "i");
            mimimi = mimimi.replaceAll(vowel.toUpperCase(), "I");
        }
        String[] agudos = {"á", "é", "ó", "ú"};
        for (String  agudo : agudos) {
            mimimi = mimimi.replaceAll(agudo, "í");
        }

        return mimimi;
    }
}
