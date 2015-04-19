import org.apache.hadoop.io.Text;

public class TestTrigram {
    
    public static void main(String args[]) {
        
        testToString();
        testCompareTo();
            
    }
    
    public static void testToString() {
        Text x = new Text("hello");
        Text y = new Text("trigram");
        Text z = new Text("test");
        Trigram tg = new Trigram(x,y,z);
        
        System.out.println(tg.toString());
    }
    
    public static void testCompareTo() {
        Text x = new Text("hello");
        Text y = new Text("trigram");
        Text z = new Text("test");
        Trigram tg = new Trigram(x,y,z);
        
        Trigram other = new Trigram(x,y,z);
        
        if (tg.compareTo(other) == 0) {
            System.out.println("It worked");
        }
    }
}