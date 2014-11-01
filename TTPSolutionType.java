//  PermutationSolutionType.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
// 
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.


import jmetal.core.Problem;
import jmetal.core.SolutionType;
import jmetal.core.Variable;
import jmetal.encodings.variable.Permutation;
import jmetal.encodings.variable.Binary;

/** 
 * Class representing the solution type of solutions composed of Permutation
 * variables 
 */
public class TTPSolutionType extends SolutionType {
	private Problem problem_;
	/**
	 * Constructor
	 * @param problem  Problem to solve
	 */
	public TTPSolutionType(Problem problem) {
		super(problem) ;
		problem_ = problem;
	  } // PermutationSolution
	
	/**
	 * Creates the variables of the solution
	 */
	public Variable[]  createVariables() {
		if(problem_.getNumberOfVariables() != 2){
	    	System.out.println("Error: TSPSolutionType with " + String.valueOf(problem_.getNumberOfVariables()) + " variables is invalid") ;
	    	System.exit(-1) ;
		}
		Variable [] variables = new Variable[2];
		variables[0] = new Permutation(problem_.getLength(0)) ;
	   	variables[1] = new Binary(problem_.getLength(1)) ;   
	    
	    return variables ;
	} // createVariables
} // PermutationSolutionType
