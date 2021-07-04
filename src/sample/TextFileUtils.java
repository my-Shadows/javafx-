package sample;

import java.io.File;
import java.io.FileInputStream;

public class TextFileUtils {
    public static String read(File file, String charset) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            int fileSize = (int) file.length();
            if (fileSize > 1024 * 512) {
                throw new Exception("File too large to read,size=" + fileSize);
            }
            byte[] buffer = new byte[fileSize];
            fileInputStream.read(buffer);
            return new String(buffer, charset);
        } finally {
            try {
                fileInputStream.close();
            } catch (Exception ignored) {
            }
        }
    }
}
