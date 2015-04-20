import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class TrigramCount {
    
    public static class TrigramMapper 
        extends Mapper<Object, Text, Trigram, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text first = new Text();
        private Text second = new Text();
        private Text third = new Text();

        public void map(Object key, Text value, Context context
            ) throws IOException, InterruptedException {
            
            String line = value.toString().toLowerCase();
            line = line.replaceAll("[^a-z\\s]","");
            
            String[] words = line.split("\\s");
            int len = words.length;
            
            for(int i = 0; i < len; i++) {
                
                
                /*
                short lines tend to produce trigrams of # # #
                this helps cut down on the phenomenon
                */
                if(len <= 1) {
                    continue;
                }
                
                if(i <= 1) { // if i is first or second
                    first.set("#");
                }
                else {first.set(words[i]);}
                
                if(i==0 || i+1 == len) { // if i is first or last
                    second.set("#");
                }
                else {second.set(words[i+1]);}    
                
                if(i+1 == len || i+2 == len) { // if i is next-to-last or last
                    third.set("#");
                } 
                else {second.set(words[i+2]);}
                
                Trigram trigram = new Trigram(first, second, third);
                
                context.write(trigram, one);                
            }
        }
    }
    
    public static void main(String args[]) throws Exception {
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Trigram Count");
        
        job.setJarByClass(TrigramCount.class);
        job.setMapperClass(TrigramMapper.class);
        job.setNumReduceTasks(0);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        //definte inputs and outputs
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // terminate program
        System.exit(job.waitForCompletion(true) ? 0 : 1);
        
    }
}