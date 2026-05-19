package org.example.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Model.Account;
import org.example.Model.Loan;
import org.example.Model.Transaction;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class Api {

    private static  final String ACCOUNT_API_URL = "http://localhost:3000/api/accounts/";

    private static final String LOAN_API_URL = "http://localhost:3000/api/loans/";

    private static final String TRANSACTION_API_URL = "http://localhost:3000/api/transactions/";
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

    public List<Account> getAllAccounts()  {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ACCOUNT_API_URL))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                return mapper.readValue(responseBody, new TypeReference<List<Account>>() {});
            } else {
                System.out.println("Failed to fetch accounts from database. Status code: " + response.statusCode());
            }
            return List.of();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed get accounts from database");
        }
        return List.of();

    }

    public void saveLoan(Loan loan) {
        try {
            String requestBody = mapper.writeValueAsString(loan);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LOAN_API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Failed to save loan: " + loan.getId() + ". Status code: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println("Error saving loan: " + loan.getId());
        }
    }

    public List<Loan> getAllLoans() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LOAN_API_URL))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                return mapper.readValue(responseBody, new TypeReference<List<Loan>>() {});
            } else {
                System.out.println("Failed to fetch loans from database. Status code: " + response.statusCode());
            }
            return List.of();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed get loans from database");
        }
        return List.of();
    }

    public void saveTransaction(Transaction transaction) {
        try {
            String requestBody = mapper.writeValueAsString(transaction);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TRANSACTION_API_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Failed to save transaction: " + transaction.getId() + ". Status code: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println("Error saving transaction: " + transaction.getId());
        }
    }

    public List<Transaction> getAllTransactions() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TRANSACTION_API_URL))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                return mapper.readValue(responseBody, new TypeReference<List<Transaction>>() {});
            } else {
                System.out.println("Failed to fetch transactions from database. Status code: " + response.statusCode());
            }
            return List.of();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed get transactions from database");
        }
        return List.of();
    }
}
