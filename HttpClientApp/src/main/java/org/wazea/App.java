package org.wazea;

public class App {
    public static void main( String[] args ) {
        HttpClient client = new HttpClient();
        client.httpGet();
        client.httpPost();
    }
}
