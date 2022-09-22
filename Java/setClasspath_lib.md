### set class path of java lib on command line
```java -cp "CobaMain.jar;lib\*" com.coba.o.main.CobaMain``` \
```java``` cmd line to call java.exe from environtment path \
```-cp``` mean class path \
```"CobaMain.jar``` java program which will run \
```;lib\*"``` semicolon is for separator between java program name directory, ```lib\``` is directory where java lib is exist and ```*``` is load all jar file on ```lib\``` directory \
```com.coba.o.main.CobaMain``` is a class with ```main(String[] args)``` method that will be running

### change class path of java lib on manifest file
open/extract executeable jar file then open ```MANIFEST``` file on ```META-INF``` directory in jar.\
```MANIFEST``` file content consist of :
      
      Manifest-Version: 1.0
      Class-Path: . lib/json-20180130.jar
      Main-Class: com.coba.o.main.CobaMain

then change the ```Class-Path``` according to directory of library, then replace ```MANIFEST``` file on ```META-INF``` directory in jar.
