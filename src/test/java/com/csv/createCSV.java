package com.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.collections.Lists;

/**
 * @Author : Donald
 * @Description :
 * @Date : 2017/9/27 14:36.
 */

public class createCSV {
    
    private static Logger log = LoggerFactory.getLogger(createCSV.class);
    
    private static List<String> names = Lists.newArrayList();
    
    private static Integer AMOUT = 10000;
    
    private static final String nameFilePath = "C://names.txt";
    private static final String filePath = "C://JMeterTest.txt";
    
    // private static final String nameFilePath = "src/main/resources/names.txt";
    // private static final String filePath = "src/main/resources/JMeterTest.txt";
    
    public static void main(String[] args) {
        
        readNames();
        writeData();
        log.debug("End.");
        
    }
    
    private static void readNames() {
        File namesFile = new File(nameFilePath);
        try {
            FileReader reader = new FileReader(namesFile);
            BufferedReader br = new BufferedReader(reader);
            names = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            log.debug("Error : {}", e.getMessage());
        }
    }
    
    private static void writeData() {
        try {
            File listFile = new File(filePath);
            FileWriter output = new FileWriter(filePath);
            if (listFile.exists()) {
                listFile.delete();
                log.debug("{} is already exist!  Removing it...", filePath);
            }
            BufferedWriter bf = new BufferedWriter(output);
            for (int i = 1; i <= AMOUT; i++) {
                StringBuilder lineString = new StringBuilder();
            
                lineString
                    .append(i).append(",")
                    .append(names.get(RandomUtils.nextInt(0, names.size()))).append(",")
                    .append(RandomUtils.nextInt(1, 101)).append("\r\n");
            
                bf.write(lineString.toString());
                log.debug(lineString.toString());
            }
            bf.flush();// 此处很关键，如果不写该语句，是不能从缓冲区写到文件里的
            bf.close();
            output.close();
        } catch (IOException e) {
            log.debug("Error : {}", e.getMessage());
        }
    }
    
}
