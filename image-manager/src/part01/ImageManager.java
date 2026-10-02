package part01;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * An imageManager class to include a number of system functions such as search methods and displaying all images
 */
public class ImageManager {
	private ArrayList<ImageRecord> images;

	/**
	 * Constructor for the ImageManager class, creates an empty array of ImageRecords
	 */
	public ImageManager() {
		this.images = new ArrayList<ImageRecord>();
	}

	public void addImage(ImageRecord image) {
		images.add(image);

	}

	/**
	 * Searches for an ImageRecord by its own unique image ID
	 * 
	 * @param id the unique integer ID to search for
	 * @return the matching ImageRecord or null if its not found
	 */
	public ImageRecord searchId(int id) {
		for (int index = 0; index < images.size(); index++) {
			ImageRecord image = images.get(index);
			if (image.getId() == (id)) {
				return image;
			}

		}
		return null;
	}
	/**
	 * Searches for an ImageRcord based off the image title
	 * @param str the string that is searched to find the title - doesnt need to be an exact match
	 * @return the matching ImageRecord
	 */
	public ImageAlbum searchTitle(String str) {

		ImageAlbum album = new ImageAlbum();
		for (int index = 0; index < images.size(); index++) {
			ImageRecord image = images.get(index);
			if (image.getTitle().toLowerCase().contains(str)) {
				album.addImage(image);
			}
		}

		return album;
	}
	/**
	 * Searches for an ImageRecord based off the image description
	 * @param str the string that is entered to find the description - doesnt need to be an exact match
	 * @return the corresponding ImageRecord
	 */
	public ImageAlbum searchDescription(String str) {
		ImageAlbum album = new ImageAlbum();
		for (int index = 0; index < images.size(); index++) {
			ImageRecord image = images.get(index);
			if (image.getDescription().toLowerCase().contains(str)) {
				album.addImage(image);
			}
		}
		return album;
	}
	/**
	 * Searches for an ImageRecord based off the ImageType
	 * @param type refers to the type of genre searched for.
	 * @return the corresponding ImageRecord
	 */
	public ImageAlbum searchGenre(ImageType type) {
		ImageAlbum album = new ImageAlbum();
		for (int index = 0; index < images.size(); index++) {
			ImageRecord image = images.get(index);
			if (image.getGenre().equals(type)) {
				album.addImage(image);
			}
		}
		return album;
	}
	/**
	 * Searches for ImageRecords created within a certain time frame
	 * @param start the date at the beginning of the search
	 * @param end the date at the end of the search
	 * @return the matching ImageRecord
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		ImageAlbum album = new ImageAlbum();
		for (int index = 0; index < images.size(); index++) {
			ImageRecord image = images.get(index);
			if ((image.getDateTaken().isAfter(start) || image.getDateTaken().equals(start))
					&& (image.getDateTaken().isBefore(end) || image.getDateTaken().equals(end)))
				album.addImage(image);
		}

		return album;
	}
	/**
	 * uses a for loop to display all images
	 * @return an Image Record, due to the for loop it returns them allD
	 */
	public ImageAlbum getAllImages() {
		ImageAlbum album = new ImageAlbum();
		for (int index = 0; index < images.size(); index++) {
			ImageRecord image = images.get(index);
			album.addImage(image);

		}
		return album;

	}

}
