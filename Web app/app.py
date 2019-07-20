from flask import Flask
import pymysql,json,datetime
from flask import render_template,url_for,flash,redirect,request,abort,Flask,jsonify

app = Flask(__name__)

host = "127.0.0.1"
user = "root"
password = ""
db = "touching_lives"

@app.route("/dept")
def home():

	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()

	# cur.execute("SELECT * FROM department")
	# result = cur.fetchall()
	# con.close()

	# return result[0]

@app.route('/')
def my_form():
    return render_template('index.html')

@app.route('/', methods=['POST'])
def add_user():
	password = request.form['password']
	prog_id= request.form['prog_id']

	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()

	
	cur.execute("INSERT into user values('password' ,'prog_id')")
	cur.close()



@app.route("/program",methods=["GET"])
def programname():
	progname=["Beginner","Intermediate","Advanced"]
	return jsonify({"key1":2})

@app.route("/login",methods=["POST"])
def login():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	uid = request.form['uid']
	pwd = request.form['pwd']
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

@app.route("/addactivities",methods=["GET"])
def addactivities():
	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()
	# a_id = request.form['a_id']
	# date = request.form['date']
	# a_name = request.form['a_name']
	# description = request.form['description']
	prog_id = 1
	date = "2019-07-16"
	a_name = "affasf"
	description = "sdfdsfvasdv"
	sql = "INSERT INTO activity (a_name,description,date,prog_id) values (%s,%s,%s,%s)"
	cur.execute(sql,(a_name,description,date,prog_id))
	# result = cur.fetchall()
	con.commit()
	con.close()
	return "1"

# @app.route("")

app.run()