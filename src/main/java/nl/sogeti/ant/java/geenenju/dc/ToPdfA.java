/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.sogeti.ant.java.geenenju.dc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 *
 * @author geenenju
 */
public abstract class ToPdfA extends AbstractAsposeConverter{

     private final PdfConverter pdfConverter = new PdfConverter();
     
     protected PdfConverter getPdfConverter() {
          return pdfConverter;
     }

     protected boolean convertToPdfA(ByteArrayOutputStream bos, OutputStream os) throws Exception {
          ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
          return pdfConverter.convert(bis, os);
     }

}
