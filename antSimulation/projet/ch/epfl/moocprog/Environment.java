package ch.epfl.moocprog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;

public final class Environment implements FoodGeneratorEnvironmentView {

	private FoodGenerator fg;
	private List<Food> foodList;
	
	public Environment() {
		fg = new FoodGenerator();
		foodList = new LinkedList<Food>();
	}
	
	@Override
	public void addFood(Food food) {
		Utils.requireNonNull("Food must not be null", food);
		foodList.add(food);
	}
	
	public List<Double> getFoodQuantities() {
		List<Double> quantityList = new ArrayList<Double>();
		for (Food foodInList: foodList) {
			quantityList.add(foodInList.getQuantity());
		}
		return quantityList;
	}
	
	public void update(Time dt) {
		fg.update(this, dt);
		foodList.removeIf(food -> food.getQuantity() <= 0);
	}

}
