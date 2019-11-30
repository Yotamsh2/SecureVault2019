package com.securevault19.securevault2019;

import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    private MainActivity mainActivity;
    private EditText editText;
    private EditText editText2;

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>( MainActivity.class );

    @Before
    public void setUp() throws Exception {

        mainActivity = activityActivityTestRule.getActivity();
        editText = mainActivity.findViewById( R.id.email ); //username_EditText belongs to the sign_up screen
        editText2 = mainActivity.findViewById( R.id.password_EditText );
    }

    //#######################################################################
    // NewUser Register Test:
    //#######################################################################

    @Test
    public void testNewUserRegister(){

        // Click on the create your own vault button
        Espresso.onView( withId(R.id.newAccount) ).perform( click());

        // Add Username, Password, Verify_Password
        Espresso.onView( withId(R.id.email_EditText)).perform( typeText( "test@securevault.com" ));
        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
        Espresso.onView( withId(R.id.verifyPassword_EditText)).perform( typeText( "test" ));

        // Scroll down and click on Pattern
        Espresso.onView( withId( R.id.patternBtn ) ).perform( scrollTo() );
        Espresso.onView( withId( R.id.patternBtn ) ).perform( click() );

        // Creates a pattern and repeat it
        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );

        // Save the new user
        Espresso.onView( withId(R.id.saveBtn) ).perform( scrollTo());
        Espresso.onView( withId(R.id.saveBtn) ).perform( click());

        Espresso.closeSoftKeyboard();

    }

    //#######################################################################
    // SignIn Test:
    //#######################################################################

    @Test
    public void testSignIn(){

        // Enter username and password
        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));

        // Click on signIn button
        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );

        // Enter the pattern
        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );

        Espresso.closeSoftKeyboard();
    }

    //#######################################################################
    // Password Inputs Tests:
    //#######################################################################

    @Test
    public void testNotValidPassword() {

        // Enter an empty password
        Espresso.onView( withId( R.id.password_EditText ) ).perform( typeText( "" ) );

        // Checks if the password is valid
        assertFalse(editText2.getText().toString(), mainActivity.validatePassword( editText2.getText().toString() ));

        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testNotValidPassword2() {

        // Enter a password with spaces
        Espresso.onView( withId( R.id.password_EditText ) ).perform( typeText( "pass   word" ) );

        // Checks if the password is valid
        assertFalse(editText2.getText().toString(), mainActivity.validatePassword( editText2.getText().toString() ));

        Espresso.closeSoftKeyboard();
    }

    //#######################################################################
    // Email Inputs Tests:
    //#######################################################################

    @Test
    public void testValidEmail() {

        // Enter a correct email
        Espresso.onView( withId( R.id.email ) ).perform( typeText( "securevault@gmail.com" ) );

        // Checks if the email is valid
        assertTrue(editText.getText().toString(), mainActivity.isValidEmail( editText.getText().toString() ));

        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testNotValidEmail() {

        // Enter email without @
        Espresso.onView( withId( R.id.email ) ).perform( typeText( "HIT1969" ) );

        // Checks if the email is valid
        assertFalse(editText.getText().toString(), mainActivity.isValidEmail( editText.getText().toString() ));

        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testNotValidEmail2() {

        // Enter email without .com
        Espresso.onView( withId( R.id.email ) ).perform( typeText( "securevault@gmail" ) );

        // Checks if the email is valid
        assertFalse(editText.getText().toString(), mainActivity.isValidEmail( editText.getText().toString() ));

        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testNotValidEmail3() {

        // Enter an empty email
        Espresso.onView( withId( R.id.email ) ).perform( typeText( "" ) );

        // Checks if the email is valid
        assertFalse(editText.getText().toString(), mainActivity.isValidEmail( editText.getText().toString() ));

        Espresso.closeSoftKeyboard();
    }

    @Test
    public void testNotValidEmail4() {

        // Enter email with space
        Espresso.onView( withId( R.id.email ) ).perform( typeText( "securevault@g mail.com" ) );

        // Checks if the email is valid
        assertFalse(editText.getText().toString(), mainActivity.isValidEmail( editText.getText().toString() ));

        Espresso.closeSoftKeyboard();
    }

    @After
    public void tearDown() throws Exception {

        mainActivity = null;
    }
}