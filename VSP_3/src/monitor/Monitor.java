/**
 * 
 * @author Martin Schindler, Sebastian Mueller
 * 
 */
package monitor;

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
    
    private static String monitorName;
	
	public static void main(String args[]){
		try{
			String lagerName = args[4];
			monitorName = args[5];
			//Properties props = new Properties();
			//props.put("org.omg.CORBA.ORBInitialPort", "1050");
			//props.put("org.omg.CORBA.ORBInitialHost", "141.22.27.102");
			final ORB orb = ORB.init(args, null);
			NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			org.omg.CORBA.Object obj = nc.resolve_str(lagerName);
			final Lager lagerRef = LagerHelper.narrow(obj);
			MonitorImpl monitor = new MonitorImpl(monitorName, orb);
			monitor.setLagerRef(lagerRef);
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPoa.the_POAManager().activate(); 
			
			org.omg.CORBA.Object ref = rootPoa.servant_to_reference(monitor);
			final lagern.Monitor href = MonitorHelper.narrow(ref);
			monitor.setHref(href);
			lagerRef.monitorHinzufuegen(href);
			System.out.printf("Monitor \"%s\" erfolgereich erstellt!\n", monitorName);
			
            // Shutdown-Hook fuer Beenden mit strg+c
            Thread hook = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.printf("Monitor \"%s\" wird entfernt...\n", monitorName);
                    lagerRef.monitorEntfernen(href);
                    System.out.printf("Monitor \"%s\" wurde entfernt.\n", monitorName);
                    orb.shutdown(true);
                }//run
            });//hook
            monitor.setHook(hook);
            
            // Hook und Objektreferenzen setzen fuer quit() Methode
            Runtime.getRuntime().addShutdownHook(hook);
            monitor.setHook(hook);
            System.out.printf("Monitor \"%s\" gestartet...\n", monitorName);
			
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
