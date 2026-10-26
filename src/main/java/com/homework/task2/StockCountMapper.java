package com.homework.task2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class StockCountMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString().trim();
        if (line.isEmpty()) return;
        String[] fields = line.split("\\s+");
        if (fields.length < 3) return;
        String indexId = fields[0];
        String relation = fields[2];
        context.write(new Text(indexId), new Text(relation));
    }
}
