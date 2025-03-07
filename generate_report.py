import webbrowser
import os

# Specify the file you want to open
filename = "./report.html"

# Get the absolute path to the file
full_path = os.path.abspath(filename)

# Convert the file path to a URL (using the file:// protocol)
url = "file://" + full_path

# Open the URL in the default web browser
webbrowser.open(url)
