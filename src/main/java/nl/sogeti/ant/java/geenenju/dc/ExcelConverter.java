/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.sogeti.ant.java.geenenju.dc;

import com.aspose.cells.License;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author geenenju
 */
public class ExcelConverter extends ToPdfA{
     
     private static final License LICENSE = applyLicense(License.class);

     @Override
     public Collection<String> getSupportedExtensions() {
          return Arrays.asList("xls","xlsx");
     }
     
     

     @Override
     public boolean convert(InputStream is, OutputStream os) throws Exception{
          final Workbook workbook = new Workbook(is);
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          workbook.save(bos, SaveFormat.PDF);
          return convertToPdfA(bos, os);
     }

}
