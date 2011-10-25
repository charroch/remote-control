package android.parser

import org.specs2._

class AndroidParserTest extends Specification {
  // install on (HTCE2132132, ergreg)
  // install on emulators
  // install on devices
  // install on all
  // install on HT*
  // install on all where ro.name.manufacturer is | startsWith | etc... "Sony Ericsson" and

  // run <activity or main or etc..> on ...
  // instrument <package> (optional with "uk.co.economist.customInstrumentation") on ...
  // e.g. instrument uk.co.novoda with uk.co.novoda.CustomrInstrumentation on ...
  def is =
    "DSL for android" ^
      p ^
      "The selection of devices should" ^
      "accept the string 'install on all' " ! e1 ^
      end

  def e1 = AndroidParser.parse("""install on all""").successful should beTrue
}