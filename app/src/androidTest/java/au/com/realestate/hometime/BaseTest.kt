package au.com.realestate.hometime

import au.com.realestate.hometime.service.TramsRepository

abstract class BaseTest {

    protected lateinit var testRepo: TestRepository
    protected lateinit var tramsRepo: TramsRepository

    protected val UILOAD_DELAY = 2000L

    open fun setup() {
        testRepo = TestRepository.getInstance()
        tramsRepo = testRepo.tramsRepository
    }

    fun addDelay(millis:Long){
        Thread.sleep(millis)
    }
}
