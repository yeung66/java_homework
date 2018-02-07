package ta.example;

import ta.example.interfaces.FileHandler;
import ta.example.interfaces.StockSorter;
import ta.example.vo.StockInfo;
import java.util.Formatter;
import ta.example.interfaces.FileRead;
import ta.example.interfaces.Sorter;

public class Main {

    private static FileHandler fileHandler;

    private static StockSorter stockSorter;

    static{
        //TODO:Initialize fileHandler and stockSorter with your implement class
        fileHandler=new FileRead();
        stockSorter=new Sorter();
    }

    public static void main(String[] args) {
	// write your code here
        if(args.length < 2){
            System.exit(0);
        }
        String filePath = args[0];
        String targetPath = args[1];

        //数据读取
        StockInfo[] stocks = fileHandler.getStockInfoFromFile(filePath);
        if(stocks != null)
            System.out.println("数据读入成功");

        //数据排序
        StockInfo[] sortedStocks = stockSorter.sort(stocks,true);
        System.out.println("排序结束");

        int writeLenght = fileHandler.setStockInfo2File(targetPath,sortedStocks);
        Formatter formatter = new Formatter(System.out);
        if(writeLenght == sortedStocks.length)
            formatter.format("写入操作成功，共写入%d条数据",2);
        else
            formatter.format("写入失败");

    }

}
