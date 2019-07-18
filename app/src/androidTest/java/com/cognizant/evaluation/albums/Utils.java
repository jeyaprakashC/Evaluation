package com.cognizant.evaluation.albums;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.test.espresso.matcher.BoundedMatcher;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.HashSet;
import java.util.Set;

public class Utils {


    public static Matcher<View> hasCheckedItem(final int id) {
        return new BoundedMatcher<View, BottomNavigationView>(BottomNavigationView.class) {
            Set<Integer> checkedIds = new HashSet<>();
            boolean itemFound = false;
            boolean triedMatching = false;

            @Override
            public void describeTo(Description description) {
                if (!triedMatching) {
                    description.appendText("BottomNavigationView");
                    return;
                }

                description.appendText("BottomNavigationView to have a checked item with id=");
                description.appendValue(id);
                if (itemFound) {
                    description.appendText(", but selection was=");
                    description.appendValue(checkedIds);
                } else {
                    description.appendText(", but it doesn't have an item with such id");
                }
            }

            @Override
            protected boolean matchesSafely(BottomNavigationView navigationView) {
                triedMatching = true;

                final Menu menu = navigationView.getMenu();
                for (int i = 0; i < menu.size(); i++) {
                    final MenuItem item = menu.getItem(i);
                    if (item.isChecked()) {
                        checkedIds.add(item.getItemId());
                    }
                    if (item.getItemId() == id) {
                        itemFound = true;
                    }
                }
                return checkedIds.contains(id);
            }
        };
    }


}
