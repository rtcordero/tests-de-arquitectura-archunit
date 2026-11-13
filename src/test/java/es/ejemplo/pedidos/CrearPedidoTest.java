package es.ejemplo.pedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import es.ejemplo.pedidos.aplicacion.CrearPedido;
import es.ejemplo.pedidos.dominio.Pedido;
import es.ejemplo.pedidos.infraestructura.RepositorioEnMemoria;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class CrearPedidoTest {

  @Test
  void un_pedido_creado_se_puede_buscar_por_su_id() {
    var repositorio = new RepositorioEnMemoria();

    new CrearPedido(repositorio).ejecutar("P-1", 12_000);

    assertEquals(Optional.of(new Pedido("P-1", 12_000)), repositorio.buscar("P-1"));
  }
}
