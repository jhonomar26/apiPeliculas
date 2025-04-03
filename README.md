# ApiPeliculas

Aplicación Android que consume una API de películas utilizando Retrofit para realizar peticiones HTTP. Cuenta con una interfaz gráfica en desarrollo y funcionalidades como visualización de películas y una pantalla de "Acerca de".

## Características
- Consumo de API de películas con Retrofit.
- Listado de películas con adaptador personalizado.
- Pantalla "Acerca de".
- Opción para limpiar la pantalla.
- Uso de clave de API oculta para mayor seguridad.

## Estructura del proyecto

El proyecto incluye los siguientes archivos clave:

- `MainActivity.kt`: Actividad principal donde se muestra la lista de películas.
- `AboutActivity.kt`: Pantalla de información sobre la aplicación.
- `Movie.kt`: Modelo de datos para representar una película.
- `MovieAdapter.kt`: Adaptador para manejar la lista de películas en un RecyclerView.
- `MovieApiService.kt`: Interfaz que define los endpoints de la API.
- `MovieResponse.kt`: Modelo para la respuesta de la API.
- `RetrofitClient.kt`: Cliente para gestionar las peticiones a la API mediante Retrofit.

## Instalación y Ejecución

1. Clona este repositorio:
   ```sh
   git clone https://github.com/tuusuario/ApiPeliculas.git
   ```
2. Abre el proyecto en Android Studio.
3. Asegúrate de tener configurado el API Key en `local.properties`:
   ```
   API_KEY=tu_api_key
   ```
4. Ejecuta la aplicación en un emulador o dispositivo físico.

## Tecnologías utilizadas
- Kotlin
- Retrofit
- RecyclerView
- Android Jetpack Components

## Estado del proyecto
La aplicación se encuentra en desarrollo, con una interfaz gráfica a medias. Se está trabajando en mejorar la presentación y agregar nuevas funcionalidades.

## Contribuciones
Si deseas contribuir, por favor haz un fork del repositorio y envía un pull request con tus mejoras.

## Licencia
Este proyecto se encuentra bajo la licencia MIT.

