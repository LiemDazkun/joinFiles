package lfr.ld.files.joinFiles;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lfr.ld.files.joinFiles.exception.JoinFilesException;

public class Arguments {

  private static Logger logger = LogManager.getLogger(JoinFiles.class);
  private static Arguments arguments = null;

  private static final String ORIGEN = "-o";
  private static final String DESTINO = "-d";
  private static final String PASS = "-p";
  private static final String SUFIJO = "zipper";

  private Path origenPath;
  private Path destinoPath;
  private String password;
  
  private HashMap<String, List<String>> archivos = new HashMap<String, List<String>>();

  static {
    try {
      arguments = new Arguments();
    } catch (Exception e) {
      logger.error("Error al cargar las rutas. ", e);
    }
  }

  /**
   * java -jar JoinFiles -i -i carpeta de entrada -d carpeta de destino -p password
   * 
   * @param args
   */

  /**
   * Gets the single instance of Arguments.
   *
   * @param args the args
   * @return single instance of Arguments
   * @throws JoinFilesException the tsb exception
   */
  public static Arguments getInstance(String[] args) throws JoinFilesException {

    arguments = new Arguments();
    int z = 0;
    for (int i = 0; i < args.length; i++) {
      logger.info("Lectura " + i + " [" + args[i] + "]");
      if ((i + 1) >= args.length) {
        z = i;
      } else {
        z = i + 1;
      }
      formValue(args[i], args[z]);
    }

    if (arguments.getOrigenPath() == null || arguments.getOrigenPath().toString().isEmpty()) {
      logger.error("voy a usar la ruta: " + System.getProperty("user.dir"));
      arguments.setOrigenPath(Paths.get(System.getProperty("user.dir")));
      arguments.setDestinoPath(Paths.get(System.getProperty("user.dir"),SUFIJO));
    }
      logger.info("Origen " + arguments.getOrigenPath().toString());
      logger.info("Destino " + arguments.getDestinoPath().toString());
      logger.info("Password " + arguments.getPassword());

    return arguments;
  }

  /**
   * Form value.
   *
   * @param value the value
   * @param valor the valor
   */
  private static void formValue(String value, String valor) {
    switch (value) {
      case ORIGEN:
        arguments.setOrigenPath(Paths.get(valor));
        if (arguments.getDestinoPath() == null) {
          arguments.setDestinoPath(Paths.get(valor,SUFIJO));
        }
        break;
      case DESTINO:
        arguments.setDestinoPath(Paths.get(valor));
        break;
      case PASS:
        arguments.setPassword(valor);
        break;
    }
  }

  public static Arguments getArguments() {
    return arguments;
  }

  public static void setArguments(Arguments arguments) {
    Arguments.arguments = arguments;
  }

  public Path getOrigenPath() {
    return origenPath;
  }

  public void setOrigenPath(Path origenPath) {
    this.origenPath = origenPath;
  }

  public Path getDestinoPath() {
    return destinoPath;
  }

  public void setDestinoPath(Path destinoPath) {
    this.destinoPath = destinoPath;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public HashMap<String, List<String>> getArchivos() {
    return archivos;
  }

  public void setArchivos(HashMap<String, List<String>> archivos) {
    this.archivos = archivos;
  }


}
