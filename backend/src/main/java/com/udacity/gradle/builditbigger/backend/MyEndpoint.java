/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.gradle.builditbigger.backend;

import com.example.JokesFromHell;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that returns a joke from jokefactory
     * Here is where you should call the Joke Library and set the data to return a joke!
     */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyBean response = new MyBean();

        try {
            CountDownLatch latch = new CountDownLatch(5);
            String joke = JokesFromHell.getJoke();
            latch.countDown();
            latch.await(10, TimeUnit.SECONDS);
            response.setData(joke);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return response;
    }

}
