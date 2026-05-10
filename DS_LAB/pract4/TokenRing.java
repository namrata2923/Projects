
import java.util.*;
public class TokenRing {
	static final int NUM_NODES = 3;
	static int token = 0;
	static class Process extends Thread {
	int id;
	public Process(int id) {
		this.id = id;
	}

public void run() {
	try {
	while (true) 
	{
		Thread.sleep((int)(Math.random() * 3000));
		if (token == id) {
		// Enter critical section
		System.out.println("Process " + id + " ENTERING CS");
		Thread.sleep(1000);
		System.out.println("Process " + id + " EXITING CS");
		// Pass token
		token = (id + 1) % NUM_NODES;
		System.out.println("Token passed to Process " + token);
		}
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}//process class ends

public static void main(String[] args) {
	for (int i = 0; i < NUM_NODES; i++) {
	new Process(i).start();
	}
}
}