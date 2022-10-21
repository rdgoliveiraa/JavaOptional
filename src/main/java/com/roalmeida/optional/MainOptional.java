package com.roalmeida.optional;

import java.util.Optional;

public class MainOptional {

    public static void main(String[] args) {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent());

        String name = "baeldung";
        Optional<String> opt = Optional.of(name);
        System.out.println(opt.isPresent());

        // Optional não aceita objetos nulos na criação
        /**String nameNull = null;
        Optional.of(nameNull);**/

        // Para contornar esse problema existe o método .ofNullable que trata o Optional como vazio
        String nameNull = null;
        Optional<String> optNullable = Optional.ofNullable(nameNull);
        System.out.println(optNullable.isPresent());

        // Para verificar a existencia de valor de um Optional é utilizado o método .isPresent
        Optional<String> optPresent = Optional.of("Baeldung");
        System.out.println(optPresent.isPresent());
        optPresent = Optional.ofNullable(null);
        System.out.println(optPresent.isPresent());

        // A partir do java 11 existe o método .isEmpty que verifica o oposto
        Optional<String> optEmpty = Optional.of("Baeldung");
        System.out.println(optEmpty.isEmpty());
        optEmpty = Optional.ofNullable(null);
        System.out.println(optEmpty.isEmpty());

        // Para executar algum código no valor encapsulado caso o mesmo não seja nulo é possível utilizar o método .ifPresent()
        Optional<String> optIfPresent = Optional.of("baeldung");
        opt.ifPresent(nameIfPresent -> System.out.println(nameIfPresent.length()));

        String nullName = null;
        System.out.println(Optional.ofNullable(nullName).orElse("john"));

        System.out.println(Optional.ofNullable(nullName).orElseGet(() -> "john"));

        OrElseAndOrElseGetDiffer orElseAndOrElseGetDiffer = new OrElseAndOrElseGetDiffer();
        orElseAndOrElseGetDiffer.whenOrElseGetAndOrElseDiffer();


        //String name1 = Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new);
        //Java 10 introduziu uma versão simplificada do método .orElseThrow
        //String name2 = Optional.ofNullable(nullName).orElseThrow();

        /**
         * Esta é a principal falha do método get() . Idealmente, Opcional deve nos ajudar a evitar tais exceções imprevistas.
         * Portanto, essa abordagem funciona de acordo com os objetivos de Optional e provavelmente será preterida em uma versão futura.
         *
         * Portanto, é aconselhável usar as outras variantes que nos permitem preparar e lidar explicitamente com o caso nulo .
         */
        //Optional<String> optNull = Optional.ofNullable(null);
        //String name3 = optNull.get();

        Integer year = 2016;
        Optional<Integer> yearOptional = Optional.of(year);
        boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
        System.out.println(is2016);
        boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
        System.out.println(is2017);
    }


}
