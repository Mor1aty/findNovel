package com.moriaty;

import com.moriaty.novel.Utils;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Work {
    public static void main(String[] args) {
        String bookId = "7_7724";
        String basePath = "C:\\Users\\97189\\Desktop\\";
        String baseUrl = "http://www.ltoooo.com/";
        String temp = "";
        String breakUrl = "";
        try {
            Document document = Utils.getNovelDoc(baseUrl + bookId);
            String title = Utils.getNovelTitle(document);
            File file = new File(basePath + title);
            FileWriter writer = new FileWriter(file + ".txt", true);
            writer.write("<<" + title + ">>");
            System.out.println(title + "开始爬取");
            List<String> chapterUrlList = Utils.getChapterUrlList(document);
            for (String url : chapterUrlList) {
                if (temp != "") {
                    if (temp.equals(url)) {
                        temp = "";
                    }
                    continue;
                }
                breakUrl = url;
                Document novelDoc = Utils.getNovelDoc(baseUrl + url);
                String chapterTitle = Utils.getChapterTitle(novelDoc);
                writer.write("\n" + chapterTitle + "\n");
                writer.write(Utils.getChapterContent(novelDoc));
                writer.flush();
                System.out.println(chapterTitle + " ---------------------------- 完成");
            }
            writer.close();
            System.out.println(title + "------- 爬取结束");
        } catch (IOException e) {
            System.out.println("截断位置：" + breakUrl);
            e.printStackTrace();
        }
    }
}
