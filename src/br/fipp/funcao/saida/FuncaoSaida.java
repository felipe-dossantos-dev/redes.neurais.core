/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fipp.funcao.saida;

/**
 * Interface da Funcao de Transferencia da Rede Neural
 * @author felipe
 */
public interface FuncaoSaida {
    public double calcular(double net);
    public double calcularDerivada(double net);
}
