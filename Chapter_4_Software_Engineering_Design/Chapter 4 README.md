# Software Engineering: Design — OBE Question Paper

An outcome-based (OBE) midterm / class test on **Software Design**.

| Item | Detail |
|---|---|
| Course | Software Engineering |
| Source | Lecture slides `4-SoftEng-Design.pdf` |
| Full marks | 40 |
| Time | 1 hour |
| Total questions | 15 |

---

## 1. Course Outcomes (CO)

> The slides do not list official COs. These five are derived from the lecture topics. Replace them with your official CO statements if they differ.

| CO | Statement |
|---|---|
| CO1 | Explain fundamental software design concepts (modularity, abstraction, encapsulation, coupling, cohesion) |
| CO2 | Describe software architecture and architectural styles |
| CO3 | Explain the intent, structure, benefits and liabilities of GoF design patterns |
| CO4 | Describe enterprise application architecture patterns (domain, data source and presentation logic) |
| CO5 | Explain design quality metrics and user interface design principles |

---

## 2. Paper Structure

| Type | Questions | Marks |
|---|---|---|
| Short answer (1–2 marks each) | Q1–Q7 | 10 |
| Short answer (3 marks each) | Q8–Q12 | 15 |
| Descriptive (5 marks each) | Q13–Q15 | 15 |
| **Total** | **15 questions** | **40** |

---

# QUESTION PAPER

**Time:** 1 hour | **Full Marks:** 40 | Answer all questions.

| Q | Question | Marks | CO |
|---|---|---|---|
| 1 | Name the design step that describes how software is organized into components. | 1 | CO1 |
| 2 | Define **coupling** in software design. | 1 | CO1 |
| 3 | Which type of cohesion is considered the strongest (best)? | 1 | CO1 |
| 4 | The command `ls -l \| grep "Aug" \| sort` follows a well-known architectural style. (a) Name the style. (b) State its main idea in one sentence. | 2 | CO2 |
| 5 | In the Model–View–Controller (MVC) style, what is the role of the **controller**? | 1 | CO2 |
| 6 | (a) What does the **CBO** (Coupling Between Object classes) metric measure? (b) A class `Controller` has attributes `boolean alarm`, `Bell b` and `Light l`, and uses no other classes. State CBO(Controller). | 2 | CO5 |
| 7 | (a) Name the UI modality in which the user selects a command from a list of commands. (b) Name any **two** other UI modalities. | 2 | CO5 |
| 8 | Distinguish between **architectural design** and **detailed design**. Why is recording the rationale of design decisions important? | 3 | CO1 |
| 9 | Explain any **three** UI design principles (e.g., learnability, consistency, minimal surprise, recoverability) with a brief reason for each. | 3 | CO5 |
| 10 | Explain the intent of the **Strategy** pattern. State **two** benefits and **one** liability. | 3 | CO3 |
| 11 | Explain the difference between **Page Controller** and **Front Controller**. When is each suitable? | 3 | CO4 |
| 12 | Explain what **WMC** and **LCOM** measure. Name two ways to measure method complexity for WMC. | 3 | CO5 |
| 13 | Define **software architecture**. Describe the **layered**, **event-based** and **MVC** architectural styles, stating the main idea of each. | 5 | CO2 |
| 14 | Describe the **Façade** pattern: (a) its intent (1), (b) its participants/structure (2), (c) its benefits and liabilities (2). | 5 | CO3 |
| 15 | Explain how **Domain Model**, **Service Layer** and **Data Mapper** fit into a layered enterprise application architecture. State the responsibility of each. | 5 | CO4 |

---

## 3. CO Coverage

| CO | Questions | Marks |
|---|---|---|
| CO1 | Q1, Q2, Q3, Q8 | 6 |
| CO2 | Q4, Q5, Q13 | 8 |
| CO3 | Q10, Q14 | 8 |
| CO4 | Q11, Q15 | 8 |
| CO5 | Q6, Q7, Q9, Q12 | 10 |
| **Total** | | **40** |

---

## 4. Answer Key and Marking Guide

| Q | Expected answer / points | Marks |
|---|---|---|
| 1 | Architectural design (also called high-level or top-level design). | 1 |
| 2 | A measure of the interdependence among components in a program. | 1 |
| 3 | Functional cohesion. | 1 |
| 4 | (a) Pipes and filters (1). (b) Filters perform computations and are connected by pipes; the output stream of one filter is the input of the next (1). | 2 |
| 5 | Takes user input, manipulates the model and causes the view to update appropriately. | 1 |
| 6 | (a) The number of classes used by a class; inheritance is typically not counted (1). (b) CBO(Controller) = 2 (Bell and Light) (1). | 2 |
| 7 | (a) Menu selection (1). (b) Any two of: question-answer, direct manipulation, form fill-in, command language, natural language (1). | 2 |
| 8 | Architectural design = how software is organized into components; detailed design = desired behavior of those components (1.5). Recorded rationale enhances long-term maintainability (1.5). | 3 |
| 9 | Any three principles, each named (0.5) and explained with a reason (0.5). | 3 |
| 10 | Intent: define a family of algorithms, encapsulate each and make them interchangeable (1). Benefits, any 2: isolates Context from algorithm details, new strategies without changing Context, avoids complex conditionals, swap at runtime (1). Liabilities, any 1: common interface hard to specify, more objects (1). | 3 |
| 11 | Page Controller: one controller per page/action, suits simple navigation logic (1.5). Front Controller: handles all requests through a web handler plus a command hierarchy, suits complex navigation logic (1.5). | 3 |
| 12 | WMC = sum of method complexities in a class (1). Measured by LOC or McCabe (conditions + 1) (1). LCOM = lack of cohesion of methods, based on method pairs that share or do not share attributes (1). | 3 |
| 13 | Definition: set of structures (elements, relations, properties) needed to reason about the system (1). Layered: components organized in layers, each depends only on the next layer (1). Event-based: producers and consumers communicate through event distribution (1). MVC: model holds domain information, view displays it, controller takes input and updates model and view (1). Clarity (1). | 5 |
| 14 | Intent: unified, higher-level interface to a set of subsystem interfaces (1). Structure: Façade delegates to subsystem classes; subsystem classes do the work and do not know the Façade (2). Benefits: shields clients, easier to use, subsystem still accessible. Liability: can become a God class (2). | 5 |
| 15 | Domain Model: object model with behavior and data for complex business logic (1.5). Service Layer: defines the application boundary and available operations, avoiding duplicated interaction logic across interfaces (1.5). Data Mapper: moves data between objects and the database while keeping them independent (1.5). Placement in the layers (0.5). | 5 |
