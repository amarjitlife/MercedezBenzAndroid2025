
/*
kotlinc 01KotlinNature.kt -include-runtime -d nature.jar
java -jar nature.jar
*/

package learnKotlin

import java.util.TreeMap

// ____________________________________________________________
// Complete Following Code And On Completetion RAISE YOUR HAND!!!!

// Function
//		Takes No Argument
//		Returns Unit Value of Unit Type 

fun helloWorld() {
	println("Hello World!")
}

// _________________________________________________________________
// Complete Following Experiment And On Completion RAISE YOUR HAND!!
// Function
//		Takes Two Argument Of Type Int
//		Return Value of Int Type

fun max(a : Int, b : Int) : Int {
	// with if-else construct
	// Can You Do Following Line Of Code In Java/C/C++ ????
	// Statement : Any Line of Code Which Is Executable
	// Expression Is A Statement with Return Value

	// if-else Construct
	// In Kotlin Is An Expression
	// In Java/C/C++ Is A Statement

	return if ( a > b ) a else b
	// if ( a > b ) return a; else return b;
}

// Kotlin Design Principle
//		Designed Towards Expressivness
//		Desgined Towards Brevity Over Verbosness

// Functions Are First Class Citizen
// Functions Can Be Assigned Expressions
fun maximum(a : Int, b : Int) = if ( a > b ) a else b

fun playWithMax() {
	println( max(100, 200) )
	println( max(100, -200) )

	println( maximum(100, 200) )
	println( maximum(100, -200) )
}

// _________________________________________________________________
// Complete Following Experiment and Then RAISE YOUR HAND!!

// Design Principle
//		Design Towards Immutability Rather Than Towards Mutability

// Created Type Person Having Two Properties name And isMarried

// Kotlin Compiler Will Generate Following Things For You
// 1. Will Generate Constructor i.e. Memberwise Inititalizer
// 2. Will Generate Getters and Setters for Two Properties
//		name Property Only Getter Generated
//		isMarried Property Both Getter and Setter Generated
// 3. Will Generate Member Fields/Variables for Two Properties

// val means Immutable and var for Mutbale Property

//				Constructor Definition With Two Arguments
class Person(val name: String, var isMarried: Boolean) 

fun playWithPerson() {
	val person = Person("Alice", false) // Constructor Call

	// Compiler Will Convert person.name to person.getName()
	println(person.name) 

	// Compiler Will Convert person.isMarried to person.getIsMarried()
	println(person.isMarried)
	
	// name is Immutable
	// person.name = "Alice Carol" // 76:2: error: val cannot be reassigned
	person.isMarried = true  // person.setIsMarried( true )

	println(person.name)
	println(person.isMarried)

//	person = Person("Mr. Bob", true)
}

// _________________________________________________________________
// Complete Following Experiment And On Completion RAISE YOUR HAND!!

// Created Type Rectangle Having Three Properties height, width and isSquare

// Kotlin Compiler Will Generate Following Things For You
// 1. Will Generate Constructor i.e. Memberwise Inititalizer
// 2. Will Generate Getters and Setters for Three Properties
//		height and width Property Only Getter Generated
//		height, width and isSquare Property Doesn't Have Setter Generated
//			Because It's Immutable Properties
// 3. Will Generate Member Fields/Variables for Two Properties
//				Constructor Definition With Two Member Arguments
class Rectangle(val height: Int, val width: Int) {
	val isSquare: Boolean // Computed Property
		get() { 		  // Custom Getter
			return height == width
		}
}
// Complete Following Experiment And On Completion RAISE YOUR HAND!!
class Rectangle1(val height: Int, val width: Int) {
	val isSquare: Boolean // Computed Property
		get() = height == width  		  // Custom Getter
			
}

fun playWithRectangle() {
	var rectangle = Rectangle(100, 200)
	println(rectangle.height)
	println(rectangle.width)
	println(rectangle.isSquare)

	rectangle = Rectangle(288, 288)
	println(rectangle.height)
	println(rectangle.width)
	println(rectangle.isSquare)

	val rectangle1 = Rectangle1(400, 400)
	println(rectangle1.height)
	println(rectangle1.width)
	println(rectangle1.isSquare)
}

// _________________________________________________________________
// Complete Following Experiment And On Completion RAISE YOUR HAND!!

// Design Principle
//		Design Towards Deterministic Systems Rather Non Deterministic System
//		Definition Driven
//		Write Type Safe Code
//		BEST PRACTICE
//			Prefer All Branches Rather Than else Block

// Creating Colour Type
//		Range Of Colour Type = { RED, GREEN, BLUE }
enum class Colour {
	RED, GREEN, BLUE, YELLOW, ORGANGE, UNKOWN
}

fun getColourString( colour: Colour) : String {
	// In Kotlin when Is An Type Safe Expression
	// In Java switch Is A Statement and It's Not Type Safe
	//		Always Prefer Exhaustive Cases Rather Than else Block

	//  error: 'when' expression must be exhaustive, 
	// add necessary 'BLUE' branch or 'else' branch instead
	return when( colour ) {
		Colour.RED 		-> "Red Colour"
		Colour.GREEN 	-> "Green Colour"
		Colour.BLUE  	-> "Blue Colour"
		Colour.YELLOW  	-> "Yellow Colour"
		Colour.ORGANGE  -> "Orange Colour"
		Colour.UNKOWN   -> "Unknown Colour"
	}
}

fun getColourStringAgain( colour: Colour) : String {
	// In Kotlin when Is An Type Safe Expression
	// In Java switch Is A Statement and It's Not Type Safe
	//		Always Prefer Exhaustive Cases Rather Than else Block

	//  error: 'when' expression must be exhaustive, 
	// add necessary 'BLUE' branch or 'else' branch instead
	return when( colour ) {
		Colour.RED 		-> "Red Colour"
		// Colour.RED == colour -> "Red Color"
		Colour.GREEN 	-> "Green Colour"
		Colour.BLUE  	-> "Blue Colour"
		Colour.YELLOW  	-> "Yellow Colour"
		Colour.ORGANGE  -> "Orange Colour"
		Colour.UNKOWN   -> "Unknown Colour"
	}
}

fun getColourStringConcise( colour: Colour) = when( colour ) {
	Colour.RED 		-> "Red Colour"
	Colour.GREEN 	-> "Green Colour"
	Colour.BLUE  	-> "Blue Colour"
	Colour.YELLOW  	-> "Yellow Colour"
	Colour.ORGANGE  -> "Orange Colour"
	Colour.UNKOWN   -> "Unknown Colour"
}

fun playWithEnumColour() {
	println(Colour.RED)
	println(Colour.GREEN)
	println(Colour.BLUE)

	println( getColourString(Colour.RED) )
	println( getColourString(Colour.GREEN) )
	println( getColourString(Colour.BLUE) )

	println( getColourStringAgain(Colour.RED) )
	println( getColourStringAgain(Colour.GREEN) )
	println( getColourStringAgain(Colour.BLUE) )
}

fun getColourWarmth(colour: Colour) : String {
	return when (colour) {
		Colour.RED, Colour.ORGANGE 	-> "Hot Color"
		Colour.BLUE, Colour.YELLOW 	-> "Neutral Color"
		Colour.GREEN 				-> "Cold Color"
		Colour.UNKOWN   			-> "Unknown Color"
	}
} 

fun playWithColorWarmth() {
	println( getColourWarmth(Colour.RED) )
	println( getColourWarmth(Colour.GREEN) )
	println( getColourWarmth(Colour.BLUE) )
}

fun mixColors(c1: Colour, c2: Colour) : Colour {
	return when ( setOf(c1, c2) ) {
		setOf(Colour.BLUE, Colour.GREEN) -> Colour.YELLOW
		setOf(Colour.RED, Colour.YELLOW) -> Colour.ORGANGE
		else  							 -> throw Exception("Unknown Color")
	}
}

// Design Principle
//		Exceptions Are Not That Exceptional Such That It Breaks Design
fun mixColorsAgain(c1: Colour, c2: Colour) : Colour {
	return when (setOf(c1, c2) ) {
		setOf(Colour.BLUE, Colour.GREEN) -> Colour.YELLOW
		setOf(Colour.RED, Colour.YELLOW) -> Colour.ORGANGE
		else  							 -> Colour.UNKOWN
	}
}

fun playWithMixingColors() {
	println( mixColors(Colour.RED, Colour.YELLOW) )
	println( mixColors(Colour.GREEN, Colour.BLUE) )
//	println( mixColors(Colour.BLUE, Colour.YELLOW) )

	println( mixColorsAgain(Colour.RED, Colour.YELLOW) )
	println( mixColorsAgain(Colour.GREEN, Colour.BLUE) )
	println( mixColorsAgain(Colour.BLUE, Colour.YELLOW) )
}

fun fizzBuzz(i: Int) : String {
	return when { // Pattern Matching 
		i % 15 == 0 	-> "FizzBuzz "
		i % 5 == 0 		-> "Fizz "
		i % 3 == 0 		-> "Buzz "
		else 			-> " $i "
	}
}

// Expression
// LHS = RHS
// 1. Infer Type From RHS Value
// 2. Bind The Type With LHS
fun fizzBuzzAgain(i: Int) = when { // Pattern Matching 
	i % 15 == 0 	-> "FizzBuzz "
	i % 5 == 0 		-> "Fizz "
	i % 3 == 0 		-> "Buzz "
	else 			-> " $i "
}

fun playWithFizzBuzz() {
	for ( i in 1..100 ) { // Closed Interval [1, 100] 
		print(fizzBuzz(i))
	} 

	for( i in 100 downTo 1 step 2 ) {
		print(fizzBuzz(i))	
	}
}

// _________________________________________________________________
// Complete Following Experiment and Then RAISE YOUR HAND!!

// import java.util.TreeMap
fun binaryRepresentation() {
	val binaryRep = TreeMap<Char, String>()

// 	Indexing Loop
//	for(i= 0; i < n; i ++) {}

// 	Best Practice
//  for-in Loop

	for (character in 'A'..'F') { // Closed Interval ['A', 'F'] Is Ordered Set
		val binary = Integer.toBinaryString( character.code )
		binaryRep[ character ] = binary
	}

	for( (letter, binary) in binaryRep ) {
		println(" $letter = $binary ")
	}
}

// Every Expression Has Value
// 		Value Have Type
fun isLeter(character: Char) : Boolean = character in 'a'..'z' || character in 'A'..'Z'

// error: type mismatch: inferred type is Boolean but Char was expected
// fun isNotDigit(character: Char) : Char = character !in '0'..'9'

fun isNotDigit(character: Char) = character !in '0'..'9'

// Design Priciple
// 		Flat Is Better Than Nested
// 		Reduced Cyclomatic Complexity Is Better

fun recogniseCharacter( character: Char ) : String {
	return when(character) {
		in '0'..'9' 			-> "It's Digit"
		in 'a'..'z', in 'A'..'Z' 	-> "It's English Letter"
		else 					-> "Unknown Character"
	}
}

fun playWithCharacter() {
	println( isLeter('A') ) 
	println( isLeter('c') ) 
	println( isLeter('#') ) 
	println( isNotDigit('9') ) 
	println( isNotDigit('A') ) 
	println( recogniseCharacter('A') ) 
}


// _________________________________________________________
// REASON FOLLOWING CODE and Then RAISE YOUR HAND!!

// Complete Following sum Function

// BAD CODE
// int sum(int a, int b) {
// 	int result = a + b;
// 	return result;
// }


// int k = sum(a, b);
// PersonRecord record[100];
// char a[1000];

// for ( ; k > 0 ; k-- ) {
// 	printf( a[k] ) ;
// }

// // GOOD CODE : VALID SUM
// #include <limits.h>

// // Type Definition
// //		Must Be Respected Like God And Never Mess With It!
// // [ -32768, 32767 ]  
// signed int sum(signed int a, signed int b) {
// 	  signed int result = 0;

// 	  // Doing Type Checking and It's Called Type Safety
// 	  if (((b > 0) && (a > (INT_MAX - b))) ||
// 	      ((b < 0) && (a < (INT_MIN - b)))) {
// 	    	/* Handle/Print Error */
// 	  } else {
// 	    	result = a + b;
// 	  }
	  
// 	  return result;
// 	  /* ... */
// }

// _________________________________________________________________
// _________________________________________________________________
// _________________________________________________________________
// _________________________________________________________________
// _________________________________________________________________
// _________________________________________________________________
// Complete Following Experiment And On Completion RAISE YOUR HAND!!

fun main() {
	println("\nFunction : helloWorld")
	helloWorld()

	println("\nFunction : playWithMax")
	playWithMax()

	println("\nFunction : playWithPerson")
	playWithPerson()

	println("\nFunction : playWithRectangle")
	playWithRectangle()

	println("\nFunction : playWithEnumColour")
	playWithEnumColour()

	println("\nFunction : playWithColorWarmth")
	playWithColorWarmth()

	println("\nFunction : playWithMixingColors")
	playWithMixingColors()

	println("\nFunction : playWithFizzBuzz")
	playWithFizzBuzz()

	println("\nFunction : binaryRepresentation")
	binaryRepresentation()

	println("\nFunction : playWithCharacter")
	playWithCharacter()

	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
}


// ____________________________________________________________
// ____________________________________________________________
// ____________________________________________________________
// ____________________________________________________________
// ____________________________________________________________
// ____________________________________________________________
// ____________________________________________________________
// ____________________________________________________________
// Complete Following Code And On Completetion RAISE YOUR HAND!!!!



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




