package ui;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import manage.Searcher;
import vo.Program;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private MenuItem itemSpider;

    @FXML
    private TextField inputText;

    @FXML private Label l_pageCount;

    @FXML
    protected void runSpider(){
        Alert i=new Alert(Alert.AlertType.INFORMATION);
        i.setTitle("提示");
        i.setHeaderText("爬虫中，请稍等！");
        i.show();
        Searcher.run();
        i.hide();
        i.setContentText("数据已经保存到数据库！");

        i.setHeaderText("爬虫成功！");
        i.show();
    }

//    @FXML//测试数据库的删除而已
//    protected  void deleteInfo(){
//        Main.s.getiMapper().deleteById("9748d33f7503409db028cd43c652a96c");
//
//        Program p =Main.s.getiMapper().findById("046fdc47371444d99faae2856aa0f5f3");
//        if(p!=null){
//            p.setUniversity("ttttttttt");
//            Main.s.getiMapper().updateById(p);
//        }
//    }

    @FXML
    protected void remove(){
        inputText.setText("");
        l_pageCount.setText("");
        inputPage.setText("");
        data.clear();
        tips.setVisible(false);
    }

    private List<Program> res;
    private int pageCount;
    private static final int numPerPage=22;
    private int currentPage=-1;

    @FXML
    protected void searchProgram(){
        data.clear();
        currentPage=-1;
        String text=inputText.getText();
        if(text.equals("")){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("请输入关键字");
            a.show();
            return;
        }
        if(text.toLowerCase().equals("cs")) text="computer";
        else if(text.toLowerCase().equals("ee")) text="elect";
        tips.setVisible(true);
        res=Main.s.getiMapper().findPrograms(text);

        pageCount=res.size()/numPerPage;
        l_pageCount.setText("/ "+(pageCount+1));

        gotoPage(0);

    }

    private void gotoPage(int i){
        if((currentPage==i) || (i>pageCount) || (i<0)) return;
        data.clear();
        for(int j=i*numPerPage;(j<(i+1)*numPerPage)&&(j<res.size());j++)
            data.add(new tableDataModel(res.get(j)));
        currentPage=i;
        inputPage.setText(String.valueOf(i+1));
    }

    @FXML
    protected void goSpecPage(){
        try{
            String s=inputPage.getText();
            int num=Integer.valueOf(s);
            if(num<1||num>pageCount) throw new Exception();
            gotoPage(num-1);
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("请输入合适的页码");
            a.show();
        }
    }

    @FXML protected void goFirstPage(){gotoPage(0);}
    @FXML protected void goPreviousPage(){gotoPage(currentPage-1);}
    @FXML protected void goNextPage(){gotoPage(currentPage+1);}
    @FXML protected void goLastPage(){gotoPage(pageCount);}

    @FXML private TextField inputPage;

    @FXML
    private Label tips;

    @FXML
    private TableView tableData;


    private final ObservableList<tableDataModel> data
            = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showList();
        tableData.setRowFactory(tv->{//为表格添加行事件
            TableRow<tableDataModel> row=new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(!row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount()==2){
                    tableDataModel dataShow=row.getItem();
                    displayInfo(dataShow);
                }
            });
            return row;
        });

    }

    private void showList() {
        ObservableList<TableColumn> observableList = tableData.getColumns();
        observableList.get(0).setCellValueFactory(new PropertyValueFactory("country"));
        observableList.get(1).setCellValueFactory(new PropertyValueFactory("university"));
        observableList.get(2).setCellValueFactory(new PropertyValueFactory("program_name"));
        observableList.get(3).setCellValueFactory(new PropertyValueFactory("degree"));
        observableList.get(4).setCellValueFactory(new PropertyValueFactory("school"));

        tableData.setItems(data);

    }

    private void displayInfo(tableDataModel t){
        Program p=t.getPro();
        Stage window=new Stage();
        window.setTitle("详细信息");
        int count=0;
        Label[] info=new Label[11];
        info[count++]=new Label("  国家： "+p.getCountry());
        info[count++]=new Label("  学校： "+p.getUniversity());
        info[count++]=new Label("  项目名： "+p.getProgram_name());
        info[count++]=new Label("  学位： "+p.getDegree());
        info[count++]=new Label("  学院： "+p.getSchool());
        info[count++]=new Label("  主页： "+p.getHomepage());
        info[count++]=new Label("  地点： "+p.getLocation());
        info[count++]=new Label("  邮箱： "+p.getEmail());
        info[count++]=new Label("  电话： "+p.getPhone_Number());
        info[count++]=new Label("  有奖ddl： "+p.getDeadline_With_Aid());
        info[count++]=new Label("  无奖ddl： "+p.getDeadline_Without_Aid());
        VBox layout=new VBox(20);
        for(int i=0;i<count;i++){
            info[i].setWrapText(true);
            info[i].setPrefWidth(600);
            layout.getChildren().add(info[i]);
        }
        Scene s=new Scene(layout);

        window.setScene(s);
        window.show();
    }

}
