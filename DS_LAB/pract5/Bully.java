import java.util.*;
public class Bully {
static int n = 5;
static boolean[] active = {true, true, true, true, true};
static int coordinator = 4; // index (0-based), so process 5
public static void election(int initiator) {
System.out.println("\nProcess " + (initiator + 1) + " starts election");
boolean higherAlive = false;
for (int i = initiator + 1; i < n; i++) {
if (active[i]) {
System.out.println("Process " + (initiator + 1) + " sends ELECTION to " + (i + 1));
higherAlive = true;
}
}
if (!higherAlive) {
coordinator = initiator;
System.out.println("Process " + (initiator + 1) + " becomes COORDINATOR");
} else {
for (int i = initiator + 1; i < n; i++) {
if (active[i]) {
System.out.println("Process " + (i + 1) + " sends OK to " + (initiator + 1));
election(i); // higher process takes over
return;
}
}
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.println("Initial Coordinator: Process " + (coordinator + 1));
while (true) {
System.out.println("\n1. Down Process\n2. Start Election\n3. Exit");
int choice = sc.nextInt();
switch (choice) {
case 1:
System.out.print("Enter process to down: ");
int down = sc.nextInt() - 1;
active[down] = false;
System.out.println("Process " + (down + 1) + " is DOWN");
if (down == coordinator) {
System.out.println("Coordinator failed!");
}
break;
case 2:
System.out.print("Enter initiator process: ");
int init = sc.nextInt() - 1;
election(init);
System.out.println("New Coordinator: Process " + (coordinator + 1));
break;
case 3:
return;
}
}
}
}