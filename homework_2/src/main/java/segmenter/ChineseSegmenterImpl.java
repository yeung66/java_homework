package segmenter;

import org.ansj.recognition.impl.*;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.domain.Term;
import vo.StockInfo;

import java.util.ArrayList;
import java.util.List;

public class ChineseSegmenterImpl implements ChineseSegmenter {

    /**
     * this func will get chinese word from a list of stocks. You need analysis stocks' answer and get answer word.
     * And implement this interface in the class : ChineseSegmenterImpl
     * Example: 我今天特别开心 result : 我 今天 特别 开心
     *
     * @param stocks stocks info
     * @return chinese word
     * @see ChineseSegmenterImpl
     */
    @Override
    public List<String> getWordsFromInput(StockInfo[] stocks) {
        //TODO: write your code here
        List<String> result=new ArrayList<String>();
        for(StockInfo info:stocks){
            String temp=info.getAnswer();
            List<Term> terms=removeStopWord(temp,0);
            StopRecognition filter=new StopRecognition();
            filter.insertStopNatures("m");
           for(int i=0;i<terms.size();i++){
               result.add(terms.get(i).getName());
           }
        }
        return result;
    }

    public static List<String> getWordsFromContent(StockInfo stock){
        List<String> temp=new ArrayList<String>();
        List<Term> terms=removeStopWord(stock.getContent(),1);
        for(int i=0;i<terms.size();i++){
            temp.add(terms.get(i).getName());
        }
        return temp;
    }

    public static List<String> getSegWords(String str){
        List<String> res=new ArrayList<String>();
        List<Term> terms=removeStopWord(str,1);
        for(Term term:terms){
            res.add(term.getName());
        }
        return res;
    }

    private static List<Term> removeStopWord(String str,int index){
        String []stopWords={"的","了","得","是","吗"};
        StopRecognition stop=new StopRecognition();
        stop.insertStopNatures("w");//标点
        stop.insertStopNatures("null");
        stop.insertStopWords(stopWords);//
        if(index==0) stop.insertStopNatures("m");
        List<Term> t=ToAnalysis.parse(str).recognition(stop).getTerms();
        return t;
    }
}
