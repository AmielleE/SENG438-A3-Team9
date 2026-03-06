**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:  9    |    |
| -------------- | --- |
| Student Names: |  UCID:   |
|      Josral Frederick          |  30195360   |
| Amielle El Makhzoumi           | 30175286    |
| Fatma Alzubaidi               |     |  
| Faris Janjua               |     |  
| Erioluwa Olubadejo             | 30187041    |  

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

  The goal of this lab was to evaluate the adequacy of a unit test suite using code coverage techniques and to design additional tests to improve coverage. The system under test was the JFreeChart data utilities package, specifically the Range and DataUtilities classes.


  We measured control-flow coverage for the Range and DataUtilities classes using the EclEmma coverage tool in Eclipse. In addition, we manually analyzed data flow coverage for the methods DataUtilities.calculateColumnTotal() and Range.constrain() by identifying and tracing their definition-use pairs. Based on the coverage results, we designed additional JUnit tests to exercise uncovered branches and edge cases. This process helped improve overall coverage and provided a better understanding of the strengths and limitations of coverage metrics in evaluating test suites.

# Measure Control Flow Coverage

We measured control-flow adequacy of our existing Assignment 2 test suite by instrumenting the target classes in org.jfree.data and running the full test suite under a coverage tool. The classes selected for instrumentation were:
-  org.jfree.data.Range
-  org.jfree.data.DataUtilities

The results are shown below: 

<img width="686" height="122" alt="Screenshot 2026-03-02 at 3 38 38 AM" src="https://github.com/user-attachments/assets/c1ef51db-e11b-4b87-a6e0-f958e71144c0" />

Condition coverage was not directly supported by EclEmma in our configuration, so we replaced it with method coverage. 


### Coverage tool used

We used EclEmma (Eclipse code coverage plug-in) to measure coverage. We executed our JUnit 5 tests using 
Coverage As → JUnit Test, which produced:

- percentage coverage per class,

- counts of covered vs missed instructions/branches,

- visual highlighting of uncovered code paths (green/red) inside the instrumented source files.

This visualization helped us identify which parts of Range and DataUtilities were not executed by the current test suite.

### Pros and cons of the coverage metrics

#### Statement (Instruction) Coverage — Pros

- Easy to improve and interpret: it directly shows which lines/instructions were never executed.

- Good first signal that certain parts of the codebase have no tests at all.

#### Statement (Instruction) Coverage — Cons

- It can be misleading: a statement can execute without verifying correctness (weak or missing assertions).

- 100% statement coverage does not guarantee all decision outcomes were tested.

#### Decision (Branch) Coverage — Pros

- Stronger than statement coverage: it requires tests that exercise both outcomes of decisions (e.g., true/false paths).

- Useful for finding missing edge-case tests, especially around validation and exception branches.

#### Decision (Branch) Coverage — Cons

-Still does not guarantee full logical coverage: complex conditions can still hide untested sub-conditions.

- Like statement coverage, it measures execution—not whether results are correct.

#### Pros of Method Coverage

- Shows which methods were never tested.

- Easy to understand and interpret.

- Complements statement and branch coverage by giving a high-level view of test reach.

#### Cons of Method Coverage

- Very coarse metric — only checks if a method was entered.

- Does not ensure all internal branches or edge cases were tested.

- Weaker than condition coverage for measuring logical completeness.

## Pros and Cons of EclEmmma coverage tool 

Pros:

Easy integration with Eclipse.

Clear red/green highlighting of uncovered code.

Supports instruction, branch, and method metrics.

Helps identify missing test paths quickly.

Cons:

Does not support condition coverage directly.

Measures execution, not correctness.

High coverage can give a false sense of quality.

Does not support data-flow coverage (DU-pairs).


# 2 Manual data-flow coverage calculations for X and Y methods

For the manual data flow coverage analysis we selected the following two methods as required by the lab instructions:

- X Method: DataUtilities.calculateColumnTotal(Values2D data, int column)
- Y Method: Range.constrain(double value)

These methods were chosen because we already had test cases for them from Assignment 2 and reused them in Assignment 3. This allowed us to manually trace the execution of each test case and identify the definition-use pairs as required by the lab instructions for manual data flow coverage.

## DataUtilities.calculateColumnTotal(Values2D data, int column)

### Method description
This method calculates the total of values in a specified column of a 2D dataset. The method first checks that the data object is not null initializes a total variable, retrieves the number of rows, and then iterates through each row to accumulate non null values. Finally it returns the computed total.

The method also contains a second loop with the condition r2 > rowCount. Since r2 starts at 0 and rowCount is non negative, this loop is logically unreachable and represents an infeasible path.

### Data flow graph:
The main control and data flow of the method can be described as:

- Entry
- Null check on data
- Initialize total
- Define rowCount using data.getRowCount()
- Loop condition r < rowCount
- Retrieve n = data.getValue(r, column)
- Check if n is not null
- Add n to total if non null
- Repeat loop until condition becomes false
- Return total and exit

The second loop is infeasible because the condition r2 > rowCount is false when r2 starts at 0. Therefore DU-pairs related to that loop are not considered in feasible coverage.

### Def-use sets per statement
Variables considered: data, column, total, rowCount, r, and n.

**-data** 
  - Defined at method entry
  - Used in null check, getRowCount(), and getValue()

**-column**
  - Defined at method entry
  - Used in getValue(r, column)

**-total**
  - Defined at initialization
  - Used in accumulation and return statement
  - Redefined inside the loop when values are added

**-rowCount**
  - Defined using data.getRowCount()
  - Used in the loop condition

**-r**
  - Defined in the for loop
  - Used as an index in getValue(r, column)

**-n**
  - Defined when retrieving each cell value
  - Used in the null check and in the addition to total

**-DU-pairs per variable(feasable)**
Only feasible DU-pairs are considered because the second loop is unreachable.

**-data**
  - Definition at entry, use in null check
  - Definition at entry, use in getRowCount()
  - Definition at entry, use in getValue()

**-rowCount**
  - Definition, use in loop condition (r < rowCount)

**-r**
  - Definition in loop, use in getValue(r, column)

**-n**
  - Definition at getValue, use in null check
  - Definition at getValue, use in addition to total

**-total**
  - Definition at initialization, use in accumulation
  - Redefinition in loop, use in next iteration
  - Definition at initialization, use in return (when loop not executed)
  - Redefinition in loop, use in return

DU-pairs associated with the second loop are infeasible and excluded from the coverage calculation.

### Test case coverage of DU-pairs
We manually traced the execution of our existing test cases from the test suite.

- calculateColumnTotal_ValidColumn_ShouldSumColumnValues  
  This test executes the main loop with non null values. It covers DU-pairs for data, rowCount, r, n, and total during accumulation and return.

- calculateColumnTotal_NullValues_ShouldThrowException  
  This test passes null as the data parameter and triggers the exception at the null check. It covers the DU-pair where data is used in the validation statement.

- calculateColumnTotal_ZeroRows_ShouldReturnZero  
  This execution ensures the loop is not entered and covers the DU-pair where total is defined and directly returned.

- calculateColumnTotal_NullValues_ShouldIgnoreNulls  
  This test covers the branch where n is null and ensures both the null and non-null paths are exercised.

### DU-pair coverage calculation
Total feasible DU-pairs identified: 11  
Covered DU-pairs through the test executions: 11  

Therefore the DU-pair coverage for DataUtilities.calculateColumnTotal is approximately 100 percent for all feasible paths.  
DU-pairs related to the unreachable second loop are considered infeasible and excluded from the final coverage calculation.

## Range.constrain(double value)

### Method description
This method constrains a given value so that it stays within the lower and upper bounds of a Range object. If the value is inside the range, it returns the same value. If the value is greater than the upper bound it returns the upper bound. If the value is less than the lower bound, it returns the lower bound.

### Data flow graph
The execution flow of the method is:

- Entry with input value
- Initialize result = value
- Check if value is inside the range using contains(value)
- If value > upper, result is set to upper
- Else if value < lower, result is set to lower
- Return result and exit

This creates three main execution paths: inside range, above upper bound, and below lower bound.

### Def-use sets per statement
Variables considered: value, result, lower, and upper.

**-value**
  - Defined at method entry
  - Used in contains(value) and comparison conditions

**-result**
  - Defined initially as value
  - Possibly redefined if value is outside the bounds
  - Used in the return statement

**-lower and upper**
  - Used in conditional comparisons to determine constraint behavior

### DU-pairs per variable (feasible)
The main DU-pairs are for the variable result:

- Initial definition of result, use in return when value is inside range
- Redefinition of result at upper bound, use in return
- Redefinition of result at lower bound, use in return

Total feasible DU-pairs for result = 3

### Test case coverage of DU-pairs
We used the following test cases from our Range test suite:

- constrain_ValueInsideRange_ShouldReturnSameValue
- constrain_ValueAboveUpper_ShouldReturnUpperBound
- constrain_ValueBelowLower_ShouldReturnLowerBound

These tests cover all logical execution paths of the method and ensure that every definition of result reaches a use in the return statement.

### DU-pair coverage calculation
Total feasible DU-pairs identified: 3  
Covered DU-pairs: 3  

Therefore, the DU-pair coverage for Range.constrain is 100 percent since all feasible definition use paths for the result variable are exercised by the test suite.


# 3 A detailed description of the testing strategy for the new unit test

  The strategy for developing the new unit tests focused on increasing control-flow coverage by targeting code paths that were not exercised by the existing test suite. Using the EclEmma coverage results, we first identified methods and branches in the Range and DataUtilities classes that had low coverage or contained untested conditions. New tests were then designed specifically to execute those paths.

  The tests were created using JUnit 5, with Mockito used when mocking interfaces such as Values2D or KeyedValues was required. Each test was written to focus on a single scenario so that the executed path and expected behavior were clear. Particular attention was given to edge cases and boundary conditions, including null inputs, zero values, negative numbers, empty datasets, and ranges that cross or approach zero.

  To improve branch coverage, tests were also written to exercise different logical outcomes within methods. For example, cases where values fall inside a range, outside the range, or exactly on the boundary were tested. Similarly, data utility methods were tested with combinations of valid values, null values, and mixed positive/negative numbers to ensure all relevant execution paths were triggered.

  Overall, the strategy was to systematically use coverage feedback to guide test creation, ensuring that each additional test contributed to executing previously uncovered statements or branches while still verifying the expected behavior of the system.


# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

<img width="1001" height="563" alt="image" src="https://github.com/user-attachments/assets/74d4a97c-cb02-4b46-b732-946e1bb232e7" />


# 6 Pros and Cons of coverage tools used and Metrics you report

  In this lab we used EclEmma, the Eclipse code coverage plugin, to measure how much of the code was exercised by our JUnit tests. The main metrics reported were instruction (statement) coverage, branch coverage, and method coverage. These metrics helped identify which parts of the Range and DataUtilities classes were not being executed during testing. One of the main advantages of using EclEmma is that it integrates directly with Eclipse and is easy to run alongside JUnit tests. The tool also highlights covered and uncovered lines in green and red, which makes it straightforward to identify missing test paths visually. This makes it useful for quickly locating sections of code that require additional testing.

  However, coverage tools also have several limitations. Coverage metrics only indicate whether code was executed, not whether the behavior was correct. It is possible to achieve high coverage while still having weak assertions or missing logical validations in tests. Additionally, the version of EclEmma used in our setup did not support condition coverage, which meant we had to rely on method coverage instead. Another limitation is that tools like EclEmma do not detect infeasible paths or logical issues within the code.

  Overall, coverage tools are useful for guiding test development and identifying gaps in test suites, but they should be used together with well designed test cases and meaningful assertions rather than as the only measure of test quality.

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.
  Requirements-based test generation focuses on creating tests based on the expected behavior of the system, typically derived from specifications, documentation, or requirements. This approach ensures that the system is validated against what it is supposed to do from a user or functional perspective. One advantage of this approach is that it helps verify correctness of functionality and ensures that important features are tested. However, it may miss internal execution paths or edge cases that are not explicitly described in the requirements.

  Coverage-based test generation, on the other hand, is based on analyzing the source code structure to ensure that different execution paths are exercised. By using coverage metrics such as statement or branch coverage, testers can identify areas of the code that are not being executed and design tests specifically to target those sections. This approach is useful for improving the completeness of a test suite and for identifying hidden branches or conditions that might otherwise be overlooked. Despite this, coverage-based testing does not guarantee that the program is tested according to its intended behavior. A test may execute a line of code without actually verifying that the output is correct. Because of this, relying solely on coverage metrics can give a false sense of confidence in the test suite.

  In practice, both approaches are complementary. Requirements-based testing ensures that the system meets its intended functionality, while coverage-based testing helps ensure that the internal implementation is thoroughly exercised. Using both strategies together results in a more robust and reliable testing process.

# 8 A discussion on how the team work/effort was divided and managed

Text…

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Text…

# 10 Comments/feedback on the lab itself

Text…
