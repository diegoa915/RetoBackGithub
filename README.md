# RetoBackGithu

En el ejercicio se utilizó una base de datos en mysql para la lista de productos.
Cada segmento fue probado con  postman
Para ver la lista de los productos : 
	http://localhost:8080/products

Para selecionar uno de los prodcutos de la lista: 
	http://localhost:8080/products/(numero del id)  ejemplo:  http://localhost:8080/products/1

Luego en el generador se mostrará el producto seleccionado y por consola será generada la factura siguiendo los lineamentos del reto.

Para editar los productos de la lista:

 http://localhost:8080/products/editar
  
 se tendra en cuenta el tiempo permitido
 
 para eliminar  el prodcuto :
 http://localhost:8080/products/eliminar/(numero del id)  ejemplo:  http://localhost:8080/products/eliminar/1
Para almacenar la lista de productos se creo una base de datos con mysql, la cual esta djunta como script para ser generada
