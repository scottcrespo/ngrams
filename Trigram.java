import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class Trigram implements WritableComparable {
    
    private Text first;
    private Text second;
    private Text third;
    
    public void write(DataOutput out) throws IOException {
        
    }
    
    public void readFields(DataInput in) throws IOException {
        
    }
    
    public int compareTo(Trigram tgram) {
        
    }
    
    public int hashCode() {
        
    }
}