package search.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import search.Parser;
import vo.Program;

import java.util.UUID;

public class ParserImpl implements Parser{

    public Program parseHtml(String html){
        Program result=new Program();

        result.setCountry("United States");
        result.setUniversity("University of Delaware");
        result.setId(UUID.randomUUID().toString().replace("-","").substring(0,32));
        Document doc= Jsoup.parse(html);
        Element container=doc.select("div#AllContents").select("div.et_pb_text_inner").first();
        result.setSchool(container.getElementsByTag("div").first().getElementsByTag("a").first().text());

        Element header=container.getElementsByTag("h1").first();
        result.setProgramName(header.getElementsByTag("a").first().text());
        result.setHomepage(header.getElementsByTag("a").first().attr("href"));
        result.setDegree(header.getElementsByTag("span").first().text());

        Element ddlInfo=container.select("h4:contains(Application Deadline)").first().nextElementSibling();
        Elements ddlWithAidInfo=ddlInfo.select("em:contains(To be considered for departmental funding)");
        if(ddlWithAidInfo.size()!=0) {//找有无有奖ddl
            String ddlWithaid="";
            for (Element e : ddlWithAidInfo) {
                String season=e.parent().previousElementSibling().text();
                String date=e.previousElementSibling().text();
                ddlWithaid= ddlWithaid+season+date+" ";
                e.previousElementSibling().remove();
                if(e.nextElementSibling()==null) e.parent().previousElementSibling().remove();//如果该列只有有奖ddl，把申请的季节也移除
                e.remove();
            }
            result.setDeadlineWithAid(ddlWithaid);
        }else
            result.setDeadlineWithAid("NULL");
        String ddl=ddlInfo.text();
        result.setDeadlineWithoutAid(ddl);

        Element contactInfo=doc.select("div.program-column2").first().
                getElementsByTag("div").last();
        Element email=contactInfo.getElementsByTag("a").last();
        String emailInfo=email.attr("href").replace("mailto:","");
        if(emailInfo.equals("") ) result.setEmail("NULL");
        else result.setEmail(emailInfo);
        result.setPhoneNumber(email.previousSibling().toString().replace(" •",""));
        email.previousSibling().remove();email.remove();
        result.setLocation(contactInfo.text());
        return result;
    }
}
