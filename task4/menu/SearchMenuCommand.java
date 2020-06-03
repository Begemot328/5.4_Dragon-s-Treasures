package by.module5.task4.menu;

public enum SearchMenuCommand {
	RESET_SEARCH ("1", "start new search"),
	SEARCH_BY_NAME ("2", "search by name"), 
	SEARCH_BY_PRICE_HIGHER ("3", "search by price higher"), 
	SEARCH_BY_PRICE_LOWER ("4", "search by price lower"), 
	SEARCH_BY_PRICE_HIGHEST ("5", "search by price highest"), 
	SEARCH_BY_PRICE_LOWEST("6", "search by price lowest"), 
	SEARCH_BY_AMOUNT ("7", "search by money amount"),
	MAIN_MENU ("8", "exit to main menu"),
	EXIT ("9", "exit to system");
	
	String value;
	String name;
	
	SearchMenuCommand(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	static SearchMenuCommand getCommand(String name) {
		for (SearchMenuCommand menuCommand: SearchMenuCommand.values()) {
			if(menuCommand.value.equals(name)) {
				return menuCommand;
			}
		}
		return null;
	}
}
