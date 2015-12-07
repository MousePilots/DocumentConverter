/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.sogeti.ant.java.geenenju.dc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 *
 * @author geenenju
 */
public abstract class AbstractAsposeConverter implements Converter{

     private static final Logger LOG = Logger.getLogger(AbstractAsposeConverter.class.getName());
     
     
     protected static <T> T applyLicense(Class<T> licenseClass){
          try {
               final String licenseFileName = "/Aspose.Total.Java.lic";
               LOG.info("loading license-file as stream from " + licenseFileName);
               final InputStream licenseFileStream = AbstractAsposeConverter.class.getResourceAsStream(licenseFileName);
               final T license = licenseClass.newInstance();
               final Method setLicence = licenseClass.getMethod("setLicense", InputStream.class);
               setLicence.invoke(license, licenseFileStream);
               return license;
          } catch (Throwable t) {
               throw new IllegalStateException("cannot apply license to " + licenseClass , t);
          }
     }
     
}
