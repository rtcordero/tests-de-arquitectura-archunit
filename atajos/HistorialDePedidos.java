package es.ejemplo.pedidos.dominio;

import java.sql.Connection;
import java.sql.SQLException;

// El otro atajo: SQL dentro del dominio.
public class HistorialDePedidos {
  private final Connection conexion;

  public HistorialDePedidos(Connection conexion) {
    this.conexion = conexion;
  }

  public int contar() throws SQLException {
    try (var consulta = conexion.createStatement();
         var filas = consulta.executeQuery("SELECT COUNT(*) FROM pedidos")) {
      filas.next();
      return filas.getInt(1);
    }
  }
}
