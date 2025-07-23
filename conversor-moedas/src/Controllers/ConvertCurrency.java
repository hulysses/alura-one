package Controllers;

import Models.CurrencyResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConvertCurrency {

    private String API_KEY = "6440b010fd2ecdbd60a40048";

    public Double convert(String from, String to, double value) {
        String url = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s".formatted(API_KEY, from, to);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            CurrencyResponse currencyResponse = new Gson().fromJson(response.body(), CurrencyResponse.class);
            double conversionRate = currencyResponse.conversion_rate();
            return value * conversionRate;

        } catch (Error | IOException | InterruptedException e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
            return null;
        }
    }
}
