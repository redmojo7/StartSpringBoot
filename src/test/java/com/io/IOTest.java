package com.io;

import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * IOTest
 *
 * @author : Donald Cai
 * @date :   2017/7/29.
 */
public class IOTest {

    @Test
    public void DataOutputStreamTest(){
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("C:\\Users\\redmojo7\\tmp\\readPrim"));
            out.writeInt(123);
            out.writeUTF("你好");
            out.writeBoolean(true);
            out.flush();
            out.close();

            DataInputStream in = new DataInputStream(new FileInputStream("C:\\Users\\redmojo7\\tmp/readPrim"));
            int a = in.readInt();
            System.out.println("first int is "+a);
            String b = in.readUTF();
            System.out.println("second string is "+b);
            boolean c= in.readBoolean();
            System.out.println("third boolean is "+c);
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
