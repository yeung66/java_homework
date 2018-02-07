package search.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import search.Parser;
import search.WebSpider;

import java.io.IOException;
import java.net.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WebSpiderImpl implements WebSpider {

    private String url;
    private List<String> allSubPage=new ArrayList<>();
    public WebSpiderImpl(String u){url=u;}

    class MyThread implements Runnable{
        private int max;
        private Elements elements;
        private int count=0;
        private CountDownLatch latch;

        public MyThread(int n,Elements e,CountDownLatch l){max=n;elements=e;latch=l;}

        public void run(){
            while(true){
                int index;
                synchronized (this){
                    if(count>=max) break;
                    index=count;
                    count++;
                    System.out.print(index);
                }
                getSubHtml(elements.get(index));
            }
            latch.countDown();
        }
    }

    public void getSubHtml(Element element){
        try{
        String a=element.select("strong:contains(ADMITTANCE STATUS)").text();//判断申请状态
        if(!a.equals("")) return;//不能申请则跳过
        Element href=element.getElementsByTag("a").last().previousElementSibling();
        String subUrl=href.attr("href");
        String docSub=Jsoup.connect(subUrl).timeout(10000).get().html();
        allSubPage.add(docSub);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public  List<String> getHtmlFromWeb(){

        try{
            Document docIndex=Jsoup.connect(url).timeout(10000).get();
            Element html=docIndex.getElementsByTag("html").first()
                    .getElementsByTag("body").first();
            Elements subInfo=html.select("div.et_pb_toggle_content").select(".clearfix");

            final CountDownLatch latch = new CountDownLatch(4);
            MyThread subSpider=new MyThread(subInfo.size(),subInfo,latch);

            new Thread(subSpider).start();
            new Thread(subSpider).start();
            new Thread(subSpider).start();
            new Thread(subSpider).start();
            latch.await();
//            for(Element element:subInfo){
//
//                String a=element.select("strong:contains(ADMITTANCE STATUS)").text();
//                if(!a.equals("")) continue;
//                Element href=element.getElementsByTag("a").first();
//                String subUrl=href.attr("href");
//                String docSub=Jsoup.connect(subUrl).get().html();
//                allSubPage.add(docSub);
//
//            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        return allSubPage;
    }

    public Parser getParser(){
        return new ParserImpl();
    }
}
