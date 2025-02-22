# generator-maddenmanel 🚀

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

**A Yeoman Generator for Enterprise Spring Boot Applications**  
Accelerate your Spring Boot development with Clean Architecture scaffolding, powered by an interactive CLI.

---

## Features ✨

- **CLI-Driven Workflow**: Replicates Spring Initializr functionality with a keyboard-first interactive interface
- **Clean Architecture**: Implements layered architecture patterns from ["Think in Clean Architecture"](https://maddenmanel.github.io/blog/think-in-clean-architecture/)
- **Modular Generators**: 
  - `maddenmanel`: Core Spring Boot service with DDD project
  - `maddenmanel:api`: JSF-based API project scaffolding
  - `maddenmanel:gateway`: RESTful gateway project template
  - `maddenmanel:mvc`: MVC project template
- **Version Compatibility**: Stable foundation using battle-tested dependencies (Node 8.17.0 + Yeoman 3.1.0)

---

## Prerequisites ⚙️

- Node.js Version Manager ([nvm](https://github.com/nvm-sh/nvm))
  - **Windows**: [nvm-windows](https://github.com/coreybutler/nvm-windows)
  - **macOS/Linux**: 
    ```bash
    curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.5/install.sh | bash
    ```
- Node.js 8.17.0 (LTS/Dubnium)

---

## Installation 🛠️

1. **Configure Node Version**:
   ```bash
   nvm install 8.17.0
   nvm use 8.17.0
   ```

2. **Install Yeoman**:
   ```bash
   npm install -g yo@3.1.0
   ```

3. **Install Generator**:
   ```bash
   npm install -g generator-maddenmanel
   ```

## Usage 📦

1. **Generate a new project**:
   ```bash
   yo maddenmanel
   ```

2. **Generate a new API project**:
   ```bash
   yo maddenmanel:api
   ```

3. **Generate a new gateway project**:
   ```bash
   yo maddenmanel:gateway
   ```  

4. **Generate a new mvc project**:
   ```bash
   yo maddenmanel:mvc
   ```

## License 📝

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.