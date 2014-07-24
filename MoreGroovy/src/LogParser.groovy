def filePath = "C:\\Users\\s0041664\\Documents\\Projects\\NG Rx Standalone\\Rx Testing\\Regression Tests\\Logs\\"
def inFileName = "2014-01-27 Big Test"

List lines 

def readFromFile = { 
    IN_NAME = "${filePath}${inFileName}.log"
    File inputFile = new File(IN_NAME) 
    lines = inputFile.readLines()
    lines = lines.findAll { it.trim().size()>0 }        // remove empty lines
    }
    
def writeToFile = { outFileName, outList ->
    OUT_NAME = "${filePath}${outFileName}.csv"
    new File(OUT_NAME).withWriter { out ->
        outList.each {
            out.println it
//            println it
        }
    }
}

readFromFile()
println "Lines= ${lines.size()}"
//lines.each {println it}

def sevLines = (lines.findAll { it==~".*ERROR:.*Severity.*" }).unique()
def missingLines = (lines.findAll { it==~".*ERROR:.*Can't.*" }).unique()

println "Severity errors = ${sevLines.size()} Missing errors = ${missingLines.size()}"
println "sevLines:"
sevLines.each {println it}
println "Sample Sev: ${sevLines[0]}\nMissing: ${missingLines[0]}"

def allPattern = ".*\\[(.*)\\].*row: ([0-9]*).*Drug:(.*)\\(([0-9]*|DNO.*)\\).* expected severity: ([0-9]*).*\$"
def singleMissingPattern = ".*\\[(Single.*)\\].*row: ([0-9]*).*Drug:(.*)\\(([0-9]*|DNO.*)\\).* search: (.*?) expected severity: ([0-9]*).*\$"
def singleSevErrorPattern = ".*\\[(Single.*)\\].*row: ([0-9]*).*Drug:(.*)\\(([0-9]*|DNO.*)\\).* expected severity: ([0-9]*).*\$"

def outLines = []
sevLines.each {
    def res = it =~ singleSevErrorPattern
//    def res = it =~ allPattern
    if (res.matches()) 
        outLines << "${res[0][3]},${res[0][4]},${res[0][5]},${res[0][2]},${res[0][1]}"
    }

outLines = outLines.unique()
writeToFile("${inFileName} - Sev", outLines)

outLines = []

missingLines.each {
    def res = it =~ singleMissingPattern
//    def res = it =~ allPattern
    if (res.matches()) 
        outLines << "${res[0][3]},${res[0][4]},${res[0][6]},${res[0][2]},${res[0][1]}"
    }

outLines = outLines.unique()
writeToFile("${inFileName} - Missing", outLines)
println "Done"