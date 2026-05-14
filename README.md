# Triangle Geometry Processing Application

## 📌 Overview
This Java application performs geometric processing on triangles. It reads triangle data from a CSV file, validates and classifies each triangle, caches computed attributes such as area and perimeter, and supports filtering and sorting operations. It's designed using clean code principles, modular architecture, and modern Java features (Java 21).

---

## 🔧 Features
- **CSV Input Reader**: Parses triangle definitions from CSV.
- **Validation**: Detects invalid or malformed triangles.
- **Geometry Analysis**: Computes area and perimeter, and classifies triangle types (right, acute, obtuse, equilateral, isosceles).
- **Specification Pattern**: Used for triangle type filtering.
- **Warehouse Caching**: Stores computed area and perimeter.
- **Sorting**: Sorts triangles by area using a service layer.
- **Logging**: Integrated via SLF4J + Log4j2.
- **Unit Testing**: Includes comprehensive tests with TestNG and soft asserts.

---

## 🧱 Project Structure
```
com.ay.triangles
├── app
│   └── MainApp.java                # Entry point
│   └── TriangleApplicationRunner   # Application logic class
├── entity
│   └── Point.java
│   └── Triangle.java
├── exception
│   └── FigureException.java
│   └── FigureParseException.java
│   └── InvalidTriangleException.java
├── factory
│   └── TriangleFactory.java
├── registry
│   └── TriangleRegistry.java
├── reader
│   └── TriangleCsvReader.java
├── repository
│   └── TriangleRepository.java
├── service
│   └── TriangleService.java
│   └── TriangleSorter.java
│   └── TriangleValidator.java
├── specification
│   └── TriangleSpecification.java
│   └── TriangleSelector.java
│   └── RightSpecification.java
│   └── AcuteSpecification.java
│   └── ObtuseSpecification.java
│   └── EquilateralSpecification.java
│   └── IsoscelesSpecification.java
├── warehouse
│   └── TriangleData.java
│   └── TriangleEventListener.java
│   └── Warehouse.java
├── util
│   └── MathConstants.java
├── test
│   └── TriangleServiceTest.java
│   └── TriangleSorterTest.java
│   └── TriangleValidatorTest.java
│   └── WarehouseTest.java
└── resources
    └── log4j2.xml
```

---

## 🚀 Running the Application

### From IntelliJ IDEA:
1. Right-click `MainApp.java`
2. Choose `Run 'MainApp.main()'`

### From Terminal:
```bash
mvn compile exec:java
```

### CSV input path:
- Default path: `input/triangles.csv`
- Custom path: pass `--file=...`

Examples:
```bash
mvn compile exec:java -Dexec.args="--file=input/triangles.csv"
mvn compile exec:java -Dexec.args="--file=path/to/triangles.csv --mode=graphics --type=acute"
```

### Output modes:
- Basic mode (default):
```bash
mvn compile exec:java
```
- Graphics mode (ASCII triangle rendering in console):
```bash
mvn compile exec:java -Dexec.args="--mode=graphics"
```

### Triangle type filter for the classification section:
- Default type is `right`
- Supported values: `right`, `acute`, `obtuse`, `equilateral`, `isosceles`

Examples:
```bash
mvn compile exec:java -Dexec.args="--type=acute"
mvn compile exec:java -Dexec.args="--mode=graphics --type=isosceles"
```

### Vertex labels in graphics mode:
- Default labels mode is `on`
- Supported values: `on`, `off`

Examples:
```bash
mvn compile exec:java -Dexec.args="--mode=graphics --labels=off"
mvn compile exec:java -Dexec.args="--mode=graphics --type=right --labels=on"
```

Ensure the input file exists at:
```
input/triangles.csv
```
Or pass a custom path using `--file=...`.

---

## 🧪 Running Tests
```bash
mvn test
```

---

## 🛠 Dependencies
- Java 21
- Maven
- TestNG
- SLF4J + Log4j2

---

## 🧼 Code Practices Followed
- Domain-Driven Design
- Specification & Factory Pattern
- Clean separation of concerns
- `Optional` over nulls
- Immutability where applicable
- Soft asserts in unit tests
- Centralized exception handling

---

## 👀 Sample Output
```plaintext
📥 Reading triangles from file...
✅ All valid triangles:
- Triangle{T1: (0,0), (1,0), (0,1)}
...
🧠 Cached data from Warehouse:
T1 | area = 0.50 | perimeter = 3.41
...
🔍 Right triangles:
Triangle{T1: ...}
...
📊 Sorting right triangles by area:
T1 | area = 0.50 | perimeter = 3.41
...
```

---

## 📂 Logging
Logs are managed using `log4j2.xml` under `resources`. You can configure output file, format, and log level there.

---

## 📄 License
MIT License.

---

## 👨‍💻 Author
Artsiom Yurkevich – Senior AQA and Java Engineer, 2025

---

## 📌 To Do
- Add triangle drawing to SVG
- Export results to JSON/CSV
- Add GUI (JavaFX or Web)