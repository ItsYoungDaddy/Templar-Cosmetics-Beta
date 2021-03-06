package tae.cosmetics.util;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import tae.cosmetics.exceptions.TAEModException;

public class FileHelper {
	
	private static final String dir = "TemplarCosmetics";
	
	static {
		
		new File(dir).mkdir();
	
	}
	
	public static void createFile(String fileName) {
		File file = new File(dir+"/"+fileName);
		try {
			file.createNewFile();
		} catch (Exception e) {
			new TAEModException(FileHelper.class, "Couldn't create " + fileName + " : " + e.getMessage()).post();
		}
		
	}
	
	public static void overwriteFile(String fileName, String toWrite) {
		File file = new File(dir+"/"+fileName);
		
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(toWrite);
			writer.close();
		} catch (Exception e) {
			new TAEModException(FileHelper.class, "Couldn't write " + fileName + " : " + e.getMessage()).post();

		}
	}
	
	public static void writeFile(String fileName, String toWrite) {
		StringBuilder builder = new StringBuilder();
		for(String s : readFile(fileName)) {
			builder.append(s + "\n");
		}
		builder.append(toWrite);
		
		File file = new File(dir+"/"+fileName);
		
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(builder.toString());
			writer.close();
		} catch (Exception e) {
			new TAEModException(FileHelper.class, "Couldn't write " + fileName + " : " + e.getMessage()).post();

		}
	}
	
	public static String readRaw(String fileName) {
		
		File file = new File(dir+"/"+fileName);
		
		try {
			Scanner scanner = new Scanner(file);
			
			StringBuilder builder = new StringBuilder();
			
			while(scanner.hasNextLine()) {
				builder.append(scanner.nextLine());
			}
			
			scanner.close();
			
			return builder.toString();
			
		} catch (Exception e) {
			new TAEModException(FileHelper.class, "Couldn't read " + fileName + " : " + e.getMessage()).post();
			return "";
		}
		
	}
	
	public static ArrayList<String> readFile(String fileName){		
		
		File file = new File(dir+"/"+fileName);
		
		try {
			Scanner scanner = new Scanner(file);
			
			ArrayList<String> toReturn = new ArrayList<>();
			
			while(scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				if(!nextLine.isEmpty()) {
					toReturn.add(nextLine);
				}
			}
			
			scanner.close();
			
			return toReturn;
			
		} catch (Exception e) {
			new TAEModException(FileHelper.class, "Couldn't read " + fileName + " : " + e.getMessage()).post();
			return new ArrayList<String>();
		}
		
	}
	
	public static String getDir() {
		return dir;
	}
	
}
