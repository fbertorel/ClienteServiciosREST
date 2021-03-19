# Tema 4 - Laboratorio I

Clon de postman

## Necesario

Se usa la libreria Apache-commons-http.

Se utiliza un servidor local json para realizar las request (informacion para instalarlo en

https://medium.com/chaya-thilakumara/how-to-create-a-local-api-server-rest-api-for-testing-945bbb2d31b7).

Se debera correr en powershell para inicializarlo luego de instalado: 

```bash
json-server --watch db.json
```

# Detalles

Se deberá realizar un cliente de servicios rest, con funcionalidad limitada, similar a la del clásico Postman. Para las requests http, NO se debe realizar la funcionalidad desde cero, sino que se debe utilizar una librería. La más común es apache-commons-http.

## Funcionalidad basica requerida

Poder escribir una url y ejecutar métodos GET, POST, PUT, DELETE contra ella. Los parámetros de la url (querystring) se ingresan manualmente en la url (ej. servicor.com?a=123&b=xyz)
Caja de texto o lista para adicionar headers de ser necesario. Uno por renglón. 
Caja de texto para escribir el body de la request
Caja de texto para visualizar la response 
Administrar urls y parámetros “Favoritos” en una base de datos, que se deberán precargar al ser seleccionados.

## Funcionalidad Adicional

Hacer del ingreso de headers una grilla.
Poder adicionar parámetros de querystring en una tabla de claves-valores dedicada a ello, similar a la de los headers, con una grilla.
Visualizar la respuesta en diferentes formatos (si es xml, que lo indente, si es json que lo indente, si es binario que muestre la imagen). Se puede mostrar la respuesta cruda en un tab, y la respuesta formateada en otro.

## Funcionalidad para mayor puntos

Poder adicionar parámetros de querystring en una tabla de claves-valores dedicada a ello, logrando que se adosen a la url a medida que se tipean (hint: patrón observer)
Cuando se completen parámetros de querystring en la tabla, se debe actualizar la url. Cuando se toque la url se deben actualizar los valores en las tablas
Guardar historial de las urls en las requests hechas recientemente para poder “autocompletar” si se desea repetir alguna.

## Realizado por

Franco Bertorello para la materia Laboratorio I de la Universidad de Palermo. 
