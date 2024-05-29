package ano2023.segundafase.solucaooficial;

import util.TestCaseReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class loadbalancer {

    private static final int MAX_SERVERS = 1000;
    private static final int MAX_REQUESTS = 10000;

    public static String main(TestCaseReader reader) {
        int numServers = reader.nextInt();
        int numRequests = reader.nextInt();
        int whichRequest = reader.nextInt();

        long[] servers = new long[numServers];

        List<Request> requests = new ArrayList<>();

        int maxT = 0;
        for (int i = 0; i < numRequests; i++) {
            int t = reader.nextInt();
            int d = reader.nextInt();
            requests.add(new Request(i + 1, t, d));
            if (t > maxT) {
                t = maxT;
            }
        }

        Collections.sort(requests);

        for (int i = 0; i < requests.size(); i++) {
            Request request = requests.get(i);

            // Verifica se alguma tarefa terminou.
            for (int j = 0; j < i; j++) {
                Request previousRequest = requests.get(j);
                if (previousRequest.removed)
                    continue;

                if (request.arrivalTime >= previousRequest.arrivalTime + previousRequest.duration) {
                    servers[previousRequest.handledBy]--;
                    previousRequest.removed = true;
                    // System.out.println("Liberando servidor para request " +
                    // previousRequest.index);
                }
            }

            // System.out.println("=====");
            // System.out.println("Instante: " + request.arrivalTime);
            // System.out.println("Sobrecarga:");
            // for (int j = 0; j < numServers; j++) {
            // System.out.println("Servidor " + (j + 1) + ": " + servers[j]);
            // }
            // System.out.println("=====");

            // Encontra o servidor que atenderá a requisição.
            int serverIndex = 0;
            long minWorkload = servers[0];
            for (int j = 1; j < numServers; j++) {
                if (servers[j] < minWorkload) {
                    minWorkload = servers[j];
                    serverIndex = j;
                }
            }
            servers[serverIndex]++;
            request.handledBy = serverIndex;
            // System.out.println("Alocando em " + (serverIndex + 1));

            if (request.index == whichRequest) {
                return String.valueOf(serverIndex + 1);
            }
        }

        throw new IllegalStateException("Unexpected state: request not found");
    }
}

class Request implements Comparable<Request> {
    int index, arrivalTime, duration;
    boolean removed;
    int handledBy;

    public Request(int index, int arrivalTime, int duration) {
        this.index = index;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    @Override
    public int compareTo(Request other) {
        return Integer.compare(this.arrivalTime, other.arrivalTime);
    }
}