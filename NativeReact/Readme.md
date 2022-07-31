## Steps creating Android Applications using ReactNative
### 1. Install Node JS
### 2. Install OpenJDK/JDK
### 3. Install Android Studio
### 4. Open Terminal/CMD, execute code below
	npx react-native init AwesomeProject
	......
	Run instructions for Android:
	• Have an Android emulator running (quickest way to get started), or a device connected.
	• cd "E:\Android\ReactNative\BelajarProject" && npx react-native run-android

	Run instructions for Windows:
	• See https://aka.ms/ReactNativeGuideWindows for the latest up-to-date instructions.

	npm notice
	npm notice New minor version of npm available! 8.11.0 -> 8.15.1
	npm notice Changelog: https://github.com/npm/cli/releases/tag/v8.15.1
	npm notice Run npm install -g npm@8.15.1 to update!
	npm notice
### 5. open project in ```Vs. Code``` using code below
	code .
### 6. Open terminal/cmd in Vs. Code the execute code below for testing.
	npx react-native start
	......
	To reload the app press "r"
	To open developer menu press "d"

keep terminal/cmd open. then split terminal/cmd
### 7. On right side of the terminal execute code below to Start the application
	npx react-native run-android

if emulator appear then ready for development.
### 8. Install React Navigation by execute code below on Vs. Code terminal/cmd https://reactnavigation.org/docs/getting-started/
	npm install @react-navigation/native
### 9. Install another lib using code below
	npm install react-native-screens react-native-safe-area-context
### 10. Install other lib using code below
	npm install @react-navigation/native-stack
### 11. Install other lib using code below
	npm install @react-navigation/bottom-tabs
### 12. if we use svg image add this lib using code below
	npm install react-native-svg
	npm react-native-svg-transformer
	dJbW^KfwDkNjsDiq&qTM

on metro.config.js replace the code using below code

	const { getDefaultConfig } = require("expo/metro-config");

	module.exports = (() => {
	  const config = getDefaultConfig(__dirname);
	  const { transformer, resolver } = config;
	  config.transformer = {
		...transformer,
		babelTransformerPath: require.resolve("react-native-svg-transformer"),
	  };
	  config.resolver = {
		...resolver,
		assetExts: resolver.assetExts.filter((ext) => ext !== "svg"),
		sourceExts: [...resolver.sourceExts, "svg"],
	  };
	  return config;
	})();
### 13. Create Directory ```src``` then create subdirectory ```assest, components, router, pages``` respectively
### 14. Move APP.js file into src
