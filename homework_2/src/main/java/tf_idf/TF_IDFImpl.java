package tf_idf;

import javafx.util.Pair;
import segmenter.ChineseSegmenter;
import segmenter.ChineseSegmenterImpl;
import util.StockSorter;
import util.StockSorterImpl;
import vo.StockInfo;
import segmenter.ChineseSegmenterImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TF_IDFImpl implements TF_IDF {
    /**
     * this func you need to calculate words frequency , and sort by frequency.
     * you maybe need to use the sorter written by yourself in example 1
     *
     * @param words the word after segment
     * @return a sorted words
     * @see StockSorter
     */
    @Override
    public Pair<String, Double>[] getResult(List<String> words, StockInfo[] stockInfos) {
        //TODO: write your code here
        Pair<String, Double>[] result = creArray(new Pair<String, Double>("", 0.0), words.size());
        //List<Pair<String,Integer>> result=new ArrayList<Pair<String, Integer>>();
        List<String> wordsChecked = new ArrayList<String>();
        int n = -1;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (wordsChecked.contains(word)) continue;
            n++;
            wordsChecked.add(word);
            int countFrequency = Collections.frequency(words, word);
            double TF = 1d*countFrequency / words.size();
            double TDF = getIDF(word, stockInfos,true);
            double IF_TDF =  (TF * TDF);//
            result[n] = new Pair<String, Double>(word, IF_TDF);
        }
        Pair<String, Double>[] result1=creArray(new Pair<String, Double>("", 0.0), n);
        result1= Arrays.copyOf(result,n);
        result=StockSorterImpl.sortPairInList1(result1);

        return result;
    }

    private static double getIDF(String word, StockInfo[] stockInfos,boolean from) {
        int count = 0, numAnswer = 0;
        for (StockInfo info : stockInfos) {
            if (info.getAnswer().contains(word)&&from) count++;
            else if(info.getContent().contains((word))&&!from) count++;//from为false时从content算idf
            numAnswer++;
        }
        return Math.log(numAnswer / (count + 1));
    }

    public static <T> T[] creArray(T obj, int size) {
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) Array.newInstance(obj.getClass(), size);
        return arr;
    }

    public static String[][] getKeyWords(StockInfo[] stockInfos){
        String [][] result= new String[stockInfos.length][20];
        for(int i =0;i<stockInfos.length;i++){
            List<String> words= ChineseSegmenterImpl.getWordsFromContent(stockInfos[i]);
            List<String> wordsChecked = new ArrayList<String>();
            Pair<String, Double>[] pairs = creArray(new Pair<String, Double>("", 0.0d), words.size());
            int n=-1;
            for(int j =0;j<words.size();j++){
                String word=words.get(j);
                if(wordsChecked.contains(word)) continue;;
                n++;wordsChecked.add(word);
                int countFrequency=Collections.frequency(words,word);
                double TF=1d*countFrequency/words.size();
                double IDF=getIDF(word,stockInfos,false);
                double TF_IDF=TF*IDF;
                pairs[n]=new Pair<String, Double>(word, TF_IDF);
            }
            Pair<String,Double> []pairs1=creArray(new Pair<String, Double>("", 1d), n);
            pairs1=Arrays.copyOf(pairs,n);
            pairs=StockSorterImpl.sortPairInList1(pairs1);
            for(int k=0;k<20;k++)result[i][k]=pairs[k].getKey();
        }
        return result;
    }
}
