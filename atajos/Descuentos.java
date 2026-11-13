package es.ejemplo.pedidos.util;

// El tercer atajo: una clase en un paquete que ninguna capa declara.
public class Descuentos {
  public static long conDescuentoDeSocio(long totalEnCentimos) {
    return totalEnCentimos * 90 / 100;
  }
}
