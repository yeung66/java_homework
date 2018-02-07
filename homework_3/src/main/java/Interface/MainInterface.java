package Interface;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import segmenter.ChineseSegmenter;
import sort.Sorter;
import tf_idf.TF_IDF;
import tf_idf.recommand;
import vo.StockInfo;
import io.FileHandler;
import java.util.List;

public class MainInterface implements ActionListener  {
    private JFrame f = new JFrame("Main");
    private JPanel p = new JPanel();
    private JPanel display=new JPanel();
    private Menu menuBar;
    private JTextField textField;
    private JButton searchButton;


    private JPanel topPanel;

    public static StockInfo[] stocks;
    public static double[][] similarity;

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="导入"){
            JFileChooser fd = new JFileChooser();
            fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fd.showOpenDialog(null);
            File file = fd.getSelectedFile();
            if(file != null){
                stocks=FileHandler.getInfoFromFile(file);
                similarity= recommand.calculateMatrix(stocks);
            }


        }else if(e.getActionCommand()=="搜索"){
            display.removeAll();
            display.setVisible(false);display.setVisible(true);
            String str=textField.getText();
            if(""==str) return;
            ChineseSegmenter seg=new ChineseSegmenter();
            List<String> searchWords=seg.getWord(str);
            if(searchWords.size()==0) {
                JOptionPane.showMessageDialog(null, "请输入关键词", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(stocks==null) {
                JOptionPane.showMessageDialog(null, "请先导入文件", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int []frequency=new int[stocks.length];
            for(int i=0;i<stocks.length;i++)
                frequency[i]=stocks[i].getFrequency(searchWords);
            int []seq= Sorter.sortByFrequency(frequency);//排序后的下标 频率数组也同时排序
            JPanel []resDisplay=new JPanel[10];
            for(int i=0;i<10;i++){
                if(frequency[i]==0) break;
                resDisplay[i]=new LabelContainer(stocks[seq[i]],searchWords);
                resDisplay[i].setBounds(10,(i-1)*71+80,display.getWidth()-20,62);
                display.add(resDisplay[i]);
            }
            display.setVisible(false);
            display.setVisible(true);
        }
    }

    public MainInterface() {
        //f.setBounds(400, 200, 500, 400);
        f.setSize(800, 1000);
        f.setLocationRelativeTo(null); //窗口位置为中间

        createTopPanel();

        JPanel wholeCon=new JPanel();
        GridBagLayout layout=new GridBagLayout();
        wholeCon.setLayout(layout );
        wholeCon.add(topPanel);wholeCon.add(display);
        GridBagConstraints s= new GridBagConstraints();
        s.fill=GridBagConstraints.BOTH;
        s.gridwidth=0;s.weightx=0;s.weighty=0;
        layout.setConstraints(topPanel,s);
        s.weighty=1;
        layout.setConstraints(display,s);


        display.setBorder(BorderFactory.createLineBorder(Color.black));
        display.setLayout(null);



        //添加菜单
        menuBar=new Menu();
        f.setJMenuBar(menuBar);
        menuBar.getItem().addActionListener(this);//菜单导入添加事件
        searchButton.addActionListener(this);




        f.add(wholeCon);

        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  //关闭窗口时终止程序。
    }

    private void createTopPanel(){
        textField=new JTextField(60);searchButton=new JButton("搜索");
        topPanel=new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(p);
        topPanel.add(Box.createVerticalStrut(10));
        p.setLayout(new FlowLayout());
        p.add(textField);p.add(searchButton);
    }

    public static void main(String[] args) {
        MainInterface ws = new MainInterface();
    }
}

class Menu extends JMenuBar{
    private JMenu menu=new JMenu("文件");
    private JMenuItem item=new JMenuItem("导入");

    public Menu(){
        menu.add(item);this.add(menu);
    }

    public JMenuItem getItem() {
        return item;
    }

}

class LabelContainer extends JPanel{
    private JLabel title;
    private JLabel content;

    private StockInfo info;

    public LabelContainer(StockInfo i,List<String> searchWords){
        this.info=i;

        BoxLayout layout=new BoxLayout(this,BoxLayout.Y_AXIS);
        this.setLayout(layout);

        creTitle(searchWords);creContent(searchWords);
        this.add(title);
        this.add(Box.createVerticalStrut(10));
        this.add(content);

        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int clickTimes=e.getClickCount();
                if(clickTimes==2){
                    //System.out.print(1);
                    DetailFrame show=new DetailFrame(info);
                }
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void creTitle(List<String> searchWords){
        String infoTitle=info.getTitle();
        for(String word:searchWords)
            infoTitle=infoTitle.replace(word,"<font color='red'>"+word+"</font>");
        title =new JLabel("<html>"+infoTitle+"</html>");
        this.add(title);
    }

    private void creContent(List<String> searchWords){
        String infoContent=info.getContent();
        int first;int n=0;


            for(String word:searchWords)
                infoContent=infoContent.replace(word,"<font color='red'>"+word+"</font>");
        content=new JLabel("<html>"+infoContent+"</html>");
    }
}