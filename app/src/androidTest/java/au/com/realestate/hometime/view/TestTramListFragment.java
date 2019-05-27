package au.com.realestate.hometime.view;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import au.com.realestate.hometime.BaseTest;
import au.com.realestate.hometime.R;
import au.com.realestate.hometime.service.Resource;
import au.com.realestate.hometime.view.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestTramListFragment extends BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        super.init();
    }

    @Test
    public void TestTramHeader() throws Exception {
        onView(withId(R.id.tram_header)).check(matches(isDisplayed()))
                .check(matches(withText("Tram Information")));
    }

    @Test
    public void TestNorthHeader() throws Exception {
        onView(withId(R.id.tram_header_north)).check(matches(isDisplayed()))
                .check(matches(withText("North")));
    }

    @Test
    public void TestSouthHeader() throws Exception {
        onView(withId(R.id.tram_header_south)).check(matches(isDisplayed()))
                .check(matches(withText("South")));
    }

    @Test
    public void TestNorthListLoaded() throws Exception {
        onView(withId(R.id.tram_list_north)).check(matches(isDisplayed()));
    }

    @Test
    public void TestSouthListLoaded() throws Exception {
        onView(withId(R.id.tram_list_south)).check(matches(isDisplayed()));
    }

    @Test
    public void TestNoSouthData() throws Exception {
        onView(withId(R.id.tram_list_south)).check(matches(isDisplayed()));
    }

    @Test
    public void TestSnackBar() throws Exception {
        try {
            mTestRepo.setErrorResponse();
            mTestRepo.getTramsRepository().getToken().observe(rule.getActivity(), listResource -> {
                Assert.assertEquals(Resource.Status.ERROR, listResource.getStatus());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        onView(allOf(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()));
    }

    @After
    public void onTestFinish() throws Exception {
        mTestRepo.shutDownServer();
    }

}
