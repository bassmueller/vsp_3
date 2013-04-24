/**
 * 
 * @author Martin Schindler, Sebastian Mueller
 * 
 */
package monitor;

import org.omg.CORBA.ORB;

import lagern.Lager;
import lagern.MonitorPOA;

public class MonitorImpl extends MonitorPOA {
	
	private String monitorID;
	private ORB orb;
	private lagern.Monitor href;
	private Lager lagerRef;
	private Thread hook;
	
	public MonitorImpl(String monitorID, final ORB orb){
		this.monitorID = monitorID;
		this.orb = orb;
	}
	
	@Override
	public void meldung(String msg) {
		System.out.printf("Monitor \"%s\": %s\n", this.monitorID, msg);
	}//meldung

	//Muss im eigenen Thread aufgerufen werden, da sonst CORBA-Exceptions wegen Verbindungsfehlern geworfen werden
	@Override
	public void exit() {
       new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.printf("Monitor \"%s\" wird beendet.", monitorID);
                lagerRef.monitorEntfernen(href);
                Runtime.getRuntime().removeShutdownHook(hook);
                orb.shutdown(true);
            }//run
        }).start();
	}

	public lagern.Monitor getHref() {
		return href;
	}

	public void setHref(lagern.Monitor href) {
		this.href = href;
	}
	
	public void setHook(Thread hook) {
	    this.hook = hook;
	}//setHook

	public Lager getLagerRef() {
		return lagerRef;
	}

	public void setLagerRef(Lager lagerRef) {
		this.lagerRef = lagerRef;
	}
}
