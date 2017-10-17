package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JokesFromHell { // implements Runnable {

    private String jokeurl = "https://icanhazdadjoke.com/";

    private URL getUrl(String strUrl) {
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException me) {
            me.printStackTrace();
        }
        return url;
    }


    public String getJoke() {
        String joke = null;
        URL obj = getUrl(jokeurl);
        try {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("Accept", "text/plain");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + jokeurl);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
            joke = response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joke;
    }
}
