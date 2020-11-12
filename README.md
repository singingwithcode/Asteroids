# Asteroids
A runnable Java Game by SingingWithCode

Run from FinalProject.java

To start the program, press 1 after run. 
Use the arrow keys to move the ship. 
Make sure to avoid other shapes as you will loose the game. 
To fire press space bar.

There are features such as:
A Shield button V
A pause button T
A stop button S
A hyperspace feature B


PROGRAMMING:

1)	My Creational Pattern is: Factory Patterns

Factory Patterns seem to be very useful for creating a large set of objects which replaces calling a constructor every time you create a new object in the code. With a factory pattern, we can keep the creation of the object encapsulated and reference the newly created object. This is also a powerful way to scale the program or prepare it to scale especially if you use the Factory Pattern with an interface. Factories do not need to specify the exact class of the object that will be created. Though, for a factory, you will need to have a super class with multiple sub-classes and an input – typically significant input. Real world examples of this would be when you need to create many objects such as: cars, models, serial numbers, bar codes, books, etc.

2)	My Behavioral Pattern is: Observer Patterns

An Observer Pattern is implemented when other objects want to be notified if a state of something has changed. They are best known as listeners. The Observer Pattern is widely used in the Model View Controller (MVC) design because it allows the state to change upon instant user input.  It is the observer objects job to maintain the list of observers and to deliver the state change. Observer patterns could have issues for certain applications that require specific graphic properties. They can leak memory since it requires explicit attaching and detaching – weak reference to observers. 

The Mediator Pattern is very similar to the Observer pattern but is more focused on the collaboration between objects instead of sending announcements to the objects. 

3)	My Structural Pattern is: Proxy Patterns

Proxy patterns allow the authority to act on someone behalf. Because that is so ambiguous, we can apply the proxy pattern to many different scenarios. It is merely a separate object being called to perform something behind the scenes. This could be used for logging certain statistics or outside functions so the overall run is not slowed down by this.  

We see proxies in VPNs which is a tunnel over the internet. Proxies dodge internet traffic by creating this tunnel on the network where the VPN has specific permissions on the network – different from the internet. 

Many students/workers use proxies to dodge blocked sites or tracing statistics. This allows it to appear on the network as a separate domain but really the blocked information is coming in a wrapper. 
