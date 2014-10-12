#!/usr/bin/env bash
source ./config.params
hdfs dfs -rmr $output
pig -x mapreduce -param_file config.params -f multiply_matrix_vector.pig

