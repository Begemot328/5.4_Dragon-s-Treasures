package by.module5.task4.entity;

import java.util.ArrayList;

public class Dungeon {
	private ArrayList<Book> list;
	String name;
	
	public Dungeon(String name) {
		list = new ArrayList<>();
		this.name = name;
	}

	@Override
	public String toString() {
		String result = "Dungeon [name=" + name + "]";
		for (Book  book: list) {
			result += "\n" + book;
		}
		return result;
	}

	public ArrayList<Book> getList() {
		return list;
	}

	public void setList(ArrayList<Book> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
