import * as AccUtils from "../accUtils";
import ko = require("knockout");
import "oj-c/input-text";
import "ojs/ojknockout";
import "ojs/ojlabel";
import "oj-c/input-number";
import "ojs/ojbutton";
import "ojs/ojformlayout";
import "oj-c/input-password";
import 'oj-c/button';

class IncidentsViewModel {
  firstName: ko.Observable<string>;
  lastName: ko.Observable<string>;
  gender: ko.Observable<string>;
  email: ko.Observable<string>;
  mobile: ko.Observable<string>; // Mobile as a string for flexibility
  customerStatus: ko.Observable<string>;
  password: ko.Observable<string>;
  loginStatus: ko.Observable<string>;
  loginAttempts: ko.Observable<number>;
  accountType: ko.Observable<string>;

  constructor() {
    this.firstName = ko.observable('');
    this.lastName = ko.observable('');
    this.gender = ko.observable('');
    this.email = ko.observable('');
    this.mobile = ko.observable('');
    this.customerStatus = ko.observable('');
    this.password = ko.observable('');
    this.loginStatus = ko.observable('');
    this.loginAttempts = ko.observable(0);
    this.accountType = ko.observable('');
  }

  public buttonAction = async (event: Event) => {
    // Validate input values
    const validLoginStatuses = ['NEW', 'ACTIVE', 'LOCKED'];
    const validCustomerStatuses = ['NEW', 'APPROVED'];
    const validAccountTypes = ['SAVINGS', 'CURRENT', 'FIXED DEPOSIT'];

    if (!validLoginStatuses.includes(this.loginStatus())) {
      alert('Invalid login status.');
      return;
    }

    if (!validCustomerStatuses.includes(this.customerStatus())) {
      alert('Invalid customer status.');
      return;
    }

    if (!validAccountTypes.includes(this.accountType())) {
      alert('Invalid account type.');
      return;
    }

    const data = {
      customer: {
        firstName: this.firstName(),
        lastName: this.lastName(),
        gender: this.gender(),
        email: this.email(),
        mobile: this.mobile(),
        customerStatus: this.customerStatus()
      },
      login: {
        password: this.password(),
        loginStatus: this.loginStatus(),
        loginAttempts: this.loginAttempts()
      },
      accountType: this.accountType()
    };

    console.log(data);

    try {
      const response = await fetch('http://localhost:8080/inb_bank/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      if (!response.ok) {
        // Handle HTTP errors
        const errorText = await response.text();
        throw new Error(`HTTP error! Status: ${response.status}. Response: ${errorText}`);
      }

      const result = await response.json();
      console.log(result);
      alert('Registration successful');
    } catch (error) {
      console.error('Error:', error);
      alert('An error occurred while registering');
    }
  }
}

export = IncidentsViewModel;
