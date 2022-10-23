package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import org.example.helper.Compare;

public class MyFileReader {

    String SEPARATOR=":";
    String path;

    public MyFileReader(String path){
        this.path = path;
    }

    public List setPersonList( List personList) throws IndexOutOfBoundsException{
        try (FileReader fr = new FileReader(this.path);
             BufferedReader br = new BufferedReader(fr)) {
                String newLine = br.readLine();

                while(newLine != null) {

//                  Añado los separadores necesarios mínimos indispensables para la correcta lectura del fichero.
                    int countSeparators = Compare.countChars(newLine, SEPARATOR);

//                  Comprobación de separadores mínimos requeridos.
                    try {
                        if(countSeparators < 2){
                            throw new IndexOutOfBoundsException("Incorrect Number of separators (" + countSeparators + ") -> " + newLine + " !!!The necessary separators have been added");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }

//                  Se crea un array de strings con los campos de cada línea del archivo de texto.
                    String[] fields = newLine.split(SEPARATOR);

                    Person person = new Person();

//                  Seteo los campos para la persona y compruebo excepciones.
                    person.setValidName(fields[0]);
                    person.setValidTown(fields.length>=2 ? fields[1] : "");
                    person.setValidAge(fields.length>=3 ? fields[2] : "");

//                  Guardo la persona en la lista.
                    personList.add(person);

//                  Salto de línea.
                    newLine = br.readLine();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n Success, reading List Persons Book -> method completed...");
        return(personList);
    }
}
