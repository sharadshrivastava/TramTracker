package au.com.realestate.hometime.service

import au.com.realestate.hometime.BaseTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class TramsRepositoryTest : BaseTest() {

    @Before
    fun setup() {
        addDelay(UILOAD_DELAY);
    }

    @Test
    fun testErrorResponse() {
        testRepo.setErrorResponse()
        testRepo.tramsRepository.getToken().observeForever { listResource ->
            Assert.assertEquals(Resource.Status.ERROR, listResource?.status)
        }
    }

    @Test
    fun testTokenSuccess() {
        testRepo.setResponse("token.json")
        testRepo.tramsRepository.getToken().observeForever { listResource ->
            Assert.assertEquals(Resource.Status.SUCCESS, listResource?.status)
        }
    }

    @Test
    fun testTramSuccess() {
        testRepo.setResponse("tram.json")
        testRepo.tramsRepository.getTrams("1", "abc").observeForever { listResource ->
            Assert.assertEquals(Resource.Status.SUCCESS, listResource?.status)
        }
    }

    @Test
    fun testTramCount() {
        testRepo.setResponse("tram.json")
        testRepo.tramsRepository.getTrams("1", "abc").observeForever { listResource ->
            Assert.assertEquals(3, listResource?.data.orEmpty().size.toLong())
        }
    }
}
