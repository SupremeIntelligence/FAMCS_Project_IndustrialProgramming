# **FAMCS Java Lab 4: CoffeeMaker Collection Manager**

## **About the Project**
Welcome to the **FAMCS Java Lab 4** project! This project is developed as part of a lab assignment for the Applied Mathematics and Computer Science program at Belarusian State University. The project demonstrates your proficiency in file management, object-oriented programming, and the use of various file formats (including TXT, ZIP, and JAR) in Java.

The core functionality of the project revolves around managing a collection of coffee makers using object-oriented principles, file handling, and archiving. This repository showcases various techniques such as:

- Reading and writing data from/to files.
- Implementing custom file readers and writers.
- Archiving files into ZIP and JAR formats.
- Extracting files from ZIP and JAR archives.
  
## **Features**
- **File Management:** Read and write coffee maker data to and from text files.
- **Data Handling:** Store coffee maker data in a collection, and ensure proper data representation through overridden `toString` methods.
- **Archiving and Unarchiving:** Zip and JAR archiving capabilities for file compression and extraction.
- **JUnit Tests:** Automated tests ensure reliability and correctness of the implemented methods, using the JUnit framework.

## **Technologies Used**
- **Java 11**
- **JUnit 5 (Jupiter)**
- **File I/O** (Java File API)
- **Archiving (ZIP/JAR)**

## **Installation**
To set up the project, follow these steps:

1. **Clone the repository:**
    ```bash
    git clone https://github.com/SupremeIntelligence/FAMCS_JavaLaba4.git
    ```

2. **Navigate to the project directory:**
    ```bash
    cd FAMCS_JavaLaba4
    ```

3. **Import the project into your IDE (e.g., IntelliJ IDEA, Eclipse).**

4. **Build the project:**
    If using Maven, run:
    ```bash
    mvn clean install
    ```

5. **Run the tests:**
    To run the tests, use the test suite provided in the project:
    ```bash
    mvn test   # Maven
    ```


### **File Management**
The main feature of the project is handling coffee maker data stored in files. You can create and read data from a TXT, XML, JSON, YAML files. The project provides functionality to store coffee makers' data such as:

- ID
- Brand
- Model
- Power (W)
- Price (BYN)
- Release Date

### **Archiving and Unarchiving**
You can use the `Archiver` class to zip and jar files, as well as extract them back. The following methods are available:
- `zipArchive(filePath, zipPath)` - Archives a file into a ZIP file.
- `jarArchive(filePath, jarPath)` - Archives a file into a JAR file.
- `ZipUnarchive(zipPath)` - Extracts a ZIP archive.
- `JarUnarchive(jarPath)` - Extracts a JAR archive.

### **Encrypting and Decrypting**
You can use the `Encryptor` class to encrypt files, as well as decrypt them back. The following methods are available:
- `encrypt(filePath)` - Encryptes a file.
- `Decrypt(filePath)` - Decryptes a file.

To ensure everything works correctly, simply run the unit tests after setting up the project.

## **Contributing**
We welcome contributions to the project! If you'd like to add features, fix bugs, or improve documentation, feel free to fork the repository and create a pull request.

### Steps to contribute:
1. Fork the repository.
2. Create a new branch for your feature or bug fix (`git checkout -b feature-name`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Create a new pull request.


### **Contact**
For any questions or feedback, feel free to reach out to me at Telegram (https://t.me/Supreme_Intelligence) or email (supremeintelligence1602@mail.ru) or open an issue on the GitHub repository.

---

Happy coding and enjoy exploring the Coffee Maker Collection Manager! ☕
