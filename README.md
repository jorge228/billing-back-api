# Aplicación de Facturación (Back-end)

1. Cambiar los datos de acceso a la base de datos en el [application.properties](https://github.com/jorge228/billing-back-api/blob/master/src/main/resources/application.properties):
    *    spring.datasource.url: billing_api (nombre por defecto de la base de datos)
    *    spring.datasource.username: root
    *    spring.datasource.password: sasasasa
2. Crear base de datos con el nombre puesto anteriormente, bajo el puerto 3306.
3. Levantar el proyecto desde IDE compatible (Eclipse, SpringToolSuite 4...). Por defecto usa el puerto 3306.
4. Ejecutar el [front-end](https://github.com/jorge228/billing-front) si aún no lo ha realizado.