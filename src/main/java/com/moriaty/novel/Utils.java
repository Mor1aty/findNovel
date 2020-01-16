package com.moriaty.novel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    // 获取 Document
    public static Document getNovelDoc(String url) throws IOException {
        return Jsoup.connect(url).timeout(5000).get();
    }

    // 获取小说标题
    public static String getNovelTitle(Document document) {
        return document.getElementsByTag("h1").text();
    }

    // 获取所有的章节 url
    public static List<String> getChapterUrlList(Document document) {
        Elements dds = document.getElementsByTag("dd");
        List<String> list = new ArrayList<>();
        for (Element dd : dds) {
            list.add(dd.getElementsByTag("a").attr("href"));
        }
        return list;
    }

    // 获取章节标题
    public static String getChapterTitle(Document document) {
        return document.getElementsByTag("h1").text();
    }

    // 获取章节内容
    public static String getChapterContent(Document document) {
        String content = document.getElementById("content").html();
        content = content.replaceAll("&nbsp;", " ").replaceAll("<br>", "");
        return content;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getChapterContent(getNovelDoc("http://www.ltoooo.com/3_3614/2206022.html")));
    }
}
