package lagern;


/**
* lagern/MonitorPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Mittwoch, 3. April 2013 16:15 Uhr MESZ
*/

public abstract class MonitorPOA extends org.omg.PortableServer.Servant
 implements lagern.MonitorOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("meldung", new java.lang.Integer (0));
    _methods.put ("exit", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // lagern/Monitor/meldung
       {
         String msg = in.read_string ();
         this.meldung (msg);
         out = $rh.createReply();
         break;
       }


  //Damit kann das Lager den Monitor beenden.
       case 1:  // lagern/Monitor/exit
       {
         this.exit ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:lagern/Monitor:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Monitor _this() 
  {
    return MonitorHelper.narrow(
    super._this_object());
  }

  public Monitor _this(org.omg.CORBA.ORB orb) 
  {
    return MonitorHelper.narrow(
    super._this_object(orb));
  }


} // class MonitorPOA
