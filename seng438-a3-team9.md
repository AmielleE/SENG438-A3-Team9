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
