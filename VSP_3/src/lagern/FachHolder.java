package lagern;

/**
* lagern/FachHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Mittwoch, 3. April 2013 16:15 Uhr MESZ
*/

public final class FachHolder implements org.omg.CORBA.portable.Streamable
{
  public lagern.Fach value = null;

  public FachHolder ()
  {
  }

  public FachHolder (lagern.Fach initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = lagern.FachHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    lagern.FachHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return lagern.FachHelper.type ();
  }

}
