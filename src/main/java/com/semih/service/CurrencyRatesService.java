package com.semih.service;

import com.semih.dto.response.CurrencyRatesResponse;
import com.semih.exception.BaseException;
import com.semih.exception.ErrorMessage;
import com.semih.exception.MessageType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


@Service
public class CurrencyRatesService {

    public CurrencyRatesResponse getCurrencyRate(String startDate, String endDate) {
        String rootUrl = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";

        String endpoint = rootUrl + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type="
                + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "85XhpdkUCb");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        try {
            RestTemplate restTemplate = new RestTemplate();

            // İsteği gönderme
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<CurrencyRatesResponse>() {});

            // Başarılı yanıtı kontrol etme ve döndürme
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(e.getMessage(),MessageType.CURRENY_RATES_IS_OCCURED));
        }
        return null;  // Başarısız durumda null döndürme
    }

    }


