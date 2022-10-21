package com.roalmeida.stream;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JogadorImpl jogImpl = new JogadorImpl();
        String enderecoDir = "/home/rodrigo/Downloads";
        String nomeArquivo = "jogadores";
        try {
            List<Jogador> listaDeJogadores = jogImpl.getListaDeJogadores(Paths.get(enderecoDir + "/" + nomeArquivo));
            jogImpl.imprimirJogadorArtilheiro(listaDeJogadores);
            jogImpl.imprimirJogadorMaisVelho(listaDeJogadores);
            jogImpl.imprimirJogadorMaisNovo(listaDeJogadores);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*List<Pessoa> pessoas = new Pessoa().populaPessoas();
        Stream<Pessoa> stream = pessoas.stream().filter(pessoa -> pessoa.getNacionalidade().equals("Brasil"));
        stream.forEach(pessoa -> System.out.println(pessoa.getNome()));*/

        //Exemplo de map utilizando uma lista de Stream<T>
        /*List<Pessoa> pessoas1 = new Pessoa().populaPessoas();
        Stream<Integer> stream1 = pessoas1.stream().filter(
                pessoa -> pessoa.getNacionalidade().equals("Brasil")
                ).map(Pessoa::getIdade);*/

        //Exemplo de map utilizando uma lista com tipo Stream com tipo primitivo(ex de tipos possíveis: IntStream, DoubleStream, LongStream)
        /*List<Pessoa> pessoas2 = new Pessoa().populaPessoas();
        IntStream stream2 = pessoas2.stream().filter(
                pessoa2 -> pessoa2.getNacionalidade().equals("Brasil")
        ).mapToInt(Pessoa::getIdade);
        System.out.println(stream2.toArray().length);*/

        /*List<Pessoa> pessoas = new Pessoa().populaPessoas();
        Stream<Pessoa> stream = pessoas.stream().filter(pessoa -> pessoa.getNacionalidade().equals("Brasil")).sorted(Comparator.comparing(Pessoa::getNome));
        stream.forEach(pessoa -> System.out.println(pessoa.getNome()));*/

        /*List<Pessoa> pessoas = new Pessoa().populaPessoas();
        Stream<Pessoa> stream = pessoas.stream().distinct();
        stream.forEach(pessoa -> System.out.println(pessoa.getNome()));*/

        /*List<Pessoa> pessoas = new Pessoa().populaPessoas();
        Stream<Pessoa> stream = pessoas.stream().limit(2);
        stream.forEach(pessoa -> System.out.println(pessoa.getNome()));*/

        /*List<Pessoa> pessoas = new Pessoa().populaPessoas();
        double media = pessoas.stream().filter(
                pessoa -> pessoa.getNacionalidade().equals("Brasil")
            ).mapToInt(pessoa -> pessoa.getIdade()).average().getAsDouble();
        System.out.println(media);*/

        /*List<Pessoa> pessoas = new Pessoa().populaPessoas();
        List<Pessoa> pessoasComM = pessoas.stream().filter(
                pessoa -> pessoa.getNome().startsWith("M")
            ).collect(Collectors.toList());
        pessoasComM.stream().forEach(pessoa -> System.out.println(pessoa.getNome()));*/

        /*List<Pessoa> pessoas = new Pessoa().populaPessoas();
        long qt = pessoas.stream().filter(pessoa -> pessoa.getNome().startsWith("N")).count();
        System.out.println(qt);*/

        /*List<Pessoa> pessoas = new Pessoa().populaPessoas();
        boolean todosMexicanos = pessoas.stream().allMatch(pessoa -> pessoa.getNacionalidade().equals("Mexico"));
        System.out.println(todosMexicanos);*/
    }
}