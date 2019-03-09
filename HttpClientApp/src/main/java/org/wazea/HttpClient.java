package org.wazea;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpClient {

    public static final String[] HEADERS = {
            "Access-Control-Allow-Credentials",
            "Access-Control-Allow-Origin",
            "Content-Length",
            "Content-Type",
            "Date",
            "Server",
            "Connection"
    };

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printHeaders(HttpResponse response) {
        try {
            for (String it : HEADERS) {
                Header[] header = response.getHeaders(it);
                if (header.length == 0)
                    continue;
                System.out.println(header[0].getName() + " : " + header[0].getValue());
            }

            System.out.println();
        } catch (Exception e) {
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

    public void httpHead() {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpHead headRequest = new HttpHead("http://httpbin.org/ip");
            HttpResponse response = client.execute(headRequest);
            printHeaders(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void httpPost() {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost postRequest = new HttpPost("http://httpbin.org/forms/post");
            postRequest.setHeader("User-Agent", "Client");
            postRequest.setEntity(new StringEntity("Test data"));
            HttpResponse response = client.execute(postRequest);
            printResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
