package com.example;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * ThreadIOTest
 *
 * @author : Donald Cai
 * @date :   2017/5/7.
 */
public class ThreadIOTest {
    public static void main(String[] args) throws IOException {
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter();
        writer.connect(reader);
        Thread printThread = new Thread(new Print(reader), "printThread");
        printThread.start();
        int receive = 0;
        try {
            while ( (receive = System.in.read()) != -1 ) {

                writer.write(receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }

    static class Print implements Runnable {
        private PipedReader reader;
        public Print(PipedReader reader) {
            this.reader = reader;
        }
        @Override
        public void run() {
            int receive = 0;
            try {
                while ( (receive = reader.read() ) != -1) {
                    System.out.print( (char) receive );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
