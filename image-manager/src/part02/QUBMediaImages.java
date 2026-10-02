package part02;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import console.Console;
import java.time.LocalDate;

/**
 * Main application class for the QUB images system
 */
public class QUBMediaImages {
	// Define a constant PROMPT
	static final String PROMPT = "---->";

	// Define a constant SPACES
	static final String SPACES = "     ";
	// String array of menu options
	static final String options[] = { "Add Image", "Search", "Display All", "View Images", "Options", "Exit" };

	// Define a constant QUIT
	static final int QUIT = options.length;

	// use the image manager instance
	static ImageManager manager = new ImageManager();

	// A menu title
	static String title = "QUBImages";

	// Define a menu using title & options

	// create a new array displaying the navigation instructions
	static String navOptions[] = { "Next Image", "Previous Image", "Return to main menu" };

	// create a new array displaying the options
	static String optionsMenu[] = { "Text Colour", "Text Size", "Background Mode", "Return to main menu" };
	
	//create an array displaying the different colours available
	static String textColours[] = { "Black", "White", "Red","Yellow","Blue","Orange","Pink" };
	
	//create an array displaying different font sizes
	static String textSizes[] = { "Small", "Medium", "Large"};

	// Define console
	static Console con = createConsole();
	
	//create an array output method
	/**
	 * I noticed lots of code reusage in regards to array printing
	 * so this is a method that prints and numbers the objects within an array
	 * @param optionsArray the array of options to be displayed to the user
	 */
	private static void displayOptions(String[] optionsArray) {
		for (int index = 0; index < optionsArray.length; index++) {
			con.println((index + 1) + ". " + optionsArray[index]);
	}
	}
	/**
	 * This is a method to allow the user to change the look of the system to how they desire
	 * such as textsize, text colour, background etc.
	 */
	private static void optionSection() {
		boolean menuOpen = true;
		while (menuOpen) {
			displayOptions(optionsMenu);
				switch (readInt(1,4)) {
				case 1: 
					//create a case to change the colour of the text
					con.println("Choose Text colour");
					displayOptions(textColours);
					switch (readInt(1,7)) {
					case 1: con.setColour(Color.BLACK);
					break;
					case 2: con.setColour(Color.WHITE);
					break;
					case 3: con.setColour(Color.RED);
					break;
					case 4: con.setColour(Color.YELLOW);
					break;
					case 5: con.setColour(Color.BLUE);
					break;
					case 6: con.setColour(Color.ORANGE);
					break;
					case 7: con.setColour(Color.PINK);
					break;
					}
					break;
				case 2:	
					//create a case to change the size of the text
					con.println("Choose Text Size");
					displayOptions(textSizes);
					switch(readInt(1,3)) {
					case 1: con.setFont(new Font("Courier",Font.BOLD,12));
					break;
					case 2: con.setFont(new Font("Courier",Font.BOLD,16));
					break;
					case 3: con.setFont(new Font("Courier",Font.BOLD,20));
					break;
					}
					break;
				case 3: 
					//create a case to change the background type
					con.println("Choose Background Mode:");
					con.println("1. Light Mode");
					con.println("2. Dark Mode");
					switch(readInt(1,2)) {
					case 1: con.setBgColour(Color.WHITE); 
							con.setColour(Color.BLACK); 
							con.println("Light mode, text set to Black");
							break;
					case 2: con.setBgColour(Color.BLACK); 
					con.setColour(Color.WHITE); 
					con.setColour(Color.WHITE);
					con.println("Dark mode, text set to white");
					break;
					
					}
					break;
				case 4:
					//create a case to return to main menu
					menuOpen=false;
					}
			}
		}

	/**
	 * This is a method to allow the user to cycle through the different images in the system
	 * @param album refers to the imageAlbum containing all the images that can be viewed.
	 */
	private static void viewImages(ImageAlbum album) {
		//open the 1st image 
		ImageRecord image = album.getFirst();
		boolean imageView = true;
		while (imageView) {
			con.println(new ImageIcon("Images\\" + image.getThumbnail()));
			con.println(image.toString());
			displayOptions(navOptions);
			switch (readInt(1,3)) {
			case 1:
				//case to get the next ImageRecord in an album
				ImageRecord nextImg = album.getNext();
				if (nextImg != null) {
					image = nextImg;

				} else {
					con.println("There is no next image.");
					
				}
				break;
			case 2:
				//case to get the previous ImageRecord in an album
				ImageRecord previousImg = album.getPrevious();
				if (previousImg != null) {
					image = previousImg;
				} else {
					con.println("There is no previous image.");

				}
				break;
			case 3:
				//case to return to the main menu
				imageView = false;
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {
		//load the images
		loadImages();
		int choice;
		do {
			choice = getUserChoice();
			if (choice != QUIT) {
				processChoice(choice);
			}
		} while (choice != QUIT);
		con.println("\nGoodbye! from QUB images");
	}
	/**
	 * gets the user choice 
	 * @return the users choice to the processChoice method
	 */
	private static int getUserChoice() {
		displayOptions(options);
		return readInt(1,QUIT);
	}
	/**
	 * A method used to create the console
	 * @returns the console that is used throughout the system
	 */
	private static Console createConsole() {
		Console con = new Console(true);
		con.setSize(1240, 440);
		con.setVisible(true);
		con.setFont(new Font("Courier", Font.BOLD, 16));
		con.setBgColour(Color.BLACK);
		con.setColour(Color.WHITE);
		return con;
	}

	/**
	 * 
	 * @param min refers to the smallest number that the int can be 
	 * @param max refers to the biggest number that the int can be
	 * @returns the validated int back to whichever method calls it
	 */
	// helper method to read ints - must have the correct validation
	private static int readInt(int min, int max) {
		while (true) {
			try {
				int num =  Integer.parseInt(con.readLn());
				//checks if the integer is within the max and the min value
				if(num>=min && num<=max) {
					return num;
				}else {
					con.println("Please enter a number between "+min+" and "+max +".");
				}
			} catch (Exception e) {
				con.println("Invalid input, please enter a number.");
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
		case 4:
			viewImages(manager.getAllImages());
			break;
		case 5:
			optionSection();
			break;

		default:
			con.println("Option " + choice + " is invalid.");
		}
	}

	/**
	 * a method to allow the user to search for particular attributes of records
	 */

	private static void search() {
		con.println("Please enter the type of search:\n " + "1. Search by ID\n" + "2. Search by Title\n"
				+ "3. Search by Description\n" + "4. Search by Genre\n" + "5. Search by Date Range\n"
				+ "6. Back to main menu");

		int searchNum;
		while (true) {
			try {
				searchNum = readInt(1,6);
				break;
			} catch (Exception e) {
				con.println("Invalid input, please enter a number:");
				con.readLn();
			}
		}
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
			con.println("Returning to main menu");
		}

	}

	/**
	 * a method to allow the user to see all records within a specified time period.
	 */
	private static void searchByDateRange() {
		con.println("Please enter the start date.");
		LocalDate start = getDate();
		con.println("Please enter the end date:");
		LocalDate end = getDate();
		if (end.isBefore(start)) {
			con.println("You have entered the end date to be sooner than the start date, please try again:");
			searchByDateRange();
			return;
		}
		ImageAlbum img = manager.searchDates(start, end);
		ImageRecord image = img.getFirst();
		if (image == null) {
			con.println("Sorry, there is no image assosiated with that time period.");
		} else {
			while (image != null) {
				con.println(image.toString());
				ImageIcon im = new ImageIcon("Images\\" + image.getThumbnail());
				con.println(im);
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
			con.println("Sorry, there is no image associated with that Genre");
		} else {
			while (image != null) {
				con.println(image.toString());
				ImageIcon im = new ImageIcon("Images\\" + image.getThumbnail());
				con.println(im);
				image = img.getNext();
			}
		}

	}

	/**
	 * A method to allow the user to search for an ImageRecord based off certain
	 * words in the description - doesn't need to be an exact match
	 */
	private static void searchByDescription() {

		con.print(PROMPT + "Please enter the image Description: ");
		String description = con.readLn();
		ImageAlbum img = manager.searchDescription(description);
		ImageRecord image = img.getFirst();
		if (image == null) {
			con.println("Sorry, there is no image associated with that Description");
		} else {
			while (image != null) {
				con.println(image.toString());
				ImageIcon im = new ImageIcon("Images\\" + image.getThumbnail());
				con.println(im);
				image = img.getNext();
			}
		}

	}

	/**
	 * a method to allow the user to search for ImageRecords based off their title -
	 * doesnt need to be an exact match
	 */
	private static void searchByTitle() {
		con.print(PROMPT + "Please enter the image Title: ");
		title = con.readLn();
		ImageAlbum img = manager.searchTitle(title);
		ImageRecord image = img.getFirst();
		if (image == null) {
			con.println("Sorry, there is no image associated with that Title");
		} else {
			while (image != null) {
				con.println(image.toString());
				ImageIcon im = new ImageIcon("Images\\" + image.getThumbnail());
				con.println(im);
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
				con.print(PROMPT + "Please enter the image ID: ");
				int id = readInt(1,Integer.MAX_VALUE);
				ImageRecord img = manager.searchId(id);
				if (img == null) {
					con.println("Sorry, there is no image associated with that ID");
				} else {
					con.println(img.toString());
					ImageIcon im = new ImageIcon("Images\\" + img.getThumbnail());
					con.println(im);
				}
				valid = true;
			} catch (Exception e) {
				con.println("Invalid input, please enter a Unique ID Number.");
				con.readLn();
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
		con.println("Please enter a genre:\n " + "1. ASTRONOMY\n" + "2. ARCHITECTURE\n" + "3. SPORT\n"
				+ "4. LANDSCAPE\n" + "5. PORTRAIT\n" + "6. NATURE\n" + "7. AERIAL\n" + "8. FOOD\n" + "9. OTHER");
		ImageType genre = ImageType.OTHER;
		boolean valid = false;
		while (!valid) {
			try {
				int genreNum = readInt(1,9);

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
				valid = true;

			} catch (Exception e) {
				con.println("Invalid input, please enter a number");
				con.readLn();
			}
		}
		return genre;

	}

	/**
	 * A method that allows the user to add their own ImageRecords to QUBImages Uses
	 * appropriate validation to help ensure no bad values are added
	 */
	private static void addImage() {
		con.println("OK - Add a new Image.");
		String title = notNull("Please enter the Title:");
		String description = notNull("Please enter the Description:");
		String thumbnail = "";
		boolean valid = false;
		while (!valid) {
			thumbnail = notNull("Please enter the thumbnail: ");
			if (thumbnail.endsWith(".png") || thumbnail.endsWith(".jpg")) {
				valid = true;
			} else {
				con.println("Please enter a thumbnail that includes either .png or .jpg");
			}
		}

		ImageType genre = selectGenre();
		LocalDate dateTaken = getDate();
		ImageRecord newImage = new ImageRecord(title, description, genre, dateTaken, thumbnail);
		manager.addImage(newImage);

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
			con.print(PROMPT + heading);
			userInput = con.readLn();
			if (userInput.isEmpty()) {
				con.println("This field cannot be left empty, please try again.");
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
			con.print(PROMPT + "Please enter the year:");
			int year = readInt(1900,2100);
			con.print(PROMPT + "Please enter the month:");
			int month = readInt(1,12);
			con.print(PROMPT + "Please enter the day:");
			int day = readInt(1,31);
			LocalDate dateTaken = LocalDate.of(year, month, day);
			return dateTaken;
		} catch (Exception e) {
			con.println("Invalid input, please enter numbers only");
			con.readLn();
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
			con.println("There are no images");
		} else {
			while (image != null) {
				con.println(image.toString());
				ImageIcon im = new ImageIcon("Images\\" + image.getThumbnail());
				con.println(im);
				image = album.getNext();
			}
		}

		con.println();
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
