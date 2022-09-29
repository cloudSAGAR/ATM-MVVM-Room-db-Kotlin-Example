# Android’s Room in Kotlin ft. MVVM Architecture and Coroutines

# hi team, this is SAGAR RAVAL : thats the code for learning purpose ..

**KOTLIN**

**MVVM**

**JETPACK**

**Custom Recyclerview**

**Room Database:** Database layer on top of SQLite database that takes care of mundane tasks that you used to handle with an SQLiteOpenHelper. Database holder that serves as an access point to the underlying SQLite database. The Room database uses the DAO to issue queries to the SQLite database.

**Entity:** When working with Architecture Components, this is an annotated class that describes a database table.

**SQLite database:** On the device, data is stored in an SQLite database. For simplicity, additional storage options, such as a web server, are omitted. The Room persistence library creates and maintains this database for you.

**DAO:** Data access object. A mapping of SQL queries to functions. You used to have to define these painstakingly in your SQLiteOpenHelper class. When you use a DAO, you call the methods, and Room takes care of the rest.

**Kotlin:** Kotlin is an open-source, statically-typed programming language that supports both object-oriented and functional programming. Kotlin provides similar syntax and concepts from other languages, including C#, Java, and Scala, among many others. Kotlin does not aim to be unique — instead, it draws inspiration from decades of language development. It exists in variants that target the JVM (Kotlin/JVM), JavaScript (Kotlin/JS), and native code (Kotlin/Native).

**ViewModel:** Provides data to the UI. Acts as a communication center between the Repository and the UI. Hides where the data originates from the UI. ViewModel instances survive configuration changes.

**LiveData:** A data holder class that can be observed. Always holds/caches latest version of data. Notifies its observers when the data has changed. LiveData is lifecycle aware. UI components just observe relevant data and don’t stop or resume observation. LiveData automatically manages all of this since it’s aware of the relevant lifecycle status changes while observing.

**Repository:** A class that you create, for example using the WordRepository class. You use the Repository for managing multiple data sources.


## **Conclusion**

Hopefully this guide introduced you to a lesser known yet useful form of Android application data storage called ROOM with Kotlin and MVVM.

I hope it will help you too.

KEEP CODING.. Peace