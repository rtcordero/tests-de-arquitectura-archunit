package es.ejemplo.pedidos;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

// Las reglas de dependencia de la arquitectura, comprobadas sobre las clases compiladas.
class ArquitecturaTest {

  private static final JavaClasses CLASES = new ClassFileImporter()
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
      .importPackages("es.ejemplo.pedidos");

  @Test
  void cada_capa_solo_usa_las_que_le_tocan() {
    layeredArchitecture().consideringOnlyDependenciesInLayers()
        .ensureAllClassesAreContainedInArchitecture()
        .layer("Dominio").definedBy("..dominio..")
        .layer("Aplicación").definedBy("..aplicacion..")
        .layer("Infraestructura").definedBy("..infraestructura..")
        .whereLayer("Infraestructura").mayNotBeAccessedByAnyLayer()
        .whereLayer("Aplicación").mayOnlyBeAccessedByLayers("Infraestructura")
        .because("el dominio es el centro y los casos de uso solo conocen sus puertos")
        .check(CLASES);
  }

  @Test
  void el_dominio_no_depende_de_nada_de_fuera() {
    classes().that().resideInAPackage("..dominio..")
        .should().onlyDependOnClassesThat()
        .resideInAnyPackage("..dominio..", "java.lang..", "java.util..", "java.math..", "java.time..")
        .because("ni bases de datos, ni red, ni SDK de terceros: eso va en un adaptador")
        .check(CLASES);
  }
}
