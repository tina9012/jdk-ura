import json
import os
import csv

csv_folder = "csv_reports"
os.makedirs(csv_folder, exist_ok=True)

with open("new_methods_diff.json", "r", encoding="utf-8") as f:
    data = json.load(f)

for version in data:
    #gets current csv path 
    current_csv = os.path.join(csv_folder, f"{version}_methods.csv")
    with open(current_csv, "w", newline='', encoding="utf-8") as csvfile:

        fieldnames = ["version", "file_url", "method", "checked"]

        #https://docs.python.org/3/library/csv.html
        #creates a new writer object 
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        #since methods are grouped by file_url in the methods json
        for file_url in data[version]:
            for method in data[version][file_url]:

                #initialize all checks to false on the first time
                writer.writerow({
                    "version": version,
                    "file_url": file_url,
                    "method": method,
                    "checked": "False"
                })

    print(f"done")
