You need to add a Person class to hold necessary info about users of our app. What fields and methods would this class hold?
  Extend AbstractEntity for ID field
  firstName
    get/set
  lastName
    get/set
  displayName (for optional anonymous posting of comments/reviews/lists of attendees)
    get/set
  emailAddress
    get/set
  password
    compare?/set
  List<Event> events (many-to-many)
    get/add
    
Would you need to add any additional classes to Person to make the app work? If so, what classes would be necessary?
  Possibly add a UserProfile class
  
What kinds of relationships would Person have to the other classes you already created, such as the Event class?
  ManyToMany with Event
