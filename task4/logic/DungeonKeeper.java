package by.module5.task4.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import by.module5.task4.entity.Dungeon;
import by.module5.task4.entity.Book;

public class DungeonKeeper {
	private Dungeon dungeon;
	
	public DungeonKeeper() {
		dungeon = new Dungeon("");
	}
	
	public class TreasureSearch {
		private ArrayList<Book> list;
		
		
		public TreasureSearch searchByName(String name) {
			TreasureSearch result = new TreasureSearch();
			result.list = new ArrayList<Book>();
			for (Book book: this.list) {
				if(book.getName().equalsIgnoreCase(name)) {
					result.list.add(book);
				}
			}
			return result;
		}
		
		public TreasureSearch searchByNameSimilar(String name) {
			TreasureSearch result = new TreasureSearch();
			result.list = new ArrayList<Book>();
			if (list.isEmpty()) {
				return result;
			}
			for (Book book: this.list) {
				if(book.getName().contains(name) 
					|| name.contains(book.getName()) ) {
					result.list.add(book);
				}
			}
			return result;
		}
		
		public TreasureSearch searchByPriceLower(double price) {
			TreasureSearch result = new TreasureSearch();
			result.list = new ArrayList<Book>();
			if (list.isEmpty()) {
				return result;
			}
			for (Book book: list) {
				if(book.getPrice() <= price) {
					result.list.add(book);
				}
			}
			return result;
		}
		
		public TreasureSearch searchByPriceHigher(double price) {
			TreasureSearch result = new TreasureSearch();
			result.list = new ArrayList<Book>();
			if (list.isEmpty()) {
				return result;
			}

			System.out.println(price);
			for (Book book: list) {
				if(book.getPrice() >= price) {
					result.list.add(book);
				}
			}
			return result;
		}
		
		public TreasureSearch searchByPriceHighest() {
			TreasureSearch result = new TreasureSearch();
			result.list = new ArrayList<Book>();
			if (list.isEmpty()) {
				return result;
			}
			result.list.add(list.get(0));
			
			for (Book book: list) {
				
				if(book.getPrice() > result.list.get(0).getPrice()) {
					result.list.remove(0);
					result.list.add(book);
				}
			}
			return result;
		}
		
		public TreasureSearch searchByPriceLowest() {
			TreasureSearch result = new TreasureSearch();
			result.list = new ArrayList<Book>();
			if (list.isEmpty()) {
				return result;
			}
			result.list.add(list.get(0));
			for (Book book: this.list) {
				if(book.getPrice() < result.list.get(0).getPrice()) {
					result.list.remove(0);
					result.list.add(book);
				}
			}
			return result;
		}
		
		public TreasureSearch searchByAmount(double amount) {
			double currentAmount = 0;
			boolean enough;
			
			TreasureSearch result = new TreasureSearch();
			result.list = new ArrayList<Book>();
			if (list.isEmpty()) {
				return result;
			}
			while (currentAmount <= amount) {
				enough = true;
				
				for (Book book : list) {
					if (book.getPrice() + currentAmount <= amount 
							&& !result.list.contains(book)) {
						currentAmount += book.getPrice();
						enough = false;
						result.list.add(book);
					}
				}
				if (enough) {
					break;
				}
			}
			return result;
		}
		
		public ArrayList<Book> getResult() {
			return list;
		}
		
	}
	
	public TreasureSearch searchAll() {
		TreasureSearch result = new TreasureSearch();
		result.list = DungeonKeeper.this.dungeon.getList();
		return result;
	}
	
	public void createDungeon(String name) {
		dungeon = new Dungeon(name);
	}
	
	public void createTreasure(String name, double price) {
		dungeon.getList().add(new Book(name, price));
	}
	
	public void openDungeon(String name) throws FileNotFoundException {
		XMLParser parser = new XMLParser();
		try {
			dungeon = parser.parseDungeon(name);
		} catch (FileNotFoundException e) {
			throw e;
		}
	} 
	
	public File saveDungeon() {
		return saveDungeon(dungeon.getName());
	}
	
	public File saveDungeon(String filename) {
		XMLParser parser = new XMLParser();
		return parser.writeDungeon(dungeon, filename);
	}

	public Dungeon getDungeon() {
		return dungeon;
	} 	
	
}
