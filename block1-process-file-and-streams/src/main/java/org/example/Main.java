package org.example;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        List<Person> personListMaxAge25;
        MyFileReader csvFileReader = new MyFileReader("people.csv");

//      Captura de las personas contenidas en el csv.
        csvFileReader.setPersonList(personList);

//      Lectura de la Lista.
        personList.forEach(person -> System.out.println(person.getInfo()));

        System.out.println("\n a) Invocar a la función con un filtro que conserve únicamente las personas menores de 25 años. Imprimir la lista devuelta en la consola. Para los campos opcionales vacíos, imprimir: unknown.");
        personListMaxAge25 = Person.findPersonsUnderAge(personList, 25);
        personListMaxAge25.forEach(person -> System.out.println(person.getInfo()));

        System.out.println("\n b) Invocar a la función con un filtro que elimine las personas cuyo nombre empiece con la letra A.");
        Person.findPersonsWithStr(personList,"A").forEach(person -> System.out.println(person.getInfo()));

        System.out.println("\n c) Usando el resultado del apartado a), crear un Stream a partir de la lista y obtener el primer elemento cuya ciudad sea Madrid. Si existe algún elemento imprimirlo. Tratar correctamente el Optional.");
        Person firstPersonInMadrid = Person.findFirstTown(personListMaxAge25, "Madrid").orElse(new Person());
        System.out.println(firstPersonInMadrid.getInfo());

        System.out.println("\n d) Usando el resultado del apartado a), crear un Stream a partir de la lista y obtener el primer elemento cuya ciudad sea Barcelona. Si existe algún elemento imprimirlo. Tratar correctamente el Optional.");
        Person firstPersonInBarcelona = Person.findFirstTown(personListMaxAge25, "Barcelona").orElse(new Person());
        System.out.println(firstPersonInBarcelona.getInfo());

        System.out.println("\n");
    }
}
