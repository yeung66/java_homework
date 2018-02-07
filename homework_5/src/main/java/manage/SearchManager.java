package manage;



import search.WebSpider;

import java.util.ArrayList;
import java.util.List;

public final class SearchManager {

    private static List<WebSpider> webSpiders;



    private SearchManager(){}

    public static void registSpider(WebSpider webSpider){
        getWebSpider().add(webSpider);
    }


    public static List<WebSpider> getWebSpider(){
        if(webSpiders == null){
            synchronized (SearchManager.class){
                if(webSpiders == null)
                    webSpiders = new ArrayList<>();
            }
        }
        return webSpiders;
    }



}
