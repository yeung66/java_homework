package io;
import vo.StockInfo;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class FileHandler {
    static public StockInfo[] getInfoFromFile(File file) {
        int n = 0;
        StockInfo[] result=new StockInfo[10000];
        BufferedReader reader = null;
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
            reader = new BufferedReader(read);
            String tempString = null;
            reader.readLine();
            while ((tempString = reader.readLine()) != null) {
                String[] tempInfo = tempString.split("\t");
                result[n] = new StockInfo(tempInfo,n);
                n++;
            }
            reader.close();
        } catch (IOException excep) {
            return null;
        }
        StockInfo[] res= new StockInfo[n];
        for(int i=0;i<n;i++) res[i]=result[i];
        return res;
    }
}
