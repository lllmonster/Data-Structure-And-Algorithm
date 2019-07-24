// 1. Singleton with public final field
public class Elvis {
	public static final Elvis INSTANCE = new Elvis();
	private Elvis() {...}
}

// 2. Singleton with static factory
public class Elvis {
	private static final Elvis INSTANCE = new Elvis();
	private Elvis() {...}
	public static Elvis getInstance() {return INSTANCE;}
}

// 3. Enum Singleton - the preferred approach!
public enum Elvis {
	INSTANCE;
}