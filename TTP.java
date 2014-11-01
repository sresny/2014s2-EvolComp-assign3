//  TSP.java
//
//  Author:
//      Svetlana Resnyanskaya
//		
//
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

package jmetal.problems;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;

/**
 * Class representing problem of the Traveling Thief
 */
public class TTP extends Problem {                 

  private int numberOfCities_;
  private int numberOfItems_;
  private double[][] distances_;
  private double[] values_;
  private double[] weights_;
 /**
  * Constructor.
  * Creates a default instance of the TTP.
  * @param solutionType The solution type must "IntSolutionType".
  */
  public TTP(String solutionType, int numCities, int numObjects) {
    numberOfVariables_   = 2;
    numberOfCities_      = numCities;
    numberOfItems_     = numObjects;
    numberOfObjectives_  = 3 ;
    numberOfConstraints_ = 1 ;
    problemName_         = "TTP";

    if (solutionType.compareTo("TTP") == 0)
      solutionType_ = new PermutationSolutionType(this) ;
    else {
      System.out.println("Error: solution type " + solutionType + " invalid") ;
      System.exit(-1) ;
    }
 } // TTP
	
  /**
   * Evaluates a solution
   * @param solution The solution to evaluate
   * @throws JMException 
   */
  public void evaluate(Solution solution) throws JMException {
    double distance = 0.0;

    for(int i=0; i<numCities; i++){
      j = (i+1)%numCities;

    }
    solution.setObjective(0,f[0]);    
    solution.setObjective(1,f[1]);
    solution.setObjective(2,f[2]);
    solution.setObjective(3,f[3]);
    solution.setObjective(4,f[4]);
  } // evaluate

  /** 
   * Evaluates the constraint overhead of a solution 
   * @param solution The solution
   * @throws JMException 
   */  
  public void evaluateConstraints(Solution solution) throws JMException {
    double [] constraint = new double[7]; // 7 constraints
    double [] x          = new double[3]; // 3 objectives
        
    x[0] = solution.getDecisionVariables()[0].getValue();
    x[1] = solution.getDecisionVariables()[1].getValue();
    x[2] = solution.getDecisionVariables()[2].getValue();
 
    constraint[0] = 1 - (0.00139/(x[0]*x[1])+4.94*x[2]-0.08)             ;
    constraint[1] = 1 - (0.000306/(x[0]*x[1])+1.082*x[2]-0.0986)         ;
    constraint[2] = 50000 - (12.307/(x[0]*x[1]) + 49408.24*x[2]+4051.02) ;
    constraint[3] = 16000 - (2.098/(x[0]*x[1])+8046.33*x[2]-696.71)      ;
    constraint[4] = 10000 - (2.138/(x[0]*x[1])+7883.39*x[2]-705.04)      ;
    constraint[5] = 2000 - (0.417*x[0]*x[1] + 1721.26*x[2]-136.54)       ;
    constraint[6] = 550 - (0.164/(x[0]*x[1])+631.13*x[2]-54.48) ;
    
    double total = 0.0;
    int number = 0;
    for (int i = 0; i < numberOfConstraints_; i++) {
      if (constraint[i]<0.0){
        total+=constraint[i];
        number++;
      } // int
    } // for
        
    solution.setOverallConstraintViolation(total);    
    solution.setNumberOfViolatedConstraint(number);        
  } // evaluateConstraints   

  @Override
  public double getLowerLimit(int i) {
    if(i < numberOfCities_){
      return 1;
    }else{
      return 0;
    }
  } // getLowerLimit
    
  @Override
  public double getUpperLimit(int i) {
    return numberOfCities_;
  } // getUpperLimit 

  public int getNumberOfCities(){
    return numberOfCities_
  }

  public int getNumberOfItems(){
    return numberOfItems_
  }
} // TSP
    