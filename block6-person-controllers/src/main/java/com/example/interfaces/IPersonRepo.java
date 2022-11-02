package com.example.interfaces;

public interface IPersonRepo {
     public String getName();

     public String getPopulation() ;

     public int getAge();

     public void setName(String name);

     public void setPopulation(String population);

     public void setAge(int age);

     String register();

     String toString();
}
