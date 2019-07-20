from flask import Flask
import pymysql

app = Flask(__name__)

host = "127.0.0.1"
user = "root"
password = ""
db = "test_cms"

@app.route("/dept")
def home():

	con = pymysql.connect(host=host, user=user, password=password, db=db, cursorclass=pymysql.cursors.DictCursor)
	cur = con.cursor()

	cur.execute("SELECT * FROM department")
	result = cur.fetchall()

	return result[0]

app.run()
