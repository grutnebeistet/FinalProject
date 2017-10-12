package com.example;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.core.Response;

public class JokesFromHell implements Runnable {
    private final static String BASE_URL = "http://webknox.com/api/jokes/random?apiKey=";
    private String jokeurl = "https://icanhazdadjoke.com/";
    private volatile String mJoke = "Default Joke";


    private URL getUrl(String strUrl) {
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException me) {
            me.printStackTrace();
        }
        return url;
    }


    public void s() {
        URL obj = getUrl(jokeurl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "User-Agent: My Library (https://github.com/username/repo)" );

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }

    @Override
    public void run() {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(jokeurl);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        System.out.println(value);
        mJoke = value;
        response.close();
    }

    public String getJoke() {
        return mJoke;
    }
}
