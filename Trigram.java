import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class Trigram implements WritableComparable<Trigram> {
    
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
    
    public void set(Text one, Text two, Text three) {
        /* 
        Sets the three Trigram attributes
        */
        this.first = one;
        this.second = two;
        this.third = three;
    }
    
    public Text getFirst() {
        // getter
        return first;
    }
    
    public Text getSecond() {
        // getter
        return second;
    }
    
    public Text getThird() {
        // getter
        return third;
    }
    
    @Override
    public String toString() {
        /* 
        Convert a Trigram instance to a string of comma 
        separated values: "first, second, third"
        */
        return first.toString() + " " +
                second.toString() + " " +
                third.toString();
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
        two objects. Method returns a non-zero
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
        
        if (other instanceof Trigram) { // short circuit if not a Trigram
            // since we know other is a Trigram, explicitly cast it as such
            Trigram trigram = (Trigram) other;

            return first.equals(trigram.first) && second.equals(trigram.second)
                && third.equals(trigram.third);
        }
        return false;
        
        
        
    }
    
    @Override
    public int hashCode() {
        /*
        The hashCode() implementation should be stable - meaning it can be
        computed consistently across various JVMs. 
        
        In this case, multiply first.hashCode() by a prime and add the hashCodes
        of second and third. This should sufficient to ensure the value is 
        unique for a Trigram with a particular set of attributes.
        
        */
        
        return first.hashCode()*163 + second.hashCode() + third.hashCode();
    }

}