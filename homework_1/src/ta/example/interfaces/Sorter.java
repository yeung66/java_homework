package ta.example.interfaces;


import ta.example.vo.StockInfo;

public class Sorter implements  StockSorter{
    public StockInfo[] sort(StockInfo[] info){
        quickSort(info,0,info.length-1);
        return info;
    }

    public StockInfo[] sort(StockInfo[] info,boolean order){
        for(int i=0;i<info.length;i++){
            int key=info[i].getAnswerLength();
            for(int j=i+1;j<info.length;j++){
                if(Compary(info[j].getAnswerLength(),info[i].getAnswerLength(),order)){
                    StockInfo temp=info[i];
                    info[i]=info[j];
                    info[j]=temp;
                }
            }
        }
        return info;
    }

    private boolean Compary(int a,int b,boolean order){
        if(order) return a<b;
        else return a>b;
    }

    private void quickSort(StockInfo[] info,int low,int high){
        int l=low;int h=high;
        int key=info[l].getAnswerLength();

        while(l<h){
            while(l<h && info[h].getAnswerLength()>key)
                h--;
            if(l<h){
                StockInfo temp=info[l];
                info[l]=info[h];
                info[h]=temp;
                l++;
            }
            while(l<h && info[l].getAnswerLength()<key)
                l++;
            if(l<h){
                StockInfo temp=info[l];
                info[l]=info[h];
                info[h]=temp;
                h--;
            }
        }
        if(l>low)quickSort(info,low,l-1);
        if(h<high)quickSort(info,l+1,high);
    }
}
