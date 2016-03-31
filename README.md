•Tools and technologies used:
Java, Java Beans, GUI using Java Swing, Netbeans IDE.

•This project demonstrates a clock bean in action. 

•The Clock Bean can display time in either analog or digital mode. 

•The Clock Bean inherits from the Canvas class, which derives from the Component class. When the Clock is in analog mode, the hour, minute, and second hands sweep an appropriate amount as seconds tick away. When it’s in digital mode, the number displayed updates with each passing second. 

•To update once per second, the Bean implements the Runnable interface, which gives it a run() method executing within a thread that triggers an update every second. 

•The advantage of building a clock bean is that the properties of the clock component can be controlled using simple setter and getter methods.

•Once, the clock bean component is dragged from the palette into the JFrame window, the clock bean can be seen ticking.

•The properties used for the Clock Bean component in the project were: mode(analog/digital), raised(3D-effect), background and foreground color. The user can manage and change these properties from the palette itself.  
