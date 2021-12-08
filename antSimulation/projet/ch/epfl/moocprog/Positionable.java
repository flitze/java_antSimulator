package ch.epfl.moocprog;

public class Positionable {
	
	private ToricPosition toricPosition;
	
	public Positionable() {
		toricPosition = new ToricPosition();
	}
	
	public Positionable(ToricPosition tp) {
		toricPosition = tp;
	}
	
	public ToricPosition getPosition() {
		return toricPosition;
	}
	
	protected void setPosition(ToricPosition tp) {
		toricPosition = tp;
	}
	
	@Override
	public String toString() {
		return toricPosition.toString();
	}
}
