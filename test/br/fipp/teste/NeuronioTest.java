package br.fipp.teste;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.fipp.funcao.saida.FuncaoLinear;
import br.fipp.funcao.saida.FuncaoSaida;
import br.fipp.rede.Neuronio;
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
public class NeuronioTest {
    
    private Neuronio neuronio;
    private Neuronio neuronio2;
    private Neuronio neuronio3;
    
    public NeuronioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        neuronio = new Neuronio(2);
        double pesos[] = {1.1, -1.4};
        neuronio.setPesos(pesos);
        
        neuronio2 = new Neuronio(2);
        double pesos2[] = {3.6, -2.1};
        neuronio2.setPesos(pesos2);
        
        neuronio3 = new Neuronio(4);
        double pesos3[] = {1.2, 1.6, 4.3, 3.2};
        neuronio3.setPesos(pesos3);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void gerarNetTest1() {
        double ents[] = {0, 1};
        double saida = neuronio.gerarNet(ents);
        Assert.assertEquals(saida, -1.4, 0.000001);
    }
    
    @Test
    public void gerarNetTest2() {
        double ents[] = {1, 0};
        double saida = neuronio.gerarNet(ents);
        Assert.assertEquals(saida, 1.1, 0.000001);
    }
    
    @Test
    public void gerarNetTest3() {
        double ents[] = {1, 0};
        double saida = neuronio2.gerarNet(ents);
        Assert.assertEquals(saida, 3.6, 0.000001);
    }
    
    @Test
    public void gerarNetTest4() {
        double ents[] = {0, 1};
        double saida = neuronio2.gerarNet(ents);
        Assert.assertEquals(saida, -2.1, 0.000001);
    }
    
    @Test
    public void gerarNetTest5() {
        double ents[] = {-1.4, -4.1, 2.5, -1};
        double saida = neuronio3.gerarNet(ents);
        Assert.assertEquals(saida, -0.69, 0.000001);
    }
 
    public void gerarNovoPeso() {
        FuncaoSaida fs = new FuncaoLinear(1);
        Neuronio n = new Neuronio(1);
        n.setErro(1.69);
        double pesos[] = {-1.4};
        n.setPesos(pesos);
        double txAprendizado = 1;
        double ic = 1.4;
        double novoErro = n.getPesos()[0] + txAprendizado * n.getErro() * ic;
        Assert.assertEquals(novoErro, -1.166, 0.001);
    }
}
