#!/bin/bash
tmpfile=/tmp/temp.$$
touch $tmpfile
sed '
s/DATAFILE1/'${1}'/
s/DATAFILE2/'${2}'/
s/DATAFILE3/'${3}'/
s/OUTFILE/graph_'${1}${2}${3}'.png/' template.gnuplot  > $tmpfile
gnuplot $tmpfile
rm /tmp/temp.$$
