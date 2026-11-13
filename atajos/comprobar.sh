#!/usr/bin/env bash
# Mete cada atajo en el código, uno cada vez, y comprueba que los tests de arquitectura lo cazan.
# Uso: atajos/comprobar.sh (desde la raíz del repositorio)
set -uo pipefail
cd "$(dirname "$0")/.."
base=src/main/java/es/ejemplo/pedidos
fallos=0

probar() {
  local fichero=$1 destino=$2 esperado=$3
  mkdir -p "$(dirname "$destino")"
  cp "atajos/$fichero" "$destino"
  local salida
  salida=$(mvn -q -B test 2>&1)
  rm "$destino"
  rmdir "$(dirname "$destino")" 2>/dev/null
  if grep -qF "$esperado" <<<"$salida"; then
    echo "✔ $fichero: cazado («$esperado»)"
  else
    echo "✖ $fichero: los tests no lo han cazado"
    fallos=$((fallos + 1))
  fi
}

probar AplicarDescuento.java "$base/aplicacion/AplicarDescuento.java" \
  "AplicarDescuento.<init>()> calls constructor <es.ejemplo.pedidos.infraestructura.RepositorioEnMemoria.<init>()>"
probar HistorialDePedidos.java "$base/dominio/HistorialDePedidos.java" \
  "has parameter of type <java.sql.Connection>"
probar Descuentos.java "$base/util/Descuentos.java" \
  "Class <es.ejemplo.pedidos.util.Descuentos> is not contained in architecture"

exit "$fallos"
