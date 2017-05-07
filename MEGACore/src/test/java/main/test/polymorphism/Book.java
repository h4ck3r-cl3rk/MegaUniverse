package main.test.polymorphism;

public class Book {
	private double price;

	private double weight;

	// accept the visitor

	public void accept(Visitor vistor) {

		// visitor.visit(this);

	}

	public double getPrice() {

		return price;

	}

	public double getWeight() {

		return weight;
	}
}
