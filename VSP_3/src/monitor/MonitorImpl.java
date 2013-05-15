/**
 * 
 */
package monitor;


import org.omg.CORBA.ORB;

import lagern.Lager;
import lagern.MonitorPOA;



/**
 * 
 * Verteilte Systeme Praktikum: "Aufgabe 1: Lager"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * 
 * Monitor Anwendung.
 * ------------------
 * 
 * monitor -ORBInitialHost <IP> -ORBInitialPort <Port> Lager <Monitorname>
 * 
 * 
 * @author Sebastian Mueller 2008588, Martin Schindler 2022759
 *
 */
public class MonitorImpl extends MonitorPOA {
	
	private String monitorID;
	private ORB orb;
	private lagern.Monitor href;
	private Lager lagerRef;
	private Thread hook;
	
	
	
	/**
	 * 
	 * @param monitorID
	 * @param orb
	 */
	public MonitorImpl(String monitorID, final ORB orb){
		this.monitorID = monitorID;
		this.orb = orb;
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			@Override
//			public void run() {  
//				lagerRef.monitorEntfernen(href);
//				orb.shutdown(true);
//			}
//		});
	}//MonitorImpl
	
	
	
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
                Runtime.getRuntime().removeShutdownHook(hook);
                orb.shutdown(true);
            }//run
        }).start();
//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				orb.shutdown(true);
//			}
//			
//		}).start();
//		System.out.println("Monitor wird beendet...");
	}//exit
	
	
	public lagern.Monitor getHref() {
		return href;
	}//getHref
	
	public void setHref(lagern.Monitor href) {
		this.href = href;
	}//setHref
	
	public void setHook(Thread hook) {
	    this.hook = hook;
	}//setHook

	public Lager getLagerRef() {
		return lagerRef;
	}//getLagerRef

	public void setLagerRef(Lager lagerRef) {
		this.lagerRef = lagerRef;
	}//setLagerRef
	
	public String getName() {
	    return this.monitorID;
	}//getName
	
}//MonitorImpl
