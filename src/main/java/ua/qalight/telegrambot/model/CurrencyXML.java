package ua.qalight.telegrambot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CurrencyXML {
    @JsonProperty("cc")
    private String currency;

    @JsonProperty("rate")
    private double rate;
}
