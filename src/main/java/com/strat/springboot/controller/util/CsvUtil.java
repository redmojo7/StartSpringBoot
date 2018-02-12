package com.strat.springboot.controller.util;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author Donald
 * @date 2017/4/28.
 */
public class CsvUtil {
    
    private static final Logger log = LoggerFactory.getLogger(CsvUtil.class);
    
    /**
     * function : read a csv file.
     * @param filePath
     * @param fileName
     * @param clazz
     * @param columns
     * @return
     */
    public static List fromCSVFile(String filePath, String fileName, Class clazz, String[] columns) {
        List list = null;
        FileReader fileReader = null;
        try {
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(clazz);
            strategy.setColumnMapping(columns);
            
            CsvToBean csv = new CsvToBean();
            fileReader = new FileReader(new File(filePath, fileName));
            CSVReader csvReader = new CSVReader(fileReader);
            /*
             *  Ignore to read first line
             */
            csvReader.readNext();
            /*
             *  Parse other lines with appointed clazz
             */
            list = csv.parse(strategy, csvReader);
        } catch (IOException e) {
            log.error("readCsvFailed", "[CsvUtil] toCSVFile failed.", e);
        } finally {
            if (Objects.nonNull(fileReader)) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    log.error("closeFailed", "[CsvUtil] fileReader close failed.", e);
                }
            }
        }
        return list;
    }
    
    /**
     * function : write a csv file.
     * @param filePath
     * @param fileName
     * @param dataList
     * @param titles
     * @param columns
     * @return
     */
    public static File toCSVFile(String filePath, String fileName,List dataList,String[] titles, String[] columns) {
        Assert.notEmpty(columns,"Columns can not be empty.");
        FileWriter fileWriter = null;
        CSVWriter writer = null;
        Object value;
        try {
            File pathFile = new File(filePath);
            pathFile.mkdirs();
            File returnFile = new File(filePath,fileName);
            String[] textLine = new String[columns.length];
            fileWriter = new FileWriter(returnFile);
            writer = new CSVWriter(fileWriter);
            /*
             *  writer titles on first line
             */
            writer.writeNext(titles);
            /*
             *  writer appointed columns to other lines
             */
            if(CollectionUtils.isNotEmpty(dataList)) {
                for (Object object : dataList) {
                    for(int i = 0; i < columns.length; i++) {
                        String column = columns[i];
                        String methodName = column.substring(0, 1).toUpperCase()+ column.substring(1);
                        Method method = object.getClass().getMethod("get" + methodName);
                        value = method.invoke(object);
                        if (Objects.nonNull(value)) {
                            textLine[i] = String.valueOf(value);
                        } else {
                            textLine[i] = "";
                        }
                    }
                    writer.writeNext(textLine);
                }
            }
            writer.flush();
            writer.close();
            fileWriter.close();
            return returnFile;
        } catch (IOException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error("writeCsvFailed", "[CsvUtil] toCSVFile failed.", e);
            return null;
        } finally {
            if (Objects.nonNull(writer)) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("closeFailed", "[CsvUtil] writer close failed.", e);
                }
            }
            if (Objects.nonNull(fileWriter)) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    log.error("closeFailed", "[CsvUtil] fileWriter close failed.", e);
                }
            }
    }
    
    }
}
