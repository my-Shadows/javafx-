package sample;

import java.io.File;

public class FileItem {
    public String fileName;
    public File file;
    public int type =BAD_FORMAT;

    public static final int TEXT = 1;
    public static final int IMAGE = 2;
    public static final int BAD_FORMAT = -1;

    private final String[] txtTypes = {"txt","java"};
    private final String[] imageTypes = {"jpg","jpeg","png","bmp","gif"};

    public FileItem(File file){
        this.file=file;
        fileName=file.getName();
        String pos=fileName.substring(fileName.indexOf('.')+1);
        for (String type:txtTypes){
            if(pos.equals(type)){
                this.type=TEXT;
                return;
            }
        }
        for(String type:imageTypes){
            if(pos.equals(type)){
                this.type=IMAGE;
                return;
            }
        }
    }

}
