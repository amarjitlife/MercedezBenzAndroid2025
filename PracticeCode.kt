
package learnJava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

//________________________________________________________________

class OuterSer implements Serializable {
	private int rank;
	class InnerSer implements Serializable {
		protected String name;
			// ...
	}
	InnerSer io = new InnerSer();
}

//________________________________________________________________

class JavaHeapDemo {
	public static void playWithJavaCollections() {
		List<String> words = new ArrayList<>();
		List<?> elements = words;

		System.out.println("Casting To List<Integer>");
		// List<Integer> numbers = elements;
		// List<Object> numbers = elements;

		// Note: Experiments.java uses unchecked or unsafe operations.
		@SuppressWarnings("unchecked")
		List<Integer> numbers = ( List<Integer> ) elements;
		System.out.println("Casting To List<Integer> :: Successful!");

		numbers.add( 444 );
		System.out.println("Integer Addition: Success");
		String word = words.get( 0 );
		System.out.println("String Removal: Success");
		System.out.println("Word : "+ word);

	}
}

//________________________________________________________________
//________________________________________________________________
//________________________________________________________________
//________________________________________________________________
//________________________________________________________________

public class Experiments {
	public static void playWithSerialisation() {
		OuterSer oo = new OuterSer();
		// oo.Serialise();
	}

	public static void playWithJavaHeap() {
		JavaHeapDemo.playWithJavaCollections();
	}

	public static void main( String[] args ) {
		System.out.println("\nFunction: playWithSerialisation");
		playWithSerialisation();

		System.out.println("\nFunction: playWithJavaHeap");
		playWithJavaHeap();

		// System.out.println("\nFunction: ", );
		// System.out.println("\nFunction: ", );
		// System.out.println("\nFunction: ", );
		// System.out.println("\nFunction: ", );
		// System.out.println("\nFunction: ", );
		// System.out.println("\nFunction: ", );
		// System.out.println("\nFunction: ", );
		// System.out.println("\nFunction: ", );
		// System.out.println("\nFunction: ", );
	}
} 

//________________________________________________________________
//________________________________________________________________
//________________________________________________________________
//________________________________________________________________
//________________________________________________________________


import java.util.Random

//_____________________________________________________

// E Is Type Place Holder
// MutableStack Is Generic Type i.e. Template

//	Generics Are Templates Programming
//		In Mathematics It's Callled Parameterised Types
//		Write Code To Generate Code
//		Compile Time Polymorphism

class MutableStack<E>( vararg items: E ) {              
	private val elements = items.toMutableList()
	fun push(element: E) = elements.add(element)        
	fun peek(): E = elements.last()                     
	fun pop(): E = elements.removeAt(elements.size - 1)
	fun isEmpty() = elements.isEmpty()
	fun size() = elements.size
	override fun toString() = "MutableStack(${elements.joinToString()})"
}


// // Compiler Generatged Code On Demand Basis
// //		Based On The Usage
// class MutableStack<Int>( vararg items: Int ) {              // 1
//   private val elements = items.toMutableList()
//   fun push(element: Int) = elements.add(element)        // 2
//   fun peek(): Int = elements.last()                     // 3
//   fun pop(): Int = elements.removeAt(elements.size - 1)
//   fun isEmpty() = elements.isEmpty()
//   fun size() = elements.size
//   override fun toString() = "MutableStack(${elements.joinToString()})"
// }


// // Compiler Generatged Code On Demand Basis
// //		Based On The Usage
// class MutableStack<String>( vararg items: String ) {              // 1
//   private val elements = items.toMutableList()
//   fun push(element: String) = elements.add(element)        // 2
//   fun peek(): String = elements.last()                     // 3
//   fun pop(): String = elements.removeAt(elements.size - 1)
//   fun isEmpty() = elements.isEmpty()
//   fun size() = elements.size
//   override fun toString() = "MutableStack(${elements.joinToString()})"
// }

fun playWithMutableStack() {
	// Type Inferred and Substitued At Type Place Holder <E> At Compile Time
	val stackInts = MutableStack<Int>( 10, 20, 30 )
	println( stackInts.size() ) 
	stackInts.push( 100 )
	stackInts.push( 200 )
	println( stackInts.size() ) 
	println( stackInts.pop() )	

	val stackStrings = MutableStack<String>( "Ding", "Dong" )
	println( stackStrings.size() ) 
	stackStrings.push( "Ting" )
	stackStrings.push( "Tong" )
	println( stackStrings.size() ) 
	println( stackStrings.pop() )	
}

//_____________________________________________________


class Temperature(var tempInCelsius: Float)

// Extension Properties 
var Temperature.tempInFahrenheit: Float
    get() = (tempInCelsius * 9 / 5) + 32
    set(value) {
        tempInCelsius = (value - 32) * 5 / 9
    }

fun playWithExtensionProperties() {
    val temp = Temperature(32f)
	println( temp.tempInFahrenheit )

	temp.tempInFahrenheit = 90f
	println( temp.tempInCelsius )
}

//_____________________________________________________

// Generic Extention Property

val <T> List<T>.penultimate: T
	get() = this[ size - 2 ]


fun playWithGenericProperty() {
	val list = listOf( 10, 20, 30, 40)
	println( "Value : ${ list.penultimate }")

	val listAgain = listOf( "Ding", "Dong", "Ting", "Tong")
	println( "Value : ${ listAgain.penultimate }")
}

//_____________________________________________________

open class Animal( val type : String  ) 
open class Pet( val name : String, type: String ) : Animal( type )

class Cat( name : String, type: String ) : Pet( name, type ) 
open class Dog( name : String, type: String ) : Pet( name, type ) 
class GermanShaperd( name : String, type: String ) : Dog( name, type ) 

// Polymorpic Functions
//		Using Mechanism: Passing Parent Type From Hierarcy
//			Can Pass Pet and Pet Child Types
fun chooseFavoriteNonGeneric( pets: List<Pet> ) : Pet {
	val random = Random()
    val favorite = pets[ random.nextInt( pets.size ) ]
    println("${favorite.name} is the favorite")
    return favorite
}

// fun <T> chooseFavorite( pets: List<T> ): T {
//		 error: unresolved reference 'name'

// Polymorphic Function
//		Compile Time Polymorphism
//		Compiler Will Generate Copy Code After 
///			Inferrering/Subtituting Type At Compile Time

// Type Bounds
//		T Can Be Any Type Whose Super Type Is Pet
//		Or Any Sub Type Of Pet
fun <T : Pet> chooseFavorite(pets: List<T> ): T {
	val random = Random()
    val favorite = pets[ random.nextInt( pets.size ) ]
    println("${favorite.name} is the favorite")
    return favorite
}

fun playWithChooseFavorite() {
	val catsAgain: List<Cat> = listOf( Cat("Whiskers", "Cat"), Cat("Rosie", "Cat") )
	val favoriteAgain = chooseFavoriteNonGeneric( catsAgain )
	println( "Favorite Name : ${ favoriteAgain.name }" )

	val dogs1: List<Dog> = listOf( Dog( "Indian Dog!", "Dog"), Dog("Hound", "Dog"),
								Dog("BitBull", "Dog"), Dog("Pug", "Dog"),
								GermanShaperd("German GermanShaperd", "Dog") )
	val favoriteOnceAgain = chooseFavoriteNonGeneric( dogs1 )
	println( "Favorite Name : ${ favoriteOnceAgain.name }" )

	val cats1: List<Cat> = listOf( Cat("Whiskers", "Cat"), Cat("Rosie", "Cat") )
	val favorite1 = chooseFavorite( cats1 )
	println( "Favorite Name : ${ favorite1.name }" )

	val dogs2: List<Dog> = listOf( Dog( "Indian Dog!", "Dog"), Dog("Hound", "Dog"),
								Dog("BitBull", "Dog"), Dog("Pug", "Dog"),
								GermanShaperd("German GermanShaperd", "Dog") )
	val favorite2 = chooseFavorite( dogs2 )
	println( "Favorite Name : ${ favorite2.name }" )

	val animals: List<Animal> = listOf( Animal("Fish"), Animal("Bird"), Animal("Mammal") )
	for (animal in animals) println( animal.type )

 	// error: type mismatch: inferred type is Animal but Pet was expected
	val favoriteAgain2 = chooseFavorite( animals )
	// print( favoriteAgain2 )
}

//_____________________________________________________
//_____________________________________________________
//_____________________________________________________
//_____________________________________________________
// EXPERIMENT FOLLOWING CODE! MOMENT DONE RAISE FLAG!!!

fun main( ) {
	println("\nFunction : playWithChooseFavorite")
	playWithChooseFavorite()

	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
	// println("\nFunction : ")
}


