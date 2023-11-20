
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
import com.example.servingwebcontent.AfterHook;

public class OpenFeatureWrapper {

    private static Client client; // Declare client as a static variable

    public static void initialize() {
        try {
            OpenFeatureAPI api = OpenFeatureAPI.getInstance();
            String sdkKey = System.getProperty("sdkKey","NULL");
            api.setProvider(new SplitProvider(sdkKey)); // put server side SDK key here
            client = api.getClient("CLIENT_NAME");
            client.addHooks(new AfterHook());

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


        return treatment;
    }
}