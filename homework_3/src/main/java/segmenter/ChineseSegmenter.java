package segmenter;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.recognition.impl.StopRecognition;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ChineseSegmenter
{
    private static StopRecognition recog;

    public ChineseSegmenter(){
        if(recog!=null) return;
        recog=new StopRecognition();
        String stopWordTable = "stopwords.txt";
        System.out.print(stopWordTable);
        File f = new File(stopWordTable);
        try{
        FileInputStream fileInputStream = new FileInputStream(f);
        //读入停用词文件
        BufferedReader StopWordFileBr = new BufferedReader(new InputStreamReader(fileInputStream));
        String stopWord = null;
        for(; (stopWord = StopWordFileBr.readLine()) != null;){
            recog.insertStopWords(stopWord);
        }
        StopWordFileBr.close();

        }catch(Exception e){
            return;
        }
        recog.insertStopNatures("null");recog.insertStopNatures("w");
    }

    public List<String> getWord(String str){
        List<String> result=new ArrayList<String>();
        List<Term> terms=ToAnalysis.parse(str).recognition(recog).getTerms();
        for(int i=0;i<terms.size();i++){
            result.add(terms.get(i).getName());
        }
        return result;
    }


}
