package au.com.realestate.hometime.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import au.com.realestate.hometime.BaseTest
import au.com.realestate.hometime.R
import au.com.realestate.hometime.service.Resource
import au.com.realestate.hometime.view.ui.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TramListFragmentTest : BaseTest() {

    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        addDelay(UILOAD_DELAY)
    }

    @Test
    fun testTramHeader() {
        onView(withId(R.id.tram_header)).check(matches(isDisplayed()))
                .check(matches(withText("Tram Information")))
    }

    @Test
    fun testNorthHeader() {
        onView(withId(R.id.tram_header_north)).check(matches(isDisplayed()))
                .check(matches(withText("North")))
    }

    @Test
    fun testSouthHeader() {
        onView(withId(R.id.tram_header_south)).check(matches(isDisplayed()))
                .check(matches(withText("South")))
    }

    @Test
    fun testNorthListLoaded() {
        onView(withId(R.id.tram_list_north)).check(matches(isDisplayed()))
    }

    @Test
    fun testSouthListLoaded() {
        onView(withId(R.id.tram_list_south)).check(matches(isDisplayed()))
    }

    @Test
    fun testNoSouthData() {
        onView(withId(R.id.tram_list_south)).check(matches(isDisplayed()))
    }

    @Test
    fun testSnackBar() {
        testRepo.setErrorResponse()
        testRepo.tramsRepository.getToken().observeForever { listResource ->
            Assert.assertEquals(Resource.Status.ERROR, listResource?.status)
        }
        onView(allOf<View>(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()))
    }

    @After
    fun onTestFinish() {
        testRepo.shutDownServer()
    }

}
