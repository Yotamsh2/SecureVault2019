//package com.securevault19.securevault2019;
//
//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.rule.ActivityTestRule;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//
//import view.explorer.CategoryList_Fragment;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.scrollTo;
//import static androidx.test.espresso.action.ViewActions.swipeRight;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//
//public class CategoryList_ActivityTest {
//
//    private MainActivity mainActivity;
//
//    @Rule
//    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>( MainActivity.class );
//
//    @Before
//    public void setUp() throws Exception {
//
//        mainActivity = activityActivityTestRule.getActivity();
//    }
//
//    //#######################################################################
//    // Favorites Clicked:
//    //#######################################################################
//
//    @Test
//    public void FavoritesClicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.favorites) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    //#######################################################################
//    // Categories Clicks:
//    //#######################################################################
//
//    @Test
//    public void category1Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category1) ).perform(click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void category2Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category2) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void category3Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category3) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void category4Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category4) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void category5Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category5) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void category6Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category6) ).perform( scrollTo());
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void category7Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category7) ).perform( scrollTo());
//        onView( withId(R.id.category7) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void category8Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category8) ).perform( scrollTo());
//        onView( withId(R.id.category8) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void category9Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category9) ).perform( scrollTo());
//        onView( withId(R.id.category9) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    @Test
//    public void category10Clicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.category10) ).perform( scrollTo());
//        onView( withId(R.id.category10) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    //#######################################################################
//    // AllRecords Clicked:
//    //#######################################################################
//
//    @Test
//    public void allRecordsClicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        onView( withId(R.id.allRecords) ).perform( scrollTo());
//        onView( withId(R.id.allRecords) ).perform( click());
//        Espresso.closeSoftKeyboard();
//    }
//
//    //#######################################################################
//    // Search Clicked:
//    //#######################################################################
//
//    @Test
//    public void SearchClicked(){
//
//        // Enter username and password
//        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        // Click on signIn button
//        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );
//
//        // Enter the pattern
//        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );
//
//        // Enter Bank Account Category
//        Espresso.onView( withId(R.id.category1) ).perform( click());
//
//        Espresso.onView( withId(R.id.button_add_record) ).perform( click());
//        Espresso.onView( withId(R.id.title_EditText)).perform( typeText( "searchMe" ));
//        Espresso.onView( withId(R.id.username_EditText)).perform( typeText( "test@securevault.com" ));
//        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));
//
//        Espresso.onView( withId(R.id.saveBtn) ).perform( scrollTo());
//        Espresso.onView( ViewMatchers.withId( R.id.saveBtn ) ).perform( ViewActions.click());
//
//        onView( withId(R.id.menu_icon) ).perform( click());
//        Espresso.onView( withId(R.id.search_bar)).perform( typeText( "searchMe" ));
//        onView( withId(R.id.search_btn) ).perform( click());
//
//        Espresso.closeSoftKeyboard();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//        mainActivity = null;
//    }
//
//}