# Contributing to Petra Examples

First off, thank you for taking the time to contribute! It's people like you who make formal methods accessible to everyone. 

By contributing to this project, you agree that your contributions will be licensed under the **Apache License 2.0**.

## 🛠️ How to Contribute

To ensure a smooth verification process, please follow these steps:

### 1. Fork and Clone
Fork the repository to your own GitHub account and clone it locally.

### 2. Setup Your Environment
Follow the installation instructions in the [README.md](README.md) to install the `petra-ast` dependency locally via Maven.

### 3. Create a Branch
Create a branch for your example or fix:
```bash
git checkout -b feature/my-cool-example
```

### 4. Code & Local Validation
Write your Petra program (in Java). Before pushing, run a local Maven build to ensure there are no syntax errors:
```bash
mvn clean install
```

### 5. The "Java-Only" Rule
Our build server is strictly configured for security and integrity. 
* **DO NOT** modify any files outside of the `src/` directory (specifically `.java` files).
* Changes to `pom.xml`, `.gitignore`, or files in the `lib/` folder will cause the verification build to fail immediately.

### 6. Commit and Push
Keep your commits clean and descriptive.
```bash
git add src/main/java/your/package/Example.java
git commit -m "Add new Petra example for [Feature]"
git push origin feature/my-cool-example
```

### 7. Raise a Pull Request
Once you raise a PR, the **Petra Verifier Build Server** will trigger. 
* **Wait for the checks:** The formal verification analysis will run automatically.
* **Check the status:** If the build fails, check the logs—it might be a formal verification error rather than a syntax error!

## 🚦 Rate Limits
Please be mindful that the build server is shared. We currently limit builds to **3 per hour per user**. If you hit this limit, use the time to double-check your logic locally!

## 💡 Tips for Great Examples
* Keep your examples focused on a single Petra concept.
* Add comments to explain the formal properties you are trying to verify.
* Reference the documentation at [www.petracode.co.uk](https://www.petracode.co.uk) where applicable.

Happy verifying!
