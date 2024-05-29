import ano2023.segundafase.*;
import ano2023.segundafase.solucaooficial.terminus;
import ano2023.segundafase.solucaooficial.injecaodependencia;
import ano2023.segundafase.solucaooficial.loadbalancer;
import ano2023.segundafase.solucaooficial.wca;
import org.junit.jupiter.api.Test;

public class InterfatecProblems2023Test {
    private static final TestCaseRunner testCaseRunner = new TestCaseRunner("test-cases/2023/");

    private final int NUMBER_OF_EXECUTIONS = 200;

    @Test
    public void testImpressora3d() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "impressora3d", Impressora3D::main);
    }

    @Test
    public void testPim() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "pim", Pim::main);
    }

    @Test
    public void facilDemais() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "facildemais", FacilDemais::main);
    }

    @Test
    public void criptografiaHash() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "criptografia", Criptografia::main);
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "criptografia", ano2023.segundafase.solucaooficial.Criptografia::main);
    }

    @Test
    public void processoSeletivoSimplificado() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "pss", ProcessoSeletivoSimplificado::main);
    }

    @Test
    public void loadBalancer() {
        double averageMySolution = testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "loadbalancer", (reader) -> new LoadBalancer().main(reader));
        double averageOficialSolution = testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "loadbalancer", loadbalancer::main);

        System.out.println("\n=====================================");
        System.out.println("Average time of my solution: " + averageMySolution + " milliseconds");
        System.out.println("Average time of official solution: " + averageOficialSolution + " milliseconds");
    }

    @Test
    public void torresDeHanoi() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "hanoi", TorresDeHanoi::main);
    }

    @Test
    public void terminaisDeTerminus() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "terminus", terminus::main);
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "terminus", (reader -> new TerminaisDeTerminus().main(reader)));
    }

    @Test
    public void wca() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS,"wca", wca::main);
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "wca", WCA::main);
    }

    @Test
    public void injecaoDeDependencias() {
//        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "injecaodependencia", injecaodependencia::main);
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "injecaodependencia", InjecaoDependencia::main);
    }
}
