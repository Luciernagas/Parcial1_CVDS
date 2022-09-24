<h1 align="center"> 🌷 Parcial primer tercio CVDS 🌷 </h1>
<h2 align="center"> Luisa Valentina De la hoz Rocha </h2>

<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190826274-4193918e-0cd6-4815-82c9-5654c7c014c0.png" height="250px"/></p>
 
* * *

Primero se efectuó el fork al repositorio seleccionado:
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190820233-bbed1995-e6b5-452e-85a7-e73ab57d56c8.png" height="250px"/></p>
Después se le asigno un nombre:
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190825951-cfbfd864-64cf-49f5-88ca-37ab1c45f8a3.png" height="400px"/></p>
Por último podemos ver como este aparece en nuestra cuenta como un repositorio nuevo:
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190822655-2ca804a2-5097-431a-93ce-e490cfe3cde6.png" height="400px"/></p>
  
Posteriormente se ejecuto el git clone:
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190837916-2d5bfb39-4772-4638-a9a0-fd5ba311df0b.png" height="190px"/></p>
  
* * *
### 🐍 Identificación de malas prácticas:
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190838979-6a68cb5c-f60d-4cd8-980a-60a577cbf0d0.png" height="400px"/></p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190839259-3a5a8cc1-be7d-4338-82aa-2257275dc0cc.png" height="300px"/></p>
  
* * *
git push de la solución de las malas practicas

<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190840372-70cff2f9-1be8-4b7f-a9a5-f2481335456c.png" height="350px"/></p>


* * *

### 🐍 Patterns
Patrón creacional: Factory Method

Su fin es crear objetos sin tener que especificar su clase exacta. Esto quiere decir que el objeto creado puede intercambiarse con flexibilidad y facilidad. 

<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/191872665-c783f080-c6ee-4be6-9c2f-682958f5f11f.gif" height="300px"/></p>  
  
𓆙	_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ 
<p align="center"> Diagrama de clases sin aplicar el patrón Factory Method <p>

![image](https://user-images.githubusercontent.com/104604359/191859263-2b5ec900-a605-4bc1-9f1b-1cf0ce848180.png)

![image](https://user-images.githubusercontent.com/104604359/191871859-b2d343f1-1418-45a8-96bc-b9595c076319.png)

𓆙	_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
<p align="center"> Diagrama de clases aplicando el patrón Factory Method <p>

![image](https://user-images.githubusercontent.com/104604359/192072700-ac71913e-6e6f-438a-9161-c839774e7227.png)

![image](https://user-images.githubusercontent.com/104604359/192072785-19d09721-a3db-429e-b24e-3cbb71df4943.png)

𓆙	_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190845120-c4ff3bc4-d114-4e23-bc2e-94628823ce22.png" height="342px"/></p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190845149-f4f46c51-b46f-4ab0-b0b7-3e439563b783.png" height="305px"/></p>


* * *
### 🐍 Unit testing
Identificación de mal uso de:
1. Patrón nombramiento de las pruebas unitarias
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190840724-6f4b4e2b-2b97-4153-9b51-c53235e62f65.png" height="350px"/></p>
  
2. Patrón AAA.
<p align="center"> Estructura  <p>
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190840867-bc50a53f-35cf-4c6b-b6de-4c74ac8659db.png" height="152px"/></p>

<p align="center"> Se hace múltiples afirmaciones(assert) <p> 
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190841530-79ba2931-62e2-4aec-8a4e-25a300bd75df.png" height="296px"/></p>

𓆙	_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
#### Diseño de casos de prueba (Posición):

Input: N y M

Valid: 1 <= N  &  M <= 49

Invalid: N = 50 || M = 50 || M = 0 || N = 0

<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190843138-7a5800f0-8238-4fb2-9381-2ef5f4c41c52.png" height="70px"/></p>
  
![image](https://user-images.githubusercontent.com/104604359/191651588-cbef0cb6-b294-444b-adc5-1aa42e3e7c05.png)

![image](https://user-images.githubusercontent.com/104604359/191651654-68603490-1e6f-4874-8edf-37b7f497e9e8.png)

![image](https://user-images.githubusercontent.com/104604359/191651695-f4fdaf5c-5fa6-41e6-a8d8-fea2c0db3525.png)

* * *
git push de la propuesta de las pruebas
<p align="center">
  <img src="https://user-images.githubusercontent.com/104604359/190844998-c8c240cc-b66b-4f71-97d6-0f157fe6be33.png" /></p>
