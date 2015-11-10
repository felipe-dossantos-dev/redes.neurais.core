/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fipp.funcao.saida;

/**
 *
 * @author felipe
 */
public class FuncaoTangenteHiperb implements FuncaoSaida{

    @Override
    public double calcular(double net) {
        double v = Math.exp(-2 * net);
        return (1 - v) / (1 + v);
    }

    @Override
    public double calcularDerivada(double net) {
        return 1 - Math.pow(this.calcular(net), 2);
    }
    
}
