package es.ejemplo.pedidos.dominio;

// Un pedido, con su importe en céntimos para no arrastrar errores de redondeo.
public record Pedido(String id, long totalEnCentimos) {}
