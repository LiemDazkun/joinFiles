package lfr.ld.files.joinFiles;

import java.util.HashMap;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import lfr.ld.files.joinFiles.utils.Archivos;

/**
 * @author lfloresr
 *
 */
public class JoinFiles {

  private static Logger logger = Logger.getLogger(JoinFiles.class);

  /**
   * java -jar JoinFiles -i 
   * -i carpeta de entrada
   * -d carpeta de destino
   * -p password
   * @param args
   */
  public static void main(String[] args) {
    BasicConfigurator.configure();
    logger.info("JoinFiles.main::INI");
    try {
        Arguments.getInstance(args);
        Arguments.getArguments().setArchivos(Archivos.readFiles());
        Archivos.printHashMap(Arguments.getArguments().getArchivos());
    } catch (Exception e) {
      e.printStackTrace();
      logger.fatal(e);
    }
    logger.info("JoinFiles.main::FIN");

  }

}
