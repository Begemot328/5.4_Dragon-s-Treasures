package by.module5.task4.menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import by.module5.task4.entity.Book;
import by.module5.task4.logic.DungeonKeeper;
import by.module5.task4.logic.DungeonKeeper.TreasureSearch;

public class Controller {
	private final String COMMAND_DESCRIPTION = "Enter COMMAND to DO_SOMETHING";
	private final String COMMAND = "COMMAND";
	private final String DO_SOMETHING = "DO_SOMETHING";
	private final String NO_SUCH_COMMAND = "No such comand";
	private final String DATA_DESCRIPTION = "Enter DATA";
	private final String DATA = "DATA";
	private final String NO_RESULTS = "No treasures found";
	private final String AMOUNT = "amount";
	private final String PRICE = "price";
	private final String NAME = "name";
	private final String FILENAME = "filename";
	private final String WRONG_DATA_TYPE = "Wrong data type!";
	private final String WRONG_COMMAND = "Wrong command!";
	private final String FILE_NOT_FOUND = "File not found!";
	private final String DEFAULT_FILENAME = "default";
	
	
	private DungeonKeeper keeper;
	private Scanner scanner;
	private Menu currentMenu;
	private TreasureSearch result;
	
	public Controller() {
		keeper = new DungeonKeeper();
		scanner = new Scanner(System.in);
		currentMenu = Menu.MAIN_MENU;
	}
	
	public void run() {
		try {
			keeper.openDungeon(DEFAULT_FILENAME);
		} catch (FileNotFoundException e) {
			System.out.println(FILE_NOT_FOUND);
		}
		readCommand();
	}
	
	
	public void readCommand() {
		String command = new String();
		
		while(true) {
			switch (currentMenu) {
			case MAIN_MENU:
				for (MainMenuCommand menuCommand: MainMenuCommand.values()) {
					System.out.println(COMMAND_DESCRIPTION.replace(
										COMMAND, menuCommand.value).replace(
										DO_SOMETHING, menuCommand.name));
					
				}
				break;
			case SEARCH_MENU:
				for (SearchMenuCommand menuCommand: SearchMenuCommand.values()) {
					System.out.println(COMMAND_DESCRIPTION.replace(
										COMMAND, menuCommand.value).replace(
										DO_SOMETHING, menuCommand.name));
				}
				break;
			}
			
			if(scanner.hasNext()) {
				command = scanner.next();
			}
			
			switch (currentMenu) {
			case MAIN_MENU:
				if (MainMenuCommand.getCommand(command) == null) {
					System.out.println(WRONG_COMMAND);
					break;
				}
				execute(MainMenuCommand.getCommand(command));
				break;
			case SEARCH_MENU:
				if (SearchMenuCommand.getCommand(command) == null) {
					System.out.println(WRONG_COMMAND);
					break;
				}
				execute(SearchMenuCommand.getCommand(command));
				break;
			}
		}
	}
	private void execute(SearchMenuCommand command) {
		String searchString;
		double searchDouble;
		
		if (command == null) {
			System.out.println(NO_SUCH_COMMAND);
		}
		switch (command) {
		case SEARCH_BY_AMOUNT:
			searchDouble = (double) readParameter(Type.DOUBLE, AMOUNT);
			result = result.searchByAmount(searchDouble);
			break;
		case SEARCH_BY_NAME:
			searchString = (String) readParameter(Type.STRING, NAME);
			result = result.searchByNameSimilar(searchString);
			break;
		case SEARCH_BY_PRICE_HIGHER:
			searchDouble = (double) readParameter(Type.DOUBLE, PRICE);
			result = result.searchByPriceHigher(searchDouble);
			break;
		case SEARCH_BY_PRICE_HIGHEST:
			result = result.searchByPriceHighest();
			break;
		case SEARCH_BY_PRICE_LOWER:
			searchDouble = (double) readParameter(Type.DOUBLE, PRICE);
			result = result.searchByPriceLower(searchDouble);
			break;
		case SEARCH_BY_PRICE_LOWEST:
			result = result.searchByPriceLowest();
			break;
		case MAIN_MENU:
			goToMenu(Menu.MAIN_MENU);
			return;
		case EXIT:
			System.exit(0);
			break;
		case RESET_SEARCH:
			result = keeper.searchAll();
			break;
		}	
		
		if (result.getResult().isEmpty()) {
			System.out.println(NO_RESULTS);
		} else {
			for (Book book: result.getResult()) {
				System.out.println(book);
			}
		}
		
		readCommand();
	}
	private void goToMenu(Menu menu) {
		currentMenu = menu;
		readCommand();
	}
	private Object readParameter(Type type, String data) {
		
		while (true) {
		System.out.println(DATA_DESCRIPTION.replace(DATA, data));	
		switch (type) {
			case STRING:
				return scanner.next();
			case DOUBLE:
				try {
					if(scanner.hasNext()) {
						return Double.parseDouble(scanner.next());
					}
					
				} catch (NumberFormatException e) {
					System.out.println(WRONG_DATA_TYPE);
				}
				
				if(scanner.hasNextDouble()) {
					return scanner.nextDouble();
				} else {
					System.out.println(WRONG_DATA_TYPE);
					scanner.next();
				}
			}
		}
	}
	private void execute(MainMenuCommand command) {
		String input;
		switch (command) {
		case OPEN:
			input = (String) readParameter(Type.STRING, FILENAME);
			try {
				keeper.openDungeon(input);
			} catch (FileNotFoundException e) {
				System.out.println(FILE_NOT_FOUND);
			}
			break;
		case SAVE:
			input = (String) readParameter(Type.STRING, FILENAME);
			keeper.saveDungeon(input);
			break;
		case EXIT:
			System.exit(0);
			break;
		case SEARCH:
			result = keeper.searchAll();
			goToMenu(Menu.SEARCH_MENU);
			return;
		}
		readCommand();
	}
	
}
