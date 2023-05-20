package maze.model;

public class Pair<Key, Value> {
	private Key key;
	private Value value;
	
	public Pair(Key key, Value value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Value value) {
		this.value = value;
	}

}
