from flask import Flask
import mysql.connector


app = Flask(__name__)

def get_db_connection():
    connection = mysql.connector.connect(user='User', password='password',
                                          host='localhost', database='library_db')
    return connection

@app.route('/')
def hello_world():
    return 'Hello, Library!'


if __name__ == '__main__':
    app.run(debug=True)