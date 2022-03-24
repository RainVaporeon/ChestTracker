package com.spiritlight.chestapi;
import okhttp3.*;
import java.io.IOException;

public class HttpSpirit {
    OkHttpClient client = new OkHttpClient();

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
