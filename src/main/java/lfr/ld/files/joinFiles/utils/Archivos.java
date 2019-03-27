package lfr.ld.files.joinFiles.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lfr.ld.files.joinFiles.Arguments;

public class Archivos {

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
        System.out.println("File " + listOfFiles[i].getName());
      }
    }
    return null;

  }

  private static String[] splitFiles(String name) {
    String[] retorno = {"",""};
    int lugar = name.lastIndexOf(".");
    if (lugar > 1) {
      retorno[0] = name.substring(0, lugar);
      retorno[1] = name.substring(lugar);
      System.out.println("Retorno[0] = " + retorno[0]);
      System.out.println("Retorno[1] = " + retorno[1]);
    }
    return retorno;
  }
}
