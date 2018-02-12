package com.csv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.strat.springboot.controller.domain.CoordinateDTO;
import com.strat.springboot.controller.util.CsvUtil;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * @author : Donald
 * @date : 2017/11/13 14:07.
 * @description :
 */
public class CsvUtilTest {
    
    //private static final String csvFilePath = "src/main/resources/sample.csv";
    private static final String csvFilePath = "D:\\gitworkspace\\BackendService\\src\\main\\resources\\";
    private static final String csvFileName = "sample.csv";
    
    public static void main(String[] args) {
        try {
            ColumnPositionMappingStrategy<CoordinateDTO> strategy = new ColumnPositionMappingStrategy<>();
            String[] columns = new String[]{"lat", "lng"};
            strategy.setType(CoordinateDTO.class);
            strategy.setColumnMapping(columns);
            CsvToBean csv = new CsvToBean();
            String csvFilename = CsvUtilTest.class.getClassLoader().getResource("sample.csv").getPath();
            //String csvFilename = csvFilePath;
    
            CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
            List<CoordinateDTO> list = csv.parse(strategy, csvReader);
            for (CoordinateDTO CoordinateDTO : list) {
                System.out.println("[CsvUtilTest.main] latLng : " + CoordinateDTO.getLat() +","+ CoordinateDTO.getLng() );
            }
        } catch (FileNotFoundException e) {
            System.out.println("[CsvUtilTest.main] Error : " + e.getMessage());
        }
        /**
         * readTest
         */
        readTest();
    }
    
    public static void readTest() {
            String[] columns = new String[]{"lat", "lng"};
            List<CoordinateDTO> list = CsvUtil.fromCSVFile(csvFilePath, csvFileName, CoordinateDTO.class, columns);
            for (CoordinateDTO CoordinateDTO : list) {
                System.out.println("[CsvUtilTest.main2] latLng : " + CoordinateDTO.getLat() +","+ CoordinateDTO.getLng() );
            }
    }
}
