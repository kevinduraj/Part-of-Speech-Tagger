package kduraj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MyHttpConnection {

    private final String USER_AGENT = "Mozilla/5.0";
    HttpURLConnection connection;
    /*--------------------------------------------------------------------------------------------
     HTTP GET request
     */

    public String sendGet(String url) {

        String output = "";
        try {
            System.out.println("url = " + url);
            URL obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            //connection.setReadTimeout(1000);
            //connection.setConnectTimeout(1000);

            List values = connection.getHeaderFields().get("content-Length");
            System.out.println("values = " + values);
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            output = response.toString();

        } catch (Exception ex) {
            connection.disconnect();
        }

        return output;

    }
    /*--------------------------------------------------------------------------------------------*/
    
}

