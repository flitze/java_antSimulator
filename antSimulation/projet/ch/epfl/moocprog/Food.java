package ch.epfl.moocprog;

public final class Food extends Positionable {
	
	private double quantity;
	
	public Food(ToricPosition tp, double quantity) {
		super.setPosition(tp);
		this.quantity = quantity > 0 ? quantity: 0;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public double takeQuantity(double quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException();
		}
		
		double quantityDiff = this.quantity - quantity;
		if (quantityDiff >= 0) {
			this.quantity = this.quantity - quantity;
			return quantity;
		} else {
			this.quantity = 0;
			return quantity + quantityDiff;
		}
		
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" +
			String.format("Qunatity : %.2f", getQuantity()); 
	}
}
