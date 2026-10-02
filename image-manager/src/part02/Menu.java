package part02;

import java.util.Scanner;

public class Menu {
	private String items[];
	private String title;
	private Scanner input;

	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
	}

	public void display() {
		System.out.println(title);
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}
		System.out.println();
		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}
		System.out.println();
	}

	public int getUserChoice() {

		display();
		while (true) {
			try {
				System.out.print("Enter Selection: ");
				int value = input.nextInt();
				return value;
			} catch (Exception e) {
				System.out.println("Invalid input, please enter a number:");
				input.nextLine();
			}
		}
	}

}
