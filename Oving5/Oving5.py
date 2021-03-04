from flask import Flask, render_template, request
from flask_cors import CORS, cross_origin
import io, sys

def runCode(code): 
    old_stdout = sys.stdout 
    new_stdout = io.StringIO() 
    sys.stdout = new_stdout
    exec(code)
    result = sys.stdout.getvalue().strip()
    sys.stdout = old_stdout 
    return result 

app = Flask(__name__)
CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

@app.route("/react")
def hostRact():  
    return render_template("index.html", token = "Hello Flask + React")

@app.route("/")
def home():
    return { "message": "Hello"}

@app.route("/horeri", methods = ['POST']) 
def execute(): 
    body = request.json
    code = body.get('code')
    svar = runCode(code)
    return { "result": svar}



if __name__ == "__main__":
    app.run()

