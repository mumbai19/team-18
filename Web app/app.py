from flask import Flask
import pymysql
import json
from flask import render_template,url_for,flash,redirect,request,abort,Flask,jsonify
from datetime import datetime
from datetime import date
app = Flask(__name__)

host = "127.0.0.1"
user = "root"
password = "123456"
db = "touching_lives"

@app.route("/dept")
def home():

	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	print(str(cur))
	cur.execute("SELECT * FROM user")
	result = cur.fetchall()
	print(result)

	return "hel"


@app.route("/program",methods=["GET"])
def programname():
	progname=["Beginner","Intermediate","Advanced"]
	return jsonify({"key1":2})

@app.route("/getstudentdata",methods=["GET"])
def getstudentdata():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	cur.execute("SELECT * FROM student");
	result=cur.fetchall()
	finres={}
	for res in result:
		id=res['s_id']
		nam=res['s_name']
		finres[id]=nam
	print(finres)
	return jsonify(finres)

@app.route("/addstudentdata",methods=["POST"])
def addstudentdata():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	content=request.get_json(force=True)
	sql="INSERT INTO `student` (`s_name`,`prog_id`,`ph_no`,`doj`,`dol`,`present_status`) VALUES (%s,%s,%s,%s,%s,%s)";
	val=(content['name'],int(content['program_id']),int(content['phone']),content['doj'],content['dol'],int(content['status']))
	cur.execute(sql,val)
	con.commit()
	return "Hello"



@app.route("/addattendance",methods=["POST"])
def addattendance():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()

	content=request.get_json(force=True)
	list1=content['list1']
	for elem in list1:
		print(elem)
		day1= datetime.now().day
		month1= datetime.now().month
		year1= datetime.now().year
		str1=str(year1)+'-'+str(month1)+'-'+str(day1)
		s_id=1
		# sql="INSERT INTO `attendance` (`date`,`s_id`,`ispresent`,`u_id`) VALUES ('2019-05-11',1,1,1)";
		sql="INSERT INTO `attendance` (`date`,`s_id`,`ispresent`,`u_id`) VALUES (%s,%s,%s,%s)";
		val=(str1,1,elem,s_id)
		print(sql)
		cur.execute(sql,val)
		con.commit()
		
	return "Hello"

@app.route("/getattendance",methods=["GET"])
def getattendance():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	cur.execute("SELECT * FROM attendance")
	result=cur.fetchall()
	print(result)

	return "Hello2"

@app.route("/addsavings",methods=["POST"])
def addsavings():
	content=request.get_json(force=True)
	amount=content['amount']
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	u_id=int(content['u_id'])
	for k in amount.keys():
		print(k+"-"+amount[k])
		sql="INSERT INTO `savings` (`savings_val`,`s_id`,`u_id`,`date`,`status`) VALUES (%s,%s,%s,%s,%s)";
		val=(int(amount[k]),int(k),u_id,date.today(),0)
		cur.execute(sql,val)
	con.commit()
	return "Hello"

app.run(debug=True)