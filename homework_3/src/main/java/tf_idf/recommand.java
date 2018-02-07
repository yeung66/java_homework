package tf_idf;

import sort.Sorter;
import vo.StockInfo;

import java.util.*;


public class recommand
{
    public static double[][] calculateMatrix(StockInfo[] stocks){
        int size=stocks.length;
        String [][] keyWords=TF_IDF.getKeyWords(stocks);
        double [][] result=new double[size][size];
        for(int i=0;i<size;i++){
            for(int j=i;j<size;j++){
                if(i==j) result[i][j]=0;
                else{
                    String[] keys=new String[60];
                    System.arraycopy(keyWords[i],0,keys,0,30);
                    System.arraycopy(keyWords[j],0,keys,30,30);
                    Set<String> words=new HashSet<String>(Arrays.asList(keys));
                    int[] vector1=new int[words.size()];
                    int[] vector2=new int[words.size()];
                    int n=0;
                    List<String> segWords1= stocks[i].getWords();
                    List<String> segWords2= stocks[j].getWords();
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

    private static double calcCos(int []vector1,int [] vector2, int size){
        int up=0,down1=0,down2=0;
        for(int i=0;i<size;i++){
            up=vector1[i]*vector2[i]+up;
            down1=vector1[i]*vector1[i]+down1;
            down2=vector1[i]*vector1[i]+down2;
        }
        return up/(Math.sqrt(down1*down2));
    }

    public static int[] get5Recommand(double[][] matrix,int i){
        double []similarity=matrix[i];
        return Sorter.sortTop5Similarity(similarity);

    }
}
