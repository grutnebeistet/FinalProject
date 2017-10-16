package com.udacity.gradle.builditbigger;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * Created by Adrian on 15/10/2017.
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void init() {
        onView(withId(R.id.get_joke_button)).perform(ViewActions.click());
    }

    @Test
    public void testAsyncTask() throws Throwable {
        String retrievedJoke = mActivityRule.getActivity().endpointsAsyncTask.get();
        assertThat(retrievedJoke,not(isEmptyOrNullString()));
        Log.d("Testing", "Jokezz: " + retrievedJoke);
        onView(withId(R.id.display_joke_textview)).check(matches(withText(retrievedJoke)));

    }
}