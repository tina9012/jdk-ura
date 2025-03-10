import json
import os
import csv
import webbrowser
from html import escape


with open("new_methods_diff.json", "r", encoding="utf-8") as f:
    data = json.load(f)

checked_methods = {}
csv_folder = "csv_reports"

for version in data:

    csv_file = os.path.join(csv_folder, f"{version}_methods.csv")
    if os.path.exists(csv_file):
        with open(csv_file, "r", encoding="utf-8") as f_csv:
            reader = csv.DictReader(f_csv)
            for row in reader:
                key = (row["version"], row["file_url"], row["method"])
                #gets the checked status and stores in checked_methods, identified by version, file_url, and method
                checked_methods[key] = (row["checked"].strip().lower() == "true")


output_dir = "reports"
os.makedirs(output_dir, exist_ok=True)


custom_css = """
<style>
  body {
    background-color: #f8f9fa;
    padding: 2rem;
  }
  .card {
    margin-bottom: 1.5rem;
    border: none;
  }
  .card-header {
    background-color: #3230a1;
    color: white;
    font-weight: bold;
  }
  .card-header a {
    color: white;
  }
  .badge {
    font-size: 0.8rem;
    margin-left: 0.5rem;
  }
</style>
"""

#html for the index page
#uses bootstrap for styling
index_content = f"""<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>JDK API Changes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  {custom_css}
</head>
<body>
<div class="container">
  <h1>JDK API Changes</h1>
  <ul class="toc">
"""

#using bootstrap to for css styling
for version in data:
    version_file = f"{output_dir}/{version}.html"
    index_content += f'    <li><a href="{version_file}">JDK {version} API Changes</a></li>\n'

    version_content = f"""<!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JDK {version} Changes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    {custom_css}
  </head>
  <body>
  <div class="container">
    <h1>JDK {version} Changes</h1>
    <a href="../report-styling.html" class="back-link">← Back to all versions</a>
    <hr>
  """

    for file_url in data[version]:
        version_content += f"""
  <div class="card">
    <div class="card-header">
      <a href="{file_url}" target="_blank">{file_url}</a>
    </div>
    <ul class="list-group list-group-flush">
"""
        for method in data[version][file_url]:

            #gets the key to access the checked_method status created earlier
            
            key = (version, file_url, method)
            if checked_methods.get(key, False):
                indicator = '<span class="badge bg-success">Checked</span>'
            else:
                indicator = '<span class="badge bg-warning text-dark">Not Checked</span>'

            version_content += f'      <li class="list-group-item">{escape(method)} {indicator}</li>\n'

        version_content += """
    </ul>
  </div>
"""
    version_content += """</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
"""

    with open(version_file, "w", encoding="utf-8") as f_out:
        f_out.write(version_content)

index_content += """  </ul>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
"""

with open("report-styling.html", "w", encoding="utf-8") as f_index:
    f_index.write(index_content)

webbrowser.open("file://" + os.path.abspath("report-styling.html"))
