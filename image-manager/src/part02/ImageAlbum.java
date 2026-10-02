package part02;

import java.util.ArrayList;
/**
 * A class for the collection of Image Records
 * includes an array of ImageRecords
 */
public class ImageAlbum {
	private ArrayList<ImageRecord> images;
	private int currentPosition;

	/**
	 * Constructor for ImageAlbum, initialises an empty array of ImageRecords
	 */
	public ImageAlbum() {
		images = new ArrayList<ImageRecord>();
		currentPosition = 0;
	}

	/**
	 * A method to return the 1st record in the album
	 * 
	 * @return either the 1st ImageRecord or null if array is empty.
	 */
	public ImageRecord getFirst() {
		if (images.size() == 0) {
			return null;
		} else {
			currentPosition = 0;
			return images.get(currentPosition);
		}
	}

	/**
	 * A method to move onto the next ImageREcord in an album
	 * 
	 * @return either the next ImageRecord in the array, or null if there's no image
	 *         next in the array
	 */
	public ImageRecord getNext() {
		if (currentPosition + 1 < images.size()) {
			currentPosition += 1;
			return images.get(currentPosition);
		} else {
			return null;
		}
	}

	/**
	 * A method to move onto the previous ImageRecord in an album
	 * 
	 * @return either the previous ImageRecord in the array, or null if there's no
	 *         previous ImageRecord in the array
	 */
	public ImageRecord getPrevious() {
		if (currentPosition - 1 >= 0) {
			currentPosition -= 1;
			return images.get(currentPosition);
		} else {
			return null;
		}
	}
	/**
	 * A method that adds an ImageRecord to the array and sorts it within the existing records based off the date
	 * @param image adds an ImageRecord to the arrayList
	 */
	public void addImage(ImageRecord image) {
		for (int index = 0; index < images.size(); index++) {
			if (image.getDateTaken().isBefore(images.get(index).getDateTaken())) {
				images.add(index, image);
				return;
			}
		}
		images.add(image);

	}

}
