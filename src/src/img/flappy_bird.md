# Proyecto Final — Flappy Bird
### Desarrollo de Aplicaciones con JavaFX

---

## Descripción general

El estudiante debe desarrollar una versión funcional del juego Flappy Bird. La aplicación debe estar desarrollada en Java con JavaFX, sin el uso de motores de juego externos ni librerías ajenas al SDK estándar de JavaFX. El juego debe correr en tiempo real mediante un `AnimationTimer` o `Timeline`.

---

## Requisitos del juego

El jugador controla un pájaro que cae constantemente por efecto de la gravedad simulada. Al presionar la barra espaciadora o hacer clic en la pantalla, el pájaro recibe un impulso hacia arriba. El objetivo es pasar por los espacios entre pares de tuberías o obstáculos que se desplazan de derecha a izquierda a velocidad constante. Los obstáculos deben generarse con alturas aleatorias manteniendo siempre un espacio suficiente para que el pájaro pueda pasar. Cada par de obstáculos superado suma un punto al marcador. La partida termina si el pájaro choca con un obstáculo, toca el suelo o sale por la parte superior de la pantalla. La velocidad de desplazamiento de los obstáculos debe aumentar progresivamente conforme sube la puntuación. El juego debe registrar la puntuación máxima alcanzada durante la sesión.

---

## Requisitos de interfaz

La interfaz debe mostrar el pájaro, los obstáculos en movimiento, el suelo y un marcador de puntos visible durante la partida. Debe existir una pantalla de inicio que indique cómo comenzar y una pantalla de game over que muestre la puntuación obtenida, la puntuación máxima de la sesión y un botón para reiniciar. La transición entre pantallas debe ser clara y sin errores visuales.

---

## Requisitos técnicos

- Desarrollado en Java con JavaFX y FXML.
- El bucle principal del juego debe implementarse con `AnimationTimer` o `Timeline`.
- La física del pájaro (gravedad e impulso) debe simularse mediante variables de velocidad vertical actualizadas en cada fotograma.
- La detección de colisiones entre el pájaro y los obstáculos o el suelo debe implementarse manualmente mediante comparación de posiciones o intersección de bounds.
- La generación de obstáculos y la lógica del juego deben estar separadas de la capa de renderizado en al menos una clase independiente.

---

## README

El proyecto debe incluir un archivo `README.md` en la raíz con el nombre del estudiante, una descripción breve del juego, las instrucciones para compilar y ejecutar la aplicación, los controles utilizados y los parámetros de física implementados (gravedad, impulso, velocidad inicial).

---

## Resultado esperado

Al finalizar, la aplicación debe permitir jugar partidas completas con física de gravedad e impulso, obstáculos generados aleatoriamente, aumento progresivo de dificultad, detección de colisiones, marcador de puntos, registro de puntuación máxima y pantallas de inicio y game over.
