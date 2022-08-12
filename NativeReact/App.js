/**Using props in our own Component */

import React,{Component} from "react";
import {StyleSheet, Text, View} from 'react-native';

class ChildClass extends Component{
  render(){
    return(
      <View style={{alignItems:'center'}}>
        <Text style={styles.welcome}>Hello {this.props.name}!</Text>
      </View>
    );
  }
}

export default class ParentsClass extends Component{
  render(){
    return(
      <View style={{alignItems: 'center'}}>
        <ChildClass name='Indra'/>
        <ChildClass name='Jaelani'/>
        <ChildClass name='Fahmi'/>
      </View>
    );
  }
}

const styles=StyleSheet.create({
  welcome:{
    fontSize:30,
  }
});


/**React Native Default custom Props */
/*
import React,{Component} from "react";
import{
  Platform,
  StyleSheet,
  Image,
  Text,
  View
} from 'react-native';

export default class Aing extends Component{
  render(){
    let imagePath = {uri:'https://www.tutorialspoint.com/react_native/images/react-native-mini-logo.jpg'};
    return(
      <View style={styles.container}>
        <Text style={styles.welcome}>Selamat datang di react</Text>
        <Image style={styles.gbr} source={imagePath} style={{width:250, height:250}} />
      </View>
    );
  }
}
const styles = StyleSheet.create({
  container:{
    flex: 1,
    justifyContent:'center',
    alignItems: 'center',
    backgroundColor: '#a7a6a9',
  },
  welcome:{
    fontSize:30,
    textAlign: 'center',
    margin:20,
  },
  gbr:{
    borderColor:'black',
  }
});
*/

/**React Native state Example 2 */
/*
import React,{Component} from "react";
import {StyleSheet, Text, View, TextInput, TouchableOpacity} from 'react-native';

export default class Aing extends Component{
  state: {
    password: string,
    isPasswordVisible:boolean,
    toggleText: string,
  }
  constructor(props: Props){
    super(props);
    this.state={
      password:'',
      isPasswordVisible: true,
      toggleText:'Show',
    };
  }
  handleToggle =()=>{
    const {isPasswordVisible} = this.state
    if(isPasswordVisible){
      this.setState({isPasswordVisible:false});
      this.setState({toggleText:'Hide'});
    }else{
      this.setState({isPasswordVisible:true});
      this.setState({toggleText:'Show'});
    }
  };
  render(){
    return(
      <View style={styles.container}>
        <TextInput
          secureTextEntry={this.state.isPasswordVisible}
          style={{width:400, height:50, backgroundColor:'#a7a6a9', color:'white', fontSize:20}}/>
        <TouchableOpacity onPress={this.handleToggle}>
          <Text style={{fontSize:20}}>{this.state.toggleText}</Text>
        </TouchableOpacity>
      </View>
    );
  }
} 
const styles=StyleSheet.create({
  container:{
    flex: 1,
    justifyContent:'center',
    alignItems: 'center',
  }
});
*/

/**React Native State update

import React, {Component} from "react";
import {Text, View} from 'react-native';

export default class Aing extends Component{
  state = {
    stateAing: 'This is a text Component, created using state data. it will change or updated on click it'
  }
  updateState = () => this.setState({stateAing: 'The state is updated'})
  render(){
    return(
      <View>
        <Text onPress={this.updateState}>{this.state.stateAing}</Text>
      </View>
    );
  }
}
*/

/*
import React,{Component} from "react";
import {StyleSheet, View, Text} from 'react-native'

export default class SwitchExample extends Component{
  render(){
    return(
      <View style={styles.container}>
        <View style={{backgroundColor:'blue',flex:0.3}}/>
        <View style={{backgroundColor:'red',flex:0.5}} />
        <Text style={{fontSize:18}}>View Example</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container:{
    flex:1,
    flexDirection: 'row',
    height:100,
    width:"80%",
    backgroundColor: "#5ead97"
  }
})
*/
/*
import React, {Component} from "react";
import { Platform, StyleSheet,Text,View } from "react-native";

// const instructions = Platform.select({
//   ios: 'Press cmd+R to reload,\nCmd+D or shake for dev menu',
//   android: 'Double tap R on your keyboard to reload,\n' +
//             'Shake or press menu button for dev menu'
// });

type Props={};

export default class App extends Component<Props>{

  render(){
    return(
      <View>
        <Text style={styles.welcome}>Hello world!</Text>
      </View>
    );
  }
}
const styles = StyleSheet.create({
	welcome:{
		fontSize:20,
		textAlign: 'center',
		margin:10,
	}
});
*/



/** contoh juga */
/*
import React from "react";
import {StyleSheet, Text, View } from 'react-native';

export default class App extends React.Component{
  state = {
    myState: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, used do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'
  }
  updateState =()=>this.setState({ myState: 'kontent di update'})
  render(){
    return(
      <View>
          <Text onPress={this.updateState}>{this.state.myState}</Text>
      </View>
    );
  }
}
*/
/** contoh awal */
/*import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
*/