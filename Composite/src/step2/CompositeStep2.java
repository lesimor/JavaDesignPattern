package step2;

import java.util.ArrayList;
import java.util.List;

abstract class ComputerDevice{
	abstract int getPrice();
	abstract int getPower();
}

class Keyboard extends ComputerDevice{
	private int price;
	private int power;

	public Keyboard(int power, int price){
		this.power = power;
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public int getPower(){
		return power;
	}
}

class Body extends ComputerDevice{
	private int price;
	private int power;

	public Body(int power, int price){
		this.power = power;
		this.price = price;
	}

	int getPrice(){
		return price;
	}

	int getPower(){
		return power;
	}
}

class Monitor extends ComputerDevice{
	private int price;
	private int power;

	public Monitor(int power, int price){
		this.power = power;
		this.price = price;
	}

	int getPrice(){
		return price;
	}

	int getPower(){
		return power;
	}
}

class Computer{
	// 해결.
	// 주변기기를 추상화하여 새로운 주변기기의 추가에도 코드를 수정할 필요가 없도록 설계.
	private List<ComputerDevice> components = new ArrayList<ComputerDevice>();
	
	public void addComponent(ComputerDevice component){
		this.components.add(component);
	}
	
	public void removeComponent(ComputerDevice component){
		this.components.remove(component);
	}
	
	// 새로운 부품인 스피커가 추가되어도 코드를 수정할 필요가 없게 되었다.
	public int getPrice(){
		int totalPrice = 0;
		
		for(ComputerDevice component:components){
			totalPrice += component.getPrice();
		}
		return totalPrice;
	}
	
	public int getPower(){
		int totalPower = 0;
		
		for(ComputerDevice component:components){
			totalPower += component.getPower();
		}
		return totalPower;
	}
	
}

public class CompositeStep2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
