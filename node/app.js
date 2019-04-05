var express = require('express');
var db = require('./db');
var app = express();
var path = require('path');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

db.connect(function(err){
  console.log(err);
  if(err){
    console.log('Unable to connect to MariaDB.');
    process.exit(1);
  }
});

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', require("./routes/index"));
app.use('/member', require("./routes/member"));

module.exports = app;
