package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.function.Function;


public class TestCaseReader {
    private final BufferedReader br;
    private StringTokenizer st;

    public TestCaseReader(BufferedReader br) throws IOException {
        this.br = br;
        this.st = new StringTokenizer(br.readLine());
    }

    public static TestCaseReader readTestCaseFile(String filePath) {
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(filePath));
            return new TestCaseReader(br);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File[] readInputFilesFromFolder(String folder) {
        File dir = new File(folder);
        return dir.listFiles((dir1, name) -> name.contains("in"));
    }

    public static String getExpectedOutput(String parentPath, String inputFilename) {
        String expectedOutputPath = parentPath + File.separator + inputFilename.replace("in", "out");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(expectedOutputPath));
            return br.lines().map(s -> s + "\n").reduce(String::concat).orElse("").trim();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean nextBooleanFromInt() {
        return nextInt() == 1;
    }

    public String nextString() {
        return parseNextToken(String::toString);
    }

    public String nextLine() {
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken()).append(" ");
        }

        return sb.toString().trim();
    }

    public int nextInt() {
        return parseNextToken(Integer::parseInt);
    }

    public long nextLong() {
        return parseNextToken(Long::parseLong);
    }

    public long nextUnsignedLong() {
        return parseNextToken(Long::parseUnsignedLong);
    }

    public double nextDouble() {
        return parseNextToken(Double::parseDouble);
    }

    private <T> T parseNextToken(Function<String, T> parser) {
        if (st.hasMoreTokens()) {
            return parser.apply(st.nextToken());
        }

        try {
            st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parser.apply(st.nextToken());
    }

    public boolean hasNext() {
        try {
            return st.hasMoreTokens() || br.ready();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}