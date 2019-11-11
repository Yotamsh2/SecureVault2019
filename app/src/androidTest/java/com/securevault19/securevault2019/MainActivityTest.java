package com.securevault19.securevault2019;

import android.app.Instrumentation;
import android.widget.EditText;
import android.widget.Toast;

import androidx.test.espresso.Espresso;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import view.entrance.SignUp_Activity;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    private MainActivity mainActivity;
    private SignUp_Activity signUp_activity;
    private EditText editText;
    private EditText editText2;

    //This rule provides functional testing of a single activity.
    //When launchActivity is set to true in the constructor, the activity under test will be launched
    //before each test annotated with Test and before methods annotated with Before, and it will be terminated
    //after the test is completed and methods annotated with After are finished.
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>( MainActivity.class );
    public Instrumentation instr = InstrumentationRegistry.getInstrumentation();

    Instrumentation.ActivityMonitor monitor = instr.addMonitor( MainActivity.class.getName(), null, false );

    @Before
    public void setUp() throws Exception {

        mainActivity = activityActivityTestRule.getActivity();
        editText = mainActivity.findViewById( R.id.userName ); //username_EditText belongs to the sign_up screen
        editText2 = mainActivity.findViewById( R.id.password_EditText );
    }

    @Test
    public void testLaunch(){

        Espresso.onView( withId(R.id.signUp) ).perform( click() );
        Espresso.onView( withId(R.id.username_EditText)).perform( typeText( "test" ));
        assertEquals( "test", editText.getText().toString() );
        Espresso.closeSoftKeyboard();

//        Espresso.onView( withId(R.id.signUp) ).perform( click() );
//        Espresso.onView( withId(R.id.userName)).check( matches(hasErrorText( "please enter username" )) );
//        Espresso.onView( withId(R.id.userName)).perform( typeText( "test" ));
//        Espresso.closeSoftKeyboard();
//
//        Espresso.onView( withId(R.id.signUp) ).perform( click() );
//        Espresso.onView( withId(R.id.email)).check( matches(hasErrorText( "please enter your email" )) );
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test" ));
//        Espresso.closeSoftKeyboard();
//
//        Espresso.onView( withId(R.id.signUp) ).perform( click() );
//        Espresso.onView( withId(R.id.email)).check( matches(hasErrorText( "enter a valid email" )) );
//        Espresso.onView( withId(R.id.email)).perform( typeText( "@gmail.com" ));
//        Espresso.closeSoftKeyboard();
//
//        Espresso.onView( withId(R.id.signUp) ).perform( click() );
//        Espresso.onView( withId(R.id.password_EditText)).check( matches(hasErrorText( "enter a password" )) );
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "12341" ));
//        Espresso.closeSoftKeyboard();
    }

    @After
    public void tearDown() throws Exception {

        mainActivity = null;
    }
}