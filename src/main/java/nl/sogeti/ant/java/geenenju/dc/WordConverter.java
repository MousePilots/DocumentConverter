/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.sogeti.ant.java.geenenju.dc;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import static nl.sogeti.ant.java.geenenju.dc.AbstractAsposeConverter.applyLicense;

/**
 *
 * @author geenenju
 */
public class WordConverter extends ToPdfA{
     
     private static final License LICENSE = applyLicense(License.class);
     
     @Override
     public Collection<String> getSupportedExtensions() {
          return Arrays.asList("doc","docx");
     }
     

     @Override
     public boolean convert(InputStream is, OutputStream os) throws Exception {
          Document document = new Document(is);
          final ByteArrayOutputStream pdfos = new ByteArrayOutputStream();
          document.save(pdfos, SaveFormat.PDF);
          return this.convertToPdfA(pdfos, os);
     }
     
}
