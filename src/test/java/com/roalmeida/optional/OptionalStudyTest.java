package com.roalmeida.optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OptionalStudyTest {

    private Optional<String> getEmpty() {
        return Optional.empty();
    }

    private Optional<String> getHello() {
        return Optional.of("hello");
    }

    private Optional<String> getBye() {
        return Optional.of("bye");
    }

    private Optional<String> createOptional(String input) {
        if (input == null || "".equals(input) || "empty".equals(input)) {
            return Optional.empty();
        }
        return Optional.of(input);
    }

    @Test
    public void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturned() {
        Optional<String> found = Stream.of(getEmpty(), getHello(), getBye())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        assertEquals(getHello(), found);
    }

    @Test
    public void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturnedAndRestNotEvaluated() {
        Optional<String> found = Stream.<Supplier<Optional<String>>>of(this::getEmpty, this::getHello, this::getBye)
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        assertEquals(getHello(), found);
    }

    @Test
    public void givenTwoEmptyOptionals_whenChaining_thenDefaultIsReturned() {
        String found = Stream.<Supplier<Optional<String>>>of(
                () -> createOptional("empty"),
                () ->createOptional("empty")
        )
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseGet(() -> "default");

        assertEquals("default", found);
    }

    @Test
    public void givenOptional_whenPresent_thenShouldTakeAValueFromIt() {
        //given
        String expected = "properValue";
        Optional<String> value = Optional.of(expected);
        Optional<String> defaultValue = Optional.of("default");

        //when
        Optional<String> result = value.or(() -> defaultValue);

        //then
        org.assertj.core.api.Assertions.assertThat(result.get()).isEqualTo(expected);
    }

    @Test
    public void givenOptional_whenEmpty_thenShouldTakeAValueFromOr() {
        //given
        String defaultString = "default";
        Optional<String> value = Optional.empty();
        Optional<String> defaultValue = Optional.of(defaultString);

        //when
        Optional<String> result = value.or(() -> defaultValue);

        //then
        org.assertj.core.api.Assertions.assertThat(result.get()).isEqualTo(defaultString);
    }

    @Test
    public void givenOptional_whenNotPresent_thenShouldExecuteProperCallback() {
        //given
        Optional<String> value = Optional.empty();
        AtomicInteger successCounter = new AtomicInteger(0);
        AtomicInteger onEmptyOptionalCounter = new AtomicInteger(0);

        //when
        value.ifPresentOrElse(
                v -> successCounter.incrementAndGet(),
                onEmptyOptionalCounter::incrementAndGet);

        //then
        org.assertj.core.api.Assertions.assertThat(successCounter.get()).isEqualTo(0);
        org.assertj.core.api.Assertions.assertThat(onEmptyOptionalCounter.get()).isEqualTo(1);
    }

    @Test
    public void givenOptionalOfSome_whenToStream_thenShouldTreatItAsOneElementsStream() {
        //given
        Optional<String> value = Optional.of("a");

        //when
        List<String> collect = value.stream().map(String::toUpperCase).collect(Collectors.toList());

        //then
        org.assertj.core.api.Assertions.assertThat(collect).hasSameElementsAs(List.of("A"));
    }

    @Test
    public void givenOptionalOfNone_whenToStream_thenShouldTreatItAsZeroElementStream() {
        //given
        Optional<String> value = Optional.empty();

        //when
        List<String> collect = value.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        //then
        org.assertj.core.api.Assertions.assertThat(collect).isEmpty();
    }
}