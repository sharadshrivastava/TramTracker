package au.com.realestate.hometime.view;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import au.com.realestate.hometime.BaseTest;
import au.com.realestate.hometime.R;
import au.com.realestate.hometime.view.ui.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestMainActivity extends BaseTest{

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        super.init();
    }

    @Test
    public void TestFragmentLoaded() throws Exception{
        Fragment fragment = rule.getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        Assert.assertNotNull(fragment);
    }

}
