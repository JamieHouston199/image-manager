package part01;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Main application class for the QUB images system
 */
public class QUBImages {
	// Define a constant PROMPT
	static final String PROMPT = "---->";

	// Define a constant SPACES
	static final String SPACES = "     ";
	// String array of menu options
	static final String options[] = { "Add Image", "Search", "Display All", "Exit" };

	// Define a constant QUIT
	static final int QUIT = options.length;

	// use the image manager instance
	static ImageManager manager = new ImageManager();

	// A menu title
	static String title = "QUBImages";

	// Define a menu using title & options
	static Menu myMenu = new Menu(title, options);

	// Define a Scanner
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		loadImages();
		int choice;
		do {
			choice = myMenu.getUserChoice();
			if (choice != QUIT) {
				processChoice(choice);
			}
		} while (choice != QUIT);
		System.out.println("\nGoodbye! from QUB images");
	}

	// upon finalising part 2 i realised that i need to create a way to validate the
	// range of integers
	// create a method to prevent false input of numbers.
	private static int readInt(int min, int max) {
		while (true) {
			try {
				int num = input.nextInt();
				input.nextLine();// clears the scanner
				if (num >= min && num <= max) {
					return num;
				} else {
					System.out.println("Please enter a number between " + min + " and " + max + ".");
				}
			} catch (Exception e) {
				System.out.println("Invalid input, please enter a number.");
				input.nextLine();
			}
		}
	}

	/**
	 * a method to allow the user to enter their desired choice
	 * 
	 * @param choice the integer representing the users menu selection
	 */
	private static void processChoice(int choice) {

		switch (choice) {
		case 1:
			addImage();
			break;
		case 2:
			search();
			break;
		case 3:
			displayAll();
			break;

		default:
			System.out.println("Option " + choice + " is invalid.");
		}
		System.out.println();
	}

	/**
	 * a method to allow the user to search for particular attributes of records
	 */

	private static void search() {
		System.out.println("Please enter the type of search:\n " + "1. Search by ID\n" + "2. Search by Title\n"
				+ "3. Search by Description\n" + "4. Search by Genre\n" + "5. Search by Date Range\n"
				+ "6. Back to main menu");

		int searchNum = readInt(1, 6);
		switch (searchNum) {
		case 1:
			searchById();
			break;
		case 2:
			searchByTitle();
			break;
		case 3:
			searchByDescription();
			break;
		case 4:
			searchByGenre();
			break;
		case 5:
			searchByDateRange();
			break;
		case 6:
			System.out.println("Returning to main menu");
		}

	}

	/**
	 * a method to allow the user to see all records within a specified time period.
	 */
	private static void searchByDateRange() {
		System.out.println("Please enter the start date.");
		LocalDate start = getDate();
		System.out.println("Please enter the end date:");
		LocalDate end = getDate();
		ImageAlbum img = manager.searchDates(start, end);
		ImageRecord image = img.getFirst();
		if (image == null) {
			System.out.println("Sorry, there is no image assosiated with that time period.");
		} else {
			while (image != null) {
				System.out.println(image.toString());
				image = img.getNext();
			}
		}

	}

	/**
	 * a method to allow the user to search ImageRecords based off their particular
	 * genre
	 */
	private static void searchByGenre() {
		ImageType genre = selectGenre();
		ImageAlbum img = manager.searchGenre(genre);
		ImageRecord image = img.getFirst();
		if (image == null) {
			System.out.println("Sorry, there is no image associated with that Genre");
		} else {
			while (image != null) {
				System.out.println(image.toString());
				image = img.getNext();
			}
		}

	}

	/**
	 * A method to allow the user to search for an ImageRecord based off certain
	 * words in the description - doesn't need to be an exact match
	 */
	private static void searchByDescription() {

		System.out.print(PROMPT + "Please enter the image Description: ");
		String description = input.nextLine();
		ImageAlbum img = manager.searchDescription(description);
		ImageRecord image = img.getFirst();
		if (image == null) {
			System.out.println("Sorry, there is no image associated with that Description");
		} else {
			while (image != null) {
				System.out.println(image.toString());
				image = img.getNext();
			}
		}

	}

	/**
	 * a method to allow the user to search for ImageRecords based off their title -
	 * doesnt need to be an exact match
	 */
	private static void searchByTitle() {
		System.out.print(PROMPT + "Please enter the image Title: ");
		title = input.nextLine();
		ImageAlbum img = manager.searchTitle(title);
		ImageRecord image = img.getFirst();
		if (image == null) {
			System.out.println("Sorry, there is no image associated with that Title");
		} else {
			while (image != null) {
				System.out.println(image.toString());
				image = img.getNext();
			}
		}

	}

	/**
	 * a method to allow the user to search an ImageRecord based off its own unique
	 * ImageID
	 */
	private static void searchById() {
		boolean valid = false;
		while (!valid) {
			try {
				System.out.print(PROMPT + "Please enter the image ID: ");
				int id = readInt(1,Integer.MAX_VALUE);
				ImageRecord img = manager.searchId(id);
				if (img == null) {
					System.out.println("Sorry, there is no image associated with that ID");
				} else {
					System.out.println(img.toString());
				}
				valid = true;
			} catch (Exception e) {
				System.out.println("Invalid input, please enter a Unique ID Number.");
				input.nextLine();
			}
		}

	}

	/**
	 * A method which allows the user to select their the genre of ImageRecords by
	 * entering an integer
	 * 
	 * @return the corresponding genre to whichever method calls selectGenre
	 */
	private static ImageType selectGenre() {
		System.out.println("Please enter a genre:\n " + "1. ASTRONOMY\n" + "2. ARCHITECTURE\n" + "3. SPORT\n"
				+ "4. LANDSCAPE\n" + "5. PORTRAIT\n" + "6. NATURE\n" + "7. AERIAL\n" + "8. FOOD\n" + "9. OTHER");
		ImageType genre = ImageType.OTHER;
		int genreNum = readInt(1, 9);
		switch (genreNum) {
		case 1:
			genre = ImageType.ASTRONOMY;
			break;
		case 2:
			genre = ImageType.ARCHITECTURE;
			break;
		case 3:
			genre = ImageType.SPORT;
			break;
		case 4:
			genre = ImageType.LANDSCAPE;
			break;
		case 5:
			genre = ImageType.PORTRAIT;
			break;
		case 6:
			genre = ImageType.NATURE;
			break;
		case 7:
			genre = ImageType.AERIAL;
			break;
		case 8:
			genre = ImageType.FOOD;
			break;
		case 9:
			genre = ImageType.OTHER;
			break;
		default:
			genre = ImageType.OTHER;
		}
		return genre;
	}

	/**
	 * A method that allows the user to add their own ImageRecords to QUBImages Uses
	 * appropriate validation to help ensure no bad values are added
	 */
	private static void addImage() {
		System.out.println("OK - Add a new Image.");
		String title = notNull("Please enter the Title:");
		String description = notNull("Please enter the Description:");
		String thumbnail = "";
		boolean valid = false;
		while (!valid) {
			thumbnail = notNull("Please enter the thumbnail: ");
			if (thumbnail.endsWith(".png") || thumbnail.endsWith(".jpg")) {
				valid = true;
			} else {
				System.out.println("Please enter a thumbnail that includes either .png or .jpg");
			}
		}

		ImageType genre = selectGenre();
		LocalDate dateTaken = getDate();
		ImageRecord newImage = new ImageRecord(title, description, genre, dateTaken, thumbnail);
		manager.addImage(newImage);

		input.nextLine();

	}

	/**
	 * A method to check if a particular String is empty or not, and forces a valid
	 * input
	 * 
	 * @param heading refers to the string being checked e.g. title, description
	 * @return a non empty string to whichever method calls notNull
	 */
	private static String notNull(String heading) {
		String userInput = "";
		while (userInput.isEmpty()) {
			System.out.print(PROMPT + heading);
			userInput = input.nextLine();
			if (userInput.isEmpty()) {
				System.out.println("This field cannot be left empty, please try again.");
			}
		}
		return userInput;
	}

	/**
	 * a method used to get a date from the user
	 * 
	 * @return the year in the localDate format
	 */
	private static LocalDate getDate() {

		try {
			System.out.print(PROMPT + "Please enter the year:");
			int year = readInt(1900,2100);
			System.out.print(PROMPT + "Please enter the month:");
			int month = readInt(1,12);
			System.out.print(PROMPT + "Please enter the day:");
			int day = readInt(1,31);
			LocalDate dateTaken = LocalDate.of(year, month, day);
			return dateTaken;
		} catch (Exception e) {
			System.out.println("Invalid input, please enter numbers only");
			input.nextLine();
			return getDate();
		}

	}

	/**
	 * a method that returns all ImageRecords for the user
	 */
	private static void displayAll() {
		ImageAlbum album = manager.getAllImages();
		ImageRecord image = album.getFirst();
		if (image == null) {
			System.out.println("There are no images");
		} else {
			while (image != null) {
				System.out.println(image.toString());
				image = album.getNext();
			}
		}

		System.out.println();
	}

	/**
	 * A method that loads the base 9 images
	 */
	private static void loadImages() {
		ImageRecord image1 = new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.", ImageType.ASTRONOMY,
				LocalDate.of(2023, 01, 01), "Andromeda.png");
		manager.addImage(image1);
		ImageRecord image2 = new ImageRecord("Lanton QUB", "An image of the QUB Lanyon building.",
				ImageType.ARCHITECTURE, LocalDate.of(2023, 02, 01), "LanyonQUB.png");
		manager.addImage(image2);
		ImageRecord image3 = new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.",
				ImageType.SPORT, LocalDate.of(2023, 03, 01), "KermitGolf.png");
		manager.addImage(image3);
		ImageRecord image4 = new ImageRecord("Mourne Mountains", "A panoramic view of the mourne mountains.",
				ImageType.LANDSCAPE, LocalDate.of(2023, 04, 01), "Mournes.png");
		manager.addImage(image4);
		ImageRecord image5 = new ImageRecord("Homer Simpson", "Homer Simpson- A portrait of the man.",
				ImageType.PORTRAIT, LocalDate.of(2023, 03, 01), "Homer.png");
		manager.addImage(image5);
		ImageRecord image6 = new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.", ImageType.NATURE,
				LocalDate.of(2023, 04, 01), "RedKite.png");
		manager.addImage(image6);
		ImageRecord image7 = new ImageRecord("Central Park", "An overhead view of Central Park New York USA.",
				ImageType.AERIAL, LocalDate.of(2023, 05, 01), "CentralPark.png");
		manager.addImage(image7);
		ImageRecord image8 = new ImageRecord("Apples", "A bunch of apples.", ImageType.FOOD, LocalDate.of(2023, 06, 01),
				"Apples.png");
		manager.addImage(image8);
		ImageRecord image9 = new ImageRecord("Programming Meme", "A Chat GPT programming meme.", ImageType.OTHER,
				LocalDate.of(2023, 07, 01), "ChatGPT.png");
		manager.addImage(image9);

	}

}
