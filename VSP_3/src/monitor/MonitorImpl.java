package monitor;

import org.omg.CORBA.ORB;

import lagern.MonitorPOA;

public class MonitorImpl extends MonitorPOA {
	
	private String monitorID;
	private ORB orb;
	
	public MonitorImpl(String monitorID, ORB orb){
		this.monitorID = monitorID;
		this.orb = orb;
	}
	
	@Override
	public void meldung(String msg) {
		System.out.println(String.format("Monitor: %s Log: %s", this.monitorID, msg));
	}

	//Muss im eigenen Thread aufgerufen werden, da sonst CORBA-Exceptions wegen Verbindungsfehlern geworfen werden
	@Override
	public void exit() {
		new Thread(new Runnable(){

			@Override
			public void run() {
				orb.shutdown(true);
			}
			
		}).start();
		System.out.println("Monitor wird beendet...");
	}
}
