package com.example.servingwebcontent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.example.servingwebcontent.DisableCertificateValidation;
import javax.net.ssl.HttpsURLConnection;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PunGenerator {

    // A constant for the base URL of the pun API
    private static final String PUN_API_URL = "https://www.punapi.rest/api";

    // A method that returns a random pun as a string
    public static String getPun() {
        try {
            DisableCertificateValidation.disable(); // used due to DLP NOT FOR PRODUCTION
            // Create a URL object with the endpoint for getting a pun
            URL url = new URL(PUN_API_URL + "/pun");

            // Open a connection to the URL and cast it to HttpURLConnection
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code from the connection
            int responseCode = connection.getResponseCode();

            // If the response code is 200 (OK), read the response body
            if (responseCode == 200) {
                // Create a BufferedReader to read the response body
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // Create a StringBuilder to append the response body
                StringBuilder response = new StringBuilder();

                // Read each line from the reader and append it to the response
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Close the reader
                reader.close();

                // parse as JSON
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    JsonNode jsonNode = objectMapper.readTree(response.toString());
                    return jsonNode.get("pun").asText();
                } catch (Exception e) {
                    // If the response code is not 200, throw an exception
                    throw new Exception("Failed to parse meme: " + e);
                }
       
            } else {
                // If the response code is not 200, throw an exception
                throw new Exception("Failed to get a pun: " + responseCode);
            }
        } catch (Exception e) {
            // If any exception occurs, print the stack trace and return null
            e.printStackTrace();
            return null;
        }
    }

    // A method that returns a random meme as a string (the URL of the meme image)
    public static String getMeme() {
        try {
                  DisableCertificateValidation.disable(); // used due to DLP NOT FOR PRODUCTIOUN
            // Create a URL object with the endpoint for getting a meme
            URL url = new URL(PUN_API_URL + "/meme");

            // Open a connection to the URL and cast it to HttpURLConnection
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();




            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code from the connection
            int responseCode = connection.getResponseCode();

            // If the response code is 200 (OK), read the response body
            if (responseCode == 200) {
                // Create a BufferedReader to read the response body
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // Create a StringBuilder to append the response body
                StringBuilder response = new StringBuilder();

                // Read each line from the reader and append it to the response
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                // Close the reader
                reader.close();
                // parse as JSON
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    JsonNode jsonNode = objectMapper.readTree(response.toString());
                    return jsonNode.get("meme").asText();
                } catch (Exception e) {
                    // If the response code is not 200, throw an exception
                    throw new Exception("Failed to parse meme: " + e);
                }
            } else {
                // If the response code is not 200, throw an exception
                throw new Exception("Failed to get a meme: " + responseCode);
            }
        } catch (Exception e) {
            // If any exception occurs, print the stack trace and return null
            e.printStackTrace();
            return null;
        }
    }
}