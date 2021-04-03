The Parking App 
===


## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
An app that helps find a free parking spot in a parking lot. This app will provide navigation to the the free parking space.

### App Evaluation

- **Category:** Navigation 
- **Mobile:** Uses real time naviagation to direct the user to a free parking space. 
- **Story:** Stop trying to find parking and instead get directions to the nearest available parking 
- **Market:** People traveling to big events can now easily find a parking spot instead of blindly searching for one
- **Habit:** Can be used everytime an individual gets into a car
- **Scope:** Start out by making a functional app that maps one or two parking lots. From here can add more parking lots in the future with more integrated UI and features

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User signup/login
* User selects a destination
* Display driving directions to the parking lot
* Display driving directions to the parking space

**Optional Nice-to-have Stories**

* An expanded selection of parking lots that are mapped


### 2. Screen Archetypes

* Splash Screen
   * Shows up everytime user opens the app 
* Login/Registration
   * Login screen
   * Registration screen
* Selection Screen
    * User selects a destination
* Google Maps Screen
    * Display driving directions to the parking lot
* Custom Maps Screen
    * Display driving directions to the parking space

### 3. Navigation


**Flow Navigation** (Screen to Screen)

* Splash Screen
* Login Screen
   * Selection Screen
* Registration Screen
    * Selection Screen
* Goolge API Screen
* Custom Maps Screen 
    * Selection Screen 
## Wireframes
<img src="https://github.com/The-Parking-App/TheParkingApp/blob/master/wirefram.jpg" width=1200>



## Schema 

### Models
#### Parking Space Model 
| **Property** | **Type** | **Description** |
|:------------:|:--------:|:---------------:|
| Parking Lot |   int   | A unique value to identify each parking lot|
| Parking Space | int | The parking space's number|
| Distance from Entrance | double | Is the distance from the main entrance|
| Longitude | double | Describes the longitude cooridnate|
| Lattidude | double | Describes the lattidude coordinate|
| Status | short int | Describes the status of the parking space|
| Type | short int | Describes the type of parking space (Reserved, Handicap, etc)|

#### Parking Lot Model
| **Propery** | **Type** | **Description** |
|:-----------:|:--------:|:---------------:|
|    ID       |   int    | Unique identifier for the parking lot|
| Parking Lot |  string  | The name of the parking lot

#### User Model
| **Propery** | **Type** | **Description** |
|:-----------:|:--------:|:---------------:|
|    ID       |  string  | A unique identifier for the user|
| Username    |  string  | The username of the user inside the app|
| Password    |  string  | The password to user's account|
| Email       |  string  | The email of the user|

### Networking
- Selection of Parking Lots Screen 
  - (Read/GET) Query all selections for parking spaces
      '''
      let query = PFQuery(className:"Post")
  query.whereKey("author", equalTo: currentUser)
  query.order(byDescending: "createdAt")
  query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
     if let error = error { 
        print(error.localizedDescription)
     } else if let posts = posts {
        print("Successfully retrieved \(posts.count) posts.")
    // TODO: Do something with posts...
     }
  }
  '''
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
