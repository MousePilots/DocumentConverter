/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.sogeti.ant.java.geenenju.dc.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import nl.sogeti.ant.java.geenenju.dc.Converter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author geenenju
 */
@Named
@RequestScoped
public class FileBean implements Serializable {

     @Inject
     private Converters converters;
     
     private StreamedContent download;
     
     private UploadedFile upload;

     public UploadedFile getUpload() {
          return upload;
     }

     public void setUpload(UploadedFile upload) {
          this.upload = upload;
     }

     public StreamedContent getDownload() {
          return download;
     }
     
     public void convert() {
          if (upload != null) {
               final String extension = converters.getExtension(upload.getFileName());
               if(extension!=null){
                    final Converter converter = converters.getConverter(extension);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    try {
                         converter.convert(upload.getInputstream(), out);
                         ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());
                         final String downloadFileName = upload.getFileName().replace("." + extension, ".pdf");
                         download = new DefaultStreamedContent(bis, "application/pdf", downloadFileName);
                    } catch (Throwable t) {
                         throw new IllegalStateException("whoops",t);
                    } finally {
                         upload=null;
                    }
               }
          }
     }
}
