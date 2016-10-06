/**
 * 
 */
package com.IdgoSoft.test.intigration.functionaltestCase.util;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Properties;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * @author srikanth.vaddella
 *
 */

public class IbTradeFunctionalTestCase extends performanceFunctionalTestCase { 

 @Rule
 public TemporaryFolder startStopFileDir = new TemporaryFolder();


 protected void doSetUpBeforeMuleContextCreation() throws Exception {
     super.doSetUpBeforeMuleContextCreation();
     loadProperties();
 }

 public static void loadProperties() throws Exception {
     Properties p = System.getProperties();
     p.setProperty("IntigrationTest", "true");

     try {
         p.load(Files.newInputStream(Paths.get("../ib_trade.properties", new String[0]), new OpenOption[0]));
     } catch (NoSuchFileException var2) {
         p.load(Files.newInputStream(Paths.get("../ib_trade.properties", new String[0]), new OpenOption[0]));
     }

     System.setProperties(p);
 }

 public static void replaceExistingProperty(String key, String value) {
     if(System.getProperty(key) == null) {
         throw new IllegalStateException("Property " + key + " not defined in anatwine.properties");
     } else {
         System.setProperty(key, value);
     }
 }

 protected boolean isStartFlowsOnStartupEnabled() {
     return false;
 }
}
