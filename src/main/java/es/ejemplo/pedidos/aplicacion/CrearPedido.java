package es.ejemplo.pedidos.aplicacion;

import es.ejemplo.pedidos.dominio.Pedido;
import es.ejemplo.pedidos.dominio.RepositorioDePedidos;

// El caso de uso recibe el puerto; no sabe qué adaptador hay detrás.
public class CrearPedido {
  private final RepositorioDePedidos repositorio;

  public CrearPedido(RepositorioDePedidos repositorio) {
    this.repositorio = repositorio;
  }

  public Pedido ejecutar(String id, long totalEnCentimos) {
    var pedido = new Pedido(id, totalEnCentimos);
    repositorio.guardar(pedido);
    return pedido;
  }
}
