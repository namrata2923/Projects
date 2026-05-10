import CalculatorApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
class CalculatorImpl extends CalculatorPOA {
public float add(float a, float b) { return a + b; }
public float subtract(float a, float b) { return a - b; }
public float multiply(float a, float b) { return a * b; }
public float divide(float a, float b) {
if (b == 0) throw new ArithmeticException("Division by zero");
return a / b;
}}
public class CalculatorServer {
public static void main(String args[]) {
try {
ORB orb = ORB.init(args, null);
POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
rootpoa.the_POAManager().activate();
CalculatorImpl calcImpl = new CalculatorImpl();
org.omg.CORBA.Object ref = rootpoa.servant_to_reference(calcImpl);
Calculator href = CalculatorHelper.narrow(ref);
org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
String name = "Calculator";
NameComponent path[] = ncRef.to_name(name);
ncRef.rebind(path, href);
System.out.println("CalculatorServer ready and waiting...");
orb.run();
} catch (Exception e) {
e.printStackTrace();
}
}}