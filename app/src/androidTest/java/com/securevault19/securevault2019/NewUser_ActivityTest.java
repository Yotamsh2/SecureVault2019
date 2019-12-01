//package com.securevault19.securevault2019;
//import android.widget.EditText;
//
//import androidx.test.espresso.Espresso;
//import androidx.test.rule.ActivityTestRule;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import view.entrance.NewUser_Activity;
//
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static org.junit.Assert.*;
//
//public class NewUser_ActivityTest {
//
//    private NewUser_Activity newUser_activity;
//    private EditText editText, editText2, editText3, editText4;
//
//    @Rule
//    public ActivityTestRule<NewUser_Activity> activityActivityTestRule = new ActivityTestRule<>( NewUser_Activity.class );
//
//    @Before
//    public void setUp() throws Exception {
//
//        newUser_activity = activityActivityTestRule.getActivity();
//        editText = newUser_activity.findViewById( R.id.username_EditText ); //username_EditText belongs to the NewUser_Activity
//        editText2 = newUser_activity.findViewById( R.id.password_EditText );
//        editText3 = newUser_activity.findViewById( R.id.verifyPassword_EditText );
//        editText4 = newUser_activity.findViewById( R.id.email_EditText );
//    }
//
//    //#######################################################################
//    // Username Inputs Tests:
//    //#######################################################################
//
//    @Test
//    public void testValidUsername() {
//
//        // Enter a valid username
//        Espresso.onView( withId( R.id.username_EditText ) ).perform( typeText( "test" ) );
//
//        // Checks if the username is valid
//        assertEquals( "test", editText.getText().toString() );
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testNotValidUsername(){
//
//        // Enter too long username
//        Espresso.onView( withId( R.id.username_EditText ) ).perform( typeText("fdsjhfdsjkhfsjkhfsjkdfhsjkddhasjk") );
//
//        // Checks if the username is valid
//        assertFalse( newUser_activity.validateUsername(editText.getText().toString()));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testNotValidUsername2(){
//
//        // Enter an empty username
//        Espresso.onView( withId( R.id.username_EditText ) ).perform( typeText("") );
//
//        // Checks if the username is valid
//        assertFalse( newUser_activity.validateUsername(editText.getText().toString()));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testNotValidUsername3(){
//
//        // Enter username with space
//        Espresso.onView( withId( R.id.username_EditText ) ).perform( typeText("hit 1969") );
//
//        // Checks if the username is valid
//        assertFalse( newUser_activity.validateUsername(editText.getText().toString()));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    //#######################################################################
//    // Password Inputs Tests:
//    //#######################################################################
//
//    @Test
//    public void testNotValidPassword() {
//
//        // Enter an empty password
//        Espresso.onView( withId( R.id.password_EditText ) ).perform( typeText( "" ) );
//
//        // Checks if the password is valid
//        assertFalse(editText2.getText().toString(), newUser_activity.validatePassword( editText2.getText().toString() ));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testNotValidPassword2() {
//
//        // Enter password with space
//        Espresso.onView( withId( R.id.password_EditText ) ).perform( typeText( "pass   word" ) );
//
//        // Checks if the password is valid
//        assertFalse(editText2.getText().toString(), newUser_activity.validatePassword( editText2.getText().toString() ));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testVerifyPasswordCorrect() {
//
//        // Enter a password and matched verified password
//        Espresso.onView( withId( R.id.password_EditText ) ).perform( typeText( "123456" ) );
//        Espresso.onView( withId( R.id.verifyPassword_EditText ) ).perform( typeText( "123456" ) );
//
//        // Checks if the password is valid
//        assertEquals(editText2.getText().toString(), editText3.getText().toString() );
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testVerifyPasswordIncorrect() {
//
//        // Enter a password and unmatched verified password
//        Espresso.onView( withId( R.id.password_EditText ) ).perform( typeText( "23456" ) );
//        Espresso.onView( withId( R.id.verifyPassword_EditText ) ).perform( typeText( "12345678" ) );
//
//        // Checks if the password is valid
//        assertNotSame(editText3.getText().toString(), editText2.getText().toString() );
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    //#######################################################################
//    // Email Inputs Tests:
//    //#######################################################################
//
//    @Test
//    public void testValidEmail() {
//
//        // Enter a valid email
//        Espresso.onView( withId( R.id.email_EditText ) ).perform( typeText( "abc@gmail.com" ) );
//
//        // Checks if the email is valid
//        assertTrue( editText4.getText().toString(), newUser_activity.isValidEmail( editText4.getText().toString() ));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testNotValidEmail() {
//
//        // Enter email without @
//        Espresso.onView( withId( R.id.email_EditText ) ).perform( typeText( "HIT1969" ) );
//
//        // Checks if the email is valid
//        assertFalse(editText4.getText().toString(), newUser_activity.isValidEmail( editText4.getText().toString() ));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testNotValidEmail2() {
//
//        // Enter email without .com
//        Espresso.onView( withId( R.id.email_EditText ) ).perform( typeText( "securevault@gmail" ) );
//
//        // Checks if the email is valid
//        assertFalse(editText4.getText().toString(), newUser_activity.isValidEmail( editText4.getText().toString() ));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testNotValidEmail3() {
//
//        // Enter an empty email
//        Espresso.onView( withId( R.id.email_EditText ) ).perform( typeText( "" ) );
//
//        // Checks if the email is valid
//        assertFalse(editText4.getText().toString(), newUser_activity.isValidEmail( editText4.getText().toString() ));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void testNotValidEmail4() {
//
//        // Enter email with space
//        Espresso.onView( withId( R.id.email_EditText ) ).perform( typeText( "securevault@g mail.com" ) );
//
//        // Checks if the email is valid
//        assertFalse(editText4.getText().toString(), newUser_activity.isValidEmail( editText4.getText().toString() ));
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//        newUser_activity = null;
//    }
//}
