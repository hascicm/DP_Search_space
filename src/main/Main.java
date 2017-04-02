package main;

import java.awt.RadialGradientPaint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Repair;
import model.Smell;
import model.State;

public class Main {
	public static List<Repair> repairs = new ArrayList<Repair>();
	public static List<Smell> smells = new ArrayList<Smell>();

	public static void main(String[] args) {

		FileHandler.loadSmells(smells);
		// FileHandler.printSmells(smells);
		FileHandler.loadRepairs(repairs, smells);

		// FileHandler.printSmells(smells);
		// FileHandler.printrepairs(repairs);

		System.out.println("number of loaded smells   : " + smells.size());
		System.out.println("number of loaded repairs  : " + repairs.size());
		
		State s = new State();
		s.getSmells().add(smells.get(6));
		s.getSmells().add(smells.get(2));

		s.getAvanlibleRepairs().add(repairs.get(0));
		s.getAvanlibleRepairs().add(repairs.get(1));
		s.getAvanlibleRepairs().add(repairs.get(2));
		
		s.printSmells();
		s.printAvanlibleRepairs();
		
		ArrayList<State> states =  s.applyRepairs();
		
		System.out.println("-output-");
	
		for (State state : states){
			
			state.printSmells();
			System.out.println("-----END-----");
		}
		
		
		// s.applyRepair(repairs.get(0));
//		s.applyRepair(repairs.get(0));
//		s.applyRepair(repairs.get(2));
//		s.applyRepair(repairs.get(3));
/*		State s2 = new State();

		s2.getSmells().add(smells.get(2));
		s2.getSmells().add(smells.get(6));

		s.printSmells();
		s2.printSmells();

		if (s.getSmells().containsAll(s2.getSmells()) && s2.getSmells().containsAll(s.getSmells()))
			System.out.println("comapre sucess");
*/
	}
}
