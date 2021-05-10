package src;
public class OldCoffeeMachine {

	private static OldCoffeeMachine isCreated = null;
	
	private OldCoffeeMachine() {
		
	}
	
	public static OldCoffeeMachine getInstance() {
		if (isCreated == null) {
			isCreated = new OldCoffeeMachine();
		} else {
			
		}
		return isCreated;	
	}
	
	public void SelectA() {
		System.out.println("Selection A: Making Dark Roast...");
		System.out.println("Brewing complete. Enjoy!");
	}
	
	public void selectB() {
		System.out.println("Selection B: Making Coffee with milk...");
		System.out.println("Brewing complete. Enjoy!");
	}
}