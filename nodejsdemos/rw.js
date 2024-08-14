import fs from 'fs';
import readline from 'readline-sync';

let text = readline.question("Enter some text:");
fs.writeFileSync("demo.txt",text);
console.log("done file writing");

let fileData = fs.readFileSync('demo.txt');
console.log("welcome nice");
console.log(fileData.toString());