# JS-TSP

This repository contains the implementation to solve the Job Sequencing and Tool Switching Problem.
#### Steps to launch solver:

1. **Clone the Repository**:
   Open a terminal and run the following command to clone the repository:
   ```bash
   git clone https://github.com/<user>/JS-TSP.git
    ```

2. **Clone the Repository**:
   Launch IntelliJ IDEA.
   Select File > Open and navigate to the JS-TSP folder you cloned.
   Open the `pom.xml` file.

3. **Running the tests**:

   From the IntelliJ IDEA editor, navigate to the `src/test/java` directory.
   Right-click then select `Run 'All Tests'` to run all the tests.

   From the terminal, navigate to the root directory of the project and run the following command:
    ```bash
    mvn test
    ```
4. **Running the solver**:
   From the terminal, navigate to the root directory of the project and run the following command:
    ```bash
   java -jar target/ToolSwicthingProblemMaven-1.0-SNAPSHOT.jar -i path_to_file -p number_of_problem -s type_of_search 
    ```
   Replace path_to_file by the path of the instance that you want to solve, 
   number_of_problem by "Problem #" where the # must be replaced by the number of the problem 
   and type_of_search by "Astar", "ACS", "AWA", "IBS", "BnBdfs" or "KTNS".
   
   To build the project : 
   ```bash
    mvn package
    ```