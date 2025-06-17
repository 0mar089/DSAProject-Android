# Android App - TOCABOLAS ⚪📲

Esta es la aplicación Android complementaria al juego TOCABOLAS. Proporciona una experiencia completa fuera del juego, incluyendo tienda, inventario de ítems, configuración de cuenta, sección de ayuda y más.

## Funcionalidades principales 🧩

### 🛒 Tienda de bolas
- Visualiza y compra objetos especiales como:
  - 🧨 Bombas (destruyen bolas)
  - 💰 Oro (dinero extra)
  - ♻️ Delete (elimina la bola tocada)
- Cada ítem muestra nombre, precio, descripción e imagen.
- Los ítems comprados se almacenan en tu inventario.

### 🧺 Carrito de compras
- Añade objetos desde la tienda.
- Muestra lista de ítems antes de confirmar la compra.
- Verifica si tienes suficiente dinero.
- Confirma y envía la compra al backend.

### 🙋‍♀️ FAQs (Preguntas Frecuentes)
- Sección de ayuda con respuestas rápidas sobre:
  - Cómo jugar
  - Cómo usar objetos
  - Qué hacer si pierdes el progreso
  - Entre otros...

### 🏅 Insignias
- Visualiza insignias obtenidas por logros en el juego.
- En futuras versiones, se podrán desbloquear con hitos del usuario.

### 👤 Perfil
- Muestra tus estadísticas:
  - Nombre de usuario
  - Récord actual
  - Dinero acumulado
  - Ítems disponibles

### ⚙️ Configuración de cuenta
- Cambia contraseña
- Cierra sesión
- Elimina cuenta (requiere reautenticación)

## Conexión con el backend 🔗
- Autenticación con JWT
- Acceso seguro a datos personales y de juego
- Comunicación mediante peticiones `GET` y `POST` al backend Java

## Tecnologías utilizadas
- Java + Android SDK
- Retrofit2 (para consumir la API REST)
- Glide (para imágenes de ítems)
- Room (opcional: cache local)
- Material Design


## Autor
Desarrollado por Laura, Victor, Jan y Omar.

