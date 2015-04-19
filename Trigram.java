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
        /* 
        Constructor takes three Text objects
        */
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
        /* 
        Sets the three Trigram attributes
        */
        this.first = first;
        this.second = second;
        this.third = third;
    }
    
    public Text getFirst() {
        return first;
    }
    
    public Text getSecond() {
        return second;
    }
    
    public Text getThird() {
        return third;
    }
    
    @Override
    public String toString() {
        /* 
        Convert a Trigram instance to a string of comma 
        separated values: "first, second, third"
        */
        return Text.toString(first) + ", " +
                Text.toString(second) + ", " +
                Text.toString(third);
    }
    
    @Override
    public void write(DataOutput out) throws IOException {
        /*
        Method to write out Trigram objects. 
        Notice that hadoop's Text class makes it easy via it's 
        write() method!
        */
        first.write(out);
        second.write(out);
        third.write(out);
    }
    
    @Override
    public void readFields(DataInput in) throws IOException {
        /*
        Method to read in values.
        
        Once again, Hadoop makes it easy to read in values via the 
        Text class's readFields() method. 
        */
        first.readFields(in);
        second.readFields(in);
        third.readFields(in);
    }
    
    
    @Override
    public int compareTo(Trigram other) {
        /*
        Method to perform a bytewise comparison of
        two instances of Trigram. Method returns a non-zero
        integer value when two objects are not equal. 
        */
        int compared = first.compareTo(other.first);
        
        if (compared != 0) {
            return compared;
        }
        
        compared = second.compareTo(other.second);
        
        if (compared != 0) {
            return compared;
        }
        
        return third.compareTo(other.third);
    }
    
    @Override
    public boolean equals(Object other) {
        /* 
        Return true if two instance of Trigram have the same contents. This
        helps us conform to Java's Object interface, however Hadoop MapReduce does
        not use the equals method in performing MR jobs. 
        */
        
        if (!(other instanceof Trigram)) { // short circuit if not a Trigram
            return false;
        }
        
        return first.equals(other.first) && second.equals(other.second)
            && third.equals(other.third);
        
    }
    
    @Override
    public int hashCode() {
        /*
        The hashCode implementation should be stable, meaning it can be
        computed consistently across various JVMs. 
        
        In this case, multiply first.hashCode() by a prime, and sum the 
        hashCodes of first, second, and third.
        return first.hashCode()*163 + second.hashCode() + third.hashCode();
        */
    }
}