import java.rmi.Naming;
import java.util.Scanner;

public class AddClient {
public static void main(String args[]) {
try {
Scanner sc = new Scanner(System.in);
// Ask for server IP
System.out.print("Enter Server IP  ");
String serverIP = sc.nextLine();
String addServerURL = "//" + serverIP + "/AddServer";
AddServerIntf addServerIntf = (AddServerIntf) Naming.lookup(addServerURL);
// Take numbers as input
System.out.print("Enter first number: ");
double d1 = sc.nextDouble();
System.out.print("Enter second number: ");
double d2 = sc.nextDouble();
// Call remote method
double result = addServerIntf.add(d1, d2);
System.out.println("The sum is: " + result);
sc.close();
} catch (Exception e) {
System.out.println("Exception in main: " + e.getMessage());
e.printStackTrace();
}
}
}