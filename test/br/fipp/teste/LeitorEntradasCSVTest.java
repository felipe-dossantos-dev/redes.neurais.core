/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fipp.teste;

import br.fipp.entrada.Entrada;
import br.fipp.entrada.Normalizacao;
import br.fipp.entrada.LeitorEntradasCSV;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author felipe
 */
public class LeitorEntradasCSVTest {

    public LeitorEntradasCSVTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void leitorUmaEntrada() {
        LeitorEntradasCSV leitor = new LeitorEntradasCSV("/home/felipe/Documents/IA/trabalho2/testeLeitura1.csv");
        List<Entrada> ents = leitor.lerEntradas();

        double entrs[] = {1, 19, 35, 28, 17, 4};

        Assert.assertArrayEquals(entrs, ents.get(0).getEntradas(), 0.000001);
    }

    @Test
    public void leitorMaisDeUmaEntrada() {
        LeitorEntradasCSV leitor = new LeitorEntradasCSV("/home/felipe/Documents/IA/trabalho2/testeLeitura2.csv");

        List<Entrada> ents = leitor.lerEntradas();
        Normalizacao n = leitor.getNormalizacao();
        double entrs[] = {1, 19, 35, 28, 17, 4};
        double entrs2[] = {20, 83, 58, 84, 85, 67};
        
        double maiores[] = {20, 83, 58, 84, 85, 67};
        double menores[] = {1, 19, 35, 28, 17, 4};

        Assert.assertArrayEquals(entrs, ents.get(0).getEntradas(), 0.000001);
        Assert.assertArrayEquals(entrs2, ents.get(1).getEntradas(), 0.000001);
        Assert.assertArrayEquals(maiores, n.getMaiores(), 0.000001);
        Assert.assertArrayEquals(menores, n.getMenores(), 0.000001);
    }
}
