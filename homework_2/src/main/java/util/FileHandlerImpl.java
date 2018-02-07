package util;

import vo.StockInfo;
import vo.UserInterest;
import java.io.*;

public class FileHandlerImpl implements FileHandler {

    /**
     * This func gets stock information from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return the Stock information array from the interfaces,or NULL
     */
    @Override
    public StockInfo[] getStockInfoFromFile(String filePath) {
        //TODO: write your code here
        StockInfo [] result=new StockInfo[60];
        int n=0;
        File file=new File(filePath);
        if(!file.exists()) return null;
        BufferedReader reader=null;
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf-8");
            reader = new BufferedReader(read);
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

    /**
     * This func gets user interesting from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return
     */
    @Override
    public UserInterest[] getUserInterestFromFile(String filePath) {
        UserInterest[] res=new UserInterest[500];
        File file=new File(filePath);
        if(!file.exists()) return null;
        BufferedReader reader=null;
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf-8");
            reader = new BufferedReader(read);
            String temp=null;
            int n=0;
            while((temp=reader.readLine())!=null) {
                res[n]=new UserInterest(temp);n++;
            }
            reader.close();
        }catch (IOException e) {
            return null;
        }
        return res;
    }

    /**
     * This function need write matrix to files
     *
     * @param matrix the matrix you calculate
     */
    @Override
    public void setCloseMatrix2File(double[][] matrix) {
        //TODO: write your code here
        File file=new File(this.getClass().getClassLoader().getResource(".").getPath()+"CloseMatrixOut.txt");
        try{
            if(!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i=0;i<60;i++){
                bw.write(String.valueOf(matrix[i][0]));
                for(int j=1;j<60;j++){
                    bw.write("\t");
                    bw.write(String.valueOf(matrix[i][j]));
                }
                bw.write("\n");
            }
            bw.close();fw.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function need write recommend to files
     *
     * @param recommend the recommend you calculate
     */
    @Override
    public void setRecommend2File(double[][] recommend) {
        //TODO: write your code here
        File file=new File(this.getClass().getClassLoader().getResource(".").getPath()+"RecommandFile.txt");
        try{
            if(!file.exists()) file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i=0;i<500;i++){
                bw.write(String.valueOf(recommend[i][0]));
                for(int j=1;j<10;j++){
                    bw.write("\t");
                    bw.write(String.valueOf(recommend[i][j]));
                }
                bw.write("\n");
            }
            bw.close();fw.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
