package monitor;

import java.util.Properties;

import lagern.Lager;
import lagern.LagerHelper;
import lagern.MonitorHelper;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class Monitor {
	
	public static void main(String args[]){
		try{
			String lagerName = args[4];
			String monitorName = args[5];
			//Properties props = new Properties();
			//props.put("org.omg.CORBA.ORBInitialPort", "1050");
			//props.put("org.omg.CORBA.ORBInitialHost", "141.22.27.102");
			ORB orb = ORB.init(args, null);
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			org.omg.CORBA.Object obj = nc.resolve_str(lagerName);
			Lager lagerRef = LagerHelper.narrow(obj);
			MonitorImpl monitor = new MonitorImpl(monitorName, orb);
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			//TODO: Muss das gemacht werden?
			rootPoa.the_POAManager().activate(); 
			
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(monitor);
			lagern.Monitor href = MonitorHelper.narrow(ref);
			lagerRef.monitorHinzufuegen(href);
			System.out.printf("Monitor '%s' erfolgereich erstellt!\n", monitorName);
			orb.run();
		}catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServantNotActive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPolicy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotProceed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AdapterInactive e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
