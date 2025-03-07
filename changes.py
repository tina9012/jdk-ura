import os
import json
import difflib
import re

pattern = re.compile(r'^(?:\s*$|import\b|@|.*\/\*.*|\s*\*|\s*\}|\b(?!.*\().*)')


def valid_method(content):  
    return not pattern.match(content)

def list_java_files(directory):
    """
    Recursively list Java files in a directory.
    Returns a list of relative file paths (relative to the given directory).
    """
    java_files = []
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith('.java'):
                full_path = os.path.join(root, file)
                relative_path = os.path.relpath(full_path, directory)
                java_files.append(relative_path)
    return java_files

def read_file_lines(filepath):
    """Read a file and return its lines."""
    with open(filepath, 'r') as f:
        return f.readlines()

def extract_new_lines(old_lines, new_lines):
    """
    Use difflib.ndiff to compare two lists of lines.
    Returns the list of added lines (ignoring diff metadata).
    Optionally, you can filter out lines that are imports.
    """
    diff = difflib.ndiff(old_lines, new_lines)
    added = []
    stack = []

    for line in diff:
        # Lines starting with '+ ' are additions (while ignoring the '+ ' marker)
        if line.startswith('+ '):
            content = line[2:].strip()

            if (valid_method(content)):

                added.append(content)

    return added

def compare_version_pair(old_version_dir, new_version_dir):
    """
    Compare all Java files in the new_version_dir to their counterparts in old_version_dir.
    Returns a dictionary mapping relative file paths to a list of new method signatures.
    """
    new_files = list_java_files(new_version_dir)
    file_diff = {}
    
    for rel_path in new_files:

        branch = new_version_dir[len(new_version_dir) - 6:len(new_version_dir)]
        version_num = new_version_dir[len(new_version_dir) - 2:len(new_version_dir)]
        url = f"https://github.com/eisop/jdk/tree/{branch}"

        parts = rel_path.split("\\")
        for i in range(1, len(parts)):
            url += '/'
            url += parts[i]

        new_file_path = os.path.join(new_version_dir, rel_path)
        old_file_path = os.path.join(old_version_dir, rel_path)
        
        old_lines = read_file_lines(old_file_path) if os.path.exists(old_file_path) else []
        new_lines = read_file_lines(new_file_path)
        
        new_methods = extract_new_lines(old_lines, new_lines)
        if new_methods:
            file_diff[url] = new_methods

    return file_diff

# ---------------------------
# Main processing starts here
# ---------------------------

versions = ['master', 'jdk-18', 'jdk-19', 'jdk-20', 'jdk-21', 'jdk-22', 'jdk-23', 'jdk-24']

# Base path where these version folders reside.
base_path = './results/'  # <-- Change this to your actual path

results = {}

for i in range(1, len(versions)):
    old_version = versions[i - 1]
    new_version = versions[i]
    version_pair_key = new_version
    print(f"Comparing {version_pair_key}")
    
    old_dir = os.path.join(base_path, old_version)
    new_dir = os.path.join(base_path, new_version)
    
    diff_for_pair = compare_version_pair(old_dir, new_dir)
    results[version_pair_key] = diff_for_pair

output_json = 'new_methods_diff.json'
with open(output_json, 'w') as out_f:
    json.dump(results, out_f, indent=2)

print(f"Diff results saved to {output_json}")
