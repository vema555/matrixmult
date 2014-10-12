REGISTER myudfs.jar;

---- Load the matrix and the vector 
A = load '$input1' as (i:int, x: bag {T: tuple(t1:int, t2:double)} );
B = load '$input2' as bag {T: tuple(t1:int, t2:double)} ;
Bd = foreach B generate $0 as y;

--- Multiply each row with the weight vector
Z = foreach A  generate i, myudfs.VecVecMultiply(x,Bd.y);
W = GROUP Z ALL;
--Y = foreach B generate FLATTEN(*)
dump W;
describe W;

--store W into '$output';
Y = foreach W generate myudfs.FullTuple(Z, 4);
store Y into '$output';

 