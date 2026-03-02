**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:      |     |
| -------------- | --- |
| Student Names: |     |
|                |     |
|                |     |
|                |     |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

Text…

# 2 Manual data-flow coverage calculations for X and Y methods
For the manual data-flow coverage analysis, we selected the following two methods as required by the lab instructions:

X Method: DataUtilities.calculateColumnTotal(Values2D data, int column)  
Y Method: Range.constrain(double value)

These methods were chosen because we already developed test suites for them in Assignment 2, allowing us to trace DU pair coverage manually using our existing test cases.

2.1 DataUtilities.calculateColumnTotal(Values2D data, int column)
This method initializes a variable total, retrieves the row count from the dataset, and iterates through each row to accumulate the values in a specified column.

Definitions:
- total is defined at initialization and updated inside the loop
- rowCount is defined from data.getRowCount()
- n is defined when retrieving each cell value

Uses:
- total is used in accumulation and return statement
- n is used in the null check and addition
- rowCount is used in loop control

DU-Pairs Identified:
- total: (definition at initialization -> use in loop), (definition in loop -> use in next iteration), (definition -> return)
- n: (definition at data.getValue -> use in null check and addition)
- rowCount: (definition -> loop condition)

Test Case Coverage:
Our test case calculateColumnTotal_ValidColumn_ShouldSumColumnValues() executes the main loop path where all values are non null, covering the primary DU-pairs for total, n, and rowCount.

The test calculateColumnTotal_NullValues_ShouldThrowException() covers the exception path when data is null.

Additionally, the second loop (r2 > rowCount) is logically unreachable, meaning some DU-pairs associated with it are infeasible.

Final DU-Pair Coverage:
High coverage of feasible DU-pairs, roughly 100%, excluding infeasible paths caused by unreachable loop conditions.

2.2 Range.constrain(double value)
This method constrains a given value within the lower and upper bounds of the Range object.

Definitions:
- result is defined initially as value
- result may be redefined if value is outside the range

Uses:
- value is used in comparisons
- lower and upper bounds are used in conditional checks
- result is used in the return statement

DU-Pairs Identified:
- result: (initial definition -> return when inside range)
- result: (redefinition at upper bound -> return)
- result: (redefinition at lower bound -> return)

Test Case Coverage:
The following test cases were used:
- constrain_ValueInsideRange_ShouldReturnSameValue()
- constrain_ValueBelowLower_ShouldReturnLowerBound()
- constrain_ValueAboveUpper_ShouldReturnUpperBound()

These tests cover all control flow paths:
1. Value inside range
2. Value below lower bound
3. Value above upper bound

Final DU-Pair Coverage:
All feasible DU-pairs for the variable (result) are covered by the existing test suite, resulting in roughly 100% DU-pair coverage for this method.


# 3 A detailed description of the testing strategy for the new unit test

Text…

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

Text…

# 6 Pros and Cons of coverage tools used and Metrics you report

Text…

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

Text…

# 8 A discussion on how the team work/effort was divided and managed

Text…

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Text…

# 10 Comments/feedback on the lab itself

Text…
