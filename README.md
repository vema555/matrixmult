matrixmult
==========
Pig Scripts with UDFs to do a matrix vecto r multplication.
Though mthis may seem a little elementary this can be an important
component is designing linear regression or gradient optimiztion like 
methods where the inevitable task is a multiplication of a vector-vector
or matrix vector. Also useful in desigining cosine methods


y = X * w

Typically X is a sparse matrix and w is full weight vector.
Challenges in the problem include to retain the spareseness of the matrix X
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
           
