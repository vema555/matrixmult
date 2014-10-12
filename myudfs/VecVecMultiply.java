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


public class VecVecMultiply extends EvalFunc<Double> {
 	@Override 
 	public Double exec(Tuple input) throws IOException {
 		double result = 0.0;
        if (input == null || input.size() < 2)
            return result;

        DataBag first = (DataBag)input.get(0);
        DataBag second = (DataBag)input.get(1);

        int N = (int) second.size();
 		double [] xarr = initialize(first, N);
		double [] yarr = initialize(second, N);
        
        for( int i=0; i< N; i++){
        	result += xarr[i] * yarr[i];
        }
        return  result;
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
