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
public class FuncaoLogistica implements FuncaoSaida{

    @Override
    public double calcular(double net) {    
        return 1 / (1 + Math.exp(-net));
    }

    @Override
    public double calcularDerivada(double net) {
        return calcular(net) * (1 - calcular(net));
    }
    
}
