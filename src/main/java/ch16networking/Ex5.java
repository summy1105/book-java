package ch16networking;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class Ex5 {
    public static void main(String[] args) {
        try {
            String address = "https://stg.anyonepay.com/business/images/brands/Group%2014.png";
            URL url = new URL(address);
            InputStream inputStream = url.openStream();
            FileOutputStream fileOutputStream = new FileOutputStream("test_file.png");
            int ch= 0;
            while ((ch = inputStream.read()) != -1){
                fileOutputStream.write(ch);
            }
            inputStream.close();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
