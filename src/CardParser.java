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
	
	
	private ArrayList<HearthstoneCard> theMinionsSorted;
	public void sortLowestCostToHighestCost()
	{
		theMinionsSorted = new ArrayList<HearthstoneCard>();
		for(int i = 0; i < this.theMinions.size(); i++)
			{
			this.theMinions.get(i).getCost();
				if(this.theMinions.get(i).getCost() == 1)
					theMinionsSorted.add(theMinions.get(i));
					this.theMinions.remove(i);
			}
		
		for(int i = 0; i < this.theMinions.size(); i++)
			{
			this.theMinions.get(i).getCost();
				if(this.theMinions.get(i).getCost() == 2)
					theMinionsSorted.add(theMinions.get(i));
					this.theMinions.remove(i);
			}
		
		for(int i = 0; i < this.theMinions.size(); i++)
			{
			this.theMinions.get(i).getCost();
				if(this.theMinions.get(i).getCost() == 3)
					theMinionsSorted.add(theMinions.get(i));
					this.theMinions.remove(i);
			}
	
		for(int i = 0; i < this.theMinions.size(); i++)
			{
			this.theMinions.get(i).getCost();
				if(this.theMinions.get(i).getCost() == 4)
					theMinionsSorted.add(theMinions.get(i));
					this.theMinions.remove(i);
			}
		
		for(int i = 0; i < this.theMinions.size(); i++)
			{
			this.theMinions.get(i).getCost();
				if(this.theMinions.get(i).getCost() == 5)
					theMinionsSorted.add(theMinions.get(i));
					this.theMinions.remove(i);
			}
	
		for(int i = 0; i < this.theMinions.size(); i++)
			{
			this.theMinions.get(i).getCost();
				if(this.theMinions.get(i).getCost() == 6)
					theMinionsSorted.add(theMinions.get(i));
					this.theMinions.remove(i);
			}
		
		for(int i = 0; i < this.theMinions.size(); i++)
			{
			this.theMinions.get(i).getCost();
				if(this.theMinions.get(i).getCost() == 7)
					theMinionsSorted.add(theMinions.get(i));
					this.theMinions.remove(i);
			}
	
		for(int i = 0; i < this.theMinions.size(); i++)
			{
			this.theMinions.get(i).getCost();
				if(this.theMinions.get(i).getCost() == 8)
					theMinionsSorted.add(theMinions.get(i));
					this.theMinions.remove(i);
			}
		
		for(int i = 0; i < this.theMinions.size(); i++)
			{
				this.theMinions.get(i).getCost();
					if(this.theMinions.get(i).getCost() == 9)
						theMinionsSorted.add(theMinions.get(i));
						this.theMinions.remove(i);
			}
	
		for(int i = 0; i < this.theMinions.size(); i++)
			{
			this.theMinions.get(i).getCost();
				if(this.theMinions.get(i).getCost() == 10)
					theMinionsSorted.add(theMinions.get(i));
					this.theMinions.remove(i);
			}
		
		for(int i = 0; i < this.theMinionsSorted.size(); i++)
			{
			this.theMinionsSorted.get(i).display();
			}
				
		
			
				
			//System.out.println("The Sort 0 is:" + theMinionsSort);
		}
		
		//this methods job is to take our ArrayList of minions and re-arrange it so that
		//it is in the order of cards with the lowest cost first, and cards with the highest
		//cost last.
		//Note: this.theMinions.get(3).getCost() will give you the cost of card #3
		//Note: this.theMinions.remove(3) will remove the card that used to be at bucket 3
		//you will need to cobble together your own algorithm for getting this arraylist sorted
		
	}
