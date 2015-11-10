/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fipp.entrada;

import java.util.Arrays;

/**
 *
 * @author felipe
 */
public class Normalizacao {
    
    private double[] menores;
    private double[] maiores;
    private double[] intervalos;

    public Normalizacao(int qtdEntrada) {
        menores = new double[qtdEntrada];
        Arrays.fill(menores, Double.MAX_VALUE);
        
        maiores = new double[qtdEntrada];
        Arrays.fill(maiores, Double.MIN_VALUE);
        
        intervalos = null;
    }

    public double[] getMenores() {
        return menores;
    }

    public void setMenores(double[] menores) {
        this.menores = menores;
    }

    public double[] getMaiores() {
        return maiores;
    }

    public void setMaiores(double[] maiores) {
        this.maiores = maiores;
    }
    
    private void gerarIntervalo() {
        intervalos = new double[menores.length];
        for (int i = 0; i < menores.length; i++) {
            intervalos[i] = Math.abs(maiores[i] - menores[i]);
        }
    }
    
    public double[] normalizar(double[] vetor) {
        if (intervalos == null) {
            gerarIntervalo();
        }
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = (vetor[i] - menores[i]) / intervalos[i];
        }
        return vetor;
    }

    @Override
    public String toString() {
        return "Normalizacao{" + "menores=" + Arrays.toString(menores) + ", maiores=" + Arrays.toString(maiores) + ", intervalos=" + Arrays.toString(intervalos) + '}';
    }
}
