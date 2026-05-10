import java.io.*;
import java.net.*;
import java.util.Scanner;
public class CalculatorClient {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
int choice;
do {
System.out.println("\n=== Calculator Web Service Client ===");
System.out.println("1.Add 2.Subtract 3.Multiply 4.Divide 5.Exit");
System.out.print("Enter choice: ");
choice = sc.nextInt();
if (choice >= 1 && choice <= 4) {
System.out.print("Enter first number: ");
int a = sc.nextInt();
System.out.print("Enter second number: ");
int b = sc.nextInt();
String op;
switch (choice) {
case 1: op="add"; break;
case 2: op="subtract"; break;
case 3: op="multiply"; break;
default: op="divide";
}
System.out.println("Response: "+callService(op, a, b));
}
} while (choice != 5);
sc.close();
}
static String callService(String op, int a, int b) {
try {
URL url = new URL("http://localhost:9090/"+op+"?a="+a+"&b="+b);
HttpURLConnection c = (HttpURLConnection)url.openConnection();
c.setRequestMethod("GET");
BufferedReader in = new BufferedReader(
new InputStreamReader(c.getInputStream()));
StringBuilder r = new StringBuilder();
String line;
while ((line = in.readLine()) != null) r.append(line);
in.close(); c.disconnect();
return r.toString();
} catch (Exception e) {
return "Error: " + e.getMessage();
}
}
}
