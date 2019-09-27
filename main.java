package caso7;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		final String textoEncriptado="xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
		final String primerParteLlave="29dh120";
		final String segundaParteLlave="dk1";
		final String tercerParteLlave="3";
		LinkedList<grupo> listaGrupos=new LinkedList<grupo>();
		char listaLetras[]= { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			      's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		char listaNum[]= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for(int i=0;i<26;i++) {
			grupo nuevoAInsertar=new grupo();
			while(nuevoAInsertar.letrasSeleccionadas.size()<5) {
				nuevoAInsertar.letrasSeleccionadas.add(listaLetras[(int)(Math.random()*26)]);
			}
			while(nuevoAInsertar.numerosSeleccionados.size()<2) {
				nuevoAInsertar.numerosSeleccionados.add(listaNum[(int)(Math.random()*10)]);
			}
			listaGrupos.add(nuevoAInsertar);
		}
		int contador=0;
		for(int i=0;i<20;i++) {
			String probar="";
			for(char letra:listaGrupos.get(i).letrasSeleccionadas) {
				for(char num:listaGrupos.get(i).numerosSeleccionados) {
					probar=primerParteLlave+letra+segundaParteLlave+num+tercerParteLlave;
					contador++;
					//System.out.println(desEncriptar.decrypt(textoEncriptado, probar));					
					String respuesta=desEncriptar.decrypt(textoEncriptado, probar);
					if(!(respuesta==null)) {
						System.out.println(contador);
						System.out.println(respuesta);
						return;
					}
				}
			}
		}

		
		
	}
	

}