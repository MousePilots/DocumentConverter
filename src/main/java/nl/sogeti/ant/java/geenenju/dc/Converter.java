/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.sogeti.ant.java.geenenju.dc;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

/**
 *
 * @author geenenju
 */
public interface Converter {

     boolean convert(InputStream is, OutputStream os) throws Exception;
     
     public Collection<String> getSupportedExtensions();
     
     
}
