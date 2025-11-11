/*
kotlinc include-runtime Hello.kt -d hello.jar
java -jar nature.jar
*/

//_______________________________________________________

fun helloWorld(): Unit {
	println("Hello World! Welcome To Kotlin!!!")
}

//_______________________________________________________

fun playWithHelloWorld() {
	val something = helloWorld()
	println( something )
}

//_______________________________________________________

fun playWithIfElse() {
	//1. Type Inferring
	//2. Type Binding

	val a = 10
	val b = 20

	val something = if (a > b) a else b
	println( something)

	val something1 = if (a > b) a else {}
	println( something1)

	// val something2 = if (a > b) a
	// println( something2 )

	if (a > b) println(a)
}

//_______________________________________________________

// Expression = Statement Having Return Value

// In C/C++/Java
// 		if-else Is A Statement

// In Kotlin
// 		if-else Is An Expression

fun max( a: Int, b: Int ) : Int {
	return if (a > b) a else b
}

fun maximum( a: Int, b: Int ) = if (a > b) a else b

// Following Both Are Equivalent
fun maximumAgain1( a: Int, b: Int ) = if (a > b) a else "Guten Tag!"
fun maximumAgain2( a: Int, b: Int ): Any = if (a > b) a else "Guten Tag!"

fun playWithMax() {
	val something = max( 99, 100 )
	println(something)

	val something1 = max( 99, -100 )
	println(something1)

	val something11 = maximum( 99, 100 )
	println(something11)

	val something22 = maximum( 99, -100 )
	println(something22)
}

//_______________________________________________________

// Design Principle
//		Design Towards Immutability Rather Than Mutability

// val Means Immutable
// var Means Mutable

// In Kotlin
// Compiler Will Generate Following Things
// 		1. Constructor Having 2 Arguments Of Type String and Boolean
//		2. Two Member Variables For Each name And isMarried
//		3. Getter For val Property
//		4. Getter and Setter For var Property

/// name And isMarried Are Property
class Person( val name: String, var isMarried: Boolean = false )

fun playWithPerson() {
	val gabbar = Person( "Gabbar", false )
	println( gabbar.name ) // gabbar.getName()
	println( gabbar.isMarried ) // gabbar.getIsMarried()

	// gabbar.name = "Gabbar Singh" //  error: 'val' cannot be reassigned.
	// println( gabbar.name )
	// println( gabbar.isMarried )

	val basanti = Person("Basanti", false )
	println( basanti.name)
	println(basanti.isMarried )

	basanti.isMarried = true	// basanti.setIsMarried( true )
	println( basanti.name)
	println(basanti.isMarried )

	val sambha = Person( name = "Samba", isMarried = false )
	println( sambha.name ) // gabbar.getName()
	println( sambha.isMarried ) // gabbar.getIsMarried()

	val sambha1 = Person( name = "Samba", false )
	println( sambha1.name ) // gabbar.getName()
	println( sambha1.isMarried ) // gabbar.getIsMarried()

	val sambha2 = Person( name = "Samba" )
	println( sambha2.name ) // gabbar.getName()
	println( sambha2.isMarried ) // gabbar.getIsMarried()
}

//_______________________________________________________

// BEST PRACRTICE
//		Design Systems Towards Determinism Rather Non Determinism
//		Prefer All The Cases Rather Than else Branch
//		else Branch Bring In Rarest Rare Cases
///			Where Cases Runs Into Expontials Number

// Compilers Are Theorem Proover

// Type System Theorem
// Colour Type
//		Range = { RED, GREEN, BLUE }
//		Operation = { }

enum class Colour {
	RED, GREEN, BLUE, YELLOW, VIOLET, UNKNOWN
}

// Program : Proof
fun getColourString( colour: Colour ) : String {
	// when Is Type Safe Expression
	///			 expression Type It Will Check
	return when( colour ) {
		Colour.RED 		-> "Red Colour"
		Colour.GREEN 	-> "Green Colour"
		Colour.BLUE 	-> "Blue Colour"
		Colour.YELLOW   -> "Yellow Colour"
		Colour.VIOLET   -> "Violet Colour"
		Colour.UNKNOWN  -> "Unkown Colour"
		// else -> "something Else..." // Dead Code
		// warning: 'when' is exhaustive so 'else' is redundant here.
	}
}

// Program : Proof
fun getColourStringAgain( colour: Colour ) : String {
	return when( colour ) {
		Colour.RED 		-> "Red Colour"
		Colour.GREEN 	-> "Green Colour"
		Colour.BLUE 	-> "Blue Colour"
		Colour.YELLOW   -> "Yellow Colour"
		Colour.VIOLET   -> "Violet Colour"
		Colour.UNKNOWN  -> "Unkown Colour"

		// else 			-> "something Else..."
 // error: 'when' expression must be exhaustive. Add the 'BLUE' branch or an 'else' branch.
	}
}

//_______________________________________________________

fun getColourWarmth( colour: Colour ) : String {
	return when( colour ) {
		Colour.RED, Colour.VIOLET 		-> "Hot Colours"
		Colour.GREEN, Colour.YELLOW 	-> "Cold Colour"
		Colour.BLUE 					-> "Blue Colour"
		Colour.UNKNOWN  				-> "Unkown Colour"
	}
}

//_______________________________________________________

// fun getColourStringOnceAgain( colour: Colour ) : String {
// fun getColourStringOnceAgain( colour: Colour )  {

// Any Type Usages Is Not Recommended
//		Always Prefer Specific Types Rather Than Any, void in C/C++/Java or Object in Java

fun getColourStringOnceAgain( colour: Colour ): Any {
	return when( colour ) {
		Colour.RED 		-> "Red Colour"
		Colour.GREEN 	-> "Green Colour"
		Colour.BLUE 	-> "Blue Colour"
		Colour.YELLOW   ->  99
		Colour.VIOLET   -> "Violet Colour"
		Colour.UNKNOWN  -> "Unkown Colour"

	}
}

//_______________________________________________________

// fun mixColours( colour1: Colour, colour2: Colour ) {
// fun mixColours( colour1: Colour, colour2: Colour ) : Colour {
// fun mixColours( colour1: Colour, colour2: Colour ) : Any {

fun mixColours( colour1: Colour, colour2: Colour ) : Colour {
	return when( setOf( colour1, colour2 )) {
		setOf( Colour.RED, Colour.BLUE) 	-> Colour.VIOLET
		setOf( Colour.BLUE, Colour.GREEN) 	-> Colour.YELLOW
		// else 		-> throw Exception("Unknown Colors")
		// else 		-> "Unknown Colors"
		else 			-> Colour.UNKNOWN
	}
}

//_______________________________________________________
//_______________________________________________________
//_______________________________________________________
//_______________________________________________________
//_______________________________________________________
//_______________________________________________________

fun playWithEnums() {
	println( Colour.RED )
	println( Colour.GREEN )
	println( Colour.BLUE )	

	println( getColourString( Colour.RED ) )
	println( getColourString( Colour.GREEN ) )
	println( getColourString( Colour.BLUE )	)

	println( getColourStringAgain( Colour.RED ) )
	println( getColourStringAgain( Colour.GREEN ) )
	println( getColourStringAgain( Colour.BLUE ) )
}


//_______________________________________________________
//_______________________________________________________
//_______________________________________________________
//_______________________________________________________

fun main() {
	println("\nFunction: playWithHelloWorld")
	playWithHelloWorld()

	println("\nFunction: playWithIfElse")
	playWithIfElse()

	println("\nFunction: playWithMax")
	playWithMax()

	println("\nFunction: playWithPerson")
	playWithPerson()

	println("\nFunction: playWithEnums")
	playWithEnums()

	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")
	// println("\nFunction: ")	
}




