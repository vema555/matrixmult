package myudfs;
import java.util.List;
import java.io.IOException;
import java.util.Iterator;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.DataBag;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.impl.logicalLayer.FrontendException;
import org.apache.pig.data.BagFactory;
import org.apache.pig.data.TupleFactory;

//
// * WE collect a bag {(1,2.5), (2,3) , (6,0)} and there are missing
// * entries for index 0,3,4,5, we would like to fill  them and create 
// * a full tuple
// * 
// *
// *
//

    public class FullTuple extends EvalFunc<DataBag> {
        TupleFactory mTupleFactory = TupleFactory.getInstance();
        BagFactory mBagFactory = BagFactory.getInstance();

 	public DataBag exec(Tuple input) throws IOException {
       DataBag inpbag = (DataBag)input.get(0);
       int N = (Integer) input.get(1);
       DataBag output = convertToFull( inpbag, N); 
       return output; 
    }

    private DataBag convertToFull(DataBag first, int N) throws ExecException {
        DataBag output = mBagFactory.newDefaultBag();
        double[] xarr = new double [N];
        Iterator it = first.iterator();
        int count = 0;
        while (it.hasNext()){
            Tuple t = (Tuple)it.next();
            int idx = (Integer) t.get(0);
            xarr[idx] =  (Double) t.get(1); 
        }

        for(int i=0; i< N; i++){
            Tuple t = mTupleFactory.getInstance().newTuple(2);
            t.set(0, i );
            t.set(1, xarr[i]);
            output.add(t);
        }
        return output;
	}

}
