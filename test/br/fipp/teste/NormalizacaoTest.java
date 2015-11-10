package br.fipp.teste;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.fipp.entrada.Entrada;
import br.fipp.entrada.Normalizacao;
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
public class NormalizacaoTest {

    private Entrada ent;
    private Normalizacao norm;

    public NormalizacaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        norm = new Normalizacao(4);
        double[] maxs = {50, 1, 46.5, 2};
        double[] mins = {2, 0, 1.5, 1};
        norm.setMaiores(maxs);
        norm.setMenores(mins);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void normalizar1() {
        ent = new Entrada(4);
        double[] entradas = {10, 1, 33.4, 1};
        ent.setEntradas(entradas);
        double saidaTeste[] = {0.1667, 1, 0.7089, 0};
        double resp[] = norm.normalizar(entradas);
        Assert.assertArrayEquals(saidaTeste, resp, 0.0001);
    }

    @Test
    public void normalizar2() {
        ent = new Entrada(4);
        double[] entradas = {5, 0, 18.4, 1};
        ent.setEntradas(entradas);
        double saidaTeste[] = {0.0625, 0, 0.3755555556, 0};
        double resp[] = norm.normalizar(entradas);
        Assert.assertArrayEquals(saidaTeste, resp, 0.0001);
    }

    @Test
    public void normalizar3() {
        ent = new Entrada(4);
        double[] entradas = {2, 1, 19.5, 2};
        ent.setEntradas(entradas);
        double saidaTeste[] = {0, 1, 0.4, 1};
        double resp[] = norm.normalizar(entradas);
        Assert.assertArrayEquals(saidaTeste, resp, 0.0001);
    }
    
    @Test
    public void normalizar4() {
        ent = new Entrada(4);
        double[] entradas = {2, 1, 19.5, 2};
        ent.setEntradas(entradas);
        double saidaTeste[] = {0, 1, 0.4, 1};
        double resp[] = norm.normalizar(entradas);
        Assert.assertArrayEquals(saidaTeste, resp, 0.0001);
    }
}
