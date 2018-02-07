package recommend;


import vo.StockInfo;
import vo.UserInterest;
import tf_idf.TF_IDFImpl;
import segmenter.ChineseSegmenterImpl;

import java.util.*;

public class RecommenderImpl implements Recommender {

    /**
     * this function need to calculate stocks' content similarity,and return the similarity matrix
     *
     * @param stocks stock info
     * @return similarity matrix
     */
    @Override
    public double[][] calculateMatrix(StockInfo[] stocks) {
        //TODO: write your code here
        int size=stocks.length;
        String[][] keyWords=TF_IDFImpl.getKeyWords(stocks);
        double [][] result=new double[size][size];
        for(int i=0;i<size;i++){
            for(int j=i;j<size;j++){
                if(i==j) result[i][j]=0;
                else{
                    String[] keys=new String[40];
                    System.arraycopy(keyWords[i],0,keys,0,20);
                    System.arraycopy(keyWords[j],0,keys,20,20);
                    Set<String> words=new HashSet<String>(Arrays.asList(keys));
                    int[] vector1=new int[words.size()];
                    int[] vector2=new int[words.size()];
                    int n=0;
                    List<String> segWords1= ChineseSegmenterImpl.getSegWords(stocks[i].getContent());
                    List<String> segWords2= ChineseSegmenterImpl.getSegWords(stocks[j].getContent());
                    for(String word:words){
                        int TF1= Collections.frequency(segWords1,word);
                        int TF2= Collections.frequency(segWords2,word);
                        vector1[n]=TF1;vector2[n]=TF2;
                        n++;
                    }
                    double cos=calcCos(vector1,vector2,words.size());
                    result[i][j]=cos;result[j][i]=cos;
                }
            }
        }
        return result;
    }

    private double calcCos(int []vector1,int [] vector2, int size){
        int up=0,down1=0,down2=0;
        for(int i=0;i<size;i++){
            up=vector1[i]*vector2[i]+up;
            down1=vector1[i]*vector1[i]+down1;
            down2=vector1[i]*vector1[i]+down2;
        }
        return up/(Math.sqrt(down1*down2));
    }

    /**
     * this function need to recommend the most possibility stock number
     *
     * @param matrix       similarity matrix
     * @param userInterest user interest
     * @return commend stock number
     */
    @Override
    public double[][] recommend(double[][] matrix, UserInterest[] userInterest) {
        //TODO: write your code here
        double [][] res=new double[userInterest.length][10];
        for(int i=0;i<userInterest.length;i++){
            res[i]=userInterest[i].getRecommennd(matrix);
        }
        return res;
    }
}
