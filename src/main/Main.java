package main;

import java.util.ArrayList;
import java.util.List;

import model.Repair;
import model.Smell;
import model.State;

public class Main {
	public static List<Repair> repairs = new ArrayList<Repair>();
	public static List<Smell> smells = new ArrayList<Smell>();

	public static void main(String[] args) {

		FileHandler.loadSmells(smells);
		FileHandler.loadRepairs(repairs, smells);

		// FileHandler.printSmells(smells);
		// FileHandler.printrepairs(repairs);
		
		State s = new State();
		s.getSmells().add(smells.get(0));
		s.printSmells();
		s.findRepairsForSmells(repairs);
		
		s.printAvanlibleRepairs();
		s.calculateFitness();
		List<State> act = s.expandStete();
		System.out.println("----------generation best---------");

		System.out.println("size=   " + act.size());
		for (State state : act){
			System.out.println("-------------state------------");
			state.printSmells();
			state.printAvanlibleRepairs();
			
		}
		
		
		
//		
//		
//		State best = s;
//		List<State> act = s.applyRepairs();
//		List<State> temp = new ArrayList<State>();
//
//		for (int i = 0; i < 6; i++) {
//			
//			for (State state : act) {
//				temp.addAll(state.applyRepairs());
//			}			
//			
//			for (State findbest : temp) {
//				//System.out.println(findbest.getFittnes());
//				best = temp.get(0);
//				if (best.getFittnes() < findbest.getFittnes()) {
//					best = findbest;
//				}
//			}
//			
//			act = temp;
//			temp = new ArrayList<State>();
//			
//			System.out.println("----------generation best---------");
//			best.printSmells();
//			
//
//		}
//		
//		
//		
//		System.out.println("----------generation best---------");
//		best.printSmells();
	}
}
