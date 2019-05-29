package au.com.realestate.hometime;

import au.com.realestate.hometime.service.TramsRepository;

public class BaseTest {

    protected TestRepository mTestRepo;
    protected TramsRepository mTramsRepo;

    public void init() {
        mTestRepo = TestRepository.getInstance();
        mTramsRepo = mTestRepo.getTramsRepository();
    }

    public void addDelay(long delay) throws Exception {
        Thread.sleep(delay);
    }

}
