package caso7;

import java.util.LinkedList;
public class main {

	public static void main(String[] args) {
		final String TextoEncriptado="xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
		final String PrimerParteLlave="29dh120";
		final String SegundaParteLlave="dk1";
		final String TercerParteLlave="3";
		LinkedList<grupo> listaGrupos=new LinkedList<grupo>();
		char listaLetras[]= { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			      's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		char listaNum[]= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		LinkedList<Integer> gruposExito=new LinkedList<>();		
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
		for(int i=0;i<20;i++) {
			String probar="";
			for(char letra:listaGrupos.get(i).letrasSeleccionadas) {
				for(char num:listaGrupos.get(i).numerosSeleccionados) {
					probar=primerParteLlave+letra+segundaParteLlave+num+tercerParteLlave;			
					String respuesta=desEncriptar.decrypt(textoEncriptado, probar);
					if(!(respuesta==null)) {
						gruposExito.add(i);
						
						
					}
				}
			}
		}		
		System.out.println(gruposExito.toString());//Indices de los grupos útiles
		grupo union=new grupo();
		for(int i=0;i<gruposExito.size();i++) {			
			System.out.println(listaGrupos.get(gruposExito.get(i)).letrasSeleccionadas.toString());
			System.out.println(listaGrupos.get(gruposExito.get(i)).numerosSeleccionados.toString());
		}	
		
	}

}
