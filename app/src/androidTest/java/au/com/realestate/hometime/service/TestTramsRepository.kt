package au.com.realestate.hometime.service

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import au.com.realestate.hometime.BaseTest
import au.com.realestate.hometime.view.ui.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestTramsRepository : BaseTest() {

    @Rule @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        addDelay(UILOAD_DELAY);
    }

    @Test
    fun TestErrorResponse() {
        testRepo.setErrorResponse()
        testRepo.tramsRepository.getToken().observeForever { listResource ->
            Assert.assertEquals(Resource.Status.ERROR, listResource?.status)
        }
    }

    @Test
    fun TestTokenSuccess() {
        testRepo.setResponse("token.json")
        testRepo.tramsRepository.getToken().observeForever { listResource ->
            Assert.assertEquals(Resource.Status.SUCCESS, listResource?.status)
        }
    }

    @Test
    fun TestTramSuccess() {
        testRepo.setResponse("tram.json")
        testRepo.tramsRepository.getTrams("1", "abc").observeForever { listResource ->
            Assert.assertEquals(Resource.Status.SUCCESS, listResource?.status)
        }
    }

    @Test
    fun TestTramCount() {
        testRepo.setResponse("tram.json")
        testRepo.tramsRepository.getTrams("1", "abc").observeForever { listResource ->
            Assert.assertEquals(3, listResource?.data!!.size.toLong())
        }
    }
}
