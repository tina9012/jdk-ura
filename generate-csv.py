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

        fieldnames = ["version", "file_url", "method", "id", "checked"]

        #https://docs.python.org/3/library/csv.html
        #creates a new writer object 
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        #since methods are grouped by file_url in the methods json
        for file_url, file_data in data[version].items():
            new_changes = file_data.get("new_changes", {})
            new_classes = new_changes.get("new_classes", [])
            new_methods = new_changes.get("new_methods", [])

            # Write new class methods
            for cls in new_classes:
                declaration = cls.get("declaration").get("content")
                writer.writerow({
                    "version": version, 
                    "file_url": file_url,
                    "id": cls.get("declaration").get("id"),
                    "method": cls.get("declaration").get("content"),
                    "checked": "False"
                })
                for method in cls.get("methods"):
                    writer.writerow({
                        "version": version,
                        "file_url": file_url,
                        "id": method.get("id"),
                        "method": method.get("content"),
                        "checked": "False"
                    })

            # Write new methods in existing classes
            for method in new_methods:
                writer.writerow({
                    "version": version,
                    "file_url": file_url,
                    "id": method.get("id"),
                    "method": method.get("content"),
                    "checked": "False"
                })


    print(f"done")
