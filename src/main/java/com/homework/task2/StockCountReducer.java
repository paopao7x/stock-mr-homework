package com.homework.task2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class StockCountReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int both = 0, aOnly = 0, bOnly = 0;
        for (Text val : values) {
            switch (val.toString()) {
                case "BOTH": both++; break;
                case "A_ONLY": aOnly++; break;
                case "B_ONLY": bOnly++; break;
            }
        }
        int union = both + aOnly + bOnly;
        String result = both + "\t" + aOnly + "\t" + bOnly + "\t" + union;
        context.write(key, new Text(result));
    }
}
