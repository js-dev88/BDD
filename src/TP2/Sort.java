package TP2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Sort {
	
	private static int[] intArray = new int[10];
	private static int countOfFile = 0;
	
	
	public static String readFile() throws Exception {
		String concat = "";
		//System.out.println(System.getProperty("user.dir"));
		try {
			InputStream flux = new FileInputStream("src/TP2/inpdata.txt");
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			
			while ((ligne = buff.readLine()) != null) {
				concat += ligne;
			}
			buff.close();
			
		} catch (Exception e) {
			throw new Exception();
		}
		return concat;
	}
	
	public static void createTableau(String concat){
		
		
	}

	public static void writeFile(){
		 final String chemin = "src/TP2/outdata.txt";
         final File fichier =new File(chemin); 
		    try {
		        // Creation du fichier
		        fichier .createNewFile();
		        // creation d'un writer (un écrivain)
		        final FileWriter writer = new FileWriter(fichier);
		        try {
		            writer.write("ceci est un texte\n");
		            writer.write("\n");
		            writer.write("test");
		        } finally {
		            // quoiqu'il arrive, on ferme le fichier
		            writer.close();
		        }
		    } catch (Exception e) {
		        System.out.println("Impossible de creer le fichier");
		    }
	
	}
	
	public static void createFile(int[] intArray){
		Arrays.sort(intArray);
		File f = new File("fichier"+countOfFile+".txt");
			try
			{
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(f));
				// normalement si le fichier n'existe pas, il est crée à la racine du
				// projet
				writer.write("test");
	
				writer.close();
			}catch(
			IOException e)
			{
				e.printStackTrace();
			}
			countOfFile++;
			//deleteFile(f);
	}
	
	public static void deleteFile(File f){
		f.delete();
	}


	

}
