/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacionaula;

/**
 *
 * @author mariorl
 */
public class Delegado extends Alumno {
 // Metodos:
  public Delegado(String nom){      // Constructor 1
    super(nom); // Llamada explicita al constructor de la superclase
    curso = numAsignaturas = 1;
  }

  public Delegado(String nom, int cur, int na){  // Constructor 2
    super(nom); // Llamada explicita al constructor de la superclase
    curso = cur;
    numAsignaturas = na;
  }
}
