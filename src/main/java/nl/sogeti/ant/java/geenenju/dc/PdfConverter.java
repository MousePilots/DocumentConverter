/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.sogeti.ant.java.geenenju.dc;

import com.aspose.pdf.ConvertErrorAction;
import com.aspose.pdf.Document;
import com.aspose.pdf.License;
import com.aspose.pdf.PdfFormat;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author geenenju
 */
public class PdfConverter extends AbstractAsposeConverter{
     
     private static final License LICENSE = applyLicense(License.class);
     
     @Override
     public Collection<String> getSupportedExtensions() {
          return Collections.singleton("pdf");
     }
     

     @Override
     public boolean convert(InputStream is, OutputStream os) throws Exception {
          final Document pdfDocument = new Document(is);
          final boolean conversionOK = pdfDocument.convert(System.out, PdfFormat.PDF_A_3B,ConvertErrorAction.Delete);
          assert conversionOK : "conversion to PDF/A failed";
          assert pdfDocument.isPdfaCompliant() : "document is not PDF/A compliant";
          if(conversionOK && pdfDocument.isPdfaCompliant()){
               pdfDocument.save(os);
               return true;
          } else {
          return false;
     }
     }
     
}
