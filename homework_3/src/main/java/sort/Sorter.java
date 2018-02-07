package sort;

import java.util.Arrays;

public class Sorter {
    public static int [] sortByFrequency(int[]frequency){
        int n=frequency.length,temp;
        int []index=new int[n];
        for(int i=0;i<n;i++)
            index[i]=i;
        for(int i=0;i<n;i++)
            for(int j=i+1;j<n;j++)
                if(frequency[j]>frequency[i]) {
                    temp=frequency[j];frequency[j]=frequency[i];frequency[i]=temp;
                    temp=index[j];index[j]=index[i];index[i]=temp;
                }

        return index;
    }

    public static int[] sortByTFIDF(int []index,double[] tf_idfs){
        int n=index.length;
        int temp;double tempT;
        for(int i=0;i<n;i++)
            for(int j=i+1;j<n;j++)
                if(tf_idfs[j]>tf_idfs[i]) {
                    tempT=tf_idfs[j];tf_idfs[j]=tf_idfs[i];tf_idfs[i]=tempT;
                    temp=index[j];index[j]=index[i];index[i]=temp;
                }
        return index;
    }

    public static int[] sortTop5Similarity(double []sim){
        int n=sim.length;
        double temp;int tempIn;
        int []index=new int[n];
        for(int i=0;i<n;i++)
            index[i]=i;
        for(int i=0;i<5;i++)
            for(int j=i+1;j<n;j++){
                if(sim[j]>sim[i]){
                    temp=sim[j];sim[j]=sim[i];sim[i]=temp;
                    tempIn=index[j];index[j]=index[i];index[i]=tempIn;
                }
            }
        return Arrays.copyOfRange(index,0,5);
    }
}
