package tf_idf;

import javafx.util.Pair;
import sort.Sorter;
import vo.StockInfo;
import Interface.MainInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class TF_IDF {
    public static List<String> getKeyWords(StockInfo info){
        List<String> words=new ArrayList<String>();
        HashSet<String> h =new HashSet<String>(info.getWords());
        words.addAll(h);
        int []index=new int[words.size()];
        double []tf_idfs=new double[words.size()];
        int n=0;
        for(String word:words){
            int tf= Collections.frequency(info.getWords(),word);
            double idf=calIDF(word);
            double tf_idf=tf*idf;
            index[n]=n;tf_idfs[n]=tf_idf;n++;
        }
        index= Sorter.sortByTFIDF(index,tf_idfs);
        List<String> result=new ArrayList<String>();
        for(int i=0;i<30;i++){//取前三十个关键词
            result.add(words.get(index[i]));
        }
        return result;
    }

    public static String[][] getKeyWords(StockInfo[] infos){
        int n=infos.length;
        String [][]result=new String[n][];
        for(int i=0;i<n;i++){
            result[i]=getKeyWords(infos[i]).toArray(new String[30]);
        }
        return result;
    }

    private static double calIDF(String word){
        int count=0,n=0;
        for(StockInfo i:MainInterface.stocks){
            if(i.getWords().contains(word)) count++;
            n++;
        }
        return Math.log(n/(count+1));
    }
}
