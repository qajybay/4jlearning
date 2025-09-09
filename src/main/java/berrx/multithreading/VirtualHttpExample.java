package berrx.multithreading;

import java.net.URI;
import java.net.http.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualHttpExample {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        try (ExecutorService vthreads = Executors.newVirtualThreadPerTaskExecutor()) {
            var futures = java.util.stream.IntStream.range(0, 10)
                    .mapToObj(i -> vthreads.submit(() -> {
                        HttpRequest req = HttpRequest.newBuilder(URI.create("https://httpbin.org/get")).build();
                        return client.send(req, HttpResponse.BodyHandlers.ofString()).body();
                    }))
                    .toList();

            for (var f : futures) {
                System.out.println(f.get().substring(0, 100) + "...");
            }
        }
    }
}
