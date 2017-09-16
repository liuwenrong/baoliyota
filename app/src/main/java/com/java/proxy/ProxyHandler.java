/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.java.proxy;

import com.java.Print;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * des:
 *
 * @author liuwenrong@yotamobile.com
 * @version 1.0, 2017/9/16
 */
public class ProxyHandler<T> implements InvocationHandler {

    private T t;

    public ProxyHandler(T t) {
        this.t = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Print.out("收中介费,管理费,物业费");
        Print.out("收押金");

        return method.invoke(t, args);
//        return method.invoke(proxy, args); //狂打印数据
    }
}
