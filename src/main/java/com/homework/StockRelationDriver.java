package com.homework;

import com.homework.task1.StockRelationMapper;
import com.homework.task1.StockRelationReducer;
import com.homework.task2.StockCountMapper;
import com.homework.task2.StockCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class StockRelationDriver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        // 强制本地模式，不依赖HDFS和YARN
        conf.set("fs.defaultFS", "file:///");
        conf.set("mapreduce.framework.name", "local");

        // ========== 任务1：股票集合关系判断 ==========
        Job job1 = Job.getInstance(conf, "Task1_StockRelation");
        job1.setJarByClass(StockRelationDriver.class);
        job1.setMapperClass(StockRelationMapper.class);
        job1.setReducerClass(StockRelationReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);
        FileInputFormat.setInputPaths(job1, new Path("input"));
        FileOutputFormat.setOutputPath(job1, new Path("output_task1"));
        boolean res1 = job1.waitForCompletion(true);
        if (!res1) System.exit(1);

        // ========== 任务2：指数维度数量统计 ==========
        Job job2 = Job.getInstance(conf, "Task2_StockCount");
        job2.setJarByClass(StockRelationDriver.class);
        job2.setMapperClass(StockCountMapper.class);
        job2.setReducerClass(StockCountReducer.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
        FileInputFormat.setInputPaths(job2, new Path("output_task1"));
        FileOutputFormat.setOutputPath(job2, new Path("output_task2"));
        boolean res2 = job2.waitForCompletion(true);

        System.exit(res2 ? 0 : 1);
    }
}
