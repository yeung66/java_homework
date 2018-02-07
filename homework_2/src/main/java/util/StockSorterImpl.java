package util;

import vo.StockInfo;

import java.util.List;
import javafx.util.Pair;

public class StockSorterImpl implements StockSorter {
    /**
     * Accepting series of stock info, sorting them ascending according to their comment length.
     * List.sort() or Arrays.sort() are not allowed.
     * You have to write your own algorithms,Bubble sort、quick sort、merge sort、select sort,etc.
     *
     * @param info stock information
     * @return sorted stock
     */
    @Override
    public StockInfo[] sort(StockInfo[] info) {
        //TODO: write your code here
        return null;
    }

    /**
     * Accepting series of stock info, sorting them ascending, descending order.
     *
     * @param info  stock information
     * @param order true:ascending,false:descending
     * @return sorted stock
     */
    @Override
    public StockInfo[] sort(StockInfo[] info, boolean order) {
        //TODO: write your code here
        return null;
    }


    public static Pair<String,Double>[] sortPairInList1(Pair<String,Double>[] pairs){
        Pair<String,Double> temp;
        for(int i=0;i<pairs.length;i++){
            for(int j=i+1;j<pairs.length;j++){
                if(pairs[i].getValue()<pairs[j].getValue()){
                    temp=pairs[i];pairs[i]=pairs[j];pairs[j]=temp;
                }
            }
        }
        return pairs;
    }
}
