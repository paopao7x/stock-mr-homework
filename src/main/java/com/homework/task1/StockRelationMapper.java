package com.homework.task1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class StockRelationMapper extends Mapper<LongWritable, Text, Text, Text> {
    private String fileTag;

    @Override
    protected void setup(Context context) {
        FileSplit split = (FileSplit) context.getInputSplit();
        String fileName = split.getPath().getName();
        if (fileName.contains("A")) {
            fileTag = "A";
        } else if (fileName.contains("B")) {
            fileTag = "B";
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString().trim();
        if (line.isEmpty()) return;
        String[] fields = line.split("\\s+");
        if (fields.length < 2) return;
        String indexId = fields[0];
        String stockCode = fields[1];
        context.write(new Text(indexId + "\t" + stockCode), new Text(fileTag));
    }
}
