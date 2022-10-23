package org.example;
import org.example.helper.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Operation {

    public List<Expresion> expressionList;
    private String result;
    private String[] operators;
    boolean validatedExpression;

    public Operation() {
        result = "";
        expressionList = new ArrayList<>();
        validatedExpression = true;
    }

    public void setOperators() {
        // Esta es la lista de expresiones iniciales que no contiene operadores.
        List <String> dataTypes = new ArrayList<>(Arrays.asList("date", "number", "string"));

        // Busco e introduzco todos los posibles operadores que sean válidos para el tipo de dato.
        operators = expressionList.stream().filter(expression -> {
            boolean isOperator = Compare.isIncludeInString(expression.getType(), dataTypes.stream().collect(Collectors.joining(",")).split(","));
            if(!dataTypes.contains(expression.getType())) dataTypes.add(expression.getType());
            return !isOperator;
        }).map(expression -> expression.getType()).collect(Collectors.joining(",")).split(",");
    }

    public void addExpresion(Expresion expresion){
        expressionList.add(expresion);
        setValidatedExpression();
    }

    // Validamos la operación.
    public void setValidatedExpression() {
        String typeOperation = expressionList.get(0).getType();

        // Busco todos los operadores de las expresiones, no repetidos y los guardo.
        setOperators();

        // Se busca que todos los tipos coincidan en cada una de las expresiones, salvo con las expresiones que son operadores, esto se hace para evitar operaciones erróneas introducidas por el usuario.
        try {
            if(validatedExpression) {
                Expresion expression = expressionList.get(expressionList.size() - 1);
//                System.out.println(Arrays.stream(operators).map(str -> str).collect(Collectors.joining()));

                if(!typeOperation.equals(expression.getType()) && !Compare.isIncludeInString(expression.getType(), operators) ) {
                    validatedExpression = false;
                    throw new OperationException("Incorrect type in expresion: ( " + expression.toString() + " ) ");
                }
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void calculate() {
        String typeOperation = expressionList.get(0).getType();
        // Si todas las expresiones están validadas, entonces se puede proceder a calcular.
        if(validatedExpression) {
            try {
                if(typeOperation == "date") {
                    handleDates();
                } else if (typeOperation == "number") {
                    handleNumbers();
                } else if (typeOperation == "string"){
                    handleStrings();
                } else {
                     throw new OperationException("Incorrect type in calculate(): ( " + typeOperation + " ) ");
                }
            } catch (OperationException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleDates(){
        String may = ">";
        String men = "<";
        operators = new String[]{may, men};

        // El siguiente código viene preparado para operar 3 expresiones, dos dates y un operador.
        // Solamente podremos verificar si una fecha es mayor o menor que otra.
        try {
            for (int index = 1; index < expressionList.size() - 1; index++) {
                Expresion date = expressionList.get(index);
                Expresion dateAft = expressionList.get(index + 1);
                Expresion dateBfr = expressionList.get(index - 1);

                if(( expressionList.size() != 3 || dateAft.getType() != "date" || dateBfr.getType() != "date" ) && Compare.isIncludeInString(date.getType(), operators)){
                    throw new OperationException("Incorrect type in handleDates(): ( " + date.toString() + " ) Only 2 dates and 1 operator");
                }

                if(date.getType().equals(may)){
                    result = dateBfr.getDate().getTime() > dateAft.getDate().getTime() ? "true" : "false";
                } else if (date.getType().equals(men)) {
                    result = dateBfr.getDate().getTime() < dateAft.getDate().getTime() ? "true" : "false";
                }
            }
        } catch(OperationException e) {
            e.printStackTrace();
        }
    }

    public void handleNumbers(){
        String sum = "+";
        String res = "-";
        String div = "/";
        String mul = "*";
        operators = new String[]{sum, mul, res, div};

        // A Continuación se desarrola una calculadora básica con 3 expresiones como máximo.
        // Si se desea ver este código por separado, visita mi perfil de github, está en javaScript https://github.com/AndresMiras/casio-calculator/
        try {
            Expresion expressionBfr = expressionList.get(0);
            Expresion expression = expressionList.get(1);
            Expresion expressionAft = expressionList.get(2);
            boolean valOperator = Compare.isIncludeInString(expression.getType(), operators);
            String operator = expressionList.get(1).getType();
            if(valOperator) {
                if(operator.equals(mul)) {
                    result = String.valueOf(expressionBfr.getInteger() * expressionAft.getInteger());
                } else if(operator.equals(div)) {
                    result = String.valueOf(expressionBfr.getInteger() / expressionAft.getInteger());
                } else if(operator.equals(res)) {
                    result = String.valueOf(expressionBfr.getInteger() - expressionAft.getInteger());
                } else if(operator.equals(sum)) {
                    result = String.valueOf(expressionBfr.getInteger() + expressionAft.getInteger());
                }
            } else {
                throw new OperationException("Incorrect Operator handleNumbers(): ( " + expressionBfr.toString() + " " + expression.toString() + " )");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void handleStrings(){
        String sum = "+";
        String mul = "*";
        operators = new String[]{sum, mul};
        List<Expresion> stringList = new ArrayList<>();

        // Clonación profunda del array de expresiones original.
        for(Expresion expresion : expressionList){
            Expresion newExpresion = new Expresion(expresion);
            stringList.add(newExpresion);
        };

        try {
            // Elimino las marcas de anotacion contenidas en los strings para poder operarlos mejor.
            stringList = stringList.stream().map(expresion -> {
                expresion.setString(Utils.unformatWithQuotation(expresion.getString()));
                return expresion;
            }).toList();

            // Busco si hay multiplicaciones en la lista de expresiones, le paso la lista y la reduce.
            stringList = handleMultiInString(stringList);

            // Busco si hay sumas en la lista de expresiones, le paso la lista y la reduce.
            stringList = handleSumInString(stringList);

            // Finalmente guardo el resultado formateado con espacios
            result = stringList.stream().map(elm -> elm.getString()).collect(Collectors.joining(" "));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private List<Expresion> handleMultiInString(List<Expresion> stringList ) throws Exception {
        int index = stringList.size() - 1;

        while (index > 0 ) {
            String operatorInList = stringList.get(index).getType();

            if(operatorInList.equals("*")) {

                // Guardo las posiciones de los strings para operarlos.
                String stringBfr = stringList.get(index - 1).getString();
                String stringAft = stringList.get(index + 1).getString();

                // Busco si alguno de los pivotes es un número, en caso de no serlo se arroja un error.
                if(Compare.isNumber(stringBfr)) {
                    int integer = Integer.parseInt(stringBfr);
                    stringList.get(index - 1).setString(Utils.multipliString(stringAft, integer));
                } else if(Compare.isNumber(stringAft)) {
                    int integer = Integer.parseInt(stringAft);
                    stringList.get(index - 1).setString(Utils.multipliString(stringBfr, integer));
                } else {
                    throw new OperationException("Incorrect Operator in handleMultiInString(): ( " + stringBfr + " " + operatorInList + " " + stringAft + " )");
                }

                // Reduzco la lista con la operación hecha en el primer pivote.
                stringList = handlePivot(stringList, index);
                index = stringList.size();
            }

            index--;
        }

        return stringList;
    }

    private List<Expresion> handleSumInString(List<Expresion> stringList ) throws Exception {
        int index = stringList.size() - 1;

        while (index > 0 ) {
            String operatorInList = stringList.get(index).getType();

            if(operatorInList.equals("+")) {

                // Guardo las posiciones de los strings y los tipos para poder operarlos.
                String stringBfr = stringList.get(index - 1).getString();
                String stringAft = stringList.get(index + 1).getString();
                String typeBfr = stringList.get(index - 1).getType();
                String typeAft = stringList.get(index + 1).getType();

                // Se comprueba que los elementos a ambos lados del pivote sean de tipo string.
                if(typeBfr.equals("string") && typeAft.equals("string")) {
                    stringList.get(index - 1).setString(stringBfr + " " + stringAft);
                } else {
                    throw new OperationException("Incorrect Operator in handleSumInString(): ( " + stringBfr + " " + operatorInList + " " + stringAft + " )");
                }

                // Reduzco la lista con la operación hecha en el primer pivote.
                stringList = handlePivot(stringList, index);
                index = stringList.size();
            }

            index--;
        }

        return stringList;
    }

    private List<Expresion> handlePivot(List<Expresion> stringList, int pivot ) throws Exception {
        // Se generan dos fragmentos para guardar el pivote y borrar los elementos sumados.
        List<Expresion> newList = new ArrayList<>();
//        System.out.println(stringList + " -> " + pivot);
        stringList.subList(0, pivot).forEach( expresion -> newList.add(expresion));
        stringList.subList(pivot + 2 , stringList.size()).forEach( expresion -> newList.add(expresion));
        return newList;
    }

    public String getResult() {
        // Si la propiedad está vacía arrojará un error. en caso contrario analiza si es una fecha y si lo es la formatea con comillas.
        // El resto de resultados salen sin formateo previo.
        return result.equals("") ? "Error!!!" :
               expressionList.get(0).getType().equals("string") ? Utils.formatWithQuotation(result) :
               result ;
    }

    @Override
    public String toString() {
        String str;
        str = expressionList.stream().map(expression -> expression.toString()).collect(Collectors.joining(" "));
        return "Operación: ( " + str + " ) = " + getResult() + "\n";
    }
}
