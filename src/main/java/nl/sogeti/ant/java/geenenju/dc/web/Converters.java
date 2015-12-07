/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.sogeti.ant.java.geenenju.dc.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import nl.sogeti.ant.java.geenenju.dc.AbstractAsposeConverter;
import nl.sogeti.ant.java.geenenju.dc.Converter;
import nl.sogeti.ant.java.geenenju.dc.ExcelConverter;
import nl.sogeti.ant.java.geenenju.dc.PdfConverter;
import nl.sogeti.ant.java.geenenju.dc.WordConverter;

@ApplicationScoped
public class Converters implements Serializable {

     private final Map<String, Converter> extensionToConverter = new HashMap<>();

     @PostConstruct
     private void onPostConstruct(){
          final List<Class<? extends AbstractAsposeConverter>> converterClasses = Arrays.asList(
               WordConverter.class,
               PdfConverter.class,
               ExcelConverter.class
          );
          for (Class<? extends AbstractAsposeConverter> converterClass : converterClasses) {
               try {
                    final AbstractAsposeConverter converter = converterClass.newInstance();
                    for (String extension : converter.getSupportedExtensions()) {
                         extensionToConverter.put(extension.toLowerCase(), converter);
                    }
               } catch (InstantiationException | IllegalAccessException t) {
                    throw new IllegalStateException(t);
               }
          }
     }
     
     public String getExtension(String fileName){
          if(fileName==null){
               return null;
          }
          final int lastDotIndex = fileName.lastIndexOf('.');
          if (lastDotIndex == -1 || fileName.length() <= lastDotIndex +1) {
               return null;
          } else {
               return fileName.substring(lastDotIndex+1);               
          }     
     }
     
     

     public Converter getConverter(String extension) {
          return extensionToConverter.get(extension.toLowerCase());
     }

}
