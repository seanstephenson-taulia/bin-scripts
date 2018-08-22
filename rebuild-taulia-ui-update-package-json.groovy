
String version = null
boolean trigger = false

System.in.eachLine { line ->
  println '  ' + line

  if (trigger) {
    version = line
    trigger = false
  }
  if (line.contains('Publishing snapshot version')) {
    trigger = true
  }

  return
}

println()

if (!version) {
  println "Couldn't find 'taulia-ui' version!"
  println 'Exiting'
  System.exit(-1)
}

println "Found 'taulia-ui' version [$version]"

def packageJson = new File('frontend/package.json')
def text = packageJson.text
text = text.replaceFirst(/(?<="taulia-ui": ")[^"]+/, version)
packageJson.write(text)
println 'Installed new version into package.json'
