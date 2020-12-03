package lfr.ld.files.joinFiles;

import java.util.ArrayList;
import java.util.List;

public class A {

	public static void main(String[] args) {
        final String prefix = "Maxi";
        List<String> animals = new ArrayList<>();
        animals.add("Cat");
        animals.add("Dog");
        animals.stream().forEach(animal -> System.out.println(prefix + animal));
    }
}
