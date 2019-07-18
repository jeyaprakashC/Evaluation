package com.cognizant.evaluation.albums;

import android.content.ClipData;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.cognizant.evaluation.albums.home.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static com.cognizant.evaluation.albums.Utils.hasCheckedItem;
import static org.hamcrest.core.AllOf.allOf;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void launchPhotos() {
        onView(withId(R.id.navigation_photos)).perform(click());
    }


    @Test
    public void scrollPhotos() {
        onView(withId(R.id.navigation_photos)).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.albumslist)))
                .perform(scrollToPosition(100));

    }

    @Test
    public void scrollUsers() {
        onView(withId(R.id.navigation_users)).perform(click());
        onView(allOf(isDisplayed(), withId(R.id.albumslist)))
                .perform(scrollToPosition(10));
        onView(withId(R.id.bottom_navigation)).check(matches(hasCheckedItem(R.id.navigation_users)));
    }





}
