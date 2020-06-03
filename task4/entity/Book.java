package by.module5.task4.entity;

public class Book {
	public final String DEFAULT_NAME = "Treasure";
	
	private String name;
	private double price;
	
	public Book(String name, double price) {
		this.setName(name);
		this.setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		if (name.isEmpty() || name == null) {
			name = DEFAULT_NAME;
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
		if (price < 0) {
			this.price = - price;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Treasure [name=" + name + ", price=" + price + "]";
	}	
}
