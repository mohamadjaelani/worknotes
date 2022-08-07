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
if ```error Cannot find module 'expo/metro-config'``` raise then create file ```metro-exotic-transformer.js``` and paste code below

	const { createExoticTransformer } = require('@expo/metro-config/transformer');
	module.exports = createExoticTransformer({
	  transpileModules: ['@stripe/stripe-react-native'],
	  // You can uncomment the following lines to add any extra node_modules paths in a monorepo:
	  //   nodeModulesPaths: [
	  //     'node_modules',
	  //     // Generally you'll add this when your config is in `apps/my-app/metro.config.js`
	  //     '../../node_modules',
	  //     // If you have custom packages in a `packages/` folder
	  //     '../../packages',
	  //   ],
	});

then ```metro.config.js``` paste with code below

	const { getDefaultConfig } = require('@expo/metro-config');
	const config = getDefaultConfig(__dirname, {
	  // Initialize in exotic mode.
	  // If you want to preserve `react-native` resolver main field, and omit cjs support, then leave this undefined
	  // and skip setting the `EXPO_USE_EXOTIC` environment variable.
	  mode: 'exotic',
	});

	// Use the new transformer
	config.transformer.babelTransformerPath = require.resolve('./metro-exotic-transformer');

	// Optionally, you can add support for the `react-native` resolver field back
	// doing this will increase bundling time and size as many community packages ship untransformed code using this feature.
	// Other packages like `nanoid` use the field to support `react-native` so you may need to enable it regardless.
	// defaultConfig.resolver.resolverMainFields.unshift('react-native');

	module.exports = config;
then run ```npx react-native start``` again
### 7. On right side of the terminal execute code below to Start the application
get name of the emulator using ```C:\Users\aina\AppData\Local\Android\Sdk\emulator\emulator -list-avds``` on my computer i have ```Pixel_2_API_30``` emulator names. then run the emulator ```C:\Users\aina\AppData\Local\Android\Sdk\emulator\emulator -avd Pixel_2_API_30``` after the emulator fully loaded then run the React-Native project on emulator using ```npx react-native run-android``` if emulator appear then ready for development.
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
### 14. Move ```APP.js```file into src
### 15. Install ```ES7 React/Redux/GraphQL/React-Native-Snippets``` extension
### 16. Open ```APP.js``` file then paste below code
	import React from "react";
	import { StyleSheet, Text, View } from "react-native";

	const App = () => {
	  return (
	    <View>
	      <Text>Hello kamu</Text>
	    </View>
	  )
	}

	export default App

	const style = StyleSheet.create({})
### 17. accessing ```https://reactnavigation.org/docs/hello-react-navigation``` then copy below code
	import { NavigationContainer } from '@react-navigation/native';
	import { createNativeStackNavigator } from '@react-navigation/native-stack';
add in ```APP.js``` file just like below
	import React from "react";
	import { StyleSheet, Text, View } from "react-native";
	import { NavigationContainer } from '@react-navigation/native';
	import { createNativeStackNavigator } from '@react-navigation/native-stack';
	const App = () => {
	  return (
	    <View>
	      <Text>Hello kamu</Text>
	    </View>
	  )
	}

	export default App

	const style = StyleSheet.create({})
