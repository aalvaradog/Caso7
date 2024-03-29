/*
 * SALIDAS DEL PROGRAMA: Al ejecutar el proyecto va a obtener varias salidas:
 *- Las letras que m�s se repiten que se refiere a las letras y n�meros con m�s posibilidades
 * de ser escogidos
 *- La cantidad de veces que esa letra/s n�mero/s aparece en el arreglo
 *- Y los arreglos con todas las letras y n�meros para comprobar que las salidas mencionadas
 * est�n correctas
 * 
 * ESTRATEGIA: El codigo itera 4 veces esto para no pasarse de los 260 intentos que hace la forma
 * exhaustiva, crea 5 grupos de 5 letras y 2 numeros y luego escoge una cantidad aleatoria de
 * grupos para analizar (muestra) y se agregan las letras y n�meros de los grupos que arrojaron
 * resultados favorables a los arreglos de n�meros y letras para aumentar la posibilidad de que sean escogidos
 * en la siguiente iteraci�n, si no se obtiene resultados de la muestra escogida entonces se agrega
 * las letras y n�meros de los grupos que no fueron escogidos a los arreglos de n�meros y letras,
 * esto para que en la siguiente iteracion las letras y n�meros de la muestra que se habia escogido tengan menos 
 * posibilidades de ser escogidos. Luego vuelve a iterar con los arreglos modificados con las letras y n�meros
 * de los resultados obtenidos en la iteraci�n anterior.
 * Cuando terminan las 4 ejecuciones se busca en cada arreglo cual fue el elemento que m�s se repiti� y se retorna
 * la cantidad de veces que se repiti� ese elemento y con base a ese n�mero obtenido se buscan los elementos que se 
 * hayan repetido esa misma cantidad de veces. Luego se imprimen esos resultados.
 */

package caso7;

public class Caso7 {

	public static void main(String[] args) {
		desEncriptar.probabilisticDecrypt();
	}
}