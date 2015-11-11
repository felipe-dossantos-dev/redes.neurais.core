/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fipp.rede;

import br.fipp.entrada.Entrada;
import br.fipp.entrada.Normalizacao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * O arquivo CSV deve estar no formato entrada,entrada,entrada,entrada,...,saida
 *
 * @author felipe
 */
public class LeitorEntradasCSV implements LeitorEntradas {

    private String filePath;
    private Normalizacao normalizacao;

    public LeitorEntradasCSV() {
    }

    public LeitorEntradasCSV(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Entrada> lerEntradas() {
        ArrayList<Entrada> entradas = new ArrayList<>();
        BufferedReader entrada;
        try {
            entrada = new BufferedReader(
                    new FileReader(filePath));
            String linha = entrada.readLine();
            String vet[] = linha.split(",");
            int qtdEntrada = vet.length - 1;
            normalizacao = new Normalizacao(qtdEntrada);
            linha = entrada.readLine();
            while (linha != null) {
                vet = linha.split(",");
                Entrada t = new Entrada(qtdEntrada);
                double[] ents = new double[qtdEntrada];
                for (int i = 0; i < qtdEntrada; i++) {
                    Double x = new Double(vet[i]);
                    ents[i] = x;
                    if (x > normalizacao.getMaiores()[i]) {
                        normalizacao.getMaiores()[i] = x;
                    }
                    if (x < normalizacao.getMenores()[i]) {
                        normalizacao.getMenores()[i] = x;
                    }
                }
                t.setEntradas(ents);
                t.setResposta(new Integer(vet[qtdEntrada]));
                entradas.add(t);
                linha = entrada.readLine();
            }
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(LeitorEntradasCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entradas;
    }

    @Override
    public Normalizacao getNormalizacao() {
        return normalizacao;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
