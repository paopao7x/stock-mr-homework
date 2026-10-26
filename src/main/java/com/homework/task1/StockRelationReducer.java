package com.homework.task1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StockRelationReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Set<String> sources = new HashSet<>();
        for (Text val : values) {
            sources.add(val.toString());
        }
        String relation;
        if (sources.contains("A") && sources.contains("B")) {
            relation = "BOTH";
        } else if (sources.contains("A")) {
            relation = "A_ONLY";
        } else {
            relation = "B_ONLY";
        }
        context.write(key, new Text(relation));
    }
}
