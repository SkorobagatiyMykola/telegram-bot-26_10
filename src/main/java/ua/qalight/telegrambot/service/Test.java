/*
package ua.qalight.telegrambot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.qalight.telegrambot.model.CurrencyJSON;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client =HttpClient.newHttpClient();
        // https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json"))
                .GET()
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String res=response.body().toString();
        ObjectMapper mapper = new ObjectMapper(); // todo help us to convert JSON (!!!)

        List<CurrencyJSON> listJSON = new ArrayList<>();
        TypeReference<List<CurrencyJSON>> listType = new TypeReference<List<CurrencyJSON>>(){};
        listJSON =mapper.readValue(res, listType ); //

        // example
        String ttt ="{ \n" +
                "\"r030\":124,\"txt\":\"Канадський долар\",\"rate\":26.4274,\"cc\":\"CAD\",\"exchangedate\":\"27.10.2023\"\n" +
                " }";

        CurrencyJSON test = mapper.readValue(ttt,CurrencyJSON.class); ///  ++++


        System.out.println(res);

        System.out.println("===============  THE END  =======================");
    }
}
*/
