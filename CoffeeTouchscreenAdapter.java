package src;
public class CoffeeTouchscreenAdapter implements CoffeeMachineInterface {

private OldCoffeeMachine oldVendingMachine;

public CoffeeTouchscreenAdapter() {
	
	oldVendingMachine = OldCoffeeMachine.getInstance();
	System.out.println("oldVending pointer: " + Integer.toString(System.identityHashCode(oldVendingMachine)));
}

@Override
public void chooseFirstSelection() {
	oldVendingMachine.SelectA();
}

@Override
public void chooseSecondSelection() {
	oldVendingMachine.selectB();
}
}