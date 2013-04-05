package lagern;


/**
* lagern/FachPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Mittwoch, 3. April 2013 16:15 Uhr MESZ
*/

public abstract class FachPOA extends org.omg.PortableServer.Servant
 implements lagern.FachOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_anzahl", new java.lang.Integer (0));
    _methods.put ("_get_name", new java.lang.Integer (1));
    _methods.put ("einlagern", new java.lang.Integer (2));
    _methods.put ("auslagern", new java.lang.Integer (3));
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
       case 0:  // lagern/Fach/_get_anzahl
       {
         int $result = (int)0;
         $result = this.anzahl ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }


  //Anzahl gelagerter Teile
       case 1:  // lagern/Fach/_get_name
       {
         String $result = null;
         $result = this.name ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }


  //Name des Faches
       case 2:  // lagern/Fach/einlagern
       {
         try {
           int anzahl = in.read_long ();
           this.einlagern (anzahl);
           out = $rh.createReply();
         } catch (lagern.FachPackage.EInvalidCount $ex) {
           out = $rh.createExceptionReply ();
           lagern.FachPackage.EInvalidCountHelper.write (out, $ex);
         }
         break;
       }

       case 3:  // lagern/Fach/auslagern
       {
         try {
           int anzahl = in.read_long ();
           this.auslagern (anzahl);
           out = $rh.createReply();
         } catch (lagern.FachPackage.EInvalidCount $ex) {
           out = $rh.createExceptionReply ();
           lagern.FachPackage.EInvalidCountHelper.write (out, $ex);
         } catch (lagern.FachPackage.ENotEnoughPieces $ex) {
           out = $rh.createExceptionReply ();
           lagern.FachPackage.ENotEnoughPiecesHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:lagern/Fach:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Fach _this() 
  {
    return FachHelper.narrow(
    super._this_object());
  }

  public Fach _this(org.omg.CORBA.ORB orb) 
  {
    return FachHelper.narrow(
    super._this_object(orb));
  }


} // class FachPOA