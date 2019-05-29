package au.com.realestate.hometime;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import au.com.realestate.hometime.utils.Utils;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

    @Mock
    Context mContext;
    @Mock
    Resources mResources;
    @Mock
    DisplayMetrics mDisplayMetrics;



    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);

        Mockito.when(mContext.getResources()).thenReturn(mResources);
        Mockito.when(mResources.getDisplayMetrics()).thenReturn(mDisplayMetrics);
        mDisplayMetrics.density = 5f;
    }

    @Test
    public void testDateFromDotNetDate() throws Exception {
        String expected = "Wed May 29 14:29:21 AEST 2019";
        Date date = Utils.INSTANCE.dateFromDotNetDate("/Date(1559104161873+1000)/");
        assertEquals(expected, date.toString());
    }

    @Test
    public void testPxFromDp() throws Exception {
        float expected = 80f;
        float actual = Utils.INSTANCE.pxFromDp(mContext, 16);
        assertTrue(expected == actual);
    }
}