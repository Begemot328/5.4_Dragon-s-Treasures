package by.module5.task4;

import java.util.Calendar;
import java.util.Locale;

import by.module5.task4.logic.DungeonKeeper;
import by.module5.task4.menu.Controller;

/*  @author Yury Zmushko

 * Задача 4.
Создать консольное приложение, удовлетворяющее следующим требованиям:
• Приложение должно быть объектно-, а не процедурно-ориентированным.
• Каждый класс должен иметь отражающее смысл название и информативный состав.
• Наследование должно применяться только тогда, когда это имеет смысл.
• При кодировании должны быть использованы соглашения об оформлении кода java code convention.
• Классы должны быть грамотно разложены по пакетам.
• Консольное меню должно быть минимальным.
• Для хранения данных можно использовать файлы.
Дракон и его сокровища. Создать программу, позволяющую обрабатывать сведения о 100 сокровищах в пещере
дракона. Реализовать возможность просмотра сокровищ, выбора самого дорогого по стоимости сокровища и
выбора сокровищ на заданную сумму.
 */

public class Runner {
	
	public static void main(String[] args) {
		/* DungeonKeeper keeper = new DungeonKeeper();
		
		keeper.openDungeon("text");

		System.out.println("result");
		System.out.println(keeper.getDungeon());
		keeper.getDungeon().setName("dung");
		keeper.saveDungeon();
		System.out.println("saved"); */
		
		Controller controller = new Controller();
		
		controller.run();
	}	
}


