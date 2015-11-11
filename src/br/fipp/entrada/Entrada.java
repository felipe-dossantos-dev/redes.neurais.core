/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fipp.entrada;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author felipe
 */
public class Entrada {
    private double[] entradas;
    private String resposta;
    
    public Entrada(int qtdEntrada) {
        entradas = new double[qtdEntrada];
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public double[] getEntradas() {
        return entradas;
    }

    public void setEntradas(double[] entradas) {
        this.entradas = entradas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.hashCode(this.entradas);
        hash = 97 * hash + Objects.hashCode(this.resposta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entrada other = (Entrada) obj;
        if (!Arrays.equals(this.entradas, other.entradas)) {
            return false;
        }
        if (!Objects.equals(this.resposta, other.resposta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entrada{" + "entradas=" + Arrays.toString(entradas) + ", resposta=" + resposta + '}';
    }
}
