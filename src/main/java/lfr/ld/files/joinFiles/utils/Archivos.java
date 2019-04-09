package lfr.ld.files.joinFiles.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.log4j.Logger;
import lfr.ld.files.joinFiles.Arguments;

public class Archivos {

  private static Logger logger = Logger.getLogger(Archivos.class);

  public static HashMap<String, List<String>> readFiles() {
    HashMap<String, List<String>> ficheros = new HashMap<String, List<String>>();
    File folder = Arguments.getArguments().getOrigenPath().toFile();

    File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        String[] name = splitFiles(listOfFiles[i].getName());
        List<String> extensiones;
        if (ficheros.containsKey(name[0])) {
          extensiones = ficheros.get(name[0]);
          extensiones.add(name[1]);
        } else {
          extensiones = new ArrayList<String>();
          extensiones.add(name[1]);
        }
        ficheros.put(name[0], extensiones);
      }
    }
    return ficheros;

  }

  public static void printHashMap(HashMap<String, List<String>> mp) throws IOException {

    Iterator it = mp.entrySet().iterator();
    String folder = Arguments.getArguments().getOrigenPath().toString();
    String zipFolder = Arguments.getArguments().getDestinoPath().toString();
    File fileZipFolder = new File(zipFolder);
    if (!fileZipFolder.exists()) {
      fileZipFolder.mkdirs();
    }
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      String mainFile = Paths.get(folder, (String) pair.getKey()).toString();
      File zipper = Paths.get(zipFolder, (String) pair.getKey() + Constants.ZIPPER_EXTENSION).toFile();
      if (!zipper.exists()) {
        zipper.createNewFile();
      }
      logger.debug("nombre zipper: " + zipper);
      List<String> filesToCompress = new ArrayList<String>();
      for (String ext : (List<String>) pair.getValue()) {
        logger.debug("nombre fichero: " + mainFile + ext);
        filesToCompress.add(mainFile + ext);
      }
      ZipMultipleFiles(zipper, filesToCompress);
    }

  }

  public static void ZipMultipleFiles(File zipFile, List<String> filesToCompress) throws IOException {
    FileOutputStream fos = new FileOutputStream(zipFile);
    ZipOutputStream zipOut = new ZipOutputStream(fos);
    for (String srcFile : filesToCompress) {
      File fileToZip = new File(srcFile);
      FileInputStream fis = new FileInputStream(fileToZip);
      ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
      zipOut.putNextEntry(zipEntry);

      byte[] bytes = new byte[1024];
      int length;
      while ((length = fis.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
      }
      fis.close();
    }
    zipOut.close();
    fos.close();
  }

  private static String[] splitFiles(String name) {
    String[] retorno = {"", ""};
    int lugar = name.lastIndexOf(".");
    if (lugar > 1) {
      retorno[0] = name.substring(0, lugar);
      retorno[1] = name.substring(lugar);
    }
    return retorno;
  }
}
