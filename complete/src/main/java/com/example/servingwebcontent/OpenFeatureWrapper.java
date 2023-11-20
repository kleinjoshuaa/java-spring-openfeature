
package com.example.servingwebcontent;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.MutableContext;
import dev.openfeature.sdk.OpenFeatureAPI;
import dev.openfeature.sdk.OpenFeatureAPI;
import io.split.openfeature.SplitProvider;
import io.split.client.SplitClient;
import io.split.client.SplitClientConfig;
import io.split.client.SplitFactoryBuilder;


public class OpenFeatureWrapper {

    private static Client client; // Declare client as a static variable

    public static void initialize() {
        try {
            OpenFeatureAPI api = OpenFeatureAPI.getInstance();
            api.setProvider(new SplitProvider("mj46ha9gf7nll3iep3t1eveg43qi82kj2mka")); // put server side SDK key here
            client = api.getClient("CLIENT_NAME");
        } catch (Exception e) {
            // Handle initialization exceptions (e.g., configuration issues)
            e.printStackTrace();
        }
    }

    public static String getFeatureFlag(String flagName, String userName) {
        if (client == null) {
            throw new IllegalStateException("OpenFeature API client is not initialized. Call initialize() first.");
        }

        EvaluationContext context = new MutableContext(userName); // put user ID here
        String treatment = client.getStringValue(flagName, "default", context); // put flag name and default
        System.out.println("Serving: " + treatment);

        return treatment;
    }
}