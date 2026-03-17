# VOI Desktop Application

Java Swing desktop application for managing Vehicles of Interest.

## Features
- Dashboard screen
- Vehicles of Interest management
- Reasons for Interest management
- Makes and Models management
- Add / Edit / Delete functionality
- Apache Derby database integration

## Technologies
- Java
- Java Swing
- JDBC
- Apache Derby
- NetBeans

## How to Run
1. Clone this repository.
2. Open the project in Apache NetBeans.
3. Make sure the Derby libraries are available in the project:
   - `derby.jar`
   - `derbyclient.jar`
4. Check the database path in `VehiclesOfInterestController.java`.
5. Run the project from NetBeans.

## Notes
This application uses a local Apache Derby database.  
If running on another machine, update the database path in `VehiclesOfInterestController.java` to match your local setup.

## Author
Sameen Shahbaz
