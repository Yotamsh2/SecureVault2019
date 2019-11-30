package com.securevault19.securevault2019;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class RecordRecycler_ActivityTest {

    private MainActivity mainActivity;

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>( MainActivity.class );

    @Before
    public void setUp() throws Exception {

        mainActivity = activityActivityTestRule.getActivity();

        // Enter username and password
        Espresso.onView( withId(R.id.email)).perform( typeText( "test@securevault.com" ));
        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));

        // Click on signIn button
        Espresso.onView( ViewMatchers.withId( R.id.signIn ) ).perform( ViewActions.click() );

        // Enter the pattern
        Espresso.onView( withId( R.id.patternView ) ).perform( swipeRight() );

    }

    @Test
    public void isItemsCreated(){

        // Enter Bank Account Category
        Espresso.onView( withId(R.id.category1) ).perform( click());

        Espresso.onView( withId(R.id.button_add_record) ).perform( click());
        Espresso.onView( withId(R.id.title_EditText)).perform( typeText( "testing1" ));
        Espresso.onView( withId(R.id.username_EditText)).perform( typeText( "test@securevault.com" ));
        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));

        Espresso.onView( withId(R.id.saveBtn) ).perform( scrollTo());
        Espresso.onView( ViewMatchers.withId( R.id.saveBtn ) ).perform( ViewActions.click());

        Espresso.onView( withId(R.id.button_add_record) ).perform( click());
        Espresso.onView( withId(R.id.title_EditText)).perform( typeText( "testing2" ));
        Espresso.onView( withId(R.id.username_EditText)).perform( typeText( "testing@securevault.com" ));
        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));

        Espresso.onView( withId(R.id.saveBtn) ).perform( scrollTo());
        Espresso.onView( ViewMatchers.withId( R.id.saveBtn ) ).perform( ViewActions.click());

        Espresso.onView( withId(R.id.button_add_record) ).perform( click());
        Espresso.onView( withId(R.id.title_EditText)).perform( typeText( "testing3" ));
        Espresso.onView( withId(R.id.username_EditText)).perform( typeText( "test@securevault.com" ));
        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));

        Espresso.onView( withId(R.id.saveBtn) ).perform( scrollTo());
        Espresso.onView( ViewMatchers.withId( R.id.saveBtn ) ).perform( ViewActions.click());

        Espresso.onView( withId(R.id.button_add_record) ).perform( click());
        Espresso.onView( withId(R.id.title_EditText)).perform( typeText( "testing4" ));
        Espresso.onView( withId(R.id.username_EditText)).perform( typeText( "testing@securevault.com" ));
        Espresso.onView( withId(R.id.password_EditText)).perform( typeText( "test" ));

        Espresso.onView( withId(R.id.saveBtn) ).perform( scrollTo());
        Espresso.onView( ViewMatchers.withId( R.id.saveBtn ) ).perform( ViewActions.click());

        Espresso.closeSoftKeyboard();
    }

    @Test
    public void isItemDeleted(){

        // Enter Bank Account Category
        Espresso.onView( withId(R.id.category1) ).perform( click());

        // Click on the first item
        Espresso.onView( withId( R.id.recycler_view ) ).perform( RecyclerViewActions.actionOnItemAtPosition( 0, click() ) );

        // Delete Item
        Espresso.onView( withId( R.id.deleteTopBtn ) ).perform( click() ) ;
        Espresso.onView( withText(R.string.yes  ) ).perform( click(  ) );
    }

    @Test
    public void isItemUpdated(){

        // Enter Bank Account Category
        Espresso.onView( withId(R.id.category1) ).perform( click());

        // Click on the first item
        Espresso.onView( withId( R.id.recycler_view ) ).perform( RecyclerViewActions.actionOnItemAtPosition( 0, click() ) );

        // Changing the title to "updated!"
        Espresso.onView( withId( R.id.editForm ) ).perform( click() );
        Espresso.onView( withId( R.id.title_EditText ) ).perform( replaceText( "updated!" ) );

        // Saving the new changes
        Espresso.onView( withId(R.id.saveBtn) ).perform( scrollTo());
        Espresso.onView( ViewMatchers.withId( R.id.saveBtn ) ).perform( ViewActions.click());

    }

    @Test
    public void isItemAtPositionShowed(){

        // Enter Bank Account Category
        Espresso.onView( withId(R.id.category1) ).perform( click());

        // Showing item at position 0
        Espresso.onView(withId(R.id.recycler_view)).perform( RecyclerViewActions.actionOnItemAtPosition( 0, click() ) );

        // Getting back to Bank Account Category
        Espresso.onView( withId(R.id.menu_icon) ).perform( click(  ));

        // Showing item at position 1
        Espresso.onView(withId(R.id.recycler_view)).perform( RecyclerViewActions.actionOnItemAtPosition( 1, click() ) );

        // Getting back to Bank Account Category
        Espresso.onView( withId(R.id.menu_icon) ).perform( click(  ));

        // Showing item at position 2
        Espresso.onView(withId(R.id.recycler_view)).perform( RecyclerViewActions.actionOnItemAtPosition( 2, click() ) );

        // Getting back to Bank Account Category
        Espresso.onView( withId(R.id.menu_icon) ).perform( click(  ));

        // Showing item at position 3
        Espresso.onView(withId(R.id.recycler_view)).perform( RecyclerViewActions.actionOnItemAtPosition( 3, click() ) );

        // Getting back to Bank Account Category
        Espresso.onView( withId(R.id.menu_icon) ).perform( click(  ));


        Espresso.closeSoftKeyboard();
    }

    @After
    public void tearDown() throws Exception {

        mainActivity = null;
    }

}