/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.java.proxy;

import com.java.Print;

/**
 * des: 实现类,房东
 *
 * @author liuwenrong@yotamobile.com
 * @version 1.0, 2017/9/16
 */
public class LandlordHireImpl implements Hire {
    @Override
    public void hire() {
        Print.out("租房给用户");
    }
}
