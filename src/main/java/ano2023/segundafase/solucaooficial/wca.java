package ano2023.segundafase.solucaooficial;

import util.TestCaseReader;

import java.util.*;
import java.util.stream.Collectors;

class Competidor {
    int id;
    String nome;
}

class Participacao implements Comparable<Participacao> {
    Competidor competidor;
    TempoParticipacao[] tempos;
    TempoParticipacao media;
    TempoParticipacao minimo;

    Participacao(Competidor competidor, String tempo1, String tempo2, String tempo3, String tempo4, String tempo5) {
        this.competidor = competidor;
        this.tempos = new TempoParticipacao[5];
        this.tempos[0] = new TempoParticipacao(tempo1);
        this.tempos[1] = new TempoParticipacao(tempo2);
        this.tempos[2] = new TempoParticipacao(tempo3);
        this.tempos[3] = new TempoParticipacao(tempo4);
        this.tempos[4] = new TempoParticipacao(tempo5);
        Arrays.sort(tempos);
        int numDNFs = Arrays.stream(this.tempos).map(TempoParticipacao::retorne1SeDNF)
                .collect(Collectors.summingInt(Integer::intValue));
        if (numDNFs == 0) {
            this.minimo = tempos[0];
            this.media = new TempoParticipacao((tempos[1].totalEmMili + tempos[2].totalEmMili
                    + tempos[3].totalEmMili) / 3);
        } else {
            this.minimo = tempos[0];
            if (numDNFs == 1) {
                this.media = new TempoParticipacao((tempos[1].totalEmMili + tempos[2].totalEmMili
                        + tempos[3].totalEmMili) / 3);
            } else if (numDNFs >= 2) {
                this.media = new TempoParticipacao("0:00:000");
            }
        }
    }

    @Override
    public int compareTo(Participacao o) {
        if (media.totalEmMili == o.media.totalEmMili) {
            if (minimo.totalEmMili == o.minimo.totalEmMili) {
                return competidor.nome.compareTo(o.competidor.nome);
            }
            if (minimo.totalEmMili == 0) {
                return 1;
            } else if (o.minimo.totalEmMili == 0) {
                return -1;
            }
            return Long.compare(minimo.totalEmMili, o.minimo.totalEmMili);
        }
        if (media.totalEmMili == 0) {
            return 1;
        } else if (o.media.totalEmMili == 0) {
            return -1;
        }
        return Long.compare(media.totalEmMili, o.media.totalEmMili);
    }
}

class TempoParticipacao implements Comparable<TempoParticipacao> {
    String tempo;
    long totalEmMili;
    int minutos;
    int segundos;
    int milis;

    TempoParticipacao(String tempo) {
        this.tempo = tempo;
        this.totalEmMili = getTotalEmMili();
        String[] partes = tempo.split(":");
        this.minutos = Integer.valueOf(partes[0]);
        this.segundos = Integer.valueOf(partes[1]);
        this.milis = Integer.valueOf(partes[2]);
    }

    TempoParticipacao(long totalEmMili) {
        this.totalEmMili = totalEmMili;
        this.minutos = (int) (totalEmMili / 60000);
        this.segundos = (int) ((totalEmMili % 60000) / 1000);
        this.milis = (int) (totalEmMili % 1000);
        this.tempo = getComoEntrada();
    }

    @Override
    public int compareTo(TempoParticipacao o) {
        if (retorne1SeDNF() == 1) {
            return 1;
        } else if (o.retorne1SeDNF() == 1) {
            return -1;
        } else {
            return Long.compare(totalEmMili, o.totalEmMili);
        }
    }

    private long getTotalEmMili() {
        String[] partes = tempo.split(":");
        int minutos = Integer.valueOf(partes[0]);
        int segundos = Integer.valueOf(partes[1]);
        int mili = Integer.valueOf(partes[2]);
        return minutos * 60 * 1000 + segundos * 1000 + mili;
    }

    int retorne1SeDNF() {
        return tempo.equals("0:00:000") ? 1 : 0;
    }

    String getComoEntrada() {
        return minutos + ":" + String.format("%02d", segundos) + ":" + String.format("%03d", milis);
    }

    String getParaSaida() {
        if (minutos == 0 && segundos == 0 && milis == 0) {
            return String.format("%12s", "DNF");
        }
        String tempo = "";
        if (minutos > 0) {
            tempo += minutos + ":";
        }
        tempo += String.format("%02d", segundos) + ":" + String.format("%03d", milis);
        return String.format("%12s", tempo);
    }
}

class Prova {
    String nome;
    List<Participacao> participacoes;

    Prova(String nome) {
        this.nome = nome;
        participacoes = new ArrayList<>();
    }

    void addParticipacao(Participacao participacao) {
        participacoes.add(participacao);
    }

    void ordenar() {
        Collections.sort(participacoes);
    }

    String imprimirResultado() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append("\n");
        for (Participacao participacao : participacoes) {
            sb.append(String.format("%3s %-20s%12s%12s%n", participacao.competidor.id, participacao.competidor.nome,
                    participacao.media.getParaSaida(), participacao.minimo.getParaSaida()));
        }

        return sb.toString();
    }
}

public class wca {

    public static String main(TestCaseReader reader) {
        Map<Integer, Competidor> idsParaCompetidores;
        List<Prova> provas = new ArrayList<>(10);

        int numComp = reader.nextInt();
        idsParaCompetidores = new HashMap<>(numComp);
        for (int i = 0; i < numComp; i++) {
            Competidor competidor = new Competidor();
            competidor.id = reader.nextInt();
            competidor.nome = reader.nextLine();
            idsParaCompetidores.put(competidor.id, competidor);
        }

        String linha;
        while (!(linha = reader.nextString()).equals("FIM")) {
            int numParticipacoes = Integer.parseInt(linha);

            Prova prova = new Prova(reader.nextLine());
            for (int i = 0; i < numParticipacoes; i++) {
                Participacao participacao = new Participacao(idsParaCompetidores.get(reader.nextInt()), reader.nextString(),
                        reader.nextString(), reader.nextString(), reader.nextString(), reader.nextString());
                prova.addParticipacao(participacao);
            }

            provas.add(prova);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(".Id.Nome.......................Media......Melhor\n");
        for (Prova prova : provas) {
            prova.ordenar();
            sb.append(prova.imprimirResultado());
        }

        return sb.toString().replaceAll("\\r\\n", "\n");
    }
}