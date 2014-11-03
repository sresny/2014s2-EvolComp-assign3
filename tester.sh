#!/bin/bash
#A helper script for running simulations
#Input - problem filenames
#Output - a bunch of FUN, VAR and LOG files labelled with their algorithmic makeup
echo "Usage: ./tester.sh [PROBLEM1, PROBLEM2, etc]"
#for each problem file
javac main.java && for PROBLEM in "$@"
do
	#for each population size
	for POPSIZE in 10 100
	do
		#for each variant of the number of generations
		for NUMGEN in 100 1000 10000
		do
			#for each algorithm
			for ALG in NSGAII #SPEA2 IBEA
			do
				#run test
				#echo $PROBLEM $POPSIZE $NUMGEN $ALG
				#echo FUN_${PROBLEM}_${POPSIZE}_${NUMGEN}_${ALG}
				java main $PROBLEM $POPSIZE $NUMGEN $ALG 2>&1 |cat >>LOG_${PROBLEM}_${POPSIZE}_${NUMGEN}_${ALG}
				#rename Function file
				mv FUN FUN_${PROBLEM}_${POPSIZE}_${NUMGEN}_${ALG}
				#rename Variable file
				mv VAR VAR_${PROBLEM}_${POPSIZE}_${NUMGEN}_${ALG}
			done
		done
	done
done
	
