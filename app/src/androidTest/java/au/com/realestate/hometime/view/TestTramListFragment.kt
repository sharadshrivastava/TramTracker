package au.com.realestate.hometime.view

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import au.com.realestate.hometime.BaseTest
import au.com.realestate.hometime.R
import au.com.realestate.hometime.service.Resource
import au.com.realestate.hometime.view.ui.MainActivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import org.hamcrest.core.AllOf.allOf

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestTramListFragment : BaseTest() {

    @Rule @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Before
    override fun setup() {
        super.setup()
        addDelay(UILOAD_DELAY)
    }

    @Test
    fun TestTramHeader() {
        onView(withId(R.id.tram_header)).check(matches(isDisplayed()))
                .check(matches(withText("Tram Information")))
    }

    @Test
    fun TestNorthHeader() {
        onView(withId(R.id.tram_header_north)).check(matches(isDisplayed()))
                .check(matches(withText("North")))
    }

    @Test
    fun TestSouthHeader() {
        onView(withId(R.id.tram_header_south)).check(matches(isDisplayed()))
                .check(matches(withText("South")))
    }

    @Test
    fun TestNorthListLoaded() {
        onView(withId(R.id.tram_list_north)).check(matches(isDisplayed()))
    }

    @Test
    fun TestSouthListLoaded() {
        onView(withId(R.id.tram_list_south)).check(matches(isDisplayed()))
    }

    @Test
    fun TestNoSouthData() {
        onView(withId(R.id.tram_list_south)).check(matches(isDisplayed()))
    }

    @Test
    fun TestSnackBar() {
        try {
            testRepo.setErrorResponse()
            testRepo.tramsRepository.getToken().observeForever{ listResource ->
                Assert.assertEquals(Resource.Status.ERROR, listResource?.status)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        onView(allOf<View>(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()))
    }

    @After
    fun onTestFinish() {
        testRepo.shutDownServer()
    }

}
