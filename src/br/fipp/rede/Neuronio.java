package br.fipp.rede;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author felipe
 */
public class Neuronio {
    
    private double pesos[]; //varia entre -2 <= x <= 2 
    private double net;
    private double saida;
    private double erro;

    public Neuronio(int qtdEntrada) {
        pesos = new double[qtdEntrada];
        Random r = new Random();
        for (int i = 0; i < qtdEntrada; i++) {
            pesos[i] = r.nextDouble() * 2 - 1;
        }
    }

    public double[] getPesos() {
        return pesos;
    }

    public void setPesos(double[] pesos) {
        this.pesos = pesos;
    }

    public double getNet() {
        return net;
    }

    public double getSaida() {
        return saida;
    }

    public void setSaida(double saida) {
        this.saida = saida;
    }

    public double getErro() {
        return erro;
    }

    public void setErro(double erro) {
        this.erro = erro;
    }
    
    public double gerarNet(double[] entradas) {
        if (entradas == null)
            throw new IllegalArgumentException("entradas nao pode ser null");
        if (pesos.length != entradas.length)
            throw new IllegalArgumentException("tamanho dos vetores de entradas diferente do pesos");
        double sum = 0;
        for (int i = 0; i < pesos.length; i++) {
            sum += pesos[i] * entradas[i];
        }
        net = sum;
        return sum;
    }

    @Override
    public String toString() {
        return "Neuronio{" + "pesos=" + Arrays.toString(pesos) + ", net=" + net + ", saida=" + saida + ", erro=" + erro + '}';
    }
}
