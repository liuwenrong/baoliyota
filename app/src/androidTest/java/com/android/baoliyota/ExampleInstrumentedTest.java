/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.android.baoliyota.imageloader.GlideCacheUtil;
import com.android.baoliyota.tools.DateUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void getDiskCacheSize() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        GlideCacheUtil.getInstance().clearImageAllCache(appContext);
        assertEquals("663.23KB", GlideCacheUtil.getCacheSize(appContext));
    }

/*    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.android.systemuib", appContext.getPackageName());
    }*/

/*    @Test
    public void test() throws Exception {
//        Context context = InstrumentationRegistry.getContext();
//        assertEquals("com.android.systemuib", context.getPackageName());

        boolean debug = Utils.DEBUG;
        assertEquals(false, debug);
    }*/
    @Test
    public void testNetWorkState() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        boolean isConnected = com.android.baoliyota.network.Utils.isNetworkConnected(appContext);
        assertEquals(true, isConnected);

    }
    @Test
    public void testWifi() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals(true, com.android.baoliyota.network.Utils.isWifiConnected(appContext));
    }
    @Test
    public void testNetState() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals(0, com.android.baoliyota.network.Utils.getNetType(appContext));
    }
    @Test
    public void testTime() throws Exception {
        assertEquals(true, DateUtils.isToday(0, null));
    }
}
