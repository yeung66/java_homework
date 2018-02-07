package ta.example.interfaces;

import ta.example.vo.StockInfo;
import java.io.*;

public class FileRead implements FileHandler{
    public StockInfo[] getStockInfoFromFile(String filePath){

        StockInfo [] result=new StockInfo[10000];
        int n=0;
        File file=new File(filePath);
        if(!file.exists()) return null;
        BufferedReader reader=null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            reader.readLine();
            while ((tempString = reader.readLine()) != null) {
                String[] tempInfo = tempString.split("\t");
                result[n] = new StockInfo(tempInfo);
                n++;
            }
            reader.close();
        }catch (IOException e) {
            return null;
        }
        //System.out.println(n);
        return result;
    }

    public int setStockInfo2File(String filePath,StockInfo[] stocks){
        File file=new File(filePath);
        int n=0;
        try{
            if(!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            while(n<10000 && stocks[n]!=null){
                String []tempInfo=stocks[n].getData();
                for(int i=0;i<7;i++){
                    bw.write(tempInfo[i]);bw.write("\t");
                }
                bw.write(tempInfo[7]);bw.write("\n");
                n++;
            }

            bw.close();fw.close();
        }catch (IOException e){
            return 0;
        }
        //System.out.println(n);
        return n;
    }

}