#!/bin/bash
#A helper script for running simulations
#compile driver
javac main.java
#for each problem file
for PROBLEM in a280_n1395_uncorr-similar-weights_05.ttp  a280_n279_bounded-strongly-corr_01.ttp a280_n2790_uncorr_10.ttp
do
	#for each population size
	for POPSIZE in 10 100
	do
		#for each variant of the number of generations
		for NUMGEN in 100 1000 10000
		do
			#for each algorithm
			for ALG in NSGAII SPEA2 IBEA
			do
				#run test
				java main $PROBLEM $POPSIZE $NUMGEN $ALG 2>&1 >>TIM_$PROBLEM_using_$ALG_at_p$POPSIZE_g$NUMGEN.txt
				#rename Function file
				mv FUN FUN_$PROBLEM_using_$ALG_at_p$POPSIZE_g$NUMGEN.txt
				#rename Variable file
				mv VAR VAR_$PROBLEM_using_$ALG_at_p$POPSIZE_g$NUMGEN.txt
			done
		done
	done
done
	
