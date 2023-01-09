package ru.vkbot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Update {
    public static String timeUp="0";

    public static void timer() throws IOException {
        Calendar calendarTimer = new GregorianCalendar();
        if (calendarTimer.get(Calendar.HOUR_OF_DAY)==7 && calendarTimer.get(Calendar.MINUTE)==44 && calendarTimer.get(Calendar.SECOND)==0){
            parsHtml();
        }
    }

    public static void parsHtml() throws IOException {
        deleteOld();
        Document document = Jsoup.connect("https://www.mirea.ru/schedule/").get();
        Elements elements = document.select(".uk-link-toggle");
        for (Element element : elements) {
            if (element.attr("abs:href").indexOf("xlsx") != -1 && element.attr("abs:href").indexOf("Экз") == -1 && element.attr("abs:href").indexOf("зач") == -1 && element.attr("abs:href").indexOf("сессия") == -1) {
                System.out.println(element.attr("abs:href"));
                downloadFile(element.attr("abs:href"), Paths.get("C:\\расписание"));
            }
        }
        timeUp = new Date().toString();
    }

    public static void deleteOld(){
        File file = new File("C:\\расписание");
        if (file.length() != 0){
            for (File file1: file.listFiles()){
                file1.delete();
            }
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url=new URL(urlString);
        InputStream inputStream=url.openStream();

        Path tmp= Files.createTempFile("temp-",".tmp");
        Files.copy(inputStream,tmp, StandardCopyOption.REPLACE_EXISTING);

        String fieName=urlString.substring(urlString.lastIndexOf("/"));
        Path destPath= Paths.get(downloadDirectory.toString(), fieName);
        Files.move(tmp,destPath);

        return destPath;
    }



}

