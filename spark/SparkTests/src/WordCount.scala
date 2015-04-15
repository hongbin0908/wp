

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object WordCount {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("WordCount")
    conf.setMaster("local")
    val sc = new SparkContext(conf)

    val textRDD = sc.textFile("wp/spark/WordCount/word_count_in.3")
    val result = textRDD.flatMap {
      line => line.split(" ")
    }.map(word => (word,1)).reduceByKey(_ + _)
    
    result.saveAsSequenceFile("wp/spark/WordCount/word_count_out.3")
  }
}