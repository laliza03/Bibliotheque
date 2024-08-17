package com.LizaBenkadoum_MonessaPierre.bibliotheque.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class NetworkUtil {
    public static final String BASE_URL = "http://192.168.56.1:3000/";
    private static final OkHttpClient client = new OkHttpClient();


    // Get OkHttpClient instance
    public static OkHttpClient getClient() {
        return client;
    }


    public static String get(String endpoint) throws IOException {
        Request request = new Request.Builder().url(BASE_URL + endpoint).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            return response.body().string();
        }
    }
}

