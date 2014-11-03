# template.gnuplot
set term png
set autoscale
set xtics offset -1,-1
set xlabel "Distance" offset -1,-1
set ylabel "Weight" offset 0,0
set ytics offset 3,0
set zlabel "Travel Time" offset -1,0
set zlabel rotate by 90 left

set output "OUTFILE"
splot "DATAFILE1" with points pointtype 5 , "DATAFILE2" with points pointtype 7, "DATAFILE3" with points pointtype 9
