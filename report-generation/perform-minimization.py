import os
import subprocess
import shutil
import platform

#update these absolute paths for your environment
JDK_REPO_PATH = "path to jdk repo"
CHECKER_FRAMEWORK_REPO_PATH = "path to checker framework repo"
RESULTS_BASE_DIR = os.path.join("report-generation", "results")

#define gradle commands for environment 
if platform.system() == "Windows":
    gradle_task_cmd  = ["cmd", "/c", "gradlew.bat", "copyAndMinimizeAnnotatedJdkFiles"]
else:
    gradle_task_cmd  = ["sh", "./gradlew", "copyAndMinimizeAnnotatedJdkFiles"]

#move into JDK repository
try:
    os.chdir(JDK_REPO_PATH)
except FileNotFoundError:
    print(f"could not find {JDK_REPO_PATH}")
    exit(1)

#fetch remote branches
subprocess.run(["git", "fetch", "--all", "--prune"], check=True)

#get list of remote branches using ls-remote
result = subprocess.run(["git", "ls-remote", "--heads", "origin"],
                        capture_output=True, text=True)
branches = [line.split("\t")[1].replace("refs/heads/", "") 
            for line in result.stdout.splitlines() if line.strip()]

#processing
for jdk_version in branches:
    print(f"\nprocessing version: {jdk_version}")
    
    # Checkout the branch and reset to the remote version
    try:
        subprocess.run(["git", "checkout", jdk_version], check=True)
        subprocess.run(["git", "reset", "--hard", f"origin/{jdk_version}"], check=True)
    except subprocess.CalledProcessError as e:
        print(f"Error checking out branch {jdk_version}: {e.stderr}")
        continue
    
    print(f"Checked out JDK version: {jdk_version}")

    #move into checker-framework to run minimization
    try:
        os.chdir(CHECKER_FRAMEWORK_REPO_PATH)
    except FileNotFoundError:
        print(f"Could not find {CHECKER_FRAMEWORK_REPO_PATH}")
        exit(1)
    
    # Run the minimization Gradle task
    print(f"Running Gradle task for {jdk_version}...")
    try:
        subprocess.run(gradle_task_cmd, check=True)
        print(f"Gradle task done for {jdk_version}!")
    except subprocess.CalledProcessError as e:
        print(f"Error running for {jdk_version}: {e.stderr}")
        exit(1)

    # get output directory of the gradle task
    OUTPUT_DIR = os.path.join(CHECKER_FRAMEWORK_REPO_PATH, "framework", "build", "generated", "resources", "annotated-jdk")
    if not os.path.exists(OUTPUT_DIR):
        print("error finding otuput directory")
        os.chdir(JDK_REPO_PATH)
        continue

    # Define the final results directory for this branch
    result_dir = os.path.join(RESULTS_BASE_DIR, jdk_version)
    if os.path.exists(result_dir):
        shutil.rmtree(result_dir)
    os.makedirs(result_dir, exist_ok=True)
    
    #move the output directory into results directory
    shutil.move(OUTPUT_DIR, result_dir)
    print(f"Result stored in: {result_dir}")

    #switch back to JDK repo to process next version
    os.chdir(JDK_REPO_PATH)

print("\nAll minimizations completed!")
