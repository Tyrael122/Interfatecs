import ano2022.primeirafase.Brasilavia;
import ano2022.primeirafase.LicensePlates;
import ano2022.primeirafase.Retangulo;
import org.junit.jupiter.api.Test;

public class InterfatecProblems2022Test {
    private static final TestCaseRunner testCaseRunner = new TestCaseRunner("test-cases/2022/");

    private final int NUMBER_OF_EXECUTIONS = 1000;

    @Test
    public void licensePlate() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "license", LicensePlates::main);
    }

    @Test
    public void brasilavia() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "brasilavia", Brasilavia::main);
    }

    @Test
    public void secaomestra() {
        // matématica complexa.
    }

    @Test
    public void retangulonumerico() {
        testCaseRunner.runNTimesAndAverageExecutionTime(NUMBER_OF_EXECUTIONS, "retangulo", Retangulo::main);
    }

    @Test
    public void policripto() {
        // ifs e elses
    }

    @Test
    public void inimigosNoMapa() {
        // plano cartesiano
    }

    @Test
    public void apartamento() {
        // matematica financeira simples
    }

    @Test
    public void precoIncorreto() {
        // extremamente simples. aritmetica basica.
    }

    @Test
    public void contagem() {
        // Extremamente simples. For loop basico.
    }
}
