package com.roalmeida.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JogadorImpl {

    public boolean verificarArquivoExiste(Path caminho) {
        boolean ret = false;
        try {
            Stream<Path> stream = Files.list((caminho));
            Optional<Path> arq = stream.filter(path -> path.toString().endsWith("jogadores.txt")).findAny();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public List<Jogador> getListaDeJogadores(Path caminho) throws IOException {
        Stream<String> linhas = Files.lines(caminho, StandardCharsets.UTF_8);
        List<String> listaDeLinhas = linhas.collect(Collectors.toList());
        List<Jogador> listaDeJogadores = new ArrayList<>();
        Jogador jogador;
        Iterator it = listaDeLinhas.listIterator();
        String str = null;
        while (it.hasNext()) {
            str = (String) it.next();
            String info[] = str.split(",");
            jogador = new Jogador();
            jogador.setNome(info[0]);
            jogador.setPosicao(info[1]);
            jogador.setIdade(Integer.parseInt(info[2]));
            jogador.setTimeAtual(info[3]);
            jogador.setGolsMarcados(Integer.parseInt(info[4]));
            listaDeJogadores.add(jogador);
        }
        return listaDeJogadores;
    }

    public void imprimirJogadores(List<Jogador> jogadores) {
        jogadores.stream().forEach(System.out::println);
    }

    public void imprimirJogadoresTime(List<Jogador> jogadores, String time) {
        jogadores.stream().filter(jogador -> jogador.getTimeAtual().equals(time)).forEach(System.out::println);
    }

    public void imprimeTimeGols(List<Jogador> jogadores, String time) {
        jogadores.stream().filter(jogador -> jogador.getTimeAtual().equals(time) &&
                jogador.getGolsMarcados() > 10).forEach(System.out::println);
    }
    public void agruparJogadoresPorTime(List<Jogador> jogadores) {
        jogadores.stream().sorted(Comparator.comparing(Jogador::getTimeAtual)).forEach(System.out::println);
    }

    public double calcularMediaIdade(List<Jogador> jogadores) {
        return jogadores.stream().mapToInt(Jogador::getIdade).average().getAsDouble();
    }

    public void imprimirJogadorMaisVelho(List<Jogador> jogadores) {
        Jogador jogador = jogadores.stream().max(Comparator.comparingInt(Jogador::getIdade)).get();
        System.out.println("Jogador mais velho: " + jogador.getNome());
    }

    public void imprimirJogadorMaisNovo(List<Jogador> jogadores) {
        Jogador jogador = jogadores.stream().min(Comparator.comparingInt(Jogador::getIdade)).get();
        System.out.println("Jogador mais novo: " + jogador.getNome());
    }

    public void imprimirJogadorArtilheiro(List<Jogador> jogadores) {
        Jogador jogador = jogadores.stream().max(Comparator.comparingInt(Jogador::getGolsMarcados)).get();
        System.out.println("Jogador Artilheiro: " + jogador.getNome());
    }

    public int imprimirSomatorioGols(List<Jogador> jogadores) {
        int soma = jogadores.stream().mapToInt(jogador -> jogador.getGolsMarcados()).sum();
        return soma;
    }

    public void agruparJogadoresPeloTime(List<Jogador> jogadores) {
        Map<String, List<Jogador>> groupByTime = jogadores.stream().collect(Collectors.groupingBy(Jogador::getTimeAtual));
        System.out.println(groupByTime);
    }

    public void ordenarJogadoresGols(List<Jogador> jogadores) {
        jogadores.stream().sorted(Comparator.comparingInt(Jogador::getGolsMarcados).reversed()).forEach(System.out::println);
    }

}
