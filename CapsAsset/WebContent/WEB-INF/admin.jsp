<%@page import="com.cg.Dao.AdminDao"%>
<%@page import="com.cg.bean.User_Master"%>
<%@page import="java.util.List"%>
<%@page import="com.cg.bean.Status"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>AMS-Admin</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">

<style>
    body {
    font-family: "Lato", sans-serif;
}

@import url('https://fonts.googleapis.com/css?family=Open+Sans');

.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 80;
    left: 0;
    bbackground-color: #111;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s
}

.sidenav a:hover, .offcanvas a:focus{
    color: #f1f1f1;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
/*Sidenav Overlay End*/

#container2 {
position: absolute;
height: 360px;
top: 45%;
left: 65%;
padding-bottom:10px;
opacity: 0.8;
margin-top: -124px;
margin-left: -30px;
background: #E7EAF7;
    border-radius: 3px;
    border: 1px solid #ccc;
    box-shadow: 0 1px 2px rgba(0, 0, 0, .1);
  color:#000000;
}

form {
    margin: 0 auto;
    margin-top: 20px;
}
label {
    color: #000000;
    display: inline-block;
    margin-left: 18px;
    padding-top: 10px;
    font-size: 14px;
}
input {
    font-size: 14px;
    outline: none;
}
input[type=text],
input[type=password],
input[type=textarea],
input[type=number] {
    color: #000000;
    padding-left: 10px;
    margin: 10px;
    margin-top: 12px;
    margin-left: 18px;
    width: 290px;
    height: 35px;
}
#lower {
    background: #ecf2f5;
    width: 100%;
    height: 69px;
    margin-top: 20px;
}
input[type=checkbox] {
    margin-left: 20px;
    margin-top: 30px;
}
.check {
    margin-left: 3px;
}
input[type=submit] {
    float: right;
    margin-right: 20px;
    margin-top: 20px;
    width: 80px;
    height: 30px;
}
#a{
font-family: "Helvetica Neue", Helvetica, sans-serif;
text-align:center;
}
header{
    padding: .02px;
    color: white;
    
    clear: left;
    text-align: left;
  padding-left:25px;
}
#ver {
    list-style-type: none;
    margin: 0;
  padding:0;
    width: 300px;
    background-color: #f1f1f1;
}

#ver li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}

#ver li a.active {
    background-color: #4CAF50;
    color: white;
}

#ver li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
.button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
  float:right;
}
  
  .footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
  
   color: white;
   text-align: center;
}
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #4CAF50;
    color: white;
}
</style>
</head>
<%
	String uname = null;
	User_Master um = (User_Master) session.getAttribute("admin");
	if (um.getUserName() != null) {
		uname = um.getUserName();
	} else {
		response.sendRedirect("login.html");
	}
%>

<body>
	<div class="topnav" id="myTopnav">
	<img src="logo1.png" width="180px" height="60">	
 
  <a href="#home">Contact</a>
  <a href="#news">News</a>
  <a href="#contact">About</a>
  <a href="index.html">Home</a>
  <center> <h1 style="color:#ff4d4d">Asset Management System</h1> </center>
  
  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
</div>
	<header>
	<h4>
		Logged In as Admin (<i><%=uname%></i>)
	</h4>
	</header>

	<div id="container2">
		<form>
<label for="heading" ><b>Add Asset</b></label><br> <br>
<label for="aid"><pre>Asset ID:         </pre></label>
<input type="number" id="aid" name="aid" required><br>
<label for="aname"><pre>Asset Name:       </pre></label>
<input type="text" id="aname" name="aname" required><br>
<label for="ades"><pre>Asset Description:</pre></label>
<input type="textarea" id="ades" name="ades" required><br>
<label for="aquan"><pre>Quantity:         </pre></label>
<input type="number" id="aquan" name="aquan" required><br>
<label for="status"><pre>Status:              </pre></label>
<input type="radio" name="status" value="Available" checked> Available
  <input type="radio" name="status" value="Unavailable"> Unavailable<br>
  <button>Create Asset</button>
</form>
	</div>




<div id="mySidenav" class="sidenav" style="font-family: 'Open Sans', sans-serif;">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="http://localhost:8080/CapsAsset/admin">Add Asset</a>
  <a class="active" href="http://localhost:8080/CapsAsset/status">View Requests</a>
  <a href="http://localhost:8080/CapsAsset/report">Generate Reports</a>
  <a href="http://localhost:8080/CapsAsset/logout">Logout</a>
  
</div>
<span style="font-size:25px;color:#ff4d4d;cursor:pointer"  onclick="openNav()">&#9776; Services</span>

<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>
<div class="footer">
  <p>Capgemini Mini Project</p>
</div>

<canvas id="nokey" width="800" height="800">

    
</canvas>
<script src="js/script.js"></script>
</body>
</html>