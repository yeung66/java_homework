package vo;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import tf_idf.TF_IDFImpl;

public class UserInterest {
    private int [] read;


    public UserInterest(String str){
        char[] interest=str.toCharArray();
        int id=0;
        List<Integer> inter=new ArrayList<Integer>();
        for(char i:interest){
            id++;
            if(i=='1') {inter.add(id);}
        }
        read=new int[inter.size()];
        for(int i=0;i<inter.size();i++){
            read[i]=inter.get(i);
        }
    }

    public double[] getRecommennd(double[][]matrix){
        final int size=60;
        double point;
        Pair<Integer,Double>[] points=TF_IDFImpl.creArray(new Pair<Integer, Double>(0,0.0), size);
        for(int i=0;i<size;i++){
            point=0;
            for(int aRead:read){
                if(aRead==i+1) {point=0;break;}
                point+=matrix[i][aRead-1];
            }
            points[i]=new Pair<Integer,Double>(i+1,point);
        }
        double[] res=new double[10];
        for(int i=0;i<10;i++){
            for(int j=i+1;j<points.length;j++){
                if(points[i].getValue()<points[j].getValue()){
                    Pair<Integer,Double> temp=points[i];
                    points[i]=points[j];
                    points[j]=temp;
                }
            }
            res[i]=points[i].getKey();
        }
        return res;
    }
}
