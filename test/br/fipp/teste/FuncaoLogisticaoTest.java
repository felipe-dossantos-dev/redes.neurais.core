/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fipp.teste;

import br.fipp.funcao.saida.FuncaoLogistica;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author felipe
 */
public class FuncaoLogisticaoTest {
    
    public FuncaoLogisticaoTest() {
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
    public void calcular() {
        FuncaoLogistica fl = new FuncaoLogistica();
        Assert.assertEquals(fl.calcular(0.458), 0.61253961344091512, 0.00001);
    }
    
    @Test
    public void calcular2() {
        FuncaoLogistica fl = new FuncaoLogistica();
        Assert.assertEquals(fl.calcular(-5), 0.006692850924284855, 0.000000000000000001);
    }
    
    @Test
    public void calcularDerivada1() {
        FuncaoLogistica fl = new FuncaoLogistica();
        Assert.assertEquals(fl.calcularDerivada(-5), 0.5, 0.01);
    }
}
