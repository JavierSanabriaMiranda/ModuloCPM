# Horrotel

<img width="814" alt="Untitled" src="https://github.com/JavierSanabriaMiranda/ModuloCPM/assets/150610049/5e7dfc3b-bcf9-485b-984f-7c7a93458b83">


## Contexto del Proyecto

Repositorio para proyecto final de asignatura de Comunicación Persona Máquina

El proyecto consiste en el desarrollo de una aplicación de reserva de hoteles con interfaz gráfica realizada por medio de "javax.swing"

La finalidad de este proyecto es hacer una interfaz gráfica de usuario para aplicaciones Java con las siguientes características:

- Accesibilidad para todo tipo de usuarios
- Internacionalización (i18n)
- Menú de Ayuda
- Robustez
- Redimensión de ventanas

La interfaz gráfica guía al usuario por medio de las distintas funcionalidades de la aplicación y
le muestra sólo la información que le resulte relevante.

A su vez la aplicación, en caso de necesidad, dispone de una ayuda desarrollada con JavaHelp
que explicará al usuario con 5 HTMLs las funcionalidades básicas de la aplicación

El plazo asignado para el desarrollo del programa fue de 3 semanas.

## Contexto de la Aplicación

La aplicación simula un sistema de reserva de hoteles cuya particularidad es que son castillos encantados. Cada castillo dispone de varias características como pueden ser los tipos de encantamientos del mismo, el precio por habitación o la ubicación.

Esta aplicación, además del sistema de reserva de castillos, dispone de un juego opcional que, en caso de superar, dará al usuario la opción de obtener un descuento para alguna de sus próximas reservas. Este descuento se guardará por medio de su DNI. 

## Reserva de Castillos

<img width="818" alt="Untitled 1" src="https://github.com/JavierSanabriaMiranda/ModuloCPM/assets/150610049/325ce2b5-88b6-4149-8fe0-da28bb4e1e79">


En el apartado de reserva de castillos la aplicación muestra una sección de búsqueda de castillos por medio de los siguientes filtros:

- Ubicación
- Precio
- Encantamientos del castillo

A su vez dispone de una barra de búsqueda que permite, por medio del nombre exacto del hotel, buscarlo de manera directa.

Una vez encontrado el castillo deseado, se muestra una sección con la información completa del mismo permitiendo en ella introducir los datos de la reserva para posteriormente introducir los datos personales y realizarla. Durante esta fase se permite el uso o no de descuento (en caso de haberlo conseguido previamente en el juego)

Tras la realización de una reserva, se puede acceder desde la ventana de búsqueda de castillos al apartado “Mis Reservas” desde el cual se puede consultar la información de todas y cada una de las reservas asociadas a un DNI concreto, además de cancelar aquellas que se deseen.

## Juego

<img width="772" alt="Untitled 2" src="https://github.com/JavierSanabriaMiranda/ModuloCPM/assets/150610049/0013b99f-1d36-48c7-ac7f-40e98d3c8c7b">


En el apartado del juego se le da la posibilidad al usuario de obtener un descuento para su reserva. Los dos posibles descuentos son del 10% y del 25%, dependiendo del desempeño del usuario en el juego.

Las reglas del juego están explicadas en la propia aplicación pero se resumen en un tablero lleno de fantasmas y un dado que marca el número de casillas a desplazarse. En función de los fantasmas eliminados en el tablero, el usuario obtendrá o no descuento y, en caso de obtenerlo, será mayor o menor en función del mismo parámetro.

En caso de haber obtenido descuento este se podrá guardar mediante un DNI, el cual se debe aportar también en el momento de la reserva.

## Detalles y Limitaciones

- La barra de búsqueda de hoteles funciona únicamente introduciendo el nombre completo del hotel que se quiere buscar
- Por motivos de tiempo la ayuda ha sido implementada únicamente en español
- No se realiza comprobación de formato en el DNI (Se puede introducir cualquier combinación de caracteres)
