package lagern;


/**
* lagern/FachOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from lager.idl
* Mittwoch, 3. April 2013 16:15 Uhr MESZ
*/

public interface FachOperations 
{
  int anzahl ();

  //Anzahl gelagerter Teile
  String name ();

  //Name des Faches
  void einlagern (int anzahl) throws lagern.FachPackage.EInvalidCount;
  void auslagern (int anzahl) throws lagern.FachPackage.EInvalidCount, lagern.FachPackage.ENotEnoughPieces;
} // interface FachOperations