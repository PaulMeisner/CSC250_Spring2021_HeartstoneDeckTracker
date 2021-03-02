import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CardParser 
{
	private String urlString;
	private ArrayList<HearthstoneCard> theMinions;
	
	public CardParser(String urlString)
	{
		//initial fields
		this.urlString = urlString;
		theMinions = new ArrayList<HearthstoneCard>();
		
		URLReader hearthstoneURLReader = new URLReader(this.urlString);
		Object obj = JSONValue.parse(hearthstoneURLReader.getTheURLContents());
		
	    if(obj instanceof JSONArray)
	    {
	    	//I am only in here if obj IS a JSONArray
	    	JSONArray array = (JSONArray)obj;
	    	
		    for(int i = 0; i < array.size(); i++)
		    {
		    	JSONObject cardData = (JSONObject)array.get(i);
		    	if(cardData.containsKey("cost") && cardData.containsKey("name"))
		    	{
		    		if(cardData.containsKey("type") && cardData.get("type").equals("MINION"))
		    		{
		    			//I am only here is this is a minion card!!!
		    			System.out.println(cardData.keySet().toString());
		    			String name = (String)cardData.get("name");
		    			int cost = Integer.parseInt(cardData.get("cost").toString());
		    			int attack = Integer.parseInt(cardData.get("attack").toString());
		    			int health = Integer.parseInt(cardData.get("health").toString());
		    			HearthstoneCard temp = new HearthstoneCard(name, cost, attack, health);
		    			theMinions.add(temp);
		    		}
		    	}
		    	
		    }
	    }
	}
	
	public void showMinions()
	{
		for(int i = 0; i < this.theMinions.size(); i++)
		{
			this.theMinions.get(i).display();
		}
	}
	
	public void selectionSortHighestCostToLowestCost()
	{
		for(int max = 0; max < this.theMinions.size(); max++)
		{
			int maxIndex = this.findIndexOfLargestCostFromPosition(max);
			HearthstoneCard temp = this.theMinions.get(max);
			this.theMinions.set(max, this.theMinions.get(maxIndex));
			this.theMinions.set(maxIndex, temp);
		}
	}
	public void  insertionSortLowestAttackToHighestAttack()
	{
		for(int currStart = 1; currStart < this.theMinions.size(); currStart++)
		{
			//try to move the value at currStart as far up the array as possible
			//then move on to the next currStart
			int currIndex = currStart;
			HearthstoneCard temp;
			while(currIndex > 0 && this.theMinions.get(currIndex).getAttack() < 
					this.theMinions.get(currIndex-1).getAttack())
			{
				//we swap the 2 places
				temp = this.theMinions.get(currIndex);
				this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
				this.theMinions.set(currIndex-1, temp);
				currIndex--;
				
			}	
		}
	
		
	}
	
	public class executeBinarySearch
	{
		//need to call my sort attack function
		//need to call my binary search function
	}
	
	//how do I take in my minions array?
	 private int binarySearch(int[] theMinions, int attack)
	{
		int begin = 0;
		int end = theMinions.length-1;
		int middle;
		
		//execute at least one time
		// make sure the loop executes at least one since  using boolean expres
		do
		{
		
			middle = (begin + end)/2;
			//if this is middle and middle is val that wee are looking for, return middle as  answer
			
			if(theMinions[middle] == attack)
			{
				return middle;
			}
			//if what we are looking for is at left half, update end
			else if(attack < theMinions[middle])
			{
				end = middle - 1;
			}
			else
			{
				begin = middle + 1;
			}
		}	
		while(begin <= end);
		return -1;
	
	}
	
	
	
	public void insertionSortLowestCostToHighestCost()
	{
		for(int currStart = 1; currStart < this.theMinions.size(); currStart++)
		{
			//try to move the value at currStart as far up the array as possible
			//then move on to the next currStart
			int currIndex = currStart;
			HearthstoneCard temp;
			while(currIndex > 0 && this.theMinions.get(currIndex).getCost() < 
					this.theMinions.get(currIndex-1).getCost())
			{
				//we swap the 2 places
				temp = this.theMinions.get(currIndex);
				this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
				this.theMinions.set(currIndex-1, temp);
				currIndex--;
				
			}	
		}
	}
	//for homework, this sorts my array from lowest att to highest att
	public void sortLowestAttackToHighestAttack()
	{
		ArrayList<HearthstoneCard> theAttackSortedList = new ArrayList<HearthstoneCard>();
		HearthstoneCard nextSmallestAttack;
		while(this.theMinions.size() > 0)
		{
			nextSmallestAttack = this.findSmallestAttack();
			theAttackSortedList.add(nextSmallestAttack);
		}
		this.theMinions = theAttackSortedList;  
	}
	public void sortLowestCostToHighestCost()
	{
		//this methods job is to take our ArrayList of minions and re-arrange it so that
		//it is in the order of cards with the lowest cost first, and cards with the highest
		//cost last.
		//Note: this.theMinions.get(3).getCost() will give you the cost of card #3
		//Note: this.theMinions.remove(3) will remove the card that used to be at bucket 3
		//you will need to cobble together your own algorithm for getting this arraylist sorted
		ArrayList<HearthstoneCard> theSortedList = new ArrayList<HearthstoneCard>();
		HearthstoneCard nextSmallest;
		while(this.theMinions.size() > 0)
		{
			nextSmallest = this.findSmallest();
			theSortedList.add(nextSmallest);
		}
		
		//this is making the var theMinions point to the same place
		//as theSortedList in memory.  We could have also kept it in
		//its original place and copies our sorted card back over.
		this.theMinions = theSortedList;  

	}
	
	private int findIndexOfLargestCostFromPosition(int pos)
	{
		//find the largest cost card from this position forward and return it
		HearthstoneCard currWinner = this.theMinions.get(pos);
		int indexOfWinner = pos;
		for(int i = pos+1; i < this.theMinions.size(); i++)
		{
			if(this.theMinions.get(i).getCost() > currWinner.getCost())
			{
				//we have a new winner
				currWinner = this.theMinions.get(i);
				indexOfWinner = i;
			}
		}
		//we know that currWinner is the card with the highest cost starting 
		//at index pos and we know it is found at index indexOfWinner in theMinions
		return indexOfWinner;
	}
	
	//for Homework created a function that finds smallest attack
	private HearthstoneCard findSmallestAttack()
	{
		HearthstoneCard currWinner = this.theMinions.get(0);
		int indexOfWinner = 0;
		
		for(int i = 1; i < this.theMinions.size(); i++)
		{
			if(this.theMinions.get(i).getAttack() < currWinner.getAttack())
			{
				currWinner = this.theMinions.get(i);
				indexOfWinner = i;
			}
		}
		
		this.theMinions.remove(indexOfWinner);
		return currWinner;
	}
	private HearthstoneCard findSmallest()

	{
		//is to go through the current state of theMinions and remove and return the card with
		//the smallest value
		HearthstoneCard currWinner = this.theMinions.get(0);
		int indexOfWinner = 0;
		
		for(int i = 1; i < this.theMinions.size(); i++)
		{
			if(this.theMinions.get(i).getCost() < currWinner.getCost())
			{
				currWinner = this.theMinions.get(i);
				indexOfWinner = i;
			}
		}
		//the card with the smallest cost should be in currWinner
		//the position of the card with the smallest cost should be in indexOfWinner
		this.theMinions.remove(indexOfWinner);
		return currWinner;
	}
}