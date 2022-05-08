# Servicio de sugerencias de ciudades
## Autor: Ing. Macario Galicia Negrete

El servicio se encuentra publicado en [Google Cloud]:  (https://default-1-t6n3y7evza-uc.a.run.app).

Documentación del servico [Swagger]: (https://default-1-t6n3y7evza-uc.a.run.app/swagger-ui/index.html).

---
## HTTP Get

### **Descripcion:** Realiza sugerencia de ciudades de acuerdo a los parametros solicitados
### **Endpoint:** /api/city/suggestions?q=La&latitude=0&longitude=0
### **Parametros** dentro de la URL
- **q** - Coincidencia del nombre de la ciudad a buscar
- **latitud** - Coordena geografica latitud (opcional)
- **longitud** - Coordena geografica longitud (opcional)
---

## Response
~~~
[
  {
    "name": "string",
    "latitude": "string",
    "longitude": "string",
    "score": 0
  }
]
~~~

## Algoritmo que determina la puntuacion de 0 a 1
1. Se obtienen las ciudades que contengan el parametro *q* en su nombre, por defecto se les asigna el valor 1 al atributo *score*
2. Si el nombre de la ciudad no inicia con el paramtro *q*, se le resta 0.5 al atributo *score*
3. Si los paramtros *longitud* y *latitud* se incluyen en la petición, se determina lo siguiente:

~~~
private double factorByCoordinates(float latitude, float originLatitude, float longitude, float originLongitude) {

    double lat = Math.abs(originLatitude > latitude ? originLatitude - latitude : latitude - originLatitude);
    double lon = Math.abs(originLongitude > longitude ? originLongitude - longitude : originLongitude - longitude);

    if (lat < 3 || lon < 3)
        return 0.1;
    if (lat < 5 || lon < 5)
        return 0.2;
    if (lat < 7 || lon < 7)
        return 0.3;
    if (lat < 9 || lon < 9)
        return 0.4;
    return 0;
}
~~~
4. La funcion *factorByCoordinates* determina el valor que se le restara al atributo *score* de acuerdo a la semejanza de las cordenadas de la ciudad y las ingresadas como parámetro de consulta
    