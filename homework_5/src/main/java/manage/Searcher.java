package manage;


import search.Parser;
import search.WebSpider;
import ui.Main;
import vo.Program;

import java.util.ArrayList;
import java.util.List;

public class Searcher {

    public static void run() {

        try {
            Class.forName("search.impl.Manager");
        } catch (ClassNotFoundException e) {
            System.out.println("Manager类不存在");
        }

        List<WebSpider> webSpiders = SearchManager.getWebSpider();

        List<Program> programs = new ArrayList<>();
        for (WebSpider webSpider : webSpiders) {
            Parser parser = webSpider.getParser();
            List<String> pages = webSpider.getHtmlFromWeb();
            for (String page : pages) {
                programs.add(parser.parseHtml(page));
            }
        }
        Main.s.InsertProgram2Mysql(programs);
    }

}
