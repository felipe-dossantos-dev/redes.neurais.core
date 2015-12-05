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
public class FuncaoLinear implements FuncaoSaida{

    private final double constante;
    
    public FuncaoLinear(double constante) {
        this.constante = constante;
    }

    @Override
    public double calcular(double net) {
        return net / constante;
    }

    @Override
    public double calcularDerivada(double net) {
        return 1 / constante;
    }
    
}
