package ru.vkbot;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeTest {
    public static int work;
    public static int semWeek;

    public static int StudyWeek(){
        Calendar myCalendar = new GregorianCalendar();
        if ((myCalendar.get(Calendar.WEEK_OF_YEAR)>35&myCalendar.get(Calendar.WEEK_OF_YEAR)<=52)||(myCalendar.get(Calendar.WEEK_OF_YEAR)>=6&myCalendar.get(Calendar.WEEK_OF_YEAR)<23)){
            if (myCalendar.get(Calendar.WEEK_OF_YEAR)%2==0){
                work=1;
            }
            else {
                work=2;
            }
        }
        else {
            work=-1;
        }
        return work;
    }

    public static int SemesterWeek(){
        Calendar myCalendar = new GregorianCalendar();
        if (myCalendar.get(Calendar.WEEK_OF_YEAR)>=6&myCalendar.get(Calendar.WEEK_OF_YEAR)<23){
            semWeek = myCalendar.get(Calendar.WEEK_OF_YEAR) - 5;
        }
        else if (myCalendar.get(Calendar.WEEK_OF_YEAR)>35&myCalendar.get(Calendar.WEEK_OF_YEAR)<=52){
            semWeek = myCalendar.get(Calendar.WEEK_OF_YEAR) - 35;
        }
        return semWeek;
    }


    public static StringBuilder PrintDay(int k, StringBuilder otvet){
        switch (k){
            case 0:
                otvet.append("\uD83D\uDCC5 ПОНЕДЕЛЬНИК\n");
                break;
            case 1:
                otvet.append("\uD83D\uDCC5 ВТОРНИК\n");
                break;
            case 2:
                otvet.append("\uD83D\uDCC5 СРЕДА\n");
                break;
            case 3:
                otvet.append("\uD83D\uDCC5 ЧЕТВЕРГ\n");
                break;
            case 4:
                otvet.append("\uD83D\uDCC5 ПЯТНИЦА\n");
                break;
            case 5:
                otvet.append("\uD83D\uDCC5 СУББОТА\n");
                break;
            default:
                otvet.append("ОШИБКА");
        }
        return otvet;
    }

    public static StringBuilder PrintTime(int i, StringBuilder otvet){
        switch (i){
            case 0:
                otvet.append("⏱ 9:00-10:30 ⏱\n");
                break;
            case 1:
                otvet.append("⏱ 10:40-12:10 ⏱\n");
                break;
            case 2:
                otvet.append("⏱ 12:40-14:10 ⏱\n");
                break;
            case 3:
                otvet.append("⏱ 14:20-15:50 ⏱\n");
                break;
            case 4:
                otvet.append("⏱ 16:20-17:50 ⏱\n");
                break;
            case 5:
                otvet.append("⏱ 18:00-19:30 ⏱\n");
                break;
            default:
                otvet.append("ОШИБКА");
        }
        return otvet;
    }
}
