package nl.utwente.soa.sampleWebapplication.services;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component("rest")
public class RestEchoProxyService implements EchoProxyService {

    @Override
    public void echo(String message) {
        String urlString = "http://www.mocky.io/v2/5e68e62f2f0000119ad8b0b1?mocky-delay=60s";

        // Manual HTTP request
        StringBuffer content = new StringBuffer();
        HttpURLConnection con = null;
        try {

            // Build HTTP request
            URL url = new URL(urlString);
            con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Transform the response InputStream into a String
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
        finally {
            if(con != null) {
                con.disconnect();
            }
        }
    }
}
