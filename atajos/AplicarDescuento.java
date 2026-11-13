package es.ejemplo.pedidos.aplicacion;

import es.ejemplo.pedidos.infraestructura.RepositorioEnMemoria;

// El atajo: el caso de uso crea el adaptador en lugar de recibir el puerto.
public class AplicarDescuento {
  private final RepositorioEnMemoria repositorio = new RepositorioEnMemoria();

  public long ejecutar(String idPedido) {
    return repositorio.buscar(idPedido)
        .map(p -> p.totalEnCentimos() * 90 / 100)
        .orElse(0L);
  }
}
