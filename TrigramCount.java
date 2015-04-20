import java.io.IOException;

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
            
            // Create our string and list of words
            
            String line = value.toString().toLowerCase();   // create string and lower case
            
            line = line.replaceAll("[^a-z\\s]","");         // remove bad non-word chars
                    
            String[] words = line.split("\\s");             // split line into list of words
            
            int len = words.length;                         // need the length for our loop condition
            
            for(int i = 0; i+2 < len; i++) {
                
                /*
                short lines lines to produce trigrams of # # #
                such as when we have length of 5 (# # the # #).                
                */
                if(len <= 1) {
                    continue;
                }
                
                first.set(words[i]);
                second.set(words[i+1]);
                third.set(words[i+2]);
                
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