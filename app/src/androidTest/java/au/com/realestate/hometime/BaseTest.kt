package au.com.realestate.hometime

abstract class BaseTest {

    protected var testRepo = TestRepository.get()
    protected val UILOAD_DELAY = 2000L

    fun addDelay(millis: Long) {
        Thread.sleep(millis)
    }
}
