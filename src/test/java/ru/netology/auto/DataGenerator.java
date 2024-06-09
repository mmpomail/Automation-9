package ru.netology.auto;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {

    }

    public static String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {

        var faker = new Faker(new Locale(locale));
        return faker.address().cityName();
    }

    public static String generateName(String locale) {

        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();


        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
    }

    public static String generatePhone(String locale) {
        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {

            return new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }


}
