import json
import os
import csv

csv_folder = "csv_reports"
os.makedirs(csv_folder, exist_ok=True)

with open("new_methods_diff.json", "r", encoding="utf-8") as f:
    data = json.load(f)

for version in data:
    current_csv = os.path.join(csv_folder, f"{version}_methods.csv")
    with open(current_csv, "w", newline='', encoding="utf-8") as csvfile:

        fieldnames = ["version", "file_url", "method", "checked"]
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()

        for file_url in data[version]:
            for method in data[version][file_url]:
                writer.writerow({
                    "version": version,
                    "file_url": file_url,
                    "method": method,
                    "checked": "False"
                })
    print(f"Created CSV: {current_csv}")
