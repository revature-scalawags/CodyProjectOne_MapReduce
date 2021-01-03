import org.apache.hadoop.mapreduce.Mapper
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.Text

// Modified mapper to filter out unnecessary data
class WordMapper extends Mapper[LongWritable, Text, Text, IntWritable] {
  override def map(
      key: LongWritable,
      value: Text,
      context: Mapper[LongWritable, Text, Text, IntWritable]#Context
  ): Unit = {
    val line = value.toString

    val lineArray = line.split("\\s").filter(_.length > 0)
    var word = ""
    var count = 0
    var checker = false
    for (i <- 0 to lineArray.length - 1) {
      if (i % 4 == 0 && !lineArray(i).equals("Main_Page")) {
        word = lineArray(i)
        checker = true
      } else if (i % 4 == 1 && checker == true)
        word = word + "\t" + lineArray(i)
      else if (i % 4 == 2 && !lineArray(i).equals("link")) checker = false
      else if (i % 4 == 3 && checker == true) {
        count = lineArray(i).toInt
        context.write(new Text(word), new IntWritable(count))
        checker = false
      }
    }
  }
}
