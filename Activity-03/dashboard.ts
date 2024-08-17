/**
 * @license
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates.
 * Licensed under The Universal Permissive License (UPL), Version 1.0
 * as shown at https://oss.oracle.com/licenses/upl/
 * @ignore
 */
import ko = require("knockout");
import * as AccUtils from "../accUtils";
import "oj-c/input-text";
import "ojs/ojknockout";
import "oj-c/input-number";
import "oj-c/input-password";
import "oj-c/input-date-text";
import ArrayDataProvider = require('ojs/ojarraydataprovider');
import { ojButton } from "ojs/ojbutton";
import "ojs/ojbutton";
import "ojs/ojtable";
import 'oj-c/radioset';
import 'oj-c/message-banner';
import 'oj-c/button';
import 'ojs/ojformlayout';
import "oj-c/progress-bar";
import { ObservableKeySet } from 'ojs/ojknockout-keyset';
import { ImmutableKeySet } from 'ojs/ojkeyset';

import 'ojs/ojknockout-keyset';
import 'ojs/ojknockout';
import 'ojs/ojkeyset';
import 'ojs/ojlabel';
import 'oj-c/avatar';
import 'oj-c/list-item-layout';
import 'oj-c/list-view';
import 'oj-c/selector';
import 'oj-c/selector-all';
import { MessageBannerItem, CMessageBannerElement } from 'oj-c/message-banner';
  
import MutableArrayDataProvider = require('ojs/ojmutablearraydataprovider');
type DemoMessageBannerItem = MessageBannerItem & {
  id: string;
};
interface EmployeeData {
  id: number;
  name: string;
  title: string;
  image: string;
}
class DashboardViewModel {
  value: ko.Observable<string>;
  firstname : ko.Observable<string> | ko.Observable<any>;
  salary:ko.Observable<number>| ko.Observable<any>;
  password:ko.Observable<string> |ko.Observable<any>;
  date: ko.Observable<Date> | ko.Observable<any>;
  username: ko.Observable<string>;
  email: ko.Observable<string>;
  street: ko.Observable<string>;
  suite: ko.Observable<string>;
  activatedButton: ko.Observable<string>;
  readonly personalInformationMessages: MutableArrayDataProvider<string, DemoMessageBannerItem>;
  private counter: number;
  readonly closePersonalInformationMessage = (
    event: CMessageBannerElement.ojClose<string, DemoMessageBannerItem>
  ) => {
    // remove the message from the data to close it
    let data = this.personalInformationMessages.data.slice();
    const closeMessageKey = event.detail.key;

    data = data.filter((message) => (message as any).id !== closeMessageKey);
    this.personalInformationMessages.data = data;
  };
  readonly updatePersonalInfo = () => {
    // remove the message from the data to close it
    let data = this.personalInformationMessages.data.slice();
    data.push({
      id: `message-${++this.counter}`,
      severity: 'confirmation',
      summary: 'Updated personal information',
      detail: 'The provided personal information of the employee has been updated in the database.'
    });
    this.personalInformationMessages.data = data;
  };
  private readonly step = ko.observable(0);
    readonly progressValue = ko.pureComputed(() => {
      return Math.min(this.step(), 100);
    });
    private readonly deptArray =   [
      {
        "DepartmentId": 10,
        "DepartmentName": "Finance 9",
        "LocationId": 300,
        "ManagerId": 7001,
        "StartDate": "2014-06-13",
        "EmployeeCount": 335,
        "Type": "Sales",
        "Currency": "EUR",
        "Primary": [],
        "Rating": 3,
        "TargetComplete": 90
      },
      {
        "DepartmentId": 20,
        "DepartmentName": "Control And Credit 9",
        "LocationId": 300,
        "ManagerId": 7001,
        "StartDate": "2019-09-10",
        "EmployeeCount": 206,
        "Type": "HR",
        "Currency": "USD",
        "Primary": [],
        "Rating": 1,
        "TargetComplete": 90
      },
    ]
        
    readonly dataprovider = new ArrayDataProvider(this.deptArray, {
      keyAttributes: "DepartmentId",
      implicitSort: [{ attribute: "DepartmentId", direction: "ascending" }],
    });
    private readonly data: Array<EmployeeData> = [
      {
        id: 1,
        name: 'Chris Black',
        title: 'Oracle Cloud Infrastructure GTM Channel Director EMEA',
        image: '../images/hcm/placeholder-male-01.png'
      },
      {
        id: 2,
        name: 'Christine Cooper',
        title: 'Senior Principal Escalation Manager',
        image: '../images/hcm/placeholder-female-01.png'
      },
      {
        id: 3,
        name: 'Chris Benalamore',
        title: 'Area Business Operations Director EMEA & JAPAC',
        image: '../images/hcm/placeholder-male-03.png'
      },
      {
        id: 4,
        name: 'Christopher Johnson',
        title: 'Vice-President HCM Application Development',
        image: '../images/hcm/placeholder-male-04.png'
      },
      {
        id: 5,
        name: 'Samire Christian',
        title: 'Consulting Project Technical Manager',
        image: '../images/hcm/placeholder-male-05.png'
      },
      {
        id: 6,
        name: 'Kurt Marchris',
        title: 'Customer Service Analyst',
        image: '../images/hcm/placeholder-male-06.png'
      },
      {
        id: 7,
        name: 'Zelda Christian Cooperman',
        title: 'Senior Principal Escalation Manager',
        image: '../images/hcm/placeholder-female-02.png'
      }
    ];
  
    readonly selectedItems = new ObservableKeySet(); // observable bound to selection option to monitor current selections
    readonly selectedIds = ko.observable();
  
    getDisplayValue({ keys }: ImmutableKeySet<EmployeeData['id']>) {
      let text: string = '';
      const set = keys.all === true ? keys.deletedKeys : keys.keys;
      if (keys.all) {
        text = 'Everything selected';
        if (set.size > 0) {
          text = text + ' except: ';
        }
      }
      return text + JSON.stringify(Array.from(set.values()));
    }
  
    // Current selection is already monitored through this.selectedItems observable.
    // To perform custom selection logic on selected elements and/or on current item, an option change callback can be used:
    handleSelectedChanged = (event: any) => {
      // show selected list item elements' ids
      this.selectedIds(
        this.getDisplayValue(event.detail.value as ImmutableKeySet<EmployeeData['id']>)
      ); 
      const totalSize = this.data.length;
      if (!event.detail.value.isAddAll()) {
        // when all items are selected
        const currSize = this.selectedItems().keys.keys?.size;
        currSize === totalSize && this.selectedItems.addAll();
      } else {
        // when all items are unselected 
        const currSize = this.selectedItems().keys.deletedKeys?.size;
        currSize === totalSize && this.selectedItems(this.selectedItems().clear());
      }
    };
  
    readonly dataProvider = new ArrayDataProvider<EmployeeData['id'], EmployeeData>(this.data, {
      keyAttributes: 'id'
    });
    
    constructor() {
      this.value = ko.observable("red");
      this.firstname = ko.observable(null);
      this.salary = ko.observable(null);
      this.password = ko.observable(null);
      this.date = ko.observable(null);
      this.username = ko.observable("");
      this.email = ko.observable("");
      this.street = ko.observable("");
      this.suite = ko.observable("");
      this.activatedButton = ko.observable("(None activated yet)");
      const initialPersonalSectionData = [
        {
          id: 'message',
          severity: 'confirmation',
          summary: 'Updated personal information',
          detail:
            'The provided personal information of the employee has been updated in the database.',
          timestamp: new Date().toISOString()
        }
      ];
      this.personalInformationMessages = new MutableArrayDataProvider(initialPersonalSectionData, {
        keyAttributes: 'id'
      });
      this.counter = 0;
      window.setInterval(() => {
        this.step((this.step() + 1) % 200);
      }, 30);
    }
    
    public buttonAction = async(event:Event) => {
      let elementName = (event.currentTarget as HTMLElement).tagName;
      //alert("You clicked on a button: "+elementName);
      console.log("Name="+this.firstname()+",Salary="+this.salary());
      console.log("Element Name="+elementName)
      let id = parseInt(this.firstname());
      let URL = "https://jsonplaceholder.typicode.com/users/"+id;
      let res = await fetch(URL);
      let jsonData = await res.json();
      console.log(jsonData);
    
    this.username(jsonData.username);
    this.email(jsonData.email);
    this.street(jsonData.address.street);
    this.suite(jsonData.address.suite);
  }
  

  }
   
    // public buttonAction = (event: ojButton.ojAction) => {
    //   this.activatedButton((event.currentTarget as HTMLElement).id);
    //   return true;
    // };

export = DashboardViewModel;
