import json
import os
import webbrowser

# Load the JSON data
with open("new_methods_diff.json", "r", encoding="utf-8") as f:
    data = json.load(f)

# Directory to store version-specific HTML files
output_dir = "reports"
os.makedirs(output_dir, exist_ok=True)

# Custom CSS for styling both the TOC and version pages
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
</style>
"""

# Generate index.html as a table of contents page
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

# Generate individual version pages and add links to the TOC
for version in data:
    version_file = f"{output_dir}/{version}.html"
    index_content += f'    <li><a href="{version_file}">JDK {version} API Changes</a></li>\n'

    # Build the version-specific HTML content
    version_content = f"""<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>JDK {version} API Changes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  {custom_css}
</head>
<body>
<div class="container">
  <h1>JDK {version} API Changes</h1>
  <a href="../report-styling.html" class="back-link">← Back to Table of Contents</a>
  <hr>
"""

    # Add each file and its methods
    for file_url in data[version]:
        version_content += f"""
  <div class="card">
    <div class="card-header">
      <a href="{file_url}" target="_blank">{file_url}</a>
    </div>
    <ul class="list-group list-group-flush">
"""
        for method in data[version][file_url]:
            version_content += f'      <li class="list-group-item">{method}</li>\n'

        version_content += """
    </ul>
  </div>
"""
    version_content += """</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
"""

    with open(version_file, "w", encoding="utf-8") as f:
        f.write(version_content)

index_content += """  </ul>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
"""

with open("report-styling.html", "w", encoding="utf-8") as f:
    f.write(index_content)

webbrowser.open("file://" + os.path.abspath("report-styling.html"))
