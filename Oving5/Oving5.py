from flask import Flask, render_template, request
from flask_cors import CORS, cross_origin

app = Flask(__name__)
CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

@app.route("/")
def home():
    return { "message": "Hello"}

@app.route("/horeri", methods = ['POST']) 
def execute(): 
    body = request.json
    return { "result": body.get('code')}


if __name__ == "__main__":
    app.run()
