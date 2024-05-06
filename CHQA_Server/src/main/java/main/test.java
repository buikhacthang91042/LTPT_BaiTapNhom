package main;

import jakarta.persistence.Persistence;

public class test {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("MyPersistenceUnit");
	}

}
