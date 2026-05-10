import java.io.*; 
import java.net.*;

public class CalculatorServer {
public static void main(String[] args) throws IOException {
int port = 9090;
ServerSocket serverSocket = new ServerSocket(port);
System.out.println("Calculator Web Service at http://localhost:"+port);
while (true) {
Socket client = serverSocket.accept();
new Thread(() -> handleRequest(client)).start();
}
}
static void handleRequest(Socket client) {
try {
BufferedReader in = new BufferedReader(
new InputStreamReader(client.getInputStream()));
OutputStream out = client.getOutputStream();
String requestLine = in.readLine();
if (requestLine == null) { client.close(); return; }
String[] parts = requestLine.split(" ");
String path = parts.length > 1 ? parts[1] : "/";
String response = processCalculation(path);
String http = "HTTP/1.1 200 OK\r\n" + "Content-Type: application/json\r\n" + "Content-Length: "+response.length()+"\r\n" + "Connection: close\r\n\r\n" + response;
out.write(http.getBytes());
out.flush();
client.close();
} catch (IOException e) {
System.out.println("Error: " + e.getMessage());
}
}
static String processCalculation(String path) {
try {
String op = path.split("\\?")[0].substring(1);
String query = path.contains("?") ? path.split("\\?")[1] : "";
int a = 0, b = 0;
for (String p : query.split("&")) {
String[] kv = p.split("=");
if (kv[0].equals("a")) a = Integer.parseInt(kv[1]);
if (kv[0].equals("b")) b = Integer.parseInt(kv[1]);
}
double result;
switch (op) {
case "add": result = a + b; break;
case "subtract": result = a - b; break;
case "multiply": result = a * b; break;
case "divide": result = (double)a / b; break;

default: return "{\"error\": \"Unknown\"}";
}
return "{\"operation\": \""+op+"\", \"a\": "+a
+", \"b\": "+b+", \"result\": "+result+"}";
} catch (Exception e) {
return "{\"error\": \""+e.getMessage()+"\"}";
}
}
}