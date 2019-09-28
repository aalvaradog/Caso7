package caso7;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class desEncriptar {
	private static SecretKeySpec secretKey;
	private static byte[] key;
	desEncriptar(){	}
	
	 
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    public static void probabilisticDecrypt() {
    	final String TextoEncriptado="xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
		final String PrimerParteLlave="29dh120";
		final String SegundaParteLlave="dk1";
		final String TercerParteLlave="3";
		
		char letras[]= { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r','s', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		char numeros[]= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		
		ArrayList<String> listaLetras=new ArrayList<String>();
		ArrayList<String> listaNum=new ArrayList<String>();
		
		for(int i=0;i<letras.length;i++) {//agrega las letras al arrayList
			listaLetras.add(Character.toString(letras[i]));
		}
		
		for(int i=0;i<numeros.length;i++) {//agrega los numeros al arrayList
			listaNum.add(Character.toString(numeros[i]));
		}
		
		int numEjecuciones=4;
		
		for(int iteraciones=0;iteraciones<numEjecuciones;iteraciones++) {//Sirve para ejecutar una cantidad fija de veces, de manera que con cada corrida vaya alterando las probabilidades.			
			int numeroGrupos=5;
			LinkedList<grupo> listaGrupos=new LinkedList<grupo>();
			LinkedList<Integer> gruposExito=new LinkedList<>();
			//se crean los grupos, la cantidad de grupos que se crean depende de numeroGrupos
			for(int i=0;i<numeroGrupos;i++) {
				grupo nuevoAInsertar=new grupo();
				while(nuevoAInsertar.letrasSeleccionadas.size()<5) {
					nuevoAInsertar.letrasSeleccionadas.add((listaLetras.get((int)(Math.random()*26)).charAt(0)));
				}
				
				while(nuevoAInsertar.numerosSeleccionados.size()<2) {
					nuevoAInsertar.numerosSeleccionados.add(listaNum.get((int)(Math.random()*10)).charAt(0));
				}
				
				listaGrupos.add(nuevoAInsertar);
			}
			
			//se analiza cuales grupos podrian ser utiles, el tamaño de la muestra a analizar depende de cantidadAnalisis
			int cantidadAnalisis=(int)(Math.random()*numeroGrupos);
			for(int i=0;i<cantidadAnalisis;i++) {
				String probar="";
				for(char letra:listaGrupos.get(i).letrasSeleccionadas) {
					for(char num:listaGrupos.get(i).numerosSeleccionados) {
						probar=PrimerParteLlave+letra+SegundaParteLlave+num+TercerParteLlave;			
						String respuesta=desEncriptar.decrypt(TextoEncriptado, probar);
						if(!(respuesta==null)) {
							gruposExito.add(i);
						}
					}
				}
			}
			
			if(gruposExito.size()==0) {
				while(cantidadAnalisis<numeroGrupos) {
					gruposExito.add(cantidadAnalisis);
					cantidadAnalisis++;
				}
			}
			
			//se actualizan los arreglos de letras y numeros con los resultados obtenidos
			for(int i=0;i<gruposExito.size();i++) {			
				Object[] letrasUp=(listaGrupos.get(gruposExito.get(i)).letrasSeleccionadas.toArray());
				Object[] numerosUp=(listaGrupos.get(gruposExito.get(i)).numerosSeleccionados.toArray());
				for(int j=0;j<letrasUp.length;j++) {
					listaLetras.add(Character.toString((char)(letrasUp[j])));//ingresa las letras utiles al arreglo de letras
				}
				for(int j=0;j<numerosUp.length;j++) {
					listaNum.add(Character.toString((char)(numerosUp[j])));//ingresa los numeros utiles al arreglo de numeros
				}
			}		
		}
		
		//Calculo e impresion de los resultados del analisis
		int letraMasRepetida=mayorOcurrencia(listaLetras);//funcion que retorna el número de veces que aparece el elemento que más se repite
		int numeroMasRepetido=mayorOcurrencia(listaNum);
		
		List<String> rankingLetras = listaLetras.stream().collect(Collectors.groupingBy(s -> s)).entrySet().stream().filter(e -> e.getValue().size() > letraMasRepetida).map(e -> e.getKey()).collect(Collectors.toList());
		System.out.print("Letras que más se repiten: ");
		for(String letra : rankingLetras) {
			System.out.print(letra+"	");
		}
		
		System.out.println("");
		System.out.println("Número de veces que se repiten: "+letraMasRepetida);
		System.out.print("Arreglo de letras: ");
		for(int i=0;i<listaLetras.size()-1;i++) {
			System.out.print(listaLetras.get(i)+", ");
		}
		
		System.out.println(listaLetras.get(listaLetras.size()-1));
		
		List<String> rankingNumeros = listaNum.stream().collect(Collectors.groupingBy(s -> s)).entrySet().stream().filter(e -> e.getValue().size() > numeroMasRepetido).map(e -> e.getKey()).collect(Collectors.toList());
		System.out.print("Números que más se repiten: ");
		for(String numero : rankingNumeros) {
			System.out.print(numero+"	");
		}
		
		System.out.println("");
		System.out.println("Número de veces que más se repiten: "+numeroMasRepetido);
		System.out.print("Arreglo de números: ");
		for(int i=0;i<listaNum.size()-1;i++) {
			System.out.print(listaNum.get(i)+", ");
		}
		
		System.out.println(listaNum.get(listaNum.size()-1));
    	
    }
    
	static int mayorOcurrencia(ArrayList<String> a){
	    String mayor = a.get(0);
	    int numRep=ocurrencias(mayor,a)-1;
	    for(int i=1; i<a.size(); i++){
	        if(ocurrencias(mayor,a)<ocurrencias(a.get(i),a))
	        mayor = a.get(i);
	        numRep=ocurrencias(mayor,a)-1;
	    }
	    return numRep;
	}
	 
	static int ocurrencias(String n, ArrayList<String>arr){
	    int cuantos = 0;//
	    for(int i=0; i<arr.size(); i++){
	         if(n.equals(arr.get(i)))   
	            cuantos++;    
	    }
	    return cuantos;
	}
}
