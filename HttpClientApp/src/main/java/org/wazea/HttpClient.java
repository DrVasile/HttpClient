package org.wazea;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpClient {

    private void printResponse(HttpResponse response) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }

            System.out.println(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void httpGet() {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet("http://httpbin.org/ip");
            HttpResponse response = client.execute(getRequest);
            printResponse(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void httpPost() {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost postRequest = new HttpPost("http://httpbin.org/post");
            postRequest.setHeader("User-Agent", "Client");
            postRequest.setEntity(new StringEntity("Test data"));
            HttpResponse response = client.execute(postRequest);
            printResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
