package android

import com.android.ddmlib.IDevice

class Device(d: IDevice) {

  type Uri = String
  type Action = String
  type Data = String
  type MimeType = String
  type Categories = Seq[String]
  type Extras = Map
  type Component = (String, String)
  type Flags = Seq[String]
  type Intent = (Uri, Action, Data, MimeType, Categories, Extras, Component, Flags)

  def start(i: Intent) {
  }

  def broadcast(i: Intent) {
  }

  def instrument() {
  }
}

object Device {
  implicit def toRichDevice(d: IDevice) = new Device(d);
}