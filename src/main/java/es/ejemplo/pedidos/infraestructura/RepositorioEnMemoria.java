package es.ejemplo.pedidos.infraestructura;

import es.ejemplo.pedidos.dominio.Pedido;
import es.ejemplo.pedidos.dominio.RepositorioDePedidos;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// El adaptador: implementa el puerto guardando los pedidos en memoria.
public class RepositorioEnMemoria implements RepositorioDePedidos {
  private final Map<String, Pedido> pedidos = new HashMap<>();

  @Override
  public void guardar(Pedido pedido) {
    pedidos.put(pedido.id(), pedido);
  }

  @Override
  public Optional<Pedido> buscar(String id) {
    return Optional.ofNullable(pedidos.get(id));
  }
}
