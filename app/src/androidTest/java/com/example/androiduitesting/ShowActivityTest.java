package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @Before public void init() { Intents.init(); }
    @After  public void release() { Intents.release(); }

    private void addCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText(name));
        onView(withId(R.id.button_confirm)).perform(click());
    }

    // Check whether the activity correctly switched to ShowActivity
    @Test
    public void testSwitchToShowActivity() {
        addCity("Edmonton");

        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        intended(hasComponent(ShowActivity.class.getName()));
        onView(withId(R.id.tv_city_name)).check(matches(isDisplayed()));
    }

    // Check whether the city name displayed is consistent
    @Test
    public void testConsistentCityName() {
        addCity("Vancouver");

        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // verify that the new view shows the correct name
        onView(withId(R.id.tv_city_name)).check(matches(withText("Vancouver")));
    }

    // Test the back button
    @Test
    public void testBackButton() {
        addCity("Calgary");

        // Go to ShowActivity
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Tap the back button
        onView(withId(R.id.btn_back)).perform(click());

        // Check that it's back on MainActivity
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}
