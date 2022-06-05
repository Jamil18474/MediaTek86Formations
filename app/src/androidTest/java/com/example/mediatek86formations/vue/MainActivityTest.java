package com.example.mediatek86formations.vue;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import com.example.mediatek86formations.*;


@RunWith(JUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<MainActivity>(MainActivity.class);
    @Test
    public void scenario(){
        onView(withId(R.id.btnFormations)).perform(click());
        onView(withId(R.id.txtFiltre)).perform(typeText("doc"), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        onView(withId(R.id.txtFiltre)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(0).onChildView(withId(R.id.txtListTitle)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(0).onChildView(withId(R.id.btnListFavori)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.btnFavoris)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(0).onChildView(withId(R.id.txtListTitle)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(0).onChildView(withId(R.id.btnListFavori)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
    }
}