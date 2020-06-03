package by.module5.task4.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.module5.task4.entity.Dungeon;
import by.module5.task4.entity.Book;

public class XMLParser {

	public static final String INDENTION = System.getProperty("line.separator");
	public static final String PARENT_NODE = "dungeon";
	public static final String OBJECT_NODE = "treasure";
	public static final String NAME_NODE = "name";
	public static final String PRICE_NODE = "price";
	public static final String NODE_REGEXP = "(<NODE>)(.+?)(</NODE>)";
	public static final String NODE = "NODE";
	public static final String EXTENSION = ".xml";
	public static final String PARENT_TAG = "<TAGNAME>" + INDENTION + "CONTAINER" 
											+ INDENTION + "</TAGNAME>";
	public static final String TAGNAME = "TAGNAME";
	public static final String OBJECT_TAG = "<TAGNAME>CONTAINER</TAGNAME>";
	public static final String TAG_CONTAINER = "CONTAINER";

	public Dungeon parseDungeon(String name) throws FileNotFoundException {
		Pattern pattern;
		Matcher matcher;
		
		Dungeon result = new Dungeon(name);
		File file = new File(name.toLowerCase() + EXTENSION);
		String text = new String();
	
		// читаем из файла
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				text += scan.nextLine() + INDENTION;
			}
		} catch (FileNotFoundException e) {
			throw e;
		}
		
		pattern = Pattern.compile(NODE_REGEXP.replace(NODE, PARENT_NODE), Pattern.DOTALL);
		matcher = pattern.matcher(text);
		if (matcher.find()) {
			result.setList(parseTreasures(matcher.group(2)));
			return result;
		} else {
			return null;
		}
		
	}

	private ArrayList<Book> parseTreasures(String text) {
		Pattern pattern;
		Matcher matcher;
		
		ArrayList<Book> result = new ArrayList<>();
		String next;
		
		pattern = Pattern.compile(NODE_REGEXP.replace(NODE, OBJECT_NODE), Pattern.DOTALL);
		matcher = pattern.matcher(text);
		
		while(matcher.find()) {
			result.add(parseTreasure(matcher.group(2)));
		}
		return result;
	}

	private Book parseTreasure(String text) {
		Pattern pattern;
		Matcher matcher;
		
		String name;
		double price;
		Book result;
		
		pattern = Pattern.compile(NODE_REGEXP.replace(NODE, NAME_NODE), Pattern.DOTALL);
		matcher = pattern.matcher(text);
		if (matcher.find()) {
			name = matcher.group(2);
		} else {
			return null;
		}
		
		pattern = Pattern.compile(NODE_REGEXP.replace(NODE, PRICE_NODE), Pattern.DOTALL);
		matcher = pattern.matcher(text);
		if (matcher.find()) {
			price = Double.parseDouble(matcher.group(2));
		} else {
			return null;
		}	
		return new Book(name, price);
	}
	
	public File writeDungeon(Dungeon dungeon, String name) {
		FileWriter writer;
		File file = new File(name + EXTENSION);
		
		try {
			writer = new FileWriter(file);
			writer.write(writeToXML(dungeon));
			
			System.out.println(writeToXML(dungeon));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	private String writeToXML(Dungeon dungeon) {
		String result;
		result = PARENT_TAG.replace(TAGNAME, PARENT_NODE).replace(TAG_CONTAINER, 
				writeToXML(dungeon.getList()));
		return result;
	}

	private String writeToXML(ArrayList<Book> list) {
		String result = new String();
		for (Book book: list) {
			result += writeToXML(book) + INDENTION;
		}
		return result;
	}

	private String writeToXML(Book book) {
		String result;
		String container;
		
		container = OBJECT_TAG.replace(TAGNAME, NAME_NODE).replace(TAG_CONTAINER,
					book.getName());
		container += INDENTION;
		container += OBJECT_TAG.replace(TAGNAME, PRICE_NODE).replace(TAG_CONTAINER, 
						Double.toString(book.getPrice()));
		result = PARENT_TAG.replace(TAGNAME, OBJECT_NODE).replace(TAG_CONTAINER, container);
		return result;
	}
}
