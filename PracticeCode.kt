
//_______________________________________________________
//_______________________________________________________

// Higher Order Function
fun buildString( buildAtion: (StringBuilder) -> Unit ) : String {
	val sb = StringBuilder()
	buildAtion( sb )
	return sb.toString()
}

// Higher Order Function
//								StringBuilder Is Receiver Type For Lamba
fun createString( buildAtion: StringBuilder.() -> Unit ) : String {
	val sb = StringBuilder()
	buildAtion( sb )
	return sb.toString()
}

//_______________________________________________________

fun playWithHoF() {
	val ss = buildString ( {
		it.append("Hello!")
		it.append(" ")
		it.append("World!")
	})

	println( ss )

	val sss = buildString  {
		it.append("Hello!")
		it.append(" ")
		it.append("World!")
	}

	println( sss )

	val cc = createString  {
		this.append("Hello!")
		this.append(" ")
		this.append("World!")
	}

	println( cc )

	val ccc = createString  {
		append("Hello!")
		append(" ")
		append("World!")
	}

	println( ccc )

}

//_______________________________________________________
//_______________________________________________________

open class Tag( val name: String ) {
	private val childern = mutableListOf<Tag>()

	protected fun <T: Tag> doInit( child: T, init: T.()->Unit ) {
		child.init()
		childern.add( child )
	}

	override fun toString() = "<$name>${childern.joinToString("")}</$name>"
}

fun table( init: TABLE.() -> Unit ) = TABLE().apply( init )

class TABLE : Tag( "table" ) {
	fun tr( init: TR.() -> Unit ) = doInit( TR(), init )
}

class TR : Tag( "tr" ) {
	fun td( init: TD.() -> Unit ) = doInit( TD(), init )
}

class TD : Tag("td")

fun createTable() : TABLE {
	val something = table {
						tr {
							td {

							}
						}
					}

	return something
}


//_______________________________________________________

fun createTableAgain() : TABLE {
	val something = table {
						for ( i in 1..3 ) {
							tr {
								td {

								}
							}
						}
					}

	return something
}

//_______________________________________________________

fun playWithHTMLDSL() {
	val html = createTable()
	println( html )

	val html1 = createTableAgain()
	println( html1 )
}
//_______________________________________________________
//_______________________________________________________

fun main() {
	println("\nFunction: playWithHoF")
	playWithHoF()

	println("\nFunction: playWithHTMLDSL")
	playWithHTMLDSL()

	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")	
	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")	
}




