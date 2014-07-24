def filePath = "C:\\Users\\s0041664\\Documents\\Projects\\NG Rx Standalone\\Rx Testing\\Functional Testing\\Manual Test Results\\"

def readXmlFile = { xmlFile ->
    IN_NAME = "${filePath}${xmlFile}"
    File inputFile = new File(IN_NAME) 
    
    def xmlNode = new XmlSlurper().parse(inputFile)
    return xmlNode
}

def parseRows = { xmlNode ->
    def xmlRows = []
    
    def rows = xmlNode.option
    rows.each {
        def aRow = [it.@value.toString()]
        xmlRows << aRow
    }
    return xmlRows   
}

def parseDbRows = { xmlNode ->
    def xmlRows = []
    
    def rows = xmlNode.ROW.COLUMN
    rows.each {
        def aRow = [it.toString()]
        xmlRows << aRow
    }
    return xmlRows   
}
    

def readDbRows = { dbFile ->
    def xmlRows = []
    def xmlNode = readXmlFile( dbFile )
    xmlRows = parseDbRows(xmlNode)
    return xmlRows
}

def readHtmlRows = { htmlFile ->
    def htmlRows = []
    def xmlNode = readXmlFile( htmlFile )
    htmlRows = parseRows( xmlNode )
    return htmlRows
}

def compareDbToHtml = { dbRows, htmlRows ->
    return dbRows.sort().equals(htmlRows.sort())
}

/* Start Here */
def dbRows = []
def htmlRows = []
dbRows = readDbRows("Forms_from_db.xml")
dbRows = dbRows.sort()
//dbRows.each {println it}
println "db: ${dbRows.size()}"

htmlRows = readHtmlRows("Forms_from_html.xml")
//htmlRows.each {println it}
println "html: ${htmlRows.size()}"

assert compareDbToHtml(dbRows, htmlRows)
println "Done"