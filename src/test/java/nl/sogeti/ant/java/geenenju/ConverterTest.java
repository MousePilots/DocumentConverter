/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.sogeti.ant.java.geenenju;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.sogeti.ant.java.geenenju.dc.Converter;
import nl.sogeti.ant.java.geenenju.dc.ExcelConverter;
import nl.sogeti.ant.java.geenenju.dc.PdfConverter;
import nl.sogeti.ant.java.geenenju.dc.WordConverter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author geenenju
 */
public class ConverterTest {

     private static final Logger LOG = Logger.getLogger(ConverterTest.class.getName());
     
     private final String[] wordFiles =  {"D:\\users\\geenenju\\Downloads\\Course Outline - Statistics for Big Data - 2015 (3).docx"};
     private final String[] excelFiles = {"D:\\users\\geenenju\\Downloads\\ALP groupings (1).xlsx"};
     private final String[] pdfFiles =   {"D:\\users\\geenenju\\OneDrive\\Oracle\\specs\\ejb-3_2-core-fr-spec.pdf"};
     
     
     private InputStream readFile(String path){
          try {
               return new FileInputStream(path);
          } catch (FileNotFoundException ex) {
               throw new IllegalArgumentException(ex);
          }
     }
     
     public ConverterTest() {
     }
     
     @BeforeClass
     public static void setUpClass() {
     }
     
     @AfterClass
     public static void tearDownClass() {
     }
     
     @Before
     public void setUp() {
     }
     
     @After
     public void tearDown() {
     }
     
     private void saveAsPdf(String originalFileName, byte[] content) throws Exception{
          final int lastDotIndex = originalFileName.lastIndexOf(".");
          final String extension = originalFileName.substring(lastDotIndex+1);
          final boolean isPDF = "pdf".equalsIgnoreCase(extension);
          final String newFileName = originalFileName.substring(0, lastDotIndex) + (isPDF ? "-converted.pdf": ".pdf");
          File newFile = new File(newFileName);
          if(newFile.exists()){
               newFile.delete();
          }
          newFile.createNewFile();
          try(FileOutputStream fos = new FileOutputStream(newFile)){
               fos.write(content);
          }
          LOG.log(Level.INFO, "saved {0}", newFileName);
     }
     
     protected void doConversionTest(String[] fileNames, Converter converter) throws Exception {
          for(String fileName : fileNames){
               final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
               LOG.log(Level.INFO, "converting {0} to PDF/A", fileName);
               converter.convert(readFile(fileName), byteArrayOutputStream);
               saveAsPdf(fileName, byteArrayOutputStream.toByteArray());
          }
     }

     
     @Test
     public void testExcelConversion() throws Exception{
          Converter converter = new ExcelConverter();
          doConversionTest(excelFiles, converter);
     }

     @Test
     public void testPdfConversion() throws Exception{
          Converter converter = new PdfConverter();
          doConversionTest(pdfFiles, converter);
     }
     
     @Test
     public void testWordConversion() throws Exception{
          Converter converter = new WordConverter();
          doConversionTest(wordFiles, converter);
     }

}
