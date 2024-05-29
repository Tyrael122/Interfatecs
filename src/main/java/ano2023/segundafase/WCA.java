package ano2023.segundafase;

import util.TestCaseReader;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class WCA {
    public static String main(TestCaseReader reader) {
        int numeroParticipantes = reader.nextInt();

        String[] nomesParticipantes = new String[numeroParticipantes];
        for (int i = 0; i < numeroParticipantes; i++) {
            int idParticipante = reader.nextInt();

            String nome = reader.nextLine();

            nomesParticipantes[i] = nome;
        }

        List<Prova> provas = new ArrayList<>();

        String linha = reader.nextString();
        while (!linha.equals("FIM")) {
            provas.add(parseProva(reader, linha));

            linha = reader.nextString();
        }

        StringBuilder sb = new StringBuilder(".Id.Nome.......................Media......Melhor\n");
        for (Prova prova : provas) {
            sb.append(prova.nome()).append("\n");
            sb.append(prova.getClassificacao(nomesParticipantes));
        }

        return sb.toString();
    }

    private static Prova parseProva(TestCaseReader reader, String linha) {
        int numeroParticipacoes = Integer.parseInt(linha);

        String nomeProva = reader.nextLine();

        List<Participacao> participacoes = parseParticipacoes(reader, numeroParticipacoes);

        return new Prova(nomeProva, participacoes);
    }

    private static List<Participacao> parseParticipacoes(TestCaseReader reader, int numeroParticipacoes) {
        List<Participacao> participacoes = new ArrayList<>();
        for (int i = 0; i < numeroParticipacoes; i++) {
            participacoes.add(parseParticipacao(reader));
        }

        return participacoes;
    }

    private static Participacao parseParticipacao(TestCaseReader reader) {
        int idParticipante = reader.nextInt();

        LocalTime[] tempos = new LocalTime[5];
        for (int j = 0; j < 5; j++) {
            tempos[j] = parseTempo(reader.nextString());
        }

        return new Participacao(idParticipante, tempos);
    }

    private static LocalTime parseTempo(String tempo) {
        if (tempo.length() == 8) tempo = "0" + tempo;

        tempo = "00:" + tempo;

        return LocalTime.parse(tempo, DateTimeFormatter.ofPattern("HH:mm:ss:SSS"));
    }
}

record Prova(String nome, List<Participacao> participacoes) {
    public String getClassificacao(String[] nomesParticipantes) {
        Comparator<Participacao> comparadorMenorTempo = Comparator.comparing(Participacao::calcularMenorTempo);

        participacoes.sort(Comparator.comparing(Participacao::calcularMedia).thenComparing((participacao1, participacao2) -> {
            return participacao1.calcularMenorTempo().compareTo(participacao2.calcularMenorTempo());
        }).thenComparing(participacao -> nomesParticipantes[participacao.idParticipante() - 1]));

        StringBuilder sb = new StringBuilder();
        for (Participacao participacao : participacoes) {
            sb.append(gerarStringComAlinhamento(participacao, nomesParticipantes));

            sb.append("\n");
        }

        return sb.toString();
    }

    private String gerarStringComAlinhamento(Participacao participacao, String[] nomesParticipantes) {
        return String.format("%3d %-20s%12s%12s", participacao.idParticipante(), nomesParticipantes[participacao.idParticipante() - 1], formatLocalTime(participacao.calcularMedia()), formatLocalTime(participacao.calcularMenorTempo()));
    }

    private String formatLocalTime(LocalTime localTime) {
        if (Participacao.isDNF(localTime)) {
            return "DNF";
        }

        String pattern = "mm:ss:SSS";

        if (localTime.getMinute() < 10) {
            pattern = "m:ss:SSS";
        }

        if (localTime.getMinute() == 0) {
            pattern = "ss:SSS";
        }

        return localTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}

final class Participacao {
    private final int idParticipante;
    private final LocalTime[] tempos;

    private LocalTime media;
    private LocalTime menorTempo;

    Participacao(int idParticipante, LocalTime[] tempos) {
        this.idParticipante = idParticipante;
        this.tempos = tempos;
    }

    public LocalTime calcularMedia() {
        if (media != null) {
            return media;
        }

        if (contarDNFs() >= 2) {
            media = LocalTime.MAX;
            return LocalTime.MAX;
        }

        List<LocalTime> temposParaAMedia = new ArrayList<>(Arrays.stream(tempos).sorted().toList());
        temposParaAMedia.removeFirst();
        temposParaAMedia.removeLast();

        LocalTime soma = LocalTime.MIN;
        for (LocalTime tempo : temposParaAMedia) {
            soma = soma.plusNanos(tempo.toNanoOfDay());
        }

        media = LocalTime.ofNanoOfDay(soma.toNanoOfDay() / 3).truncatedTo(ChronoUnit.MILLIS);
        return media;
    }

    public LocalTime calcularMenorTempo() {
        if (menorTempo != null) {
            return menorTempo;
        }

        if (contarDNFs() == 5) {
            menorTempo = LocalTime.MAX;
            return LocalTime.MAX;
        }

        List<LocalTime> temposSorted = Arrays.stream(tempos).sorted().toList();
        for (LocalTime localTime : temposSorted) {
            menorTempo = localTime;

            if (!LocalTime.MIN.equals(menorTempo)) break;
        }

        return menorTempo;
    }

    private int contarDNFs() {
        int quantidadeDeDnfs = 0;
        for (LocalTime tempo : tempos) {
            if (isTimeZeroed(tempo)) quantidadeDeDnfs++;
        }

        return quantidadeDeDnfs;
    }

    private boolean isTimeZeroed(LocalTime localTime) {
        return localTime.equals(LocalTime.MIN);
    }

    public static boolean isDNF(LocalTime localTime) {
        return localTime.equals(LocalTime.MAX);
    }

    public int idParticipante() {
        return idParticipante;
    }

    public LocalTime[] tempos() {
        return tempos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Participacao) obj;
        return this.idParticipante == that.idParticipante && Arrays.equals(this.tempos, that.tempos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParticipante, Arrays.hashCode(tempos));
    }

    @Override
    public String toString() {
        return "ano2023.segundafase.Participacao[" + "idParticipante=" + idParticipante + ", " + "tempos=" + Arrays.toString(tempos) + ']';
    }
}