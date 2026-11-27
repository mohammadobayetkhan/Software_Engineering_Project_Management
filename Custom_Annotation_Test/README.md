<div align="center">

# 🥾 CampusBoot

### A tiny Spring-Boot-style framework built with **Custom Annotations** and **Java Reflection**

![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=openjdk&logoColor=white)
![Dependencies](https://img.shields.io/badge/Dependencies-None-brightgreen)
![Reflection](https://img.shields.io/badge/Powered%20by-Reflection-blue)
![Status](https://img.shields.io/badge/Build-Passing-success)

*Annotations are not magic — they are just metadata plus reflection.* ✨

</div>

---

## 📑 Table of Contents

1. [📖 About the Project](#-about-the-project)
2. [✨ Features](#-features)
3. [🗂️ Project Structure](#️-project-structure)
4. [🏷️ The Annotations](#️-the-annotations)
5. [⚙️ The Engine](#️-the-engine)
6. [🔄 How It Works](#-how-it-works)
7. [🚀 Getting Started](#-getting-started)
8. [🧪 Sample Output](#-sample-output)
9. [🧠 Reflection Questions](#-reflection-questions)
10. [📝 Design Notes](#-design-notes)
11. [📊 Grading Checklist](#-grading-checklist)

---

## 📖 About the Project

At the **Campus IT Lab**, every project repeated the same boilerplate: creating objects by hand, reading config values, validating data, and wiring URLs to methods.

Spring Boot solves this with annotations, but the lab cannot use Spring. So this project builds a miniature framework, **CampusBoot**, that does the same jobs using only the JDK:

| Spring Boot idea | CampusBoot version |
|---|---|
| Component scanning / IoC container | `Container.scan(...)` |
| `@Value` property injection | `Container.injectValues(...)` |
| Bean Validation (`@Min`, `@Max`) | `Validator.validate(...)` |
| `@GetMapping` request routing | `Router.dispatch(...)` |

> ☕ **Language:** Java 11+ &nbsp;|&nbsp; 📦 **External libraries:** none &nbsp;|&nbsp; ⏱️ **Level:** Standard

---

## ✨ Features

- 🏷️ **Four custom annotations** with proper `@Target`, `@Retention` and defaults
- 🏭 **Mini IoC container** that creates and stores beans by name
- 💉 **Value injection** into private fields, with automatic `String` / `int` conversion
- ✅ **Validator** that checks `@Range` constraints and reports readable messages
- 🧭 **Router** that maps URL paths to annotated methods and returns `404` when nothing matches
- 🧼 **Zero dependencies** — only `java.lang.reflect` and the standard library

---

## 🗂️ Project Structure

```
CampusBoot/
├── 📄 README.md             ← you are here
├── 📝 answers.txt           ← Part E: reflection answers
│
├── 🏷️ Annotations (Part A)
│   ├── Component.java       ← marks a class as a bean
│   ├── Value.java           ← injects a config value into a field
│   ├── Range.java           ← min/max validation for int fields
│   └── Route.java           ← maps a URL path to a method
│
├── ⚙️ Engine (Parts B, C, D)
│   ├── Container.java       ← IoC container + value injection
│   ├── Validator.java       ← @Range validator
│   └── Router.java          ← @Route dispatcher
│
└── 🎓 Starter code (provided)
    ├── GradeController.java ← annotated bean with 2 routes
    ├── ReportPrinter.java   ← intentionally NOT annotated
    ├── Student.java         ← fields validated by @Range
    └── Main.java            ← demo entry point
```

---

## 🏷️ The Annotations

> All four are declared with `@Retention(RetentionPolicy.RUNTIME)` so reflection can see them while the program runs.

| Annotation | 🎯 `@Target` | 🧩 Elements | 📌 Purpose |
|---|---|---|---|
| **`@Component`** | `TYPE` (class) | `String value() default ""` | Marks a class to be created and managed by the container |
| **`@Value`** | `FIELD` | `String value()` | Injects the config entry with that key into the field |
| **`@Range`** | `FIELD` | `int min()`, `int max()`, `String message() default "Value out of range"` | Validates that an `int` field lies within `[min, max]` |
| **`@Route`** | `METHOD` | `String path() default ""`, `String value() default ""` | Maps a URL path to a method |

### 🔎 Why does `@Route` have both `path()` and `value()`?

The specification names the element `path()`, but the provided starter code writes `@Route("/hello")`. Java only allows that shorthand for an element called `value()`. To satisfy both, `@Route` declares both elements and the router accepts either:

```java
@Route("/hello")          // shorthand → value()
@Route(path = "/hello")   // explicit  → path()
```

---

## ⚙️ The Engine

### 🏭 `Container` — Part B

| Method | What it does |
|---|---|
| `void scan(Class<?>... classes)` | For every class with `@Component`, creates an instance through the no-arg constructor and stores it in a `Map<String,Object>`. Prints `Registered` or `Skipped`. |
| `void injectValues(Map<String,String> config)` | For each bean, finds `@Value` fields, calls `setAccessible(true)`, converts the text to `String` or `int`, and sets the field. |
| `Object getBean(String name)` | Returns the bean registered under that name. |

**Bean naming rule**

```
@Component("gradeController")  →  "gradeController"      (explicit name)
@Component                     →  "reportPrinter"        (class name, first letter lowercase)
```

The map is a `LinkedHashMap`, so beans keep their registration order.

### ✅ `Validator` — Part C

```java
static List<String> validate(Object obj)
```

Loops over every `@Range` `int` field. When the value is outside `[min, max]`, it adds a violation in this format:

```
fieldName - message (actual: value)
```

An empty list means the object is valid.

### 🧭 `Router` — Part D

```java
String dispatch(Container c, String path)
```

Searches every bean for a method whose `@Route` matches the requested path, invokes it (no parameters, returns `String`) and returns the result. If nothing matches:

```
404 Not Found: /the/path
```

---

## 🔄 How It Works

```
                 ┌──────────────────────────────┐
                 │            Main              │
                 └──────────────┬───────────────┘
                                │
        ┌───────────────────────┼────────────────────────┐
        ▼                       ▼                        ▼
┌───────────────┐      ┌─────────────────┐      ┌─────────────────┐
│  Container    │      │    Validator    │      │     Router      │
│───────────────│      │─────────────────│      │─────────────────│
│ scan()        │      │ validate(obj)   │      │ dispatch(c, p)  │
│ injectValues()│      │                 │      │                 │
└───────┬───────┘      └────────┬────────┘      └────────┬────────┘
        │ reads                 │ reads                  │ reads
        ▼                       ▼                        ▼
   @Component              @Range                    @Route
   @Value                  (fields)                  (methods)
        └───────────────────────┴────────────────────────┘
                     via Java Reflection at runtime
```

**Step by step**

1. 🔍 `scan` inspects each class with `getAnnotation(Component.class)`.
2. 🏗️ Annotated classes are instantiated with `getDeclaredConstructor().newInstance()`.
3. 💉 `injectValues` walks `getDeclaredFields()` and sets private fields after `setAccessible(true)`.
4. 🛡️ `Validator` reads each `@Range` field value with `Field.getInt(...)` and compares it to the bounds.
5. 🚦 `Router` walks `getDeclaredMethods()`, matches `@Route`, and calls `Method.invoke(...)`.

---

## 🚀 Getting Started

### ✅ Prerequisites

- ☕ JDK 11 or newer (`java -version` to check)

### 🔨 Compile

```bash
mkdir out
javac -d out *.java
```

### ▶️ Run

```bash
java -cp out Main
```

### 🪟 Windows (PowerShell / CMD)

```powershell
mkdir out
javac -d out *.java
java -cp out Main
```

---

## 🧪 Sample Output

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

### 🧾 Input used

| ⚙️ Config key | Value |
|---|---|
| `app.name` | `CampusBoot` |
| `app.passMark` | `40` |

| 🎓 Student | Score | Semester | Result |
|---|---|---|---|
| 1 | 120 | 5 | ❌ score out of range |
| 2 | 85 | 5 | ✅ OK |
| 3 | 70 | 15 | ❌ semester out of range (default message) |

| 🌐 Request | Result |
|---|---|
| `/hello` | `Welcome to CampusBoot` |
| `/status` | `Pass mark is 40` |
| `/unknown` | `404 Not Found: /unknown` |

---

## 🧠 Reflection Questions

> Full answers are in [`answers.txt`](answers.txt). Short version:

**1️⃣ What if `@Retention(RUNTIME)` is forgotten?**
The default retention is `CLASS`, so the annotation is not visible at runtime. `getAnnotation(...)` returns `null`, so nothing is registered, injected, validated or routed.

**2️⃣ Mapping to Spring Boot**

| CampusBoot | Spring Boot |
|---|---|
| `@Component` | `@Component` / `@Service` / `@Repository` |
| `@Value` | `@Value("${key}")` |
| `@Range` | `@Min` / `@Max` (Bean Validation) |
| `@Route` | `@GetMapping` / `@RequestMapping` |

Real Spring also provides bean-to-bean dependency injection (`@Autowired`), classpath scanning, scope management and AOP proxies, which CampusBoot does not.

**3️⃣ Why is `setAccessible(true)` powerful *and* risky?**
It lets the framework touch private fields without setters, but it breaks encapsulation and can bypass validation and invariants. It can also fail on modular Java if the package is not opened.

---

## 📝 Design Notes

- 🛡️ **Safe conversions:** an invalid number in the config prints a `Skipped` message instead of crashing the program.
- 🔑 **Missing keys:** a `@Value` key absent from the config is skipped with a message.
- 🧱 **Unsupported types:** only `String` and `int` are supported for injection, as required.
- 📦 **Default package:** every file lives in the default package to match the starter code.
- 🔧 **Starter-code tweak:** `Main.java` only had the missing `java.util` imports added so it compiles.

---

## 📊 Grading Checklist

| Part | Task | Points | Status |
|---|---|:---:|:---:|
| **A** | Annotations with correct `@Target`, `@Retention`, defaults | 2 | ✅ |
| **B** | Container: scan, naming, `@Value` injection, `int` conversion | 3 | ✅ |
| **C** | Validator: `@Range` check and message format | 2 | ✅ |
| **D** | Router: `@Route` dispatch and `404` handling | 2 | ✅ |
| **E** | Three reflection answers | 1 | ✅ |
| | **Total** | **10** | 🎉 |

---

<div align="center">

### 👤 Author

**Your Name** · Student ID: `YOUR_ID`

*Built for the Campus IT Lab assignment — Java Reflection & Custom Annotations* 🥾

⭐ If you found this helpful, give the repo a star! ⭐

</div>
