package ano2023.segundafase;

import util.TestCaseReader;

public class ProcessoSeletivoSimplificado {
    public static String main(TestCaseReader reader) {
        int quantidadeDeCandidatos = reader.nextInt();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quantidadeDeCandidatos; i++) {
            sb.append(analisarCandidato(reader, i + 1));
        }

        return sb.toString();
    }

    private static String analisarCandidato(TestCaseReader reader, int idCandidato) {
        String candidato = "Cand. " + idCandidato + ": ";

        boolean possuiGraduacaoNaArea = reader.nextBooleanFromInt();
        boolean possuiEspecializacaoNaArea = reader.nextBooleanFromInt();

        boolean possuiMestradoDoutoradoNaArea = reader.nextBooleanFromInt();
        boolean possuiMestradoDoutoradoForaDaArea = reader.nextBooleanFromInt();

        long anosDeExperiencia = reader.nextLong();

        boolean possuiGraduacaoOuMestradoDoutoradoNaArea = analisaTitularidadeCaso1(possuiGraduacaoNaArea, possuiMestradoDoutoradoNaArea, possuiMestradoDoutoradoForaDaArea);
        boolean possuiGraduacaoEEspecializacaoNaArea = possuiGraduacaoNaArea && possuiEspecializacaoNaArea;

        boolean possuiTitulacaoMinima = possuiGraduacaoOuMestradoDoutoradoNaArea || possuiGraduacaoEEspecializacaoNaArea;
        if (!possuiTitulacaoMinima) {
            return candidato + "INDEFERIDO (acad)\n";
        }

        if (possuiGraduacaoOuMestradoDoutoradoNaArea && anosDeExperiencia >= 3) {
            return candidato + "deferido (comprovar 3 anos)\n";
        }

        if (possuiGraduacaoEEspecializacaoNaArea && anosDeExperiencia >= 5) {
            return candidato + "deferido (comprovar 5 anos)\n";
        }

        return candidato + "INDEFERIDO (exp)\n"; // Possui a titulação mínima, mas não entrou em nenhum dos casos de titulação por falta de exp.
    }

    private static boolean analisaTitularidadeCaso1(boolean possuiGraduacaoNaArea, boolean possuiMestradoDoutoradoNaArea, boolean possuiMestradoDoutoradoForaDaArea) {
        boolean possuiMestradoOuDoutorado = possuiMestradoDoutoradoNaArea || possuiMestradoDoutoradoForaDaArea;

        // Não possui graduação. O caso requer que ele possua graduação E mestrado/doutorado.
        // OBS.: Só é possível fazer mestrado ou doutorado com uma graduação.
        if (!possuiMestradoOuDoutorado) return false;

        return possuiGraduacaoNaArea || possuiMestradoDoutoradoNaArea;
    }
}
