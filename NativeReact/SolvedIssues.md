## React Native android build failed. SDK location not found
solution : Go to the ```android/``` directory of the ```react-native project``` then create a file called ```local.properties``` write code \
in Windows ```sdk.dir = C:\\Users\\USERNAME\\AppData\\Local\\Android\\sdk``` \
in macOS ```sdk.dir = /Users/USERNAME/Library/Android/sdk``` \
in linux ```sdk.dir = /home/USERNAME/Android/Sdk``` 

## npx react-native run-android does not work - "java.io.IOException: The filename, directory name, or volume label syntax is incorrect"
open Gradle file properties ```local.properties``` file at android directory and if the contents like below :

      org.gradle.java.home=C:\Program Files\Java\jdk-15.0.1
      sdk.dir=C:\Users\user\AppData\Local\Android\Sdk

then windows backslash (```\```) must have an escape char. Change it like below:

      org.gradle.java.home="C:\\Program Files\\Java\\jdk-15.0.1"
      sdk.dir=C:\\Users\\user\\AppData\\Local\\Android\\Sdk
