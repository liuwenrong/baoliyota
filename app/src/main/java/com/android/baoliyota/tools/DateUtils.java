/* *
   * Copyright (C) 2017 BaoliYota Tech. Co., Ltd, LLC - All Rights Reserved.
   *
   * Confidential and Proprietary.
   * Unauthorized copying of this file, via any medium is strictly prohibited.
   * */

package com.android.baoliyota.tools;

import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * des:
 *
 * @author liuwenrong
 * @version 1.0, 2017/4/5
 */
public class DateUtils {

    /**
     * @param time 时间戳
     * @param formatType 时间转化格式
     * @return 是否是当天
     */
    public static boolean isToday(long time, @Nullable String formatType) {
        if (time == 0){
            long currentTime = new Date().getTime();
            time = currentTime - 86400000;
        }
        if (formatType == null) {
            formatType = "yyyy-MM-dd";
        }
        Date dateOld = new Date(time);
//        String sDateTime = dateToString(dateOld, formatType);
//        Date date = null;
//        try {
//            date = stringToDate(sDateTime, formatType);
//        } catch (ParseException e) {
//            date = new Date();
//        }


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType, Locale.getDefault());
        if (simpleDateFormat.format(dateOld).equals(simpleDateFormat.format(new Date()))) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * @param data 日期对象
     * @param formatType 时间转化格式
     * @return 时间字符串
     */
    private static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType, Locale.getDefault()).format(data);
    }

    /**
     * @param strTime 时间字符串
     * @param formatType 时间转化格式
     * @return 日期对象
     * @throws ParseException strTime的时间格式和formatType的时间格式不同可能导致转化异常
     */
    private static Date stringToDate(String strTime, String formatType) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType, Locale.getDefault());
        Date date = formatter.parse(strTime);
        return date;
    }
}
