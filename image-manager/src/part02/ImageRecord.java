package part02;

import java.time.LocalDate;
/**
 * A class that represents an image record with data headings such as title, description, genre etc.
 */
public class ImageRecord {
	//List of Attributes
	private int iD;
	private static int nextId = 1;
	private String title;
	private String description;
	private ImageType genre;
	private LocalDate dateTaken;
	private String thumbnail;

	/**
	 * The constructor for ImageRecord, initialises all instance variables
	 * @param title displays the image title
	 * @param description displays the image description
	 * @param genre displays the image genre
	 * @param dateTaken displays the image date
	 * @param thumbnail displays the image thumbnail
	 */
	public ImageRecord(String title, String description, ImageType genre, LocalDate dateTaken, String thumbnail) {
		this.iD = nextId++;
		setTitle(title);
		setDescription(description);
		setGenre(genre);
		setDateTaken(dateTaken);
		setThumbnail(thumbnail);
	}
	//Setter Methods
	/**
	 * a method to set the title of the imageRecord
	 * @param title the title to set for the image
	 */
	public void setTitle(String title) {
		if (title != null) {
			this.title = title;
		} else {
			System.out.println("Please enter a title, this field cannot be left blank.");
		}

	}
	/**
	 * a method to set the description of the ImageRecord
	 * @param description the description to set for the image
	 */
	public void setDescription(String description) {
		if (description != null) {
			this.description = description;
		} else {
			System.out.println("Please enter a description of the image, this field cannot be left blank.");
		}
	}
	
	/**
	 * a method to set the genre of the ImageRecord
	 * @param genre for genre to set for the image
	 */
	public void setGenre(ImageType genre) {
		if (genre != null) {
			this.genre = genre;
		} else {
			System.out.println("please enter a genre. This field cannot be left blank");
		}

	}
	/**
	 * a method to set the date of the ImageRecord
	 * @param dateTaken to set for the image
	 */
	public void setDateTaken(LocalDate dateTaken) {
		if (dateTaken != null) {
			this.dateTaken = dateTaken;
		} else {
			System.out.println("Please enter a corresponding date.");
		}
	}
	/**
	 * a method to set the thumbnail of the ImageRecord
	 * @param thumbnail sets the thumbnail of the ImageRecord
	 */
	public void setThumbnail(String thumbnail) {
		if (thumbnail != null && thumbnail.endsWith(".png") || thumbnail.endsWith(".jpg")) {
			this.thumbnail = thumbnail;

		} else {
			System.out.println("Please enter a thumbnail that includes either .png or .jpg");
		}
	}
	
	//Getter methods
	/**
	 * A method to get imageID
	 * @return image ID
	 */
	public int getId() {
		return this.iD;
	}
	/**
	 * A method to get the image title
	 * @return the image title
	 */
	public String getTitle() {

		return this.title;

	}
	/**
	 * A method to get the image description
	 * @return the image description 
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * A method to get the image genre
	 * @return the image genre
	 */
	public ImageType getGenre() {
		return this.genre;

	}
	/**
	 * A method to get the image date
	 * @return the image date 
	 */
	public LocalDate getDateTaken() {
		return this.dateTaken;

	}
	/**
	 * A method to get the image thumbnail
	 * @return the image thumbnail
	 */
	public String getThumbnail() {
		return this.thumbnail;

	}
	/**
	 * A toString method to display the ImageRecord in the console
	 */
	@Override
	public String toString() {
		return "ID: " + this.iD + " | Title: " + this.title + " | Description: " + this.description + " | Genre:  "
				+ this.genre + " | Date " + this.dateTaken + " | Thumbnail: " + this.thumbnail;
	}

}
