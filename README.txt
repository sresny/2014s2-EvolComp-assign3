2014s2-EvolComp-assign3
=======================
Final assignment for the subject of Evolutionary Computation, semester 2, 2014, University of Adelaide.
=======================

Notes and Instructions:
* Q1 contains the results of simulations for exercise 1. 
  - Q1/Question1.odt contains the visualisations for the task. 
  - Folders within Q1 (named as ProblemName_PopulationSize_NumberOfGenerations) contain the logs and numerical results, as well as the source spreadsheets, for each simulation.
* For Q2, TTP and TTPSolutionType were adapted from JMetal classes
* For Q3, TTPBitFlipMutation, TTPSwapMutation and TTPTwoPointsCrossover were implemented.
* For experiments of Q4 and Q5, main.java provides the test driver
	Usage: java main FILENAME POPULATION_SIZE NUMBER_OF_GENERATIONS [ALG]
	The algorithm is a choice of NSGAII, SPEA2, IBEA.
* Sometimes a negative value crops up in the results. The origin of the bug was not found and for the plots such individuals are omitted. 
  For Q4, true results are in the folder "true", reasonable results are in the folder "positive"
* Many individuals have the same value, making the plots look sparser than expected.
* For Q5, populations of 10, 50 and 100 were used, adding a middle value of population size to Q4's setup. Small iterations of 100 generations were used for faster results.
  Runtimes are compared in file Data.xlsx in folder Q5

Team:
a1193946, Svetlana Resnyanskaya, a1193946@student.adelaide.edu.au
a1608025, Rory Stokes, a1608025@student.adelaide.edu.au
a1602352, Han Song, a1602352@student.adelaide.edu.au
a1600891, Yunfei Gong, a1600891@student.adelaide.edu.au


Repository:
https://github.com/sresny/2014s2-EvolComp-assign3
