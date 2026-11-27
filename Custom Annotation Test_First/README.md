#  CampusBoot
### A Tiny Annotation-Driven Framework Built with Java Reflection

> A small, dependency-free demonstration of how annotations and reflection can power component registration, configuration injection, validation, and routing—the core ideas behind frameworks such as Spring.

---

## 📚 Assignment Overview

**Topic:** Java Reflection & Custom Annotations  
**Language:** Java 11+ (the provided `List.of` requires Java 9 or newer)  
**Dependencies:** None (standard Java libraries only)

CampusBoot demonstrates four custom runtime annotations and a mini-framework that interprets them at runtime.

## ✨ Features

- 🧩 **IoC container:** registers classes marked with `@Component`.
- ⚙️ **Configuration injection:** injects configuration values into fields using `@Value`, supporting `String` and `int`.
- ✅ **Validation:** checks `@Range` constraints and reports violations.
- 🌐 **Routing:** maps paths to methods marked with `@Route`.
- 🔍 **Reflection:** uses Java's `Class`, `Field`, and `Method` APIs.

## 📁 Project Structure

```text
CampusBoot/
├── Component.java
├── Value.java
├── Range.java
├── Route.java
├── Container.java
├── Validator.java
├── Router.java
├── GradeController.java
├── ReportPrinter.java
├── Student.java
├── Main.java
└── answers.txt
```

All Java source files are in the root folder, so no package setup is required.

## 🚀 Run the Project

### Requirements
- Java 11 or newer
- A terminal / command prompt

### 1. Clone the repository

```bash
git clone https://github.com/YOUR-USERNAME/CampusBoot.git
cd CampusBoot
```

### 2. Compile

```bash
javac *.java
```

### 3. Run

```bash
java Main
```

## 🖥️ Expected Output

```text
[Container] Registered: gradeController (GradeController)
[Container] Skipped: ReportPrinter (no @Component)
[Container] Injected: app.name -> GradeController.appName
[Container] Injected: app.passMark -> GradeController.passMark
--- Validation ---
VIOLATION: score - Score must be between 0 and 100 (actual: 120)
OK
VIOLATION: semester - Value out of range (actual: 15)
--- Routing ---
[Router] /hello -> Welcome to CampusBoot
[Router] /status -> Pass mark is 40
[Router] /unknown -> 404 Not Found: /unknown
```

## 🧠 How It Works

### 1. Custom Annotations
| Annotation | Target | Role |
|---|---|---|
| `@Component` | Class | Marks a class for bean registration |
| `@Value` | Field | Declares a configuration key to inject |
| `@Range` | Field | Declares an integer range constraint |
| `@Route` | Method | Associates a URL path with a method |

Each annotation uses `@Retention(RetentionPolicy.RUNTIME)` so it can be inspected at runtime.

### 2. Container
The container scans the supplied classes, skips unannotated classes, instantiates components using their no-argument constructor, and stores instances in a bean map. It then reads `@Value` fields and injects configuration data.

### 3. Validator
The validator inspects fields for `@Range`. It reads each annotated integer field and reports values outside the specified minimum and maximum.

### 4. Router
The router searches registered beans for `@Route` methods. When the path matches, it invokes the method and returns its String result. If no route matches, it returns a 404 message.

## 📄 Reflection Questions

See [`answers.txt`](answers.txt) for the assignment's short-answer questions.

## 🧪 Test Cases Included

- Component registration and skipping an unannotated class
- Injecting application name and pass mark
- Valid score and semester values
- Out-of-range score and semester values
- Matching `/hello` and `/status` routes
- Unmatched `/unknown` route

## 🎓 Learning Purpose

CampusBoot is an educational miniature—not a production framework. It illustrates annotation metadata and reflection-based behavior, while real Spring includes richer dependency injection, lifecycle handling, configuration processing, web infrastructure, and validation integration.

---

<div align="center">
</div>
