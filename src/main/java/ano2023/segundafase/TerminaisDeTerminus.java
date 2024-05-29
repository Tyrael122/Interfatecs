package ano2023.segundafase;

import util.TestCaseReader;

import java.util.*;

public class TerminaisDeTerminus {
    private final Espaconave[] terminais = new Espaconave[7];
    private final StringBuilder sb = new StringBuilder();
    private final List<Espaconave> filaDeEspaconaves = new ArrayList<>();

    public String main(TestCaseReader reader) {
        List<Entrada> entradas = new ArrayList<>();
        while (reader.hasNext()) {
            String placa = reader.nextString();
            int terminal = reader.nextInt();
            int tempoEstelar = reader.nextInt();

            entradas.add(new Entrada(placa, terminal, tempoEstelar));
        }

        entradas.sort(Comparator.comparingInt(Entrada::tempoEstelar));

        for (Entrada entrada : entradas) {
            String placa = entrada.placa();

            int terminal = entrada.terminal();
            int tempoEstelar = entrada.tempoEstelar();

            int terminalAlocado = encontrarTerminalComEspaconave(placa);

            if (terminal == 0) {
                Espaconave espaconaveComMesmaPlaca;
                if (terminalAlocado != -1) {
                    espaconaveComMesmaPlaca = terminais[terminalAlocado];
                    esvaziarTerminal(terminalAlocado);

                } else {
                    int posicaoNaFila = procurarNaFila(placa);
                    espaconaveComMesmaPlaca = filaDeEspaconaves.get(posicaoNaFila);

                    filaDeEspaconaves.remove(posicaoNaFila);

                    espaconaveComMesmaPlaca.setComAdicionalDeMudanca(true);
                }

                mostrarTarifaNave(espaconaveComMesmaPlaca, tempoEstelar);

                continue;
            }

            Espaconave espaconave = new Espaconave(placa, tempoEstelar);
            alocarNave(espaconave, terminal, terminalAlocado);
        }

        return sb.toString();
    }

    private int procurarNaFila(String placa) {
        for (int i = 0; i < filaDeEspaconaves.size(); i++) {
            Espaconave espaconave = filaDeEspaconaves.get(i);
            if (placa.equals(espaconave.getPlaca())) {
                return i;
            }
        }

        return -1;
    }

    private void alocarNave(Espaconave espaconaveParaAlocar, int terminal, int terminalAlocado) {
        boolean mudouDeTerminal = terminalAlocado != -1;
        if (mudouDeTerminal) {
            espaconaveParaAlocar = terminais[terminalAlocado];

            esvaziarTerminal(terminalAlocado);

            espaconaveParaAlocar.setComAdicionalDeMudanca(true);
        }

        Espaconave espaconaveAlocada = terminais[terminal - 1];
        if (espaconaveAlocada == null) {
            terminais[terminal - 1] = espaconaveParaAlocar;

            return;
        }

        espaconaveParaAlocar.setComAdicionalDeEspera(true);
        espaconaveParaAlocar.setTerminalParaIr(terminal);
        filaDeEspaconaves.add(espaconaveParaAlocar);
    }

    private void esvaziarTerminal(int terminalParaEsvaziar) {
        terminais[terminalParaEsvaziar] = null;

        for (int i = 0; i < filaDeEspaconaves.size(); i++) {
            Espaconave espaconave = filaDeEspaconaves.get(i);
            if (espaconave.getTerminalParaIr() - 1 == terminalParaEsvaziar) {
                terminais[terminalParaEsvaziar] = espaconave;

                filaDeEspaconaves.remove(i);

                break;
            }
        }
    }

    private void mostrarTarifaNave(Espaconave espaconave, int tempoFinal) {
        double tarifa = calcularTarifa(espaconave, tempoFinal);

        sb.append(espaconave.getPlaca()).append(" ").append(truncateTo(tarifa));

        if (espaconave.isComAdicionalDeEspera()) {
            sb.append(" ").append("E");
        }

        if (espaconave.isComAdicionalDeMudanca()) {
            sb.append(" ").append("X");
        }

        sb.append("\n");
    }

    private static double calcularTarifa(Espaconave espaconave, int tempoFinal) {
        double tarifa = calcularTarifaBase(espaconave.getTempoEstelarChegada(), tempoFinal);
        double adicionalMudanca = 0;

        if (espaconave.isComAdicionalDeMudanca()) {
            adicionalMudanca = tarifa * .07;
        }

        double adicionalEspera = 0;
        if (espaconave.isComAdicionalDeEspera()) {
            adicionalEspera = tarifa * .05;
        }

        tarifa += adicionalMudanca + adicionalEspera;

        return tarifa;
    }

    private static double calcularTarifaBase(double tempoEstelarChegada, double tempoEstelarFinal) {
        double tempoEstadia = tempoEstelarFinal - tempoEstelarChegada;
        double tempoGalaxia = tempoEstadia / 123457;

        return tempoGalaxia * 4.59 * 1000;
    }

    private int encontrarTerminalComEspaconave(String placa) {
        for (int i = 0; i < terminais.length; i++) {
            Espaconave espaconave = terminais[i];
            if (espaconave == null) continue;

            if (placa.equals(espaconave.getPlaca())) {
                return i;
            }
        }

        return -1;
    }

    private static String truncateTo(double number) {
        String formatString = "%." + 2 + "f";
        return String.format(formatString, number);
    }
}

class Espaconave {
    private final double tempoEstelarChegada;
    private final String placa;

    private int terminalParaIr;

    private boolean comAdicionalDeMudanca = false;
    private boolean comAdicionalDeEspera = false;

    public Espaconave(String placa, int tempoEstelarChegada) {
        this.placa = placa;
        this.tempoEstelarChegada = tempoEstelarChegada;
    }

    public double getTempoEstelarChegada() {
        return tempoEstelarChegada;
    }

    public String getPlaca() {
        return placa;
    }

    public void setComAdicionalDeEspera(boolean comAdicionalDeEspera) {
        this.comAdicionalDeEspera = comAdicionalDeEspera;
    }

    public boolean isComAdicionalDeEspera() {
        return comAdicionalDeEspera;
    }

    public boolean isComAdicionalDeMudanca() {
        return comAdicionalDeMudanca;
    }

    public void setComAdicionalDeMudanca(boolean comAdicionalDeMudanca) {
        this.comAdicionalDeMudanca = comAdicionalDeMudanca;
    }

    public int getTerminalParaIr() {
        return terminalParaIr;
    }

    public void setTerminalParaIr(int terminalParaIr) {
        this.terminalParaIr = terminalParaIr;
    }
}

record Entrada(String placa, int terminal, int tempoEstelar) {
}