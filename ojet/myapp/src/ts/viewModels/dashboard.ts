import ko = require("knockout");
import "oj-c/input-text";
import "ojs/ojknockout";
import "ojs/ojlabel";
import "ojs/ojbutton";
import "ojs/ojformlayout";
import "oj-c/input-password";
import 'oj-c/button';

class DashBoardViewModel {
  username: ko.Observable<string> | ko.Observable<any>;
  password: ko.Observable<string> | ko.Observable<any>;

  constructor() {
    this.username = ko.observable('');
    this.password = ko.observable('');
  }

  public buttonAction = async (event: Event) => {
    // Create XML string
    const xmlData = `<customer>
      <customerId>${this.username()}</customerId>
      <login>
        <password>${this.password()}</password>
      </login>
    </customer>`;

    console.log(xmlData);

    try {
      const response = await fetch('http://localhost:8080/inb_bank/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/xml',
        },
        body: xmlData,
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const result = await response.text(); // Assuming the server response is in text format
      console.log(result);
      alert('Login Successful');
      window.location.href = "/?ojr=customers";
    } catch (error) {
      console.error('Error:', error);
      alert('Wrong username and password');
    }
  }
}

export = DashBoardViewModel;
