package org.example;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // READ ME:
    // El siguiente programa acepta operaciones con ( fechas / numeros enteros / cadenas de texto ).
    // Si el usuario mete un dato con un tipo de formato diferente al de resto de expresiones, se arrojará un error (ver Clase Expresion).
    // La clase Operation guarda las expresiones y conforme lo hace, valida que sean del tipo adecuado. (El código es mejorable, pero me he centrado en la funcionalidad).

    // Operaciones Permitidas:
    // Acepta 3 expresiones para operaciones con fechas o números.
    // Para números se admite ( + - / * ) , cuando le pasamos una operación de 3 expresiones.
    // Para strings podemos pasar ( tantas cadenas como queramos ), entre comillas -> “” <- el programa realizará primero las multiplicaciones y luego las sumas ( solo acepta + * ).
    // Para las fechas se admiten 3 expresiones con los operadores ( < > );

    public static void main(String[] args){
        String path = "operations.csv";

        // Creo un nuevo lector para el CSV del ejercicio.
        MyFileReader MyEvaluateExpresionBook = new MyFileReader(path);

        // Creo una lista con las operaciones, estas a su vez incluyen las expresiones por tratar.
        List<Operation> operationList= new ArrayList<>();

        // Evalúo que tipo de dato que tengo.
        MyEvaluateExpresionBook.evaluateExpresionList(operationList);

        // Calculo las operaciones y las envío a consola junto a sus resultados.
        operationList.forEach(operation -> {

            // El método calcular ejecuta previamente una validación por línea, verificando así que todos los elementos sean del mismo tipo.
            operation.calculate();

            // Finalmente imprimo el resultado.
            System.out.println(operation);
        });
    }
}
