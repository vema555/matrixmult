Performing Matrix Multiplication using Apache Pig
==========

With Pig Scripts and java UDFs we  do a matrix vector multplication. Although this may seem a little elementary this is probably the most essential computing step in many distributed algorithms. Ex: In a stochastic gradient implementation of linear regression, this is the inevitable 
task. In designing recommendation methods a measure of user-user similarity is a cosine method. There too this process will be utilized.

We are interested performing the following operation.

y = X * w


Typically X is a sparse matrix and w is full weight vector.
Challenges in the problem include to retain the sparseness of the matrix X
and allow for the computation of X * w. 

(1) The first step is to do the group of the row of the matrix and the column.
(2) Compute the dot product of the row and column
(3) The result of the dot product is a sparse representation, we want to compute
the full vector. (Full representation may be necessary when say  we are trying to say
evaluate ( y - yhat) ** 2.

Main Pig Script:
multiply_matrix_vector.pig: Performs all major groups of rows and columns

Java Udfs:(a) VecVecMultiply.java : Does the row * column vector dot product
          (b) FullTuple.java: Unravels the sparse computation.
           
