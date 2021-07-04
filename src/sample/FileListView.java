package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class FileListView extends ListView<FileItem> {
    private ObservableList<FileItem> listData = FXCollections.observableArrayList();
    public FileListView(){
        setItems(listData);
        setCellFactory(new Callback<ListView<FileItem>, ListCell<FileItem>>() {
            @Override
            public ListCell<FileItem> call(ListView<FileItem> fileItemListView) {
                return new MyListCell();
            }
        });
    }
    public ObservableList<FileItem> data(){
        return listData;
    }
    static class MyListCell extends ListCell<FileItem>{
        public void updateItem(FileItem item,boolean empty){
            super.updateItem(item, empty);
            if(item==null){
                this.setGraphic(null);
            }
            else {
                this.setText(item.fileName);
            }
        }
    }
}
