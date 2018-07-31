## MVC Architecture
Model - View - Controller  
Benefit: Separation of business logic from presentation layer  

## Implementation Details
- Model & Controller
- View - view.java
- Database - DataStore.java
- Entities:
  - User: id, email, password, firstname, lastname, gender, userType
  - Bookmark: id, title, profileUrl
  - Book extends Bookmark: publicationYear, publisher, authors, genre, amazonRating
  - Movie extends Bookmark: releaseYear, cast, directors, genre, imdbRating
  - WebLink extends Bookmark: url, host
  - UserBookmark: user, bookmark

- Manager: Singleton Design Pattern
  - UserManager:
  - BookmarkManager:

- Constants
  - UserType: user, editor, chiefeditor
  - Gender: MALE, FEMAIL
  - BookGenre
  - MovieGenre


Note:
1. In a singleton pattern, need to make constructors private. For a private constructor, we cannot instantiate the class. There is only one object to be cleared.
