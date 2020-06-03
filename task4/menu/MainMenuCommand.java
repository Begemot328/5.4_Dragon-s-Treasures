package by.module5.task4.menu;

public enum MainMenuCommand {
	OPEN ("1", "open dungeon from file"), 
	SAVE("2", "save dungeon to file"), 
	SEARCH("3", "search in a dungeon"),
	EXIT ("4", "exit to system");	
	
	String value;
	String name;
	
	MainMenuCommand(String value, String name) {
		this.value = value;
		this.name = name;
	}	
	
	static MainMenuCommand getCommand(String name) {
		for (MainMenuCommand menuCommand: MainMenuCommand.values()) {
			if(menuCommand.value.equals(name)) {
				return menuCommand;
			}
		}
		return null;
	}
}
