import os
from PIL import Image
from flask import render_template,url_for,flash,redirect,request,abort,Flask,jsonify
from flaskblog import app,db,bcrypt
from flask_login import login_user, current_user, logout_user, login_required



@app.route("/")
@app.route("/home")
def home():
	pass