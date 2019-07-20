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



@app.route("/program",methods=["GET"])
def programname():
	progname=["Beginner","Intermediate","Advanced"]
	return jsonify({"key1":2})

app.run()