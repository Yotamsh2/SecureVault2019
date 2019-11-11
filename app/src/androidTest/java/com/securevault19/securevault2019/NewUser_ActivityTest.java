package com.securevault19.securevault2019;

import android.app.Instrumentation;
import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import view.entrance.NewUser_Activity;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class NewUser_ActivityTest {

    private NewUser_Activity newUser_activity;
    private EditText editText, editText2, editText3, editText4;


    //This rule provides functional testing of a single activity.
    //When launchActivity is set to true in the constructor, the activity under test will be launched
    //before each test annotated with Test and before methods annotated with Before, and it will be terminated
    //after the test is completed and methods annotated with After are finished.
    @Rule
    public ActivityTestRule<NewUser_Activity> activityActivityTestRule = new ActivityTestRule<>( NewUser_Activity.class );
    public Instrumentation instr = InstrumentationRegistry.getInstrumentation();

    Instrumentation.ActivityMonitor monitor = instr.addMonitor( NewUser_Activity.class.getName(), null, false );

    @Before
    public void setUp() throws Exception {

        newUser_activity = activityActivityTestRule.getActivity();
        editText = newUser_activity.findViewById( R.id.username_EditText ); //username_EditText belongs to the sign_up screen
        editText2 = newUser_activity.findViewById( R.id.password_EditText );
        editText3 = newUser_activity.findViewById( R.id.verifyPassword_EditText );
        editText4 = newUser_activity.findViewById( R.id.email_EditText );
    }

    @Test
    public void testUsername() {

//        Espresso.onView( withId( R.id.signUp ) ).perform( click() );

        Espresso.onView( withId( R.id.username_EditText ) ).perform( typeText( "test" ) );
        assertEquals( "test", editText.getText().toString() );
        Espresso.closeSoftKeyboard();

//        Espresso.onView( withId(R.id.signUp) ).perform( click() );
//        Espresso.onView( withId(R.id.userName)).check( matches(hasErrorText( "please enter username" )) );
//        Espresso.onView( withId(R.id.userName)).perform( typeText( "test" ));
//        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testPassword() {

//        Espresso.onView( withId( R.id.signUp ) ).perform( click() );

        Espresso.onView( withId( R.id.password_EditText ) ).perform( typeText( "123456" ) );
        assertEquals( "123456", editText2.getText().toString() );
        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testVerifyPasswordSucceed() {

        Espresso.onView( withId( R.id.password_EditText ) ).perform( typeText( "123456" ) );
        Espresso.onView( withId( R.id.verifyPassword_EditText ) ).perform( typeText( "123456" ) );
        assertEquals( editText3.getText().toString(), editText2.getText().toString() );
        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testVerifyPasswordFailed() {

        Espresso.onView( withId( R.id.password_EditText ) ).perform( typeText( "123456" ) );
        Espresso.onView( withId( R.id.verifyPassword_EditText ) ).perform( typeText( "12345678" ) );
        assertNotSame(editText3.getText().toString(), editText2.getText().toString() );
        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testValidEmail() {

        Espresso.onView( withId( R.id.email_EditText ) ).perform( typeText( "aa@gmail.com" ) );
        assertTrue( editText4.getText().toString(), newUser_activity.isValidEmail( editText4.getText().toString() ));
        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testNotValidEmail() {

        Espresso.onView( withId( R.id.email_EditText ) ).perform( typeText( "HIT69" ) );
        assertFalse(editText4.getText().toString(), newUser_activity.isValidEmail( editText4.getText().toString() ));
        Espresso.closeSoftKeyboard();
    }

    @After
    public void tearDown() throws Exception {

        newUser_activity = null;
    }
}
