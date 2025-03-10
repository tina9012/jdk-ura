import os
import json
import difflib
import re

#matches for empty lines, "import", @ (annotations), any comment "/*", and closing braces (})
pattern = re.compile(r'^(?:\s*$|import\b|@|.*\/\*.*|\s*\*|\s*\})')

#https://stackoverflow.com/questions/8625991/use-python-os-walk-to-identify-a-list-of-files
#uses os.walk to get all java files in every subdirectory of results (after minimization is performed)

def list_java_files(directory):
    java_files = []
    for root, _, files in os.walk(directory):
        for file in files:
            full_path = os.path.join(root, file)
            relative_path = os.path.relpath(full_path, directory)
            java_files.append(relative_path)

    #list of relative path of every file
    return java_files

def read_file_lines(filepath):

    with open(filepath, 'r', encoding='utf-8') as f:
        return f.readlines()

#https://docs.python.org/3/library/difflib.html
#uses python's difflib to compare two lines at a time 
def extract_new_lines(old_lines, new_lines):

    diff = difflib.ndiff(old_lines, new_lines)
    newly_added = []

    for line in diff:
        #new lines will start with + 
        if line.startswith('+ '):
            content = line[2:].strip()
            #makes sure a new line passes regex check before appending
            if not pattern.match(content):
                newly_added.append(content)
    return newly_added

def compare_version_pair(old_version_dir, new_version_dir):

    new_files = list_java_files(new_version_dir)
    file_diff = {}


    for rel_path in new_files: 
        #create github URL
        branch = os.path.basename(new_version_dir)
        url = f"https://github.com/eisop/jdk/tree/{branch}"
        parts = rel_path.split(os.path.sep)
        url = url + '/' + '/'.join(parts[1:])

        #get the full file paths for the versions being compared
        new_file_path = os.path.join(new_version_dir, rel_path)
        old_file_path = os.path.join(old_version_dir, rel_path)
        
        if os.path.exists(old_file_path):
            old_lines = read_file_lines(old_file_path)
        else:
            old_lines = []
        new_lines = read_file_lines(new_file_path)
        
        #uses difflib to find the differences
        new_methods = extract_new_lines(old_lines, new_lines)
        if new_methods:
            file_diff[url] = new_methods

    return file_diff

#processing
versions = ['master', 'jdk-18', 'jdk-19', 'jdk-20', 'jdk-21', 'jdk-22', 'jdk-23', 'jdk-24']

base_path = './results/'

results = {}

#starts by comparing jdk-18 to master
for i in range(1, len(versions)):
    
    old_version = versions[i - 1]
    new_version = versions[i]

    version_pair_key = new_version
    
    old_dir = os.path.join(base_path, old_version)
    new_dir = os.path.join(base_path, new_version)
    
    current_diff = compare_version_pair(old_dir, new_dir)
    results[version_pair_key] = current_diff

output_json = 'new_methods_diff.json'
with open(output_json, 'w', encoding='utf-8') as out_f:
    json.dump(results, out_f, indent=2)