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


import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.variable.Permutation;
import jmetal.encodings.variable.Binary;
import jmetal.util.JMException;
import java.util.BitSet;
import java.io.*;

/**
 * Class representing problem of the Traveling Thief
 */
public class TTP extends Problem {                 

  private int numberOfCities_;
  private int numberOfItems_;
  private double maxVelocity_;
  private double minVelocity_;
  private double maxWeight_;
  public BitSet[] items_;
  private double[][] distances_;
  private double[] values_;
  private double[] weights_;
  private double rentingRate_;

 /**
  * Constructor.
  * Creates a default instance of the TTP.
  * @param solutionType The solution type must "IntSolutionType".
  */
  public TTP(String solutionType, String problemFile) {
    numberOfVariables_   = 2;
    numberOfObjectives_  = 3 ;
    numberOfConstraints_ = 1 ;

    problemName_         = "TTP";

    if (solutionType.compareTo("TTP") == 0)
      solutionType_ = new TTPSolutionType(this) ;
    else {
      System.out.println("Error: solution type " + solutionType + " invalid") ;
      System.exit(-1) ;
    }

    try{
      readProblem(problemFile) ;
    }catch(IOException e){
      System.out.println("Error: could not read problem file "+e) ;
      System.exit(-1) ;
    }

    length_ = new int[] {numberOfCities_-1,numberOfItems_};

 } // TTP
	

  private void readProblem(String file) throws IOException {
    Reader inputFile = new BufferedReader(
                       new InputStreamReader(
                       new FileInputStream(file)));

    StreamTokenizer token = new StreamTokenizer(inputFile);
    try {
      boolean found ;
      found = false ;
      
      token.nextToken();
      while(!found) {
        if ((token.sval != null) && ((token.sval.compareTo("DIMENSION") == 0)))
          found = true ;
        else
          token.nextToken() ;
      } // while

      token.nextToken() ;
      token.nextToken() ;
      
      numberOfCities_ =  (int)token.nval ;


      found = false;
      token.nextToken();
      while(!found) {
        if ((token.sval != null) && ((token.sval.compareTo("ITEMS") == 0)))
          found = true ;
        else
          token.nextToken() ;
      } // while

      token.nextToken() ;
      token.nextToken() ; 

      numberOfItems_ =  (int)token.nval ;

      found = false;
      token.nextToken();
      while(!found) {
        if ((token.sval != null) && ((token.sval.compareTo("KNAPSACK") == 0)))
          found = true ;
        else
          token.nextToken() ;
      } // while

      token.nextToken() ;
      token.nextToken() ; 
      maxWeight_ =  (double)token.nval ;

      found = false;
      token.nextToken();
      while(!found) {
        if ((token.sval != null) && ((token.sval.compareTo("MIN") == 0)))
          found = true ;
        else
          token.nextToken() ;
      } // while

      token.nextToken() ;
      token.nextToken() ; 
      token.nextToken() ; 

      minVelocity_ =  (double)token.nval ;

      found = false;
      token.nextToken();
      while(!found) {
        if ((token.sval != null) && ((token.sval.compareTo("MAX") == 0)))
          found = true ;
        else
          token.nextToken() ;
      } // while

      token.nextToken() ;
      token.nextToken() ; 
      token.nextToken() ; 

      maxVelocity_ =  (double)token.nval;

      found = false;
      token.nextToken();
      while(!found) {
        if ((token.sval != null) && ((token.sval.compareTo("RATIO") == 0)))
          found = true ;
        else
          token.nextToken() ;
      } // while

      token.nextToken() ;
      token.nextToken() ; 


      rentingRate_ =  (double)token.nval;

      distances_ = new double[numberOfCities_][numberOfCities_];
      values_ = new double[numberOfItems_];
      weights_ = new double[numberOfItems_];
      items_ = new BitSet[numberOfCities_];

      // Find the string SECTION  
      found = false ;
      token.nextToken();
      while(!found) {
        if ((token.sval != null) && ((token.sval.compareTo("SECTION") == 0)))
          found = true ;
        else
          token.nextToken() ;
      } // while


    // Read the data

      for(int i =0; i<8; i++){
        token.nextToken();
      }

      double [] c = new double[2*numberOfCities_] ;

      for (int i = 0; i < numberOfCities_; i++) {
        token.nextToken() ;
        int j = (int)token.nval ;
        token.nextToken() ;
        c[2*(j-1)] = token.nval ;
        token.nextToken() ;
        c[2*(j-1)+1] = token.nval ;
      } // for

      double dist ;
      for (int k = 0; k < numberOfCities_; k++) {
        distances_[k][k] = 0;
        items_[k] = new BitSet(numberOfItems_);
        for (int j = k + 1; j < numberOfCities_; j++) {
          dist = Math.sqrt(Math.pow((c[k*2]-c[j*2]),2.0) +
              Math.pow((c[k*2+1]-c[j*2+1]), 2));
          distances_[k][j] = dist;
          distances_[j][k] = dist;
        } // for
      } // for

      // Find the string NUMBER  
      found = false ;
      token.nextToken();
      while(!found) {
        if ((token.sval != null) && ((token.sval.compareTo("NUMBER") == 0)))
          found = true ;
        else
          token.nextToken() ;
      }

      token.nextToken();
      token.nextToken();

      for(int i=0; i<numberOfItems_;i++){
        token.nextToken();
        //Index
        int j = (int)token.nval;

        token.nextToken();
        //Value
        values_[j-1] = token.nval;

        token.nextToken();
        //Weight
        weights_[j-1] = token.nval;

        token.nextToken();
        //City
        items_[(int)token.nval-1].set(j-1);
      }

    } // try
    catch (Exception e) { 
      System.err.println ("TSP.readProblem(): error when reading data file "+e);
      System.exit(1);
    } // catch
  } // readProblem
  /**
   * Evaluates a solution
   * @param solution The solution to evaluate
   * @throws JMException 
   */
  public void evaluate(Solution solution) throws JMException {
    double distance = 0.0;
    double weight = 0.0;
    double time = 0.0;
    double value = 0.0;
    double velocity = maxVelocity_;

    int prev = 1;
    for(int i=2; i<numberOfCities_; i++){

      int x = ((Permutation)solution.getDecisionVariables()[0]).vector_[prev-1] ;
      int y = ((Permutation)solution.getDecisionVariables()[0]).vector_[i-1] ;

      distance += distances_[x][y];
      time += distances_[x][y]/velocity;
    
      BitSet currentItems = items_[i];
      currentItems.and(((Binary)solution.getDecisionVariables()[1]).bits_);
      for (int k = currentItems.nextSetBit(0); k >= 0; k = currentItems.nextSetBit(k+1)) {
        weight += weights_[k];
        value += values_[k];
      }
      velocity = maxVelocity_ - weight*( (maxVelocity_-minVelocity_)/maxWeight_ );
    }

    int x = ((Permutation)solution.getDecisionVariables()[0]).vector_[prev-1] ;
    distance += distances_[x][0];
    time += distances_[x][0]/velocity;

    solution.setObjective(0,distance);    
    solution.setObjective(1,time*rentingRate_-value);
    solution.setObjective(2,maxWeight_-weight);
  } // evaluate

  /** 
   * Evaluates the constraint overhead of a solution 
   * @param solution The solution
   * @throws JMException 
   */  
  public void evaluateConstraints(Solution solution) throws JMException {
    double weight = 0.0;
    BitSet currentItems = ((Binary)solution.getDecisionVariables()[1]).bits_;
    for (int k = currentItems.nextSetBit(0); k >= 0; k = currentItems.nextSetBit(k+1)) {
      weight += weights_[k];
    }
    if(weight>maxWeight_){
      solution.setOverallConstraintViolation(weight-maxWeight_);    
      solution.setNumberOfViolatedConstraint(1);
    }
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
    return numberOfCities_;
  }

  public int getNumberOfItems(){
    return numberOfItems_;
  }

} // TSP
    