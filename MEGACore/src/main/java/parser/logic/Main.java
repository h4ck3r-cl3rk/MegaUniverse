package parser.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

import parser.entities.Page;
import parser.thread.ThreadManager;
import parser.thread.ThreadRunner;

public class Main {
	private static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) throws InterruptedException {

		logger.info("getting singleton for ThreadManager...");
		ThreadManager thrdMngr = ThreadManager.getInstance();
		
		List<Page> pageList = new ArrayList<>();
		
//		pageList.add(new Page("http://www.boerse.to", "Börse"));
		pageList.add(new Page("http://www.crynet.to", "Crynet"));
		pageList.add(new Page("http://example.com/", "Example"));
		pageList.add(new Page("http://en.wikipedia.org/", "Wikipedia"));
		pageList.add(new Page("https://serienstream.to/", "SerienStream"));
		pageList.add(new Page("https://serienjunkies.org/", "SerienJunkies"));
		
		for (Page page : pageList) {
			thrdMngr.addPageThread(new ThreadRunner(page));
			thrdMngr.threads.get(thrdMngr.threads.size()-1).start();
		}
		
		for (Thread thread : thrdMngr.threads) {
			thread.join();
		}
		
		ArrayList<Element> bodyList = new ArrayList<>();
		
		for (ThreadRunner thread : thrdMngr.threads) {			
			bodyList.add((thread.getDoc().body()));
		}
		
		for (Element elmnt : bodyList) {
			System.out.println(elmnt.text());
		}
		
	}
	
	public static Logger getLogger() {
		return logger;
	}

}
