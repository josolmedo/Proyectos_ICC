Introducción a Ciencias de la Computación
=========================================

Proyecto 1: Base de datos de perros
-------------------------------------------------------

### Fecha de entrega: viernes 4 de abril, 2025

Para compilar:

```
$ mvn compile
```

Para ver las pruebas unitarias:

```
$ mvn test
```

Por último, se debe ejecutar correctamente el programa escrito en la clase
[Proyecto1](main/src/main/java/mx/unam/ciencias/icc/Practica5.java)
al hacer:

```
$ mvn install
...
$ java -jar target/proyecto1.jar
...
$ java -jar target/proyecto1.jar -g perros.db # guarda la base de datos
...
$ java -jar target/proyecto1.jar -c perros.db # carga la base de datos
```


### Repositorio

Pueden clonar el proyecto con el siguiente comando:

```
$ git clone https://github.com/josolmedo/Proyectos_ICC.git
```

### Documentación

La documentación generada por JavaDoc la pueden consultar aquí:


https://aztlan.fciencias.unam.mx/~canek/2025-2-icc/practica5/apidocs/index.html

tar -czvf proyecto1.tar.gz proyecto1

tar -xzvf proyecto1.tar.gz

