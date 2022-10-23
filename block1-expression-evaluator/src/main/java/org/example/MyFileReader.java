package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class MyFileReader {

    String SEPARATOR=" ";
    String path;

    public MyFileReader(String path){
        this.path = path;
    }

    public List evaluateExpresionList(List<Operation> expresionList) {

        try (FileReader fr = new FileReader(this.path);
             BufferedReader br = new BufferedReader(fr)) {
                String newLine = br.readLine();

                // No guardo las líneas que sean nulas o estén vacías.
                // Recorro el libro para guardar cada expresión y así poder realizar una operación con el conjunto.
                while(newLine != null && !newLine.equals("")) {
                    Operation operation = new Operation();

                    for (String expresionStr : newLine.split(SEPARATOR)) {
                        Expresion expresion = new Expresion();
                        expresion.setTypeOfVariable(expresionStr);
                        operation.addExpresion(expresion);
                    }

                    // Guardo las expresiones en la lista.
                    expresionList.add(operation);

                    //  Salto de línea.
                    newLine = br.readLine();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n Success, reading -> method completed...\n");
        return(expresionList);
    }
}
