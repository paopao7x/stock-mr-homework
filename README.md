# 股票指数成分股集合关系 MapReduce 作业

## 个人信息
- 姓名：李骏浩
- 学号：241275034

## 一、设计思路
通过两个串联的 MapReduce 任务完成需求：
1. 任务1：按「指数+股票」分组，判断每只股票属于 BOTH / A_ONLY / B_ONLY
2. 任务2：基于任务1结果，按指数维度统计交集、A独有、B独有、并集的数量
3. Driver 类控制任务依赖，任务2等待任务1完成后启动

## 二、运行环境
- Rocky Linux 9.8
- OpenJDK 1.8.0
- Hadoop 3.3.6
- Maven

## 三、运行结果
| 指数编号 | 交集数量 | A独有数量 | B独有数量 | 并集数量 |
|---------|---------|----------|----------|---------|
| 101     | 3       | 13       | 4        | 20      |
| 102     | 3       | 19       | 3        | 25      |
| 103     | 52      | 195      | 255      | 502     |

## 四、运行命令
```bash
mvn clean package -DskipTests
hadoop jar target/stock-mr.jar com.homework.StockRelationDriver

