from flask import Flask
import pymysql
import json
from flask import render_template,url_for,flash,redirect,request,abort,Flask,jsonify
import pandas as pd
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

	return "Hello"

@app.route('/addmentor')
def my_form():
    return render_template('index.html',flag="")

@app.route('/addmentor', methods=['POST'])
def add_user():
	password1 = request.form['password']
	prog_id= request.form['prog_id']

	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	
	sql="INSERT into user (`password`,`prog_id`) values (%s,%s)"
	val=(password1,prog_id)
	cur.execute(sql,val)
	con.commit()
	cur.close()
	return render_template('index.html',flag="Data saved successfully!")

@app.route('/wbaddstudent',methods=['GET'])
def wbaddstudent():
	return render_template('add_student.html',flag="")

@app.route('/wbaddstudent', methods=['POST'])
def wbaddpoststudent():
	s_name = request.form['s_name']
	prog_id= request.form['prog_id']
	ph_no= request.form['ph_no']
	doj= request.form['doj']
	dol= doj
	status=1
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	sql="INSERT into student (`s_name`,`prog_id`,`ph_no`,`doj`,`dol`,`present_status`) values (%s,%s,%s,%s,%s,%s)"
	val=(s_name,prog_id,ph_no,doj,dol,status)
	cur.execute(sql,val)
	con.commit()
	cur.close()

	return render_template('add_student.html',flag="Data saved successfully!")

@app.route('/studentlist',methods=["GET"])
def studentlist():

	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()

	cur.execute("SELECT * FROM student")
	result = cur.fetchall()
	print(result)
	flag=""
	return render_template('student_list.html',result=result)

@app.route('/wbstudentsrecord',methods=["GET"])
def wbstudentsrecord():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()

	cur.execute("SELECT SUM(savings_val) as s1,s_name, student.s_id from student inner join savings on student.s_id=savings.s_id group by student.s_id")
	result = cur.fetchall()
	# result2=[]
	# for res in result:
	# 	sql="select sum(savings_val) as s1 from savings where s_id=%s and presentstatus=0";
	# 	val=(res[student.s_id])
	# 	cur.execute(sql,val)
	# 	resul=cur.fetchone()
	# 	result2.append(resul[s1])
	print(result)
	flag="Data saved successfully!"
	# print(result2)
	return render_template('savings_record.html')
	return "Hello"

@app.route('/wbassessmentlist',methods=["GET"])
def wbassessmentlist():
	return render_template('assessment_list.html')

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
	val=(content['name'],int(content['program_id']),int(content['phone']),content['doj'],None,1)
	cur.execute(sql,val)
	con.commit()
	return jsonify({"status":True})

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
		
	return jsonify({"status":True})

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

@app.route("/paysavings",methods=["POST"])
def paysavings():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	content=request.get_json(force=True)
	# print(content['savings'])
	list1=content['savings']
	for elem in list1:
		print(elem)
		sql="update `savings` set `status`= 1 where `s_id`=%s"
		val=(int(elem))
		cur.execute(sql,val)
	con.commit()
	return "Hello"

@app.route("/generatereports",methods=["GET"])
def generate_reports():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	sql='select * from attendance'
	rep=pd.read_sql(sql,con)
	print(rep.to_string())
	return "Hello"

@app.route("/login",methods=["POST"])
def login():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	temp=request.json
	uid = temp['uid']
	pwd = temp['pwd']
	sql = "SELECT `password` FROM `user` WHERE `u_id`=%s"
	# sql = "INSERT INTO `program` (`program_name`) VALUES (%s)"
	cur.execute(sql, (uid))
	# con.commit()
	# con.close()
	# cur.execute(sql, (uid))
	result = cur.fetchone()
	# return result
	if pwd == result['password']:
		return '1'
	else:
		return '0'

@app.route("/getactivities/<uid>/<prgm_id>",methods=["GET"])
def getactivities(uid,prgm_id):
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	sql = "SELECT * FROM activity WHERE `prog_id` = %s"
	cur.execute(sql,(int(prgm_id)))
	result = cur.fetchall()
	con.close()
	return json.dumps(result)

@app.route("/addactivities",methods=["POST"])
def addactivities():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	req = request.json
	prog_id = req['prog_id']
	date = req['date']
	a_name = req['a_name']
	description = req['description']
	# prog_id = 1
	# date = "2019-07-16"
	# a_name = "affasf"
	# description = "sdfdsfvasdv"
	sql = "INSERT INTO activity (a_name,description,date,prog_id) values (%s,%s,%s,%s)"
	cur.execute(sql,(a_name,description,date,prog_id))
	# result = cur.fetchall()
	con.commit()
	con.close()
	return "1"

@app.route("/getassessments/<uid>/<prgm_id>",methods=["GET"])
def getassessments(uid,prgm_id):
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	sql = "SELECT * FROM assessment WHERE `prog_id` = %s and `u_id` = %s"
	cur.execute(sql,(prgm_id,uid))
	result = cur.fetchall()
	con.close()
	return json.dumps(result)

@app.route("/addassessments",methods=["POST"])
def addassessments():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	req = request.json
	prog_id = req['prog_id']
	date = req['date']
	a_name = req['a_name']
	description = req['description']
	# prog_id = 1
	# date = "2019-07-16"
	# a_name = "affasf"
	# description = "sdfdsfvasdv"
	sql = "INSERT INTO activity (a_name,description,date,prog_id) values (%s,%s,%s,%s)"
	cur.execute(sql,(a_name,description,date,prog_id))
	# result = cur.fetchall()
	con.commit()
	con.close()
	return "1"

app.run()
