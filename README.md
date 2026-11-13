# Tests de arquitectura con ArchUnit

Código del artículo [Tests de arquitectura con ArchUnit: que la IA respete capas](https://rubentejera.com/tests-de-arquitectura/).

Una API de pedidos pequeña, con tres capas: dominio, aplicación e infraestructura. `ArquitecturaTest` comprueba con [ArchUnit](https://www.archunit.org/) dos reglas sobre las clases compiladas:

- **Cada capa solo usa las que le tocan**, y toda clase está en alguna capa (`ensureAllClassesAreContainedInArchitecture()`).
- **El dominio solo usa Java estándar**: ni bases de datos, ni red, ni SDK de terceros.

En `atajos/` están los tres atajos que el artículo usa como ejemplo de lo que un agente de IA puede hacer para añadir un descuento:

| Atajo | Qué hace | Regla que lo caza |
| --- | --- | --- |
| `AplicarDescuento.java` | El caso de uso crea el adaptador en lugar de recibir el puerto | Capas |
| `HistorialDePedidos.java` | SQL dentro del dominio | Dominio |
| `Descuentos.java` | Una clase en un paquete que ninguna capa declara | Capas (clase fuera de la arquitectura) |

`atajos/comprobar.sh` mete cada atajo en el código, uno cada vez, y comprueba que los tests fallan con el mensaje esperado. La CI lo ejecuta en cada cambio: si alguien afloja una regla y un atajo deja de cazarse, falla.

## Requisitos

Java 25 y Maven 3.9. Probado con Maven 3.9.16, Java 25.0.4.1 (Temurin), JUnit 6.1.3, ArchUnit 1.5.1 y Surefire 3.6.1.

## Uso

```bash
mvn test
atajos/comprobar.sh
```

## Cómo se ha hecho

Desarrollado con el enfoque [IA Limpia](https://rubentejera.com/que-es-la-ia-limpia/): un agente de IA escribe el código siguiendo los pasos del artículo, los tests validan cada paso y el resultado se revisa y se aprueba a mano antes de publicarlo.

## Licencia

MIT. Ver `LICENSE`.
