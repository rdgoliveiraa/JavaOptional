package com.roalmeida.optional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ModemRobotTest {


    @Test
    public void whenFilterWithoutOptional_thenCorrect() {
        ModemRobot modemRobot = new ModemRobot();
        assertTrue(modemRobot.priceIsInRange1(new Modem(10.0)));
        assertFalse(modemRobot.priceIsInRange1(new Modem(9.9)));
        assertFalse(modemRobot.priceIsInRange1(new Modem(null)));
        assertFalse(modemRobot.priceIsInRange1(new Modem(15.5)));
        assertFalse(modemRobot.priceIsInRange1(null));
    }

    @Test
    public void whenFiltersWithOptional_thenCorrect() {
        ModemRobot modemRobot = new ModemRobot();
        assertTrue(modemRobot.priceIsInRange2(new Modem(10.0)));
        assertFalse(modemRobot.priceIsInRange2(new Modem(9.9)));
        assertFalse(modemRobot.priceIsInRange2(new Modem(null)));
        assertFalse(modemRobot.priceIsInRange2(new Modem(15.5)));
        assertFalse(modemRobot.priceIsInRange2(null));
    }

    @Test
    public void givenOptional_whenMapWorks_thenCorrect() {
        List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);
        int size = listOptional.map(List::size)
                .orElse(0);
        assertEquals(6, size);
    }

    @Test
    public void givenOptional_whenMapWorks_thenCorrect2() {
        String name = "baelgung";
        Optional<String> nameOptional = Optional.of(name);

        int len = nameOptional.map(String::length).orElse(0);
        assertEquals(8, len);
    }

    @Test
    public void givenOptional_whenMaoWorksWithFilter_thenCorrect() {
        String password = "password ";
        Optional<String> passOpt = Optional.of(password);
        boolean correctPassword = passOpt.filter(pass -> pass.equals("password")).isPresent();
        assertFalse(correctPassword);

        correctPassword = passOpt.map(String::trim).filter(pass -> pass.equals("password")).isPresent();
        assertTrue(correctPassword);
    }

    @Test void givenOptional_whenFlatMapWorks_thenCorrect2() {
        Person person = new Person("john", 26);
        Optional<Person> personOptional = Optional.of(person);

        Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getName);
        Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
        String name1 = nameOptional.orElse("");
        assertEquals("john", name1);

        String name = personOptional.flatMap(Person::getName).orElse("");
        assertEquals("john", name);
    }

}