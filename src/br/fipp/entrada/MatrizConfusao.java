/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fipp.entrada;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author felipe.santos
 */
public class MatrizConfusao {
    
    private final int matrizConfusao[][];
    private final Map<String, Integer> mapa;
    
    public MatrizConfusao(Set<String> conjunto) {
        matrizConfusao = new int[conjunto.size()][conjunto.size()];
        mapa = new HashMap<>();
        int count = 0;
        for (String valor : conjunto) {
            mapa.put(valor, count);
            count++;
        }
    }

    public void contar(String esperado, String resultado) {
        int esp = mapa.get(esperado);
        int res = mapa.get(resultado);
        matrizConfusao[esp][res]++;
    }
    
    public int[][] getMatriz() {
        return matrizConfusao;
    }
}
