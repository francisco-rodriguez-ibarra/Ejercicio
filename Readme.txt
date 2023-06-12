Para ejecutar el proyecto
-Tener instalado java 11
-Bajar codigo fuente y ejecularlo en intelliji.
-ejecutar el proyecto.

/*Deescripcion*/
El proyecto esta desarrllado en java 11 y spring boot.
Su arquitectura esta de la siguiente forma:
-controler: Donde se encuantran los servicios o peticiones rest.
-config: Donde se encuentra la coniguracion que levanta spring boot, ya que en su gran mayoria se ejecuta mediante notaciones.
-calculation: Donde se encuentra la clase de negocio, la cual realiza los calculos pertinentes.
-dto: objeto que viaha entre capas y son los objetos que se manejan como entrada de datos.
-error: se ecuentrar excepcion custom para su manejo.
-factory: objeto que realiza la collecion y llenado de los objetos para su utilizacion.
-filter: donde se encuentra la clase la cual utiliza un filtro para poder obtener los valores que viajan en el header y hacer la validaciones pertinentes.
-model: donde se encuentran los modelos de datos.
-servide: capa donde se ecuentran ,los servicios que interactuan como intermediario entre controller y capa de datos.

Adicional, la aplicacion usa beans que se crean y se implementan dentro del contexto de la aplicacion, la cual facilita la otencion de dichos bean's desde otra clase-

/*Datos de entrada*/
Se envia un json como dato de entrada:
{
    "id":"CA", 
    "KM":30,
    "amount":1500,
    "estimateType":"NORMAL"
}

/*Datos de respues*/
Se envia un json como dato de respuesta:
{
    "date": "2023-06-12T4:35:15-0600",
    "amount": 1752.75
}

adicionalk dentro de header se tiene que incluir "ip-client" , para las validaciones correspondientes
