package ano2023.segundafase;

import util.TestCaseReader;

import java.util.Arrays;
import java.util.Comparator;

public class LoadBalancer {
    public String main(TestCaseReader reader) {
        int numberOfServers = reader.nextInt();
        int numberOfRequests = reader.nextInt();

        int requestIdToFigureOutWhichServerItWasSentTo = reader.nextInt();

        Request[] requests = getRequests(reader, numberOfRequests);
        requests = Arrays.stream(requests).sorted(Comparator.comparingInt(request -> request.instantOfArrival)).toList().toArray(new Request[0]);

        int[] servers = new int[numberOfServers];

        for (Request request : requests) {
            removeDoneRequests(requests, servers, request.instantOfArrival);

            int idOfServerWhichRequestWasSent = addRequestToServer(servers, request);

            if (request.id == requestIdToFigureOutWhichServerItWasSentTo) {
                return String.valueOf(idOfServerWhichRequestWasSent);
            }
        }

        throw new IllegalStateException("Unexpected state: request not found");
    }

    private void removeDoneRequests(Request[] requests, int[] servers, int instantOfArrival) {
        for (Request request : requests) {
            if (request.instantOfDestruction <= instantOfArrival && !request.removed) {
                request.removed = true;

                servers[request.handledBy]--; // Decrease server workload;
            }
        }
    }

    private static int addRequestToServer(int[] servers, Request request) {
        int serverIdWithLeastWorkload = 0;

        int leastWorkload = servers[0];
        for (int i = 0; i < servers.length; i++) {
            if (servers[i] < leastWorkload) {
                leastWorkload = servers[i];

                serverIdWithLeastWorkload = i;
            }
        }

        servers[serverIdWithLeastWorkload]++;

        request.handledBy = serverIdWithLeastWorkload;

        return serverIdWithLeastWorkload + 1;
    }

    private static Request[] getRequests(TestCaseReader reader, int numberOfRequests) {
        Request[] requests = new Request[numberOfRequests];

        for (int i = 0; i < numberOfRequests; i++) {
            int instantOfArrival = reader.nextInt();
            int duration = reader.nextInt();

            requests[i] = new Request(i + 1, instantOfArrival, instantOfArrival + duration);
        }

        return requests;
    }
}

class Request {
    public int id;
    public int instantOfArrival;
    public int instantOfDestruction;
    public int handledBy;
    public boolean removed;

    public Request(int id, int instantOfArrival, int instantOfDestruction) {
        this.id = id;
        this.instantOfArrival = instantOfArrival;
        this.instantOfDestruction = instantOfDestruction;
    }
}