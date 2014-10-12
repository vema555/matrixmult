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
           DataBag output = mBagFactory.newDefaultBag(); 
           int N = (Integer) input.get(0);
           return output; 
        }

        private double[] initialize(DataBag first, int N) throws ExecException {
            double[] xarr = new double [N];
            Iterator it = first.iterator();
            int count = 0;
            while (it.hasNext()){
                Tuple t = (Tuple)it.next();
                int idx = (Integer) t.get(0);
                System.out.println("Howdy doing idx" + t.get(0) + "vval:" +  t.get(1));
                    xarr[idx] =  (Double) t.get(1); 
            }
            return xarr;
	}

}
