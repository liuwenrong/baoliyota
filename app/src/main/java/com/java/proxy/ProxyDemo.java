/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.java.proxy;

import java.lang.reflect.Proxy;

/**
 * des:
 *
 * @author liuwenrong@yotamobile.com
 * @version 1.0, 2017/9/16
 */
public class ProxyDemo {

    public static void main(String[] args) {

        LandlordHireImpl landlordHire = new LandlordHireImpl();
        AgencyHireProxy agencyHireProxy = new AgencyHireProxy(landlordHire);//中介代理房东租房

//        agencyHireProxy.hire();

        ProxyHandler<LandlordHireImpl> proxyHandler = new ProxyHandler(landlordHire);
//        ProxyHandler<AgencyHireProxy> proxyHandler = new ProxyHandler(agencyHireProxy);

        Hire hire = (Hire) Proxy.newProxyInstance(Hire.class.getClassLoader(), new Class[]{Hire.class}, proxyHandler);

        hire.hire();


    }

}
