/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.java.proxy;

import com.java.Print;

/**
 * des: 中介,代理租房
 *
 * @author liuwenrong@yotamobile.com
 * @version 1.0, 2017/9/16
 */
public class AgencyHireProxy implements Hire {

    LandlordHireImpl landlordHire;

    public AgencyHireProxy(LandlordHireImpl landlordHire) {
        this.landlordHire = landlordHire;
    }

    @Override
    public void hire() {

        Print.out("收中介费,管理费,物业费");
        landlordHire.hire();
        Print.out("收押金...");

    }
}
