package parser.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
	private static ThreadManager threadManagerInstance;
	public List<ThreadRunner> threads = new ArrayList<>();
	public final int MAX_THREADS = 4;
	
	private ThreadManager () {
		super();
	}
	
	public void addPageThread(ThreadRunner tr) {
		threads.add(tr);
	}
	
	
	public static ThreadManager getInstance() {
		if (threadManagerInstance == null) {
			threadManagerInstance = new ThreadManager();
		}
		
		return threadManagerInstance;
	}
	
}
