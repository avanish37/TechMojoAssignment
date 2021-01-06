package com.avanish.techmojo;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Twitter {

	private static Map<String,Integer> mapRecord = new HashMap<String, Integer>();

	public static void main(String[] args) throws IOException {
	
	int i=1;
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Enter the no of tweet you want to post : ");
	int num = sc.nextInt();
	sc.nextLine();
	
	while(i <= num) {
	
	System.out.println("Enter a tweet/sentence having #HasTag : ");
	String str = sc.nextLine();
	
	addHashTwitterWithHashTag(str);
	
	i++;
	}

	//calling to print the records.
	printTopTenRecord();
}
	
	//Method to add hastags from tweet to hastag
	public static void addHashTwitterWithHashTag(String input) 
	{
	String hasTagString=findHasTagfromInputString(input);
	addtwitterRecord(hasTagString,mapRecord);
	}

	//Method to print the top N records
	public static void printTopTenRecord()
	{
	Map<String,Integer> sortedMap=sortMapByCount(mapRecord);
	Set<Map.Entry<String, Integer>> entrySetList=sortedMap.entrySet();
	int numberOfRecords=entrySetList.size();
	if(numberOfRecords>5)
	{
	numberOfRecords=10;
	}
	int currentValue=1;

	System.out.println("Top 10 Tweets are below as : ");
	for (Map.Entry<String, Integer> entry : entrySetList) {
	         System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue());
	         if(currentValue==10) {
	         break;
	         }
	         else {
	         currentValue++;
	         }
	     }
	}

	//Method to sort the HasTags
	private static Map<String,Integer> sortMapByCount(Map<String,Integer> mapData)
	{
	  Map<String,Integer> mapResult=mapData.entrySet().stream().sorted((ob1,ob2)->{
	  int record=ob2.getValue().compareTo(ob1.getValue());
	 
	  if(record==0)
	  {
	  return ob1.getKey().compareTo(ob2.getKey());
	  }
	  return record;
	}).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	return mapResult;
	}

	//Method to add twitter record
	private static void addtwitterRecord(String hasTagString,Map<String,Integer> twitterRecord) {
	if(twitterRecord.containsKey(hasTagString)) {
	int oldValue=twitterRecord.get(hasTagString);
	int currentValue=oldValue+1;
	twitterRecord.put(hasTagString, currentValue);
	}
	else {
	twitterRecord.put(hasTagString, 1);
	}
	}

	//Method to find HasTag from input string
	private static String findHasTagfromInputString(String input)
	{
	int findHashIndex = input.indexOf("#");
	int findSpaceAfterHash = input.indexOf(" ", findHashIndex);
	if(findSpaceAfterHash == -1) 
	{
	findSpaceAfterHash = input.length();
	}
	String hashTagWord = input.substring(findHashIndex,findSpaceAfterHash);
	return hashTagWord;
	}
}
	
