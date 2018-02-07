package vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import segmenter.ChineseSegmenter;

public class StockInfo {
    private String Title;
    private String Content;
    private String Answer;
    private List<String> words;
    private int ID;

    public StockInfo(String []Info,int n){
        Title=Info[1];
        Content=Info[5];
        Answer=Info[7];
        ID=n;

        words=new ArrayList<String>();
        ChineseSegmenter seg=new ChineseSegmenter();
        List<String> temp;
        String[] s={Title,Content,Answer};
        for(String i:s){
            temp=seg.getWord(i);
            words.addAll(temp);
        }

    }

    public int getFrequency(List<String> w){
        int res=0;
        for(String word:w) res+= Collections.frequency(words,word);
        return res;
    }

    public String getTitle() {
        return Title;
    }

    public String getAnswer() {
        return Answer;
    }

    public String getContent() {
        return Content;
    }

    public List<String> getWords() {
        return words;
    }

    public int getID(){
        return ID;
    }
}
