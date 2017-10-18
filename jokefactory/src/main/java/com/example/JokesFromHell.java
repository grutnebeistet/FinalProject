package com.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JokesFromHell { // implements Runnable {


    private static String jokeurl = "https://icanhazdadjoke.com/";

    private static URL getUrl(String strUrl) {
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException me) {
            me.printStackTrace();
        }
        return url;
    }

    private static String getTestJoke() {
        ArrayList<String> jokes = new ArrayList<>();
        jokes.add("Can a kangaroo jump higher than a house? \n" +
                "-\n" +
                "Of course, a house doesn’t jump at all.");
        jokes.add("Doctor: \"I'm sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                "\n" +
                "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                "\n" +
                "Doctor: \"Nine.");
        jokes.add(" Anton, do you think I’m a bad mother?\n" +
                "\n" +
                "My name is Paul.");
        jokes.add("What is the difference between a snowman and a snowwoman?\n" +
                "-\n" +
                "Snowballs.");
        jokes.add("\"Mom, where do tampons go?\"\n" +
                "\n" +
                "\"Where the babies come from, darling.\"\n" +
                "\n" +
                "\"In the stork?");
        Random random = new Random();

        return jokes.get(random.nextInt(jokes.size()-1));
    }

    public static String getJoke() {
        System.out.println("retrieving joke...");
        String joke = getTestJoke();
        try {
            OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(jokeurl)
                        .build();

                Response response = client.newCall(request).execute();
                joke =  response.body().string();
        } catch (IOException | NoClassDefFoundError e) {
            e.printStackTrace();
        }
        return joke;
    }
}
