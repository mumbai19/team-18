from flask import Flask
from flask import render_template,url_for,flash,redirect,request,abort,Flask,jsonify

app = Flask(__name__)

@app.route("/")
def home():
	return "Hi"

@app.route("/program",methods=["GET"])
def programname():
	progname=["Beginner","Intermediate","Advanced"]
	return jsonify({"key1":2})