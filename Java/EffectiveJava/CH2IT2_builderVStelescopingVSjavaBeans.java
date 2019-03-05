// Telescoping constructor pattern - does not scale well
public class NutritionFacts {
	private final int servingSize; //required
	private final int servings; //required
	private final int calories; //optional
	private final int fat; //optional
	private final int sodium; //optional
	
	public NutritionFacts(int servingSize, int servings) {
		this(servingSize, servings, 0);
	}
	public NutritionFacts(int servingSize, int servings, int calories) {
		this(servingSize, servings, calories, 0);
	}
	public NutritionFacts(int servingSize, int servings, int calories, int fat) {
		this(servingSize, servings, calories, fat, 0);
	}
	public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
		this.servingSize = servingSize;
		this.servings = servings;
		this.calories = calories;
		this.fat = fat;
		this.sodium = sodium;
	}
}
// Create an instance
NutritionFacts cocaCola = new NutritionFacts(240,8,100,0,35);

// JavaBeans Pattern - allows inconsistency, mandates mutability
public class NutritionFacts {
	private int servingSize = -1;
	private int servings = -1;
	private int calories = 0;
	private int fat = 0;
	private int sodium = 0;
	
	public NutritionFacts() {};
	// Setters
	public void setServingSize(int val) {servingSize = val;}
	public void setServings(int val) {servings = val;}
	public void setCalories(int val) {calories = val;}
	public void setFat(int val) {fat = val;}
	public void setSodium(int val) {sodium = val;}
}
// Create an instance
NutritionFacts cocaCola = new NutritionFacts();
cocaCola.setServingSize(240);
cocaCola.setServings(8);
cocaCola.setCalories(100);
cocaCola.setSodium(35);

// Builder Pattern
public class NutritionFacts {
	private final int servingSize; 
	private final int servings; 
	private final int calories; 
	private final int fat;
	private final int sodium; 
	
	public static class Builder {
		private final int servingSize; 
		private final int servings; 
		
		private int calories = 0;
		private int fat = 0;
		private int sodium = 0;
		
		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}
		public Builder calories(int val) {
			calories = val; return this;
		}
		public Builder fat(int val) {
			fat = val; return this;
		}
		public Builder sodium(int val) {
			sodium = val; return this;
		}
		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}
	
	private NutritionFacts(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
	}
}
// Create an instance
NutritionFacts cocaCola = new NutritionFacts.Builder(240,8).calories(100).sodium(35).build();