package LCS;
//import packages
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class findLCS{

	//lcsClustering method
	//this method uses lcs to cluster i.e. find the top most documents similar to the suspicious document or the document that is 
	//to be checked for plagarism

	public static void lcsClustering(){
		System.out.println("LCS Clustering Function");
		System.out.println("---------------------------------------------------------------------------------");
		//declaring and initiliazing variables
		int i,j=0,t=0;
		String str1="",str2="";
		float[] F_score;
		String[] cluster;
		Scanner sc1, sc2;
		String[] arrOfFile1,arrOfFile2;

		//try-catch block
		try {
			//Reading the suspicious file
			File file1 = new File("D:\\AlgorithmPrac\\LCS\\textFile3.java");
    			sc1 = new Scanner(file1);
		
    			while (sc1.hasNextLine()){
      				//System.out.println(sc1.nextLine());
				str1 = str1 +  " " + sc1.nextLine();
				//System.out.print("str"+str1);
  			}//while end
			//String text = readFileAsString("textfile.txt");
			str1 = str1.replace("\n", "").replace("\r", "").replace("\t", "");
		
			File folder = new File("D:\\AlgorithmPrac\\LCS\\files");

			File[] listOfFiles = folder.listFiles();

			F_score = new float[listOfFiles.length];
			cluster = new String[listOfFiles.length];
			
			for (File file : listOfFiles) {
    				if (file.isFile()) {
					String val =file.getName();
					str2="";
					sc2 = new Scanner(new File("D:\\AlgorithmPrac\\LCS\\files\\"+val));
					System.out.println("---------------------------------------------");
       					System.out.println("For file : "+val);
					System.out.println("---------------------------------------------");
					while (sc2.hasNextLine()){
      						//System.out.println(sc2.nextLine());
						str2 = str2 + " "+sc2.nextLine();
  					}//while end
					str2 = str2.replace("\n", "").replace("\r", "").replace("\t", "");
   		 	
					arrOfFile1 = str1.split(" ");
					arrOfFile2 = str2.split(" ");
					//System.out.print("File 1");
					/*
					for(i=0;i<arrOfFile1.length;i++){
						System.out.print(arrOfFile1[i]+" ");
					}//for i end
					System.out.print("File 2");
				
					for(i=0;i<arrOfFile2.length;i++){
						System.out.print(arrOfFile2[i]+" ");
					}//for i end*/
		
					//System.out.print("values"+arrOfFile2.length+arrOfFile1.length);
					ArrayList<String> lcs  = findingLCS(arrOfFile1,arrOfFile2,arrOfFile1.length,arrOfFile2.length);

					//no sub sequence condition
					if(lcs.size() == 0){
						System.out.println("No common subsequences found");
					}else{
						System.out.print("The Longest Common Subsequence is : ");
						//for loop for array/arraylist
						for (i=0;i<lcs.size();i++){
							System.out.print(lcs.get(i)+" ");
						}//for i end
						System.out.println("\nThe length of Longest Common Subsequence is : "+lcs.size());
					}//else end

					//System.out.println(arrOfFile1.length + arrOfFile2.length+"ff"+lcs.size());
					float R = (float) lcs.size()/arrOfFile1.length;
					float S = (float) lcs.size()/arrOfFile2.length;
					float B = (float) S/R;

					//System.out.println("R"+R+"S"+S+"B"+B+lcs.size()/arrOfFile1.length);

					float F = ( (float) ( 1 + Math.pow(B,2) ) * R * S ) / (float) ( R + ( Math.pow(B,2) * S ) );
					F_score[j]=F;
					
					if (F>0.7){		
						cluster[t] = val;
						t=t+1;
						
					}//if end
					j=j+1;
				}//if end
			}//for end
			int k;
			System.out.println("F Scores of documents");
			for(k = 0;k<F_score.length;k++){
				System.out.println("Document  No. (File)"+k+" : "+F_score[k]);
			}// for k end

			if (cluster.length>0){
				System.out.println("Clusters of document i.e. files similar to suspicious files are: ");
				for(k = 0;k<cluster.length;k++){
					System.out.println("File Name: "+cluster[k]);
				}// for k end
			}else{
				System.out.println("No similar documents found");
			}//if-else end
			
			System.out.println("---------------------------------------------------------------------------------");

		}catch (FileNotFoundException e) {
    			System.out.println(e.getMessage());
		}//try - catch end

	}//lcsClustering end

	//reading multiple files from repository and performing lcs
	public static void readMultipleFiles(){
		System.out.println("readMultipleFiles Function");
		System.out.println("---------------------------------------------------------------------------------");
		//declaring and initiliazing variables
		int i;
		String str1="",str2="";
		Scanner sc1, sc2;
		String[] arrOfFile1,arrOfFile2;
		try{
			File file1 = new File("D:\\AlgorithmPrac\\LCS\\textFile3.java");
    			sc1 = new Scanner(file1);	
    			
			while (sc1.hasNextLine()){
      				//System.out.println(sc1.nextLine());
				str1 = str1 +  " " + sc1.nextLine();
				//System.out.print("str"+str1);
  			}//while end
			str1 = str1.replace("\n", "").replace("\r", "").replace("\t", "");
			File folder = new File("D:\\AlgorithmPrac\\LCS\\files");

			File[] listOfFiles = folder.listFiles();
			
			for (File file : listOfFiles) {
    				if (file.isFile()) {
					String val =file.getName();
					sc2 = new Scanner(new File("D:\\AlgorithmPrac\\LCS\\files\\"+val));
       					//System.out.println(val);
					while (sc2.hasNextLine()){
      						//System.out.println(sc2.nextLine());
						str2 = str2 + " "+sc2.nextLine();
  					}//while end
   		 		}//if end
			}//for end
			str2 = str2.replace("\n", "").replace("\r", "").replace("\t", "");
			//System.out.print("str"+str1+"\n");
			//System.out.print("str"+str2);
			arrOfFile1 = str1.split(" ");
			arrOfFile2 = str2.split(" ");
			/*System.out.print("File 1");
			for(i=0;i<arrOfFile1.length;i++){
				System.out.print(arrOfFile1[i]+" ");
			}//for i end
			System.out.print("File 2");
		
			for(i=0;i<arrOfFile2.length;i++){
				System.out.print(arrOfFile2[i]+" ");
			}//for i end*/
			//System.out.print("values"+arrOfFile2.length+arrOfFile1.length);
			ArrayList<String> lcs  = findingLCS(arrOfFile1,arrOfFile2,arrOfFile1.length,arrOfFile2.length);
			//no sub sequence condition
		
			if(lcs.size() == 0){
				System.out.println("No common subsequences found");
			}else{
				System.out.print("The Longest Common Subsequence is : ");
				//for loop for array/arraylist
				for (i=0;i<lcs.size();i++){
					System.out.print(lcs.get(i)+" ");
				}//for i end
				System.out.println("\nThe length of Longest Common Subsequence is : "+lcs.size());
				System.out.println("\nThe Percentage of Plagiarism is : "+((float)lcs.size()/arrOfFile1.length)*100);
			}//else end

			System.out.println("---------------------------------------------------------------------------------");
		}catch (FileNotFoundException e) {
    			System.out.println(e.getMessage());
		}//try - catch end

	}//readMultipleFiles end

	//single file lcs
	public static void readFiles(){
		System.out.println("readFiles Function");
		System.out.println("---------------------------------------------------------------------------------");
		//declaring and initiliazing variables
		int i;
		Scanner sc1, sc2;
		String[] arrOfFile1,arrOfFile2;
		String str1="",str2="";
		try{
			File file1 = new File("D:\\AlgorithmPrac\\LCS\\textFile3.java");
    			sc1 = new Scanner(file1);
			File file2 = new File("D:\\AlgorithmPrac\\LCS\\textFile6.java");
			File fileName = new File("textFile3.java");
    		
			sc2 = new Scanner(file2);	
    			while (sc1.hasNextLine()){
      				//System.out.println(sc1.nextLine());
				str1 = str1 +  " " + sc1.nextLine();
				//System.out.print("str"+str1);
  			}//while end

			while (sc2.hasNextLine()){
      				//System.out.println(sc2.nextLine());
				str2 = str2 + " "+sc2.nextLine();
  			}//while end
		
			str1 = str1.replace("\n", "").replace("\r", "").replace("\t", "");
			str2 = str2.replace("\n", "").replace("\r", "").replace("\t", "");
			//System.out.print("str"+str1);
			//System.out.print("str"+str2);
			arrOfFile1 = str1.split(" ");
			arrOfFile2 = str2.split(" ");
			/*System.out.print("File 1");
			for(i=0;i<arrOfFile1.length;i++){
				System.out.print(arrOfFile1[i]+" ");
			}//for i end
	
			System.out.print("File 2");
			for(i=0;i<arrOfFile2.length;i++){
				System.out.print(arrOfFile2[i]+" ");
			}//for i end*/
			//System.out.print("values"+arrOfFile2.length+arrOfFile1.length);
			ArrayList<String> lcs  = findingLCS(arrOfFile1,arrOfFile2,arrOfFile1.length,arrOfFile2.length);
			//no sub sequence condition
		
			if(lcs.size() == 0){
				System.out.println("No common subsequences found");
			}else{
				System.out.print("The Longest Common Subsequence is : ");
				//for loop for array/arraylist
				for (i=0;i<lcs.size();i++){
					System.out.print(lcs.get(i)+" ");
				}//for i end
				System.out.println("\nThe length of Longest Common Subsequence is : "+lcs.size());
				System.out.println("\nThe Percentage of Plagiarism is : "+((float)lcs.size()/arrOfFile1.length)*100);
			}//else end
			System.out.println("---------------------------------------------------------------------------------");
		}catch (FileNotFoundException e) {
    			System.out.println(e.getMessage());
		}//try - catch end

	}//readFiles end

	
	
	//builds a matrix or table and traverses backward from the last position in the table to find the longests sub sequence
	public static ArrayList<String> findingLCS(String[] seq1, String[] seq2, int size1, int size2){
		
		//matrix of size size1+1 * size2+1
		int[][] lcsInt = new int[size1+1][size2+1];

		//first row and column is zero
		for(int i=0;i<=size1;i++){
			lcsInt[i][0]=0;
		}//for i end

		for(int i=0;i<=size2;i++){
			lcsInt[0][i]=0;
		}//for i end

		//print matrix
		System.out.println("Intitial Matrix / Table : ");
		for (int i=0;i<=size1;i++){
			for (int j=0;j<=size2;j++){
			System.out.print(lcsInt[i][j]+" ");
			}//for j end
			System.out.println("");

		}//for i end
		
		//Logic: find if the sequence characters are same if is yes move diagonally
		//Logic: else move to the value which is a greater value out of left and up positons
		for(int i=1;i<=size1;i++){
			
			for(int j=1;j<=size2;j++){
				//check sequence characters are same
				
				if  (seq1[i-1].equals(seq2[j-1])){
					//move diagonally
					lcsInt[i][j] = lcsInt[i-1][j-1]+1;
					

				}else{
					//move to the value which is a greater value out of left and up positons
					lcsInt[i][j] = Math.max(lcsInt[i][j-1],lcsInt[i-1][j]);
					
					

				}//if-else end

			}//for j end
		

		}//for i end
		//print matrix
int i,j;
		System.out.println("Matrix / Table after calculation : ");
		for (i=0;i<=size1;i++){
			for (j=0;j<=size2;j++){
			System.out.print(lcsInt[i][j]+" ");
			}//for j end
			System.out.println("");

		}//for i end

		//go bakwards to find subsequence
		i=seq1.length;j=seq2.length;
		ArrayList<String> lcs = new ArrayList<String>();		
		while(i!=0 && j!=0){
			//System.out.print(i+" "+j);
			//check if sequence chars are same, if yes move diagonally up
			if (seq1[i-1].equals(seq2[j-1])){
				// as we are backtracking add elemet at begnning of list, start position
				lcs.add(0,seq1[i-1]);
				i=i-1;
				j=j-1;

			}else if(lcsInt[i][j-1]>lcsInt[i-1][j]){ //check for greater value between up and left values
				//move up; up value is greater
				j=j-1;
			}else{
				//move left; left value is greater
				i=i-1;
			}//if-else if -else end

		}//while end
		//return lcs
		return lcs;

	}//end dynamicConquerLCS


}