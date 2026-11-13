package es.ejemplo.pedidos.dominio;

import java.util.Optional;

// El puerto: lo que el dominio necesita para guardar pedidos, sin saber dónde.
public interface RepositorioDePedidos {
  void guardar(Pedido pedido);

  Optional<Pedido> buscar(String id);
}
