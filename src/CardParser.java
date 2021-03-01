import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.util.Collections;

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
	
	public void insertionSortLowestToHighestCost() 
	{
		for(int currStart =1; currStart < this.theMinions.size(); currStart++)
		{
					//try to move valuee at currstart as far up the array as possible
					//then move on to the next currstart
			
			HearthstoneCard temp;
			int currIndex = currStart;
			while(currIndex > 0 && this.theMinions.get(currIndex).getCost() < this.theMinions.get(currIndex-1).getCost())
			{
				//we swap the 2 places
				temp = this.theMinions.get(currIndex);
				this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
				this.theMinions.set(currIndex-1, temp);
				currIndex--;
			}
		}
	}
	

	public void sortLowestCostToHighestCost()
	{
		ArrayList<HearthstoneCard> theSortedList = new ArrayList<HearthstoneCard>();
		HearthstoneCard nextSmallest;
		while(this.theMinions.size() > 0)
		{
			nextSmallest = this.findSmallest();
			theSortedList.add(nextSmallest);
		}
		this.theMinions = theSortedList;
	}
	
	private HearthstoneCard findSmallest()
	{
		//is to go through the current state of theminions and remove and return the card with the smallest value
		
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
		this.theMinions.remove(indexOfWinner);
		return currWinner;
	}
	
	public void SelectionSortHighestToLowestCost() 
	{
		
		/*
		 * inital idea:
		 * create two loops that each go through the array and compare cost of minions to each other
		 * if cost of minion in loop one is greater than cost of  minmion in loop two, keep comparing the cost of minion in loop 1 with minions in  loop 2
		 */
	
		for(int currStart =1; currStart < this.theMinions.size(); currStart++)
		{
			for(int currSecond  = currStart +1; currSecond < this.theMinions.size(); currSecond++)
			{
				int currIndex = currStart;
				HearthstoneCard temp;
				if(this.theMinions.get(currStart).getCost() > this.theMinions.get(currSecond).getCost())
				{
					temp = this.theMinions.get(currStart);
					while(currIndex > 0 && temp.getCost() > this.theMinions.get(currSecond).getCost())
					{
						temp = this.theMinions.get(currIndex);
						this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
						this.theMinions.set(currIndex-1, temp);
						currIndex--;
					}
				}
				else
				{
					if(this.theMinions.get(currStart).getCost() < this.theMinions.get(currSecond).getCost())
					{
						temp = this.theMinions.get(currSecond);
						while(currIndex > 0 && temp.getCost() > this.theMinions.get(currStart).getCost())
						{
							temp = this.theMinions.get(currIndex);
							this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
							this.theMinions.set(currIndex-1, temp);
							currIndex--;
						}
					}
				}
		
			}
		}
		
		
	}
}
	
			
				

	            	
	            	
		
	

		
		

		//this methods job is to take our ArrayList of minions and re-arrange it so that
		//it is in the order of cards with the lowest cost first, and cards with the highest
		//cost last.
		//Note: this.theMinions.get(3).getCost() will give you the cost of card #3
		//Note: this.theMinions.remove(3) will remove the card that used to be at bucket 3
		//you will need to cobble together your own algorithm for getting this arraylist sorted
		
	
