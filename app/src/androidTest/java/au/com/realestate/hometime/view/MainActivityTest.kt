package au.com.realestate.hometime.view

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import au.com.realestate.hometime.BaseTest
import au.com.realestate.hometime.R
import au.com.realestate.hometime.view.ui.MainActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest : BaseTest() {

    @Rule @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testFragmentLoaded() {
        val fragment = rule.activity.supportFragmentManager.findFragmentById(R.id.fragment_container)
        Assert.assertNotNull(fragment)
    }
}
