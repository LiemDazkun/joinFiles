package lfr.ld.files.joinFiles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lfr.ld.files.joinFiles.utils.Archivos;

/**
 * @author lfloresr
 *
 */
public class JoinFiles {

  private static Logger logger = LogManager.getLogger(JoinFiles.class);

  /**
   * java -jar JoinFiles -i 
   * -i carpeta de entrada
   * -d carpeta de destino
   * -p password
   * @param args
   */
  public static void main(String[] args) {
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
