import json
import os
import webbrowser

with open("new_methods_diff.json", "r", encoding="utf-8") as f:
    data = json.load(f)

html_content = """<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>JDK API Changes</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
<h1>JDK API Changes</h1>
<ul class="nav nav-tabs" id="jdkTab" role="tablist">
"""

# Generate tab headers
first = True
for version in data:
    active_class = "active" if first else ""
    html_content += f'''
    <li class="nav-item">
        <a class="nav-link {active_class}" id="{version}-tab" data-toggle="tab" href="#{version}" role="tab" 
        aria-controls="{version}" aria-selected="{'true' if first else 'false'}">{version}</a>
    </li>
    '''
    first = False

html_content += '</ul><div class="tab-content">'

# Generate tab content
first = True
for version in data:
    active_class = "show active" if first else "fade"
    html_content += f'''
    <div class="tab-pane {active_class}" id="{version}" role="tabpanel" aria-labelledby="{version}-tab">
    '''

    for file_url in data[version]:
        html_content += f'<p><a href="{file_url}" target="_blank">{file_url}'


        html_content += "<ul>"  # Start a list
        for method in data[version][file_url]:  # Iterate over the list
            html_content += f"{method}"  # Add each method inside <li>


    html_content += '</div>'
    first = False

html_content += """</div></div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body></html>
"""

# Write the HTML to a file.
with open("report.html", "w", encoding="utf-8") as f:
    f.write(html_content)

# Open the file in a browser.
full_path = os.path.abspath("report.html")
url = "file://" + full_path
webbrowser.open(url)
