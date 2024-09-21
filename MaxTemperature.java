import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;

public class MaxTemperature {

  public static class MaxTemperatureMapper
      extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
      String line = value.toString();
      if (key.get() == 0 && line.contains("dt")) { // Skip header row
        return;
      }

      String[] fields = line.split(","); // Split by commas for CSV
      if (fields.length > 1 && !fields[1].isEmpty()) { // Ensure valid data
        String year = fields[0].substring(0, 4); // Extract year from date field (index 0)
        
        try {
          // Ensure the temperature value is valid
          double tempCelsius = Double.parseDouble(fields[1].trim());
          int temperature = (int) tempCelsius;  // Convert to integer for simplicity
          context.write(new Text(year), new IntWritable(temperature));
        } catch (NumberFormatException e) {
          // Ignore rows with invalid temperature data
        }
      }
    }
  }

  public static class MaxTemperatureReducer
      extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
        throws IOException, InterruptedException {
      int maxTemperature = Integer.MIN_VALUE;
      for (IntWritable value : values) {
        maxTemperature = Math.max(maxTemperature, value.get());  // Find the maximum temperature
      }
      context.write(key, new IntWritable(maxTemperature));  // Emit year and maximum temperature
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Max Temperature");
    job.setJarByClass(MaxTemperature.class);
    job.setMapperClass(MaxTemperatureMapper.class);
    job.setReducerClass(MaxTemperatureReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));  // Input file path
    FileOutputFormat.setOutputPath(job, new Path(args[1]));  // Output directory path
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
