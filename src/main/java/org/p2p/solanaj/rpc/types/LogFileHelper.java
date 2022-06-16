package org.p2p.solanaj.rpc.types;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class LogFileHelper {

    public static void writeFile(String fileName,String buffer){
        if (fileName.isEmpty())return;
        try {
            File file = new File(fileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(buffer.getBytes(StandardCharsets.UTF_8),0,buffer.length());
        }catch (Exception e){
        }

    }
}
