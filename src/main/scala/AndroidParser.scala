package android.parser

import util.parsing.combinator.syntactical.StandardTokenParsers
import java.io.File

object AndroidParser extends StandardTokenParsers {
  lexical.reserved +=
    ("install", "on", "all", "emulators", "devices")

  lexical.delimiters +=("(", ")", ",")

  lazy val actions: Parser[SP] =
    verbs ~ devices ^^ {
      case v ~ d => new SP(v, d)
    }

  lazy val verbs: Parser[Command] =
    ("install" | "run" | "instrument" | "uninstall") ^^ {
      case "install" => Install
      case "run" => run
      case "instrument" => instrument
      case "uninstall" => uninstall
    }

  lazy val devices: Parser[Seq[String]] =
    "on" ~> ("all" | "emulators" | "devices") ^^ {
      case "all" => List("hello", "Something else")
    }

  def parse(s: String) = {
    val tokens = new lexical.Scanner(s)
    phrase(actions)(tokens)
  }
}

class SP(i: Command, d: Seq[String]) {
  override def toString = "Command " + i + " and s " + d
}

trait Command

case class I(apk: File, reinstall: Boolean = true) extends Command

case object Install extends Command

case object run extends Command

case object instrument extends Command

case object uninstall extends Command

case object broadcast extends Command


class Device {
}

class DeviceStore {
}

class DeviceManager {
}

class something {

  import com.android.ddmlib._

  def process(device: IDevice) = {
    println("Uninstalling uk.co.economist on " + device.getSerialNumber)
    device.uninstallPackage("uk.co.economist")
    println("Installing /tmp/eco-debug-auto-download-2.apk on " + device.getSerialNumber)
    device.installPackage("/tmp/eco-debug-auto-download-2.apk", true)
    println("Starting activity on " + device.getSerialNumber)
    device.executeShellCommand("am start -a android.intent.action.MAIN -n uk.co.economist/uk.co.economist.activity.Splash", new NullOutputReceiver)
  }

  AndroidDebugBridge.init(false /* debugger support */);
  AndroidDebugBridge.createBridge
  val bridge = AndroidDebugBridge.createBridge
  Thread.sleep(5000)

  val a = bridge.getDevices
  a.foreach(process)
  println("Batched worked against %i devices " format (bridge.getDevices.size))
  AndroidDebugBridge.terminate
}