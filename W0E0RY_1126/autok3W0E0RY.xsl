<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html"/>
    <xsl:template match="/autok">
    
        <html>
        
        	<head>
        	
        		<title>Összes elem darabszám</title>
        		
      		</head>
        
            <body>
                <xsl:value-of select="count(./*)" />
            </body>
            
        </html>
        
    </xsl:template>
</xsl:stylesheet>
