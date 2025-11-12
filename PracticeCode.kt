
/*
kotlinc -include-runtime Hello.kt -d hello.jar
java -jar nature.jar
*/

//_______________________________________________________

fun collectionsInKotlin() {
	val hset 	= hashSetOf( 10, 20, 70, 100 )
	val list 	= arrayListOf( 10, 20, 70, 100 )
	val map 	= hashMapOf( 1 to "One", 10 to "Ten")

	println( hset.javaClass )
	println( list.javaClass )
	println( map.javaClass )

	val names = listOf("Gabbar Singh", "Basanti", "Samba", "Jay", "Veeru")
	println( names.javaClass )
}

//_______________________________________________________

// T Is Type Placeholder
//		T Will Get Substituted With Type At Compile Time

fun <T> joinToString(
	collection: Collection<T>,
	separator: String,
	prefix: String,
	postfix: String
) : String {

	val result = StringBuilder( prefix )

	for( (index, element) in collection.withIndex() ) {
		if ( index > 0 ) result.append( separator )
		result.append( element )
	}
	result.append( postfix )
	return result.toString()
}

/*
// Compiler Will Generate Following Code
//		By Substiting T Place Holder
fun joinToStringString(
	collection: Collection<String>,
	separator: String,
	prefix: String,
	postfix: String
) : String {

	val result = StringBuilder( prefix )

	for( (index, element) in collection.withIndex() ) {
		if ( index > 0 ) result.append( separator )
		result.append( element )
	}
	result.append( postfix )
	return result.toString()
}

fun joinToStringInteger(
	collection: Collection<Integer>,
	separator: String,
	prefix: String,
	postfix: String
) : String {

	val result = StringBuilder( prefix )

	for( (index, element) in collection.withIndex() ) {
		if ( index > 0 ) result.append( separator )
		result.append( element )
	}
	result.append( postfix )
	return result.toString()
}
*/


fun playWithJoinToString() {
	//			ArrayList<String>
	val names = listOf("Gabbar Singh", "Basanti", "Samba", "Jay", "Veeru")
	println( joinToString( names, " # ", "[", "]") )

	//			HashSet<Integer>
	var numbers = hashSetOf( 10, 20, 30, 40, 50 )
	println( joinToString( numbers, " # ", "[", "]") )
}

//_______________________________________________________
//_______________________________________________________
//_______________________________________________________
//_______________________________________________________

fun main() {
	println("\nFunction: collectionsInKotlin")
	collectionsInKotlin()

	println("\nFunction: playWithJoinToString")
	playWithJoinToString()

	// println("\nFunction: ")	
	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")	
	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")	
	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")	
}




