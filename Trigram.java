import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class Trigram implements WritableComparable {
    
    private Text first;
    private Text second;
    private Text third;
    
    public Trigram(Text first, Text second, Text third) {
        /* constructor takes three Text objects */
        set(first,second,third);
    }
    
    public Trigram() {
        /* 
        Instantiation without params creates a trigram with
        three empty Text objects 
        */
        Text empty = new Text();
        set(empty, empty, empty);
    }
    
    public void set(Text first, Text second, Text third) {
        
        this.first = first;
        this.second = second;
        this.third = third;
    }
    
    public void write(DataOutput out) throws IOException {
        first.write(out);
        second.write(out);
        third.write(out);
    }
    
    public void readFields(DataInput in) throws IOException {
        
    }
    
    public int compareTo(Trigram tgram) {
        
    }
    
    public int hashCode() {
        
    }
}