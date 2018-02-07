package Interface;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.List;

import tf_idf.TF_IDF;
import tf_idf.recommand;
import vo.StockInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;


public class DetailFrame extends JFrame {

    private List<String> keyWords;
    private byte[] bFile;

    class listener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand()=="保存"){
                FileNameExtensionFilter filter=new FileNameExtensionFilter("*.png","png");
                JFileChooser fc=new JFileChooser();
                fc.setFileFilter(filter);
                fc.setMultiSelectionEnabled(false);
                int option = fc.showSaveDialog(null);
                if(option==JFileChooser.APPROVE_OPTION){    //假如用户选择了保存
                    File file = fc.getSelectedFile();
                    String fname=fc.getName(file);
                    if(fname.length()<4||fname.lastIndexOf(".png")!=fname.length()-4){
                        file=new File(fc.getCurrentDirectory(),fname+".png");

                    }
                    saveFile(file.getAbsolutePath());

                }
        }
    }
    }

    private byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public DetailFrame(StockInfo info){
        this.setTitle("词云");
        JPanel whole=new JPanel();


        this.add(whole);


        JLabel imgContainer=drawImg(info);

        JPanel recommandCon=recommandDis(info);

        JPanel buttonContainer=new JPanel();
        JButton saveButton=new JButton("保存");
        buttonContainer.add(saveButton);

        saveButton.addActionListener(new listener());

        whole.setLayout(new BoxLayout(whole,BoxLayout.Y_AXIS));

        whole.add(buttonContainer);

        whole.add(imgContainer);
        whole.add(recommandCon);
        this.setSize(800,720);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    private JPanel recommandDis(StockInfo info){
        JPanel container=new JPanel();
        JLabel tips=new JLabel("双击进入推荐内容：");
        tips.setFont(new Font("宋体",Font.BOLD,24));
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.add(tips);
        container.add(Box.createVerticalStrut(10));
        int[] recommandIndex= recommand.get5Recommand(MainInterface.similarity,info.getID());
        for(int i:recommandIndex){
            JLabel recommandTitle=new JLabel(MainInterface.stocks[i].getTitle());
            recommandTitle.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    int clickTimes=e.getClickCount();
                    if(clickTimes==2){
                        //System.out.print(1);
                        DetailFrame show=new DetailFrame(MainInterface.stocks[i]);
                    }
                }
                public void mousePressed(MouseEvent e) { }
                public void mouseReleased(MouseEvent e) { }
                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {
                }
            });
            recommandTitle.setFont(new Font("宋体",Font.BOLD,24));
            container.add(recommandTitle);
            container.add(Box.createVerticalStrut(10));
        }
        return container;
    }



    private void deleteImg(String path){
        File file = new File(path);
        if(file.exists())
            if(file.delete())
                System.out.print(11);//测试是否删除成功
            else
                System.out.print(2);
    }

    private JLabel drawImg(StockInfo info){

        Color[] colors = new Color[10];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128);
        }
        keyWords=TF_IDF.getKeyWords(info);
        File f=new File(info.getTitle()+".png");

        WordCloudBuilder.buildWordCouldByWords(700,400,4,30,20, keyWords,
                new Color(-1),info.getTitle()+".png",colors);
        bFile=getBytes(info.getTitle()+".png");
        JLabel imgContainer=new JLabel();
        ImageIcon icon = new ImageIcon(info.getTitle()+".png");
        imgContainer.setIcon(icon);
        deleteImg(info.getTitle()+".png");
        return imgContainer;
    }

    public void saveFile(String path){
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File(path);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
