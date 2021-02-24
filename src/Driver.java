
public class Driver 
{

	public static void main(String[] args)
	{ 
		CardParser cp = new CardParser("https://api.hearthstonejson.com/v1/25770/enUS/cards.json");
		cp.showMinions();
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Start sort");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		cp.sortLowestCostToHighestCost();
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Done");
		
	}
}