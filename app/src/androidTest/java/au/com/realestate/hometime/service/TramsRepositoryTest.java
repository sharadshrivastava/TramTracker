package au.com.realestate.hometime.service;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import au.com.realestate.hometime.BaseTest;
import au.com.realestate.hometime.view.ui.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TramsRepositoryTest extends BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        super.init();
    }

    @Test
    public void TestErrorResponse() throws Exception {
        mTestRepo.setErrorResponse();
        mTestRepo.getTramsRepository().getToken().observe(rule.getActivity(), listResource -> {
            Assert.assertEquals(Resource.Status.ERROR, listResource.getStatus());
        });
    }

    @Test
    public void TestTokenSuccess() throws Exception {
        mTestRepo.setResponse("token.json");
        mTestRepo.getTramsRepository().getToken().observe(rule.getActivity(), listResource -> {
            Assert.assertEquals(Resource.Status.SUCCESS, listResource.getStatus());
        });
    }

    @Test
    public void TestTramSuccess() throws Exception {
        mTestRepo.setResponse("tram.json");
        mTestRepo.getTramsRepository().getTrams("1", "abc").observe(rule.getActivity(), listResource -> {
            Assert.assertEquals(Resource.Status.SUCCESS, listResource.getStatus());
        });
    }

    @Test
    public void TestTramCount() throws Exception {
        mTestRepo.setResponse("tram.json");
        mTestRepo.getTramsRepository().getTrams("1", "abc").observe(rule.getActivity(), listResource -> {
            Assert.assertEquals(3, listResource.getData().size());
        });
    }
}
