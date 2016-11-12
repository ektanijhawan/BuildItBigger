package com.udacity.gradle.builditbigger;




import org.junit.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by Ekta on 13-11-2016.
 */

public class TestClass  implements EndpointsAsyncTask.EndpoinrResponseInterface{
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    private final CountDownLatch mSignal = new CountDownLatch(1);

    @Test
    public void testJokeRetriever() {
        new EndpointsAsyncTask(this ).execute();
        try {
            boolean success = mSignal.await(5, TimeUnit.SECONDS);
            if (!success) {
                fail("Test timed out, make sure the server is actually running.");
            }
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Override
    public void onResponse(boolean isSuccess, String result) {
        assertTrue(isSuccess && result != null && result.length() > 0);
        mSignal.countDown();
    }
}