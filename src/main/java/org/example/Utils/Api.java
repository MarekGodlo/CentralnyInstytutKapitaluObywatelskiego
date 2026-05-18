package org.example.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Model.Account;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class Api {

    private static  final String ACCOUNT_API_URL = "http://localhost:3000/api/accounts/";

    private static final String LOAN_API_URL = "http://localhost:3000/api/loan";

    private static final String TRANSACTION_API_URL = "http://localhost:3000/api/transaction";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public void saveAccount(Account account) {
        try {
            String requestBody = mapper.writeValueAsString(account);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ACCOUNT_API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Failed to save account: " + account.getUsername() + ". Status code: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error saving book: " + account.getUsername());
        }
    }





}
