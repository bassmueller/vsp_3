package lagern.FachPackage;

/**
* lagern/FachPackage/EInvalidCountHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Mittwoch, 3. April 2013 16:15 Uhr MESZ
*/

public final class EInvalidCountHolder implements org.omg.CORBA.portable.Streamable
{
  public lagern.FachPackage.EInvalidCount value = null;

  public EInvalidCountHolder ()
  {
  }

  public EInvalidCountHolder (lagern.FachPackage.EInvalidCount initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = lagern.FachPackage.EInvalidCountHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    lagern.FachPackage.EInvalidCountHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return lagern.FachPackage.EInvalidCountHelper.type ();
  }

}
