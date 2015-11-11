/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fipp.rede;

import br.fipp.entrada.Entrada;
import br.fipp.entrada.Normalizacao;
import java.util.List;

/**
 * Interface que deve ser implementada para gerar um leitor de entradas personalizado para 
 * a rede neural, devendo retornar uma lista de entradas, e um objeto normalizador, caso
 * o getNormalizacao() seja chamado antes do lerEntradas() deve-se retornar null.
 * @author felipe
 */
public interface LeitorEntradas {
    
    public List<Entrada> lerEntradas();
    public Normalizacao getNormalizacao();
  
}

