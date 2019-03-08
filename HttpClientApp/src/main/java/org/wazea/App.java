package org.wazea;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App
{
    public static void main( String[] args ) throws IOException
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpUriRequest request = new HttpGet("http://httpbin.org/ip");
        HttpResponse response = httpClient.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line = "";
        while ((line = rd.readLine()) != null)
        {
            System.out.println(line);
        }
    }
}
