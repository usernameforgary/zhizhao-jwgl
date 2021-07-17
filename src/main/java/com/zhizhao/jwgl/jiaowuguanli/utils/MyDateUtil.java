package com.zhizhao.jwgl.jiaowuguanli.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDateUtil {
    /**
     * 判断时间是否早于
     * @param startMilliseconds 开始时间
     * @param stopMilliseconds 结束时间
     * @return true or false
     */
    public static boolean isTimeBefore(Long startMilliseconds, Long stopMilliseconds, boolean canEqual) {
        Date startDate = new Date(startMilliseconds);
        Date stopDate = new Date(stopMilliseconds);
        int res = startDate.compareTo(stopDate);
        if(res < 0) {
            return true;
        }
        if(res == 0 && canEqual) {
            return true;
        }
        return false;
    }

    /**
     * 给定【开始日期】&&【结束日期】&&【星期几】，获取【开始日期】【结束日期】之间的【星期几】的日期列表
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param dates 符合条件的日期
     * @param week 星期几
     * @return 递归返回找到的符合条件的所有日期
     */
     public static List<Long> getFutureDatesByBetweenAndWeek(Long startDate, Long endDate, Week week, List<Long> dates) {
        if(dates == null) {
            dates = new ArrayList<>();
        }
        startDate = DateUtil.beginOfDay(new Date(startDate)).getTime();
        endDate = DateUtil.beginOfDay(new Date(endDate)).getTime();
        if(DateUtil.compare(new Date(startDate), new Date(endDate)) <= 0) {
            Date start = new Date(startDate);
            Week dateWeek = DateUtil.dayOfWeekEnum(start);
            if(dateWeek.equals(week)) {
                dates.add(startDate);
            } else {
                DateTime thisWeekBegin = DateUtil.beginOfWeek(start);
                int addDays = getDayDiffWithMonday(week);
                int offset = 0;
                Long betweenCurrentWeekBegin = DateUtil.betweenDay(new Date(thisWeekBegin.getTime()), start, false);
                if(betweenCurrentWeekBegin > addDays) {
                    offset = 7;
                }
                //获取下周开始时间；注意：周一定为一周的开始时间
                DateTime nextWeekBegin = DateUtil.beginOfWeek(DateUtil.offsetDay(start, offset));
                Long target = DateUtil.offsetDay(nextWeekBegin, addDays).getTime();
                if(DateUtil.compare(new Date(target), new Date(endDate)) <= 0) {
                    dates.add(target);
                }
            }
            getFutureDatesByBetweenAndWeek(DateUtil.offsetWeek( start, 1).getTime(), endDate, week, dates);
        }
        return dates;
    }

    /**
     * 给定【开始日期】&&【星期几】&&【重复次数】；获取【开始日期】之后【重复几次】的【星期几】的列表
     * @param startDate 开始日期
     * @param week 星期几
     * @param dates 符合条件的日期
     * @param times 重复次数
     * @return 递归返回找到的符合条件的所有日期
     */
    public static List<Long> getFutureDatesByStartAndWeekAndTimes(Long startDate, Week week, int times, List<Long> dates) {
        if(dates == null) {
            dates = new ArrayList<>();
        }
        if(times > 0) {
            Date thisDate = new Date(startDate);
            Week dateWeek = DateUtil.dayOfWeekEnum(thisDate);
            if(dateWeek.equals(week)) {
                dates.add(startDate);
            } else {
                DateTime thisWeekBegin = DateUtil.beginOfWeek(thisDate);
                int addDays = getDayDiffWithMonday(week);
                int offset = 0;
                Long betweenCurrentWeekBegin = DateUtil.betweenDay(new Date(thisWeekBegin.getTime()), thisDate, false);
                if(betweenCurrentWeekBegin > addDays) {
                    offset = 7;
                }
                //获取下周开始时间；注意：周一定为一周的开始时间
                DateTime nextWeekBegin = DateUtil.beginOfWeek(DateUtil.offsetDay(thisDate, offset));
                Long target = DateUtil.offsetDay(nextWeekBegin, addDays).getTime();
                dates.add(target);
            }
            times -= 1;
            getFutureDatesByStartAndWeekAndTimes(DateUtil.offsetWeek(thisDate, 1).getTime(), week, times, dates);
        }
        return dates;
    }

    /**
     * 默认每周从星期一开始, 获取给定星期几与星期一之间差几天
     * @param week 星期几
     * @return 相差天数
     */
    public static int getDayDiffWithMonday(Week week) {
        int addDays = 0;
        switch (week) {
            case MONDAY:
                addDays = 0;
                break;
            case TUESDAY:
                addDays = 1;
                break;
            case WEDNESDAY:
                addDays = 2;
                break;
            case THURSDAY:
                addDays = 3;
                break;
            case FRIDAY:
                addDays = 4;
                break;
            case SATURDAY:
                addDays = 5;
                break;
            case SUNDAY:
                addDays = 6;
                break;
            default:
                break;
        }
        return addDays;
    }

    /**
     * 获取介于两个时间之间的所有日期，包含开始和结束
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 符合条件的所有日期
     */
    public static List<Long>  getEveryDatesByBetween(Long startDate, Long endDate) {
        List<Long> dates = new ArrayList<>();
        Long dateDayStart = DateUtil.beginOfDay(new Date(startDate)).getTime();
        Long dateDayEnd = DateUtil.beginOfDay(new Date(endDate)).getTime();

        dates.add(dateDayStart);
        while (DateUtil.compare(new Date(dateDayStart), new Date(dateDayEnd)) < 0) {
            dateDayStart = DateUtil.offsetDay(new Date(dateDayStart), 1).getTime();
            dates.add(dateDayStart);
        }
        return dates;
    }

    /**
     * 根据给定时间，往后获取指定重复次数的，每天的时间。包含给定时间所在的那天
     * @param startDate 开始时间
     * @param times 重复次数
     * @return 符合条件的所有日期
     */
    public static List<Long> getFutureDatesByStartAndTimes (Long startDate, int times) {
        List<Long> dates = new ArrayList<>();
        Long dateDayStart = DateUtil.beginOfDay(new Date(startDate)).getTime();
        for(int i = 0; i < times; i++) {
            dates.add(DateUtil.offsetDay(new Date(dateDayStart), i).getTime());
        }
        return dates;
    }
}
