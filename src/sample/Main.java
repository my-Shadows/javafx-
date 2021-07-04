package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    TabPane tabPane = new TabPane();
    FileListView fileListView = new FileListView();
    BorderPane root = new BorderPane();
    File []files ;
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.setLeft(fileListView);
        root.setCenter(tabPane);
        InitFiles("resources");
        fileListView.setPrefWidth(200);
        fileListView.setOnMouseClicked(e->{
            if(e.getClickCount()==2){
                try {
                    onListDoubleClicked();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
    public void InitFiles(String dir) throws Exception {
        File file=new File(dir);
        if (!file.isDirectory()){
            System.out.println("该路径不是目录！");
            return;
        }
        files=file.listFiles();
        assert files != null;
        for (File file1:files){
            FileItem fileItem = new FileItem(file1);
            fileListView.data().add(fileItem);
        }
    }
    public void onListDoubleClicked() throws Exception {
        FileItem item=fileListView.getSelectionModel().getSelectedItem();
        SingleSelectionModel selectionModel=tabPane.getSelectionModel();
        int index;
        if((index=findItem(item))>=0){
            selectionModel.select(index);
        }
        else {
            Node content=null;
            if (item.type == FileItem.TEXT) {
                TextArea textArea = new TextArea(TextFileUtils.read(item.file, "utf-8"));
                content = textArea;

            } else if (item.type == FileItem.IMAGE) {
                AfImagePane afImagePane = new AfImagePane();
                ImageView imageView=null;
                try{
                     imageView= new ImageView(new Image(item.file.toURI().toString()));
                } catch (IllegalArgumentException e){
                    System.out.println(item.file.getAbsolutePath());
                }
                afImagePane.getChildren().add(imageView);
                content=afImagePane;
            }
            Tab tab = new Tab();
            tab.setText(item.fileName);
            tab.setContent(content);
            tabPane.getTabs().add(tab);
            selectionModel.select(tab);
        }
    }
    public int findItem(FileItem item){//在TabPane中寻找是否已打开此item
        for(Tab tab:tabPane.getTabs()){
            if (tab.getText().equals(item.fileName)){
                return tabPane.getTabs().indexOf(tab);
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
