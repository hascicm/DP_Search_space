package searchAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.State;

public class BreadthFirstSearch {

	private static BreadthFirstSearch INSTANCE;

	private BreadthFirstSearch() {
	}

	public static BreadthFirstSearch getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BreadthFirstSearch();
		}

		return INSTANCE;
	}

	public State search(State start, boolean debug, int max) {
		State best = start;
		Queue<State> queue = new LinkedList<State>();
		queue.add(start);
		int i = 0;
		while (!queue.isEmpty()) {

			State current = queue.poll();
			if (current.getSmells().isEmpty()) {
				return current;
			}
			List<State> temp = current.expandStete();
			for (State s : temp) {
				queue.add(s);
			}
			i++;
			if (i > max) {
				return current;
			}
		}
		return null;
	}

}
