import readline from 'readline-sync';

let text = readline.question('Enter a text:');
let number = readline.questionInt('Enter a number:');
let password = readline.questionNewPassword('Enter your password:');
let display = readline.question('Display input y/n:');
if(display.toLowerCase() == 'y'){
    console.log("Text:"+text);
    console.log("Number:"+number);
    console.log("Password"+password)
}