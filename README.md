🍕 Simulación de Pizzería Concurrente en Java
Descripción general

Este proyecto es una aplicación de práctica desarrollada en Java cuyo objetivo es aplicar y comprender los principios de programación concurrente, mediante el uso de multihilos (multithreading) y multiprocesos, simulando el funcionamiento interno de una pizzería.

La aplicación modela distintos componentes del flujo de trabajo de una pizzería real —recepción de pedidos, preparación, horneado y entrega— ejecutándose de forma paralela y coordinada, permitiendo observar el comportamiento de múltiples tareas concurrentes y la gestión de recursos compartidos.

Objetivos del proyecto

Aplicar conceptos de concurrencia y paralelismo en Java.

Comprender la diferencia práctica entre multihilos y multiprocesos.

Gestionar recursos compartidos de forma segura.

Evitar problemas comunes como:

Condiciones de carrera

Interbloqueos (deadlocks)

Inconsistencias de datos

Simular un entorno realista con múltiples tareas simultáneas.

Funcionalidad principal

Simulación de pedidos concurrentes.

Procesos independientes para las distintas etapas de la pizzería.

Ejecución paralela de tareas mediante hilos.

Sincronización y coordinación entre componentes.

Registro por consola del flujo de ejecución para análisis y depuración.

Tecnologías y conceptos utilizados

Java

Programación concurrente

Multithreading

Multiprocessing

Sincronización de hilos

Recursos compartidos

Requisitos

Java JDK 8 o superior

IDE o terminal con soporte para Java

Git (opcional, para control de versiones)

Ejecución

Desde la raíz del proyecto:

javac src/main/java/pizzeria/*.java
java pizzeria.Main


O ejecutar directamente desde el IDE de tu preferencia.

Estado del proyecto

📌 Proyecto educativo y experimental, enfocado en el aprendizaje de programación concurrente.
No está destinado a uso en producción.

Autor: albertoportfolio

Proyecto desarrollado con fines académicos y de práctica personal.

Licencia

Este proyecto se distribuye bajo fines educativos. Puedes adaptarlo y reutilizarlo libremente para aprendizaje.
