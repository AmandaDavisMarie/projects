import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);

    this.state = {
      formControls: {
          email: {
            value: ''
          },
          firstName: {
            value: ''
          },
          lastName: {
            value: ''
          },
          npiNum: {
            value: ''
          },
          address: {
            value: ''
          },
          phoneNumber: {
            value: ''
          },
      },
      formSubmitted: false
    }
  }

  changeHandler = event => {
      
    const name = event.target.name;
    const value = event.target.value;
  
    this.setState({
      formControls: {
          ...this.state.formControls,
          [name]: {
          ...this.state.formControls[name],
          value
        }
      }
    });
}

  validateForm(){
    let message="";
    let valid=true;
    if(this.state.formControls.firstName.value.length==0){
      message += "Please enter first Name\n";
      valid= false;
    }
    if(this.state.formControls.lastName.value.length==0){
      message += "Please enter last Name\n";
      valid= false;
    }
    if(this.state.formControls.npiNum.value.length==0){
      message += "Please enter NPI Number\n";
      valid= false;    }
    if(this.state.formControls.address.value.length==0){
      message += "Please enter address\n";
      valid= false;    }
    if(this.state.formControls.phoneNumber.value.length==0){
      message += "Please enter phone number\n";
      valid= false;    }
    if(this.state.formControls.email.value.length==0){
      message += "Please enter email\n";
      valid= false;
    }
    if(!valid){
      alert(message);
    }
    return valid;
  }

  handleSubmit(event) {
    if(this.validateForm()){
      this.setState({formSubmitted:true});
      //alert("Thank you, "+ this.state.formControls.firstName.value + " " + this.state.formControls.lastName.value + " for submitting your form");
    }
    event.preventDefault();
  }

  render() {
    let message;
    if(this.state.formSubmitted){
      message="Thank you, "+ this.state.formControls.firstName.value + " " + this.state.formControls.lastName.value + " for submitting your form";
    }
    
    return (
      <div>
        <h1>{message}</h1>
          <form onSubmit={this.handleSubmit}>
          <label>First Name
              <input type="text" 
                    name="firstName" 
                    value={this.state.formControls.firstName.value} 
                    onChange={this.changeHandler} 
              />
          </label>
          <label>Last Name
              <input type="text" 
                    name="lastName" 
                    value={this.state.formControls.lastName.value} 
                    onChange={this.changeHandler} 
              />
          </label>
          <label>NPI Number
              <input type="number" 
                    name="npiNum" 
                    value={this.state.formControls.npiNum.value} 
                    onChange={this.changeHandler} 
              />
          </label>
          <label>Business Address
              <input type="text" 
                    name="address" 
                    value={this.state.formControls.address.value} 
                    onChange={this.changeHandler} 
              />
          </label>
          <label>Phone Number
              <input type="text" 
                    name="phoneNumber" 
                    value={this.state.formControls.phoneNumber.value} 
                    onChange={this.changeHandler} 
              />
          </label>
          <label>Email
              <input type="email" 
                    name="email" 
                    value={this.state.formControls.email.value} 
                    onChange={this.changeHandler} 
              />
          </label>
          <input type="submit" value="Submit" />
      </form> 
      </div>
  
    );
  }
}

export default App;
