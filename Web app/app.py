from flask import Flask
import pymysql
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

	cur.execute("SELECT * FROM department")
	result = cur.fetchall()

	return result[0]

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

@app.route('/', methods=['POST'])

def add_student():
	s_name = request.form['s_name']
	prog_id= request.form['prog_id']
	ph_no= request.form['ph_no']
	doj= request.form['doj']
	dol= request.form['dol']

	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()

	
	cur.execute("INSERT into student values('s_name' ,'prog_id','ph_no','doj','dol,'1')")
	cur.commit()
	cur.close()


def get_student_data():

	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()

	cur.execute("SELECT s_name,ph_no,doj,prog_id FROM student")
	result = cur.fetchall()


@app.route("/program",methods=["GET"])
def programname():
	progname=["Beginner","Intermediate","Advanced"]
	return jsonify({"key1":2})

app.run()