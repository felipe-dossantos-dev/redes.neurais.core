package br.fipp.rede;

import br.fipp.entrada.LeitorEntradasCSV;
import br.fipp.entrada.LeitorEntradas;
import br.fipp.entrada.Entrada;
import br.fipp.entrada.Normalizacao;
import br.fipp.funcao.saida.FuncaoSaida;
import br.fipp.funcao.saida.FuncaoTangenteHiperb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author felipe
 */
public class RedeNeural {

    public static int QTD_NEURON_ENTRADA = 6;
    public static int QTD_NEURON_OCULTO = 5;
    public static int QTD_CLASSES_SAIDA = 5;
    public static double ERRO = 0.0001;
    public static int ITERACAO = 1000;
    public static double TX_APRENDIZAGEM = 0.3;

    private final int qtdClasses;
    private final int qtdEntrada;
    private final int qtdOcultos;
    private final double erroMinimo;
    private final double txAprendizagem;  // 0 < x <= 1
    private final int iteracao;
    private final Neuronio[] camadaOculta;
    private final Neuronio[] camadaSaida;
    private List<Entrada> listaEntrada;
    private List<Double> listaSomaErros;
    private Normalizacao normalizacao;
    private final LeitorEntradas leitor;
    private final FuncaoSaida funcaoSaida;

    public static void main(String[] args) throws IOException {
        //arquivo cam.entrada cam.saida cam.oculta -i|-e(iteracao| erro) num txApren func(-lin|-sig|-hip)(linear|sigmoide|hiperbolica)
        Locale.setDefault(Locale.US);

        RedeNeural rede = new RedeNeural(QTD_NEURON_ENTRADA,
                QTD_CLASSES_SAIDA,
                QTD_NEURON_OCULTO,
                ERRO,
                TX_APRENDIZAGEM,
                ITERACAO,
                new LeitorEntradasCSV("/home/felipe/Documents/IA/trabalho2/treinamento.csv"),
                new FuncaoTangenteHiperb());
        rede.treinar();
        LeitorEntradas le = new LeitorEntradasCSV("/home/felipe/Documents/IA/trabalho2/teste.csv");
        rede.avaliar(le);
    }

    private static int calcularQtdCamadaOculta(int entrada, int saida) {
        return (entrada + saida) / 2;
    }

    public RedeNeural(int qtdEntrada, int qtdClasses, int qtdOcultos,
            double erroMinimo, double txAprendizagem, int iteracao,
            LeitorEntradas leitor, FuncaoSaida fs) {
        this.qtdClasses = qtdClasses;
        this.qtdEntrada = qtdEntrada;
        this.erroMinimo = erroMinimo;
        this.txAprendizagem = txAprendizagem;
        this.iteracao = iteracao;
        this.listaSomaErros = new ArrayList<>();
        this.normalizacao = new Normalizacao(qtdEntrada);
        this.qtdOcultos = qtdOcultos;

        this.camadaOculta = new Neuronio[qtdOcultos];
        for (int i = 0; i < qtdOcultos; i++) {
            camadaOculta[i] = new Neuronio(qtdEntrada);
        }

        this.camadaSaida = new Neuronio[qtdClasses];
        for (int i = 0; i < qtdClasses; i++) {
            camadaSaida[i] = new Neuronio(qtdOcultos);
        }
        this.leitor = leitor;
        this.funcaoSaida = fs;
    }

    public void treinar() throws IOException {
        this.listaEntrada = leitor.lerEntradas();
        normalizacao = leitor.getNormalizacao();
        double erroAtual = 1000;
        for (int x = 0; x < this.iteracao && erroAtual > erroMinimo; x++) {
            for (Entrada ent : listaEntrada) {
                if (x == 0) {
                    ent.setEntradas(normalizacao.normalizar(ent.getEntradas()));
                }
                double saidasOcultas[] = new double[qtdOcultos];
                for (int i = 0; i < qtdOcultos; i++) {
                    double net = camadaOculta[i].gerarNet(ent.getEntradas());
                    double saida = funcaoSaida.calcular(net);
                    camadaOculta[i].setSaida(saida);
                    saidasOcultas[i] = saida;
                }
                for (int i = 0; i < qtdClasses; i++) {
                    double net = camadaSaida[i].gerarNet(saidasOcultas);
                    double saida = funcaoSaida.calcular(net);
                    camadaSaida[i].setSaida(saida);
                }
                double valorMaior = Double.MIN_VALUE;
                for (int i = 0; i < qtdClasses; i++) {
                    if (camadaSaida[i].getSaida() > valorMaior) {
                        valorMaior = camadaSaida[i].getSaida();
                    }
                }
                double sum = 0;
                for (int i = 0; i < qtdClasses; i++) {
                    Neuronio n = camadaSaida[i];
                    double erro;
                    if (ent.getResposta() - 1 != i) {
                        erro = (0 - n.getSaida()) * funcaoSaida.calcularDerivada(n.getNet());
                        n.setErro(erro);
                    } else {
                        erro = (1 - n.getSaida()) * funcaoSaida.calcularDerivada(n.getNet());
                        n.setErro(erro);
                    }
                    sum += Math.pow(n.getErro(), 2);
                }
                erroAtual = sum / 2;
                
                for (int i = 0; i < qtdOcultos; i++) {
                    Neuronio n = camadaOculta[i];
                    double erro = 0;
                    for (int k = 0; k < qtdClasses; k++) {
                        Neuronio s = camadaSaida[k];
                        erro += s.getErro() * s.getPesos()[i];
                    }
                    erro = erro * funcaoSaida.calcularDerivada(n.getNet());
                    n.setErro(erro);
                }
                for (int i = 0; i < qtdClasses; i++) {
                    Neuronio s = camadaSaida[i];
                    for (int k = 0; k < qtdOcultos; k++) {
                        double pesoAtual = s.getPesos()[k];
                        pesoAtual = pesoAtual + txAprendizagem * s.getErro() * camadaOculta[k].getSaida();
                        s.getPesos()[k] = pesoAtual;
                    }
                }
                for (int i = 0; i < qtdOcultos; i++) {
                    Neuronio n = camadaOculta[i];
                    for (int k = 0; k < qtdEntrada; k++) {
                        double pesoAtual = n.getPesos()[k];
                        pesoAtual = pesoAtual + txAprendizagem * n.getErro() * ent.getEntradas()[k];
                        n.getPesos()[k] = pesoAtual;
                    }
                }
            }
            listaSomaErros.add(erroAtual);
        }
    }

    public void avaliar(LeitorEntradas le) throws IOException {
        this.listaEntrada = le.lerEntradas();
        int qtdAcertos = 0;
        for (Entrada ent : listaEntrada) {
            ent.setEntradas(normalizacao.normalizar(ent.getEntradas()));
            //ida 
            //entrada -> oculto
            double saidasOcultas[] = new double[qtdOcultos]; //para ter os valores de saida da camada oculta
            for (int i = 0; i < qtdOcultos; i++) {
                double net = camadaOculta[i].gerarNet(ent.getEntradas());
                double saida = funcaoSaida.calcular(net);
                camadaOculta[i].setSaida(saida);
                saidasOcultas[i] = saida;
            }
            //oculto -> saida
            for (int i = 0; i < qtdClasses; i++) {
                double net = camadaSaida[i].gerarNet(saidasOcultas);
                double saida = funcaoSaida.calcular(net);
                camadaSaida[i].setSaida(saida);
            }
            //neuronio vencedor
            double valorMaior = Double.MIN_VALUE;
            int pos = -1;
            for (int i = 0; i < qtdClasses; i++) {
                if (camadaSaida[i].getSaida() > valorMaior) {
                    pos = i;
                    valorMaior = camadaSaida[i].getSaida();
                }
            }
            if (pos + 1 == ent.getResposta()) {
                qtdAcertos++;
            }
        }
        System.out.println("Porcentagem: " + ((double)qtdAcertos / listaEntrada.size()));
    }

    public List<Double> getListaSomaErros() {
        return listaSomaErros;
    }

    public void setListaSomaErros(List<Double> listaSomaErros) {
        this.listaSomaErros = listaSomaErros;
    }

    @Override
    public String toString() {
        return "RedesNeurais{" + "qtdClasses=" + qtdClasses + ", qtdEntrada=" + qtdEntrada + ", qtdOcultos=" + qtdOcultos + ", erroMinimo=" + erroMinimo + ", txAprendizagem=" + txAprendizagem + ", iteracao=" + iteracao + ", listaSomaErros=" + listaSomaErros + ", normalizacao=" + normalizacao + '}';
    }
}
