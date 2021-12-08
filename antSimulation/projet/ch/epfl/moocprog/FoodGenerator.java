package ch.epfl.moocprog;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;

import ch.epfl.moocprog.random.NormalDistribution;
import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;

public final class FoodGenerator {
	
	private Time time;
	
	public FoodGenerator() {
		time = Time.ZERO;
	}
	
	void update(FoodGeneratorEnvironmentView env, Time dt) {
		time = time.plus(dt);
		
		final Time delay = getConfig().getTime(FOOD_GENERATOR_DELAY);
		final double minQuantity = getConfig().getDouble(NEW_FOOD_QUANTITY_MIN);
		final double maxQuantity = getConfig().getDouble(NEW_FOOD_QUANTITY_MAX);
		
		final int worldWidth = getConfig().getInt(WORLD_WIDTH);
		final int worldHeight = getConfig().getInt(WORLD_HEIGHT);
		
		while (time.compareTo(delay) >= 0) {
			double foodAmount = UniformDistribution.getValue(minQuantity, maxQuantity);
			double xCoordinate = NormalDistribution.getValue(worldWidth / 2.0, Math.pow(worldWidth, 2) / 16);
			double yCoordinate = NormalDistribution.getValue(worldHeight / 2.0, Math.pow(worldHeight, 2) / 16);
			env.addFood(new Food(new ToricPosition(xCoordinate, yCoordinate), foodAmount));
			time = time.minus(delay);
			
		}
		
	}
}
