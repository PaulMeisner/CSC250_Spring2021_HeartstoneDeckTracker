public class Driver 
{

	public static void main(String[] args)
	{ 
		CardParser cp = new CardParser("https://api.hearthstonejson.com/v1/25770/enUS/cards.json");
		//cp.showMinions();
		cp.sortLowestAttackToHighestAttack();
		cp.showMinions();
		//cp.executeBinarySearch(1);
		System.out.println(cp.executeBinarySearch(197));
	}
}