package br.edu.utfpr;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ClienteCambio {

    private static final String BASE_URL = "https://api.frankfurter.dev/v1/latest?base=USD&symbols=";

    private final HttpClient client;

    public ClienteCambio(HttpClient client) {
        this.client = client;
    }

    public CompletableFuture<Optional<Cotacao>> consultar(String moeda) {
        // TODO implementar aqui a chamada para a API
        var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + moeda))
                .GET()
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(response -> {
                if(response.statusCode() != 200){
                    System.err.println("Falha ao consultar a moeda: " + moeda);
                    return Optional.<Cotacao>empty();   
                }
                return JsonParser.extrairTaxa(response.body(), moeda)
                        .map(valor -> new Cotacao(moeda,valor,LocalDateTime.now()));
            })
            .exceptionally(ex -> {
                System.err.println("Falha ao consultar a moeda: " + moeda);
                return Optional.<Cotacao>empty();
            });
    }
}