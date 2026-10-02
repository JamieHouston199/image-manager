# image-manager

A Java console application for storing, searching and browsing image records. This was my first-year Object-Oriented Programming coursework at Queen's University Belfast (2025/26).

## What it does

The project is in two parts.

**Part 1** (`part01`) is a text-based system:
- Add image records with a title, description, genre, date and thumbnail filename
- Search by ID, title, description, genre or date range
- Display all images, sorted by date taken
- Input validation, so invalid numbers, empty fields and wrong file types are rejected

**Part 2** (`part02`) builds on part 1 with a windowed console:
- View the images themselves, moving forwards and backwards through an album
- Change the text colour, text size and light or dark mode

## How it's built

- `ImageRecord` holds one image's details and validates them in its setters
- `ImageAlbum` holds a list of records in date order, and keeps track of the current position for next and previous
- `ImageManager` stores all the records and returns search results as an `ImageAlbum`
- `ImageType` is an enum of the genres
- `QUBImages` and `QUBMediaImages` are the main classes for part 1 and part 2

## Running it

Part 1 runs on its own: open the project in Eclipse and run `QUBImages.java`.

Part 2 uses a `Console` class provided by the module, which isn't included in this repository, so it won't compile without it.

## What I'd improve

- The search methods in `ImageManager` repeat the same loop, so I'd combine them into one
- Title and description searches should ignore upper and lower case in the search text, not only in the record
- `selectGenre` uses a long switch statement, which could use the enum's values directly
