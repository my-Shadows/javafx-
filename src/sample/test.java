package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class test {
    public static void main(String[] args) {
        File file= new File("test");
//        Image image = new Image("/resources/1.gif");
//        ImageView imageView = new ImageView(image);
        System.out.println(file.toURI().toString());
        System.out.println(file.isFile());
    }
}
