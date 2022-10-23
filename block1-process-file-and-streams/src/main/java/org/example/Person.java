package org.example;
import org.example.helper.Compare;
import org.example.helper.Field;

import java.util.List;
import java.util.Optional;

public class Person {
    String name,town;
    int age;

    public Person(){
        this.name = "unknown";
        this.town = "unknown";
        this.age = 0;
    }

    public Person(String name, String town, int age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    public Person(String name) throws FieldFormatException {
        this.name = name != "" ? name : "unknown";
    }

    public Person(String name, String town) {
        this.name = name;
        this.town = town;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setValidName(String name) throws FieldFormatException{
        if(!name.equals("")){
            try {
                if(Field.validateName(name)) {
                    setName(name);
                } else {
                    throw new FieldFormatException(" -> " + this.name + ":" + this.town + " <- Invalid name format!!!");
                }
            } catch (FieldFormatException e){
                System.out.println(e);
            }
        } else {
            throw new FieldFormatException(" -> " + this.name + ":" + this.town + " <- Name field is empty!!!");
        }
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTown() {
        return this.town;
    }

    public void setValidTown(String town) {
        try {
            // Seteo del campo "town".
            if(!town.equals("")){
                if(Field.validatePlace(town)) {
                    setTown(town);
                } else {
                    throw new FieldFormatException(" -> " + this.name + ":" + this.town + " <- Invalid name format!!!");
                }

            } else {
                throw new FieldFormatException(" -> " + this.name + ":" + this.town + " <- Town field is empty!!!");
            }
        } catch(FieldFormatException e) {
            System.out.println(e);
        }
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return this.age;
    }

    public void setValidAge(String age) {
        int auxAge;
        try {
            //  Seteo del campo "age".
            if(!age.equals("")){
                //  Intento parsear el campo de la edad antes de crear el objeto Persona.
                if(Compare.isNumber(age)) {
                    auxAge = Integer.parseInt(age);
                    setAge(auxAge);
                } else {
                    throw new FieldFormatException(" -> " + this.town + ":" + this.age + " <- Age field haven't number format!!!");
                }
            } else {
                throw new FieldFormatException(" -> " + this.town + ":" + this.age + " <- Age field it's empty!!!");
            }
        } catch(FieldFormatException e) {
            System.out.println(e);
        }
    }

//  Filtro de la lista de personas con el fin de encontrar aquellas que tengan menos de cierta edad.
    public static List<Person> findPersonsUnderAge(List<Person> personas, int age) {
        return personas.stream().filter(person -> person.getAge() < age && person.getAge() != 0).toList();
    }

//  Filtro de la lista de personas para encontrar aquellas cullo nombre comience por el String indicado.
    public static List<Person> findPersonsWithStr(List<Person> personas, String nameStartsWith) {
        return personas.stream().filter(person -> person.getName().toUpperCase().startsWith(nameStartsWith)).toList();
    }

//  Filtrado para obtener una persona con determinado nombre ignorando mayusculas y minusculas, guardando un valor opcional
    public static Optional<Person> findFirstName(List<Person> personas, String name) {
        return personas.stream().filter(person -> person.getName().equalsIgnoreCase(name)).findFirst();
    }

//  Filtrado para obtener personas en una determinada ciudad ignorando mayusculas y minusculas, guardando un valor opcional
    public static Optional<Person> findFirstTown(List<Person> personas, String town) {
        return personas.stream().filter(person -> person.getTown().equalsIgnoreCase(town)).findFirst();
    }

    public String getInfo() {
        return("\tname:" + name + ", " + "City:" + town + ", " + "age:"  + (age != 0 ? String.valueOf(age) : "unknown"));
    }
}
