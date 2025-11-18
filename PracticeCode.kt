
/*
kotlinc -include-runtime Hello.kt -d hello.jar
java -jar nature.jar
*/

//_______________________________________________________

// Language Design Principles
//		Brings Most Common Practices As Part Of Language Design

// BEST PRACTICE
//		Design Towards Immutability Rather Mutability

// Corollary
//		Classes Are Not Meant To Be Inherited Must Be Final
//

// 03KotlinClasses.kt:13:16: error: this type is final, so it cannot be extended.
// class Button : View() {
//                ^^^^
// 03KotlinClasses.kt:14:6: error: 'click' hides member of supertype 'View' and needs an 'override' modifier.
// 	fun click() = println("Button: Clicked!...")

// In Kotlin
//		Classes Are Final By Default
//			Final Classes Can't Inherited!
//		Members Are Final By Default

// In C++/Java
//		Classes Are Open By Default
//		Members Are Open By Default

// class View {
	// error: 'click' in 'View' is final and cannot be overridden.
open class View {
	open fun click() = println("View: Clicked!...")
	open fun magic() = println("View: Magic!...")
}

class Button : View() {
	override fun click() = println("Button: Clicked!...")
	override fun magic() = println("Button: Magic!...")
	fun doFun() = println("Button: doFun!...")
}

// Adding Functionality
// Using Extention Functions
//		Extension Function Doesn't Participates In Overridding
fun View.showOff() 		= println("View: showOff!...")
fun Button.showOff() 	= println("Button: showOff!...")

fun playWithInheritance() {
	// val v: View = View()
	val v = View()
	v.click()
	v.magic()
	v.showOff()

	// val b: Button = Button()
	val b = Button()
	b.click()
	b.magic()
	b.showOff()
	b.doFun()

	// Object Can Have Multiple Types
	//		va Object Has Two Types Button and View Types
	// 		Child Call Object Can Be Stored In Parent Class Reference
	val va: View = Button() 
	va.click()
	va.magic()
	va.showOff()
	// va.doFun() // error: unresolved reference 'doFun'

	val bringKiduBack = va as Button
	bringKiduBack.doFun()
}

//_____________________________________________________
// EXPERIMENT FOLLOWING CODE! MOMENT DONE RAISE FLAG!!!

interface Clickable2 {
    fun click()
}

class Button2 : Clickable2 {
    override fun click() = println("I was clicked")
}

fun functionButtonClick() {
    Button2().click()
}


//_____________________________________________________
// EXPERIMENT FOLLOWING CODE! MOMENT DONE RAISE FLAG!!!

// Creating Type Clickable3
// Abstract Type [Mathematical]
//		Operation = { click(), showOff() }
//		Range = { }
interface Clickable3 {
    fun click()
    // Default Implementation
    fun showOff() = println("I'm clickable!") 
}

// Compiler Generate Following Interface and Class
//		To Work With < Java8
// interface Clickable3 {
//     fun click()
//     fun showOff()
// }

// class Clickable3Class implements Clickable3 {
//     fun click()
//     fun showOff() = println("I'm clickable!")
// }

// Creating Type Clickable3
// Abstract Type [Mathematical]
//		Operation = { click(), showOff() }
//		Range = { }
interface Focusable3 {
    fun setFocus(b: Boolean) =
        println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable!")
}

class Button3 : Clickable3, Focusable3 {
    override fun click() = println("I was clicked")

    override fun showOff() {
    	// super.showOff()
        super<Clickable3>.showOff()
        super<Focusable3>.showOff()
    }
}

fun functionButtonClickableAndFocusable() {
    val button = Button3()
    button.showOff()
    button.setFocus(true)
    button.click()
}

//_____________________________________________________
// EXPERIMENT FOLLOWING CODE! MOMENT DONE RAISE FLAG!!!

enum class Colour {
	RED, GREEN, BLUE, YELLOW, ORANGE, UKNOWN
}

fun mixColours(c1 : Colour, c2: Colour ) = when ( setOf(c1, c2) ) {
	setOf( Colour.BLUE, Colour.GREEN ) 	-> Colour.YELLOW
	setOf( Colour.RED, Colour.YELLOW )  -> Colour.ORANGE
	// else -> throw Exception("Dirty Colour!")
	// else -> "Unknown Colour"
	else -> Colour.UKNOWN
}

fun playWithColourMixing() {
	println( mixColours( Colour.GREEN, Colour.BLUE ) )
	println( mixColours( Colour.YELLOW, Colour.RED ) )
	// println( mixColours( Colour.GREEN, Colour.RED ) )	
}

//_____________________________________________________
// EXPERIMENT FOLLOWING CODE! MOMENT DONE RAISE FLAG!!!

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr 

fun evaluate(e: Expr) : Int = when(e) {
	is Num -> e.value
	is Sum -> evaluate(e.left) + evaluate(e.right)
	else -> throw IllegalArgumentException("Unknown Expression")
}

fun playWithEvaluate() {
	// 100 + 200
	println( evaluate(Sum( Num(100), Num(200) ) ) )

	// (100 + 200) + 99
    println( evaluate(Sum(Sum(Num(100), Num(200)), Num(99))))
}

//_____________________________________________________
// EXPERIMENT FOLLOWING CODE! MOMENT DONE RAISE FLAG!!!

sealed class Expr1 {
	class Num(val value: Int) : Expr1()
	class Sum(val left: Expr1 , val right: Expr1 ) : Expr1() 
}

// class Sub(val left: Expr1 , val right: Expr1 ) : Expr1() 

fun evaluateAgain( e: Expr1 ) : Int = when(e) {
	is Expr1.Num -> e.value
	is Expr1.Sum -> evaluateAgain(e.left) + evaluateAgain(e.right)
	// else -> throw IllegalArgumentException("Unknown Expression")
}

fun playWithEvaluateAgain() {
	// 100 + 200
	println( evaluateAgain( Expr1.Sum( Expr1.Num(100), Expr1.Num(200) ) ) )

	// (100 + 200) + 99
    println( evaluateAgain( Expr1.Sum( Expr1.Sum( Expr1.Num(100), 
    	Expr1.Num(200)), Expr1.Num(99))))
}

//_________________________________________________________

enum class Color(val r: Int, val g: Int, val b: Int ) {
	RED( 255, 0, 0 ), GREEN( 0, 255, 0 ), BLUE(0, 0, 255 );

	// Member Function
	fun rgb() = ( r * 256 + g ) * 256 + b
}


//_________________________________________________________
// DESIGN PRINCIPLE
//		Design Towards Determinism Rather Than Non Determinism

// Assume Library Code
class File(name: String)
class DataSource( source: String )

sealed interface Error
sealed class IOError() : Error
class FileReadError( val file: File ) : IOError() 
class DatabaseError( val source: DataSource ) : IOError()

// Assume Library Code Consumer
fun handleError( e: Error ) = when( e ) {
	is FileReadError -> { println("Error While File Reading!") }
	is DatabaseError -> { println("Error While Database Dealing!") }
	// else -> 
	//	error: 'when' expression must be exhaustive. 
	//			Add the 'is DatabaseError' branch or an 'else' branch.
}

fun playWithHandleError() {
	val file = File(name = "data.txt")
	val source = DataSource( source = "employee.db")
	
	val fileReadError = FileReadError(file)
	val databaseError = DatabaseError(source)

	handleError( fileReadError )
	handleError( databaseError )
}

//_____________________________________________________

// DEFININING STATE MACHINE
//_____________________________________________________

/*
sealed class UIState {
    data object Loading : UIState()
    data class Success(val data: String) : UIState()
    data class Error(val exception: Exception) : UIState()
}

fun updateUI(state: UIState) {
    when (state) {
        is UIState.Loading 	-> showLoadingIndicator()
        is UIState.Success 	-> showData(state.data)
        is UIState.Error 	-> showError(state.exception)
    }
}

fun showLoadingIndicator() 	= println("UIState Loading...")
fun showData( val state: UIState.Success ) 	= println("UIState Success: $state")
fun showError( val state: UIState.Error ) 	= println("UIState Error: $state")

*/

//_______________________________________________________

// BEST PRACTICE
//		FUNCTION/METHOD/CONSTRUCTOR POLYMORPHISM
//			1. Always Prefer Default Arguments Over Function/Method/Constructor Overloading
//					Placed Arguments Than Named Arguments/Arrays Than Maps/Dictitionary

//			Constructor With Default Arugments
//	Default Arguments
//		Means Arguments With Default Value
class User( val nickName: String, val isSubscribed: Boolean = true )

fun playWithUser() {
	val basanti = User("Basanti")
	println( basanti.nickName )
	println( basanti.isSubscribed )

	val gabbar = User("Gabbar Singh", false )
	println( gabbar.nickName )
	println( gabbar.isSubscribed )
}

// Function Arguments Are Immutable By Default
// fun functionFullName( val firstName: String, var middleName: String = "", lastName: String = "" ): String {

// Polymorphic Function
// 		Using Mechanims : Default Arguments
fun functionFullName( firstName: String, middleName: String = "", lastName: String = "" ): String {
	return firstName + " " + middleName + " " + lastName
}

// Polymorphic Function
// 		Using Mechanims : Function Overloading
fun getFullName( firstName: String ): String {
	return firstName
}

fun getFullName( firstName: String, middleName: String ): String {
	return firstName + " " + middleName
}

// fun getFullName( firstName: String, lastName: String ): String {
// 	return firstName + " " + lastName
// }

fun getFullName( firstName: String, middleName: String, lastName: String): String {
	return firstName + " " + middleName + " " + lastName
}

fun playWithFullName() {
	// Function Call With Labels/Named Arguments
	var gabbar = functionFullName( firstName = "Gabbar " )
	println( gabbar )

	gabbar = functionFullName( firstName = "Gabbar", lastName = "Singh ")
	println( gabbar )

	gabbar = functionFullName( firstName = "Gabbar", middleName = "Singh", lastName = "Dakku")
	println( gabbar )	

	// Function Call With Placed Arguments
	gabbar = functionFullName( "Gabbar", "Singh","Dakku")
	println( gabbar )	

	gabbar = getFullName( firstName = "Gabbar " )
	println( gabbar )

	// gabbar = getFullName( firstName = "Gabbar", lastName = "Singh ")
	// println( gabbar )

	gabbar = getFullName( firstName = "Gabbar", middleName = "Singh", lastName = "Dakku")
	println( gabbar )	
}


//_______________________________________________________

fun getFacebookName( accountID: Int ) = "FB:$accountID"

// Interface With Property Members
//		Interfaces Are Abstract Type [Mathematicall]
//		Inferfaces Should Be Used For Forcing Contract In System
//		Should Be Last Choice In Design and Should Be Used In Rarest Rare Scenrios
//		Rather Properties Should Be Part Of Concrete

interface User1 {
	 // error: property initializers in interfaces are prohibited.
	// val nickName : String = "Unknown"
	val nickName : String // Defining Contract : Property Name And Accessor	
}

// error: 'nickName' hides member of supertype 'User1' and needs an 'override' modifier.
// class PrivateUser( val nickName : String ) : User1

// Overriding Properties Means Overriding Accessor Methods i.e. Getter/Setter
class PrivateUser( override val nickName : String ) : User1

class SubscribingUser( val email: String ) : User1 {
	override val nickName : String
		get() = email.substringBefore('@')
}

class FacebookUser( val email: String ) : User1 {
	override val nickName = getFacebookName( 420 )
}

fun playWithInterfaceProperties() {
	val thakur = PrivateUser("gabbar@ramgrah.com")
	println( thakur.nickName ) // thakur.getNicName()

	val basanti = SubscribingUser("gabbar@ramgrah.com")
	println( basanti.nickName )

	val gabbar = FacebookUser( "420@ramgrah.com" )
	println( gabbar.nickName )
}

//_______________________________________________________

// Every Property Have At Most Three Things
//		Member Variable/Backing Field
//		Getter and Setter

// Abstract Types = { Operation, {} }
// 		Hence field Can Be Accessed/Stored In Interfaces

interface User2 {
	val email: String
	val nickName: String
		get() = email.substringBefore('@') // Getter Contract With Default Implementation

	var name: String
		// Getter/Setter Contract With Default Implementation
		// get() = "Unknown!"
		get() {
			// return field
			return "Unknown!"
		}

		set( value ) {
			// Here Backing field Is Member Variable For Proeprty name
			// field = value // error: property in interface cannot have a backing field
			println("Setter Called...: $value ")
		}
}

class Employee() : User2 {
	// override val email: String = "gabbar@ramgrah.com"
	override val email: String
		get() = "gabbar@ramgrah.com"
}

fun playWithEmployee() {
	val gabbar = Employee()
	println( gabbar.email )
	println( gabbar.nickName )
	println( gabbar.name )

	gabbar.name = "Gabbar Singh"
	println( gabbar.name )	
}

//_______________________________________________________

// Type = { Operation, Range }
// Concrete Class
//		Concrete Classes Can Have Instances/Object
//		Object = { State, MetaState }
//			Here MetaState State About State: Minimum MetaState is Message Handling

class User3( val name: String ) {
	var address: String = "Unknown!"
		get() {
			println("Getter Called...")
			return field
		}
		set ( value: String ) {
			println("Setter Called...: $field")			
			field = value
			println("Setter Called...: $field")
		}
}

fun playWithUser3() {
	val gabbar = User3("Gabbar Singh")
	println( gabbar.name )
	println( gabbar.address )

	gabbar.address = "Ramgarh Ke Sholay!"
	println( gabbar.address )
}

//_______________________________________________________

class User4( ) {
	var name: String = "Unknown!"
	var address: String = "Unspecified!"
		get() {
			println("Getter Called...")
			return field
		}
		set ( value: String ) {
			println("Setter Called...: $field")			
			field = value
			println("Setter Called...: $field")
		}
}

fun playWithUser4() {
	val gabbar = User4()
	println( gabbar.name )
	println( gabbar.address )

	gabbar.name = "Gabbar Singh"
	gabbar.address = "Ramgarh Ke Sholay!"
	println( gabbar.name )
	println( gabbar.address )	
}


//_______________________________________________________
//_______________________________________________________
//_______________________________________________________
//_______________________________________________________
//_______________________________________________________

fun main() {
	println("\nFunction: playWithInheritance")
	playWithInheritance()

	println("\nFunction: playWithUser")
	playWithUser()

	println("\nFunction: playWithFullName")
	playWithFullName()

	println("\nFunction: playWithEmployee")
	playWithEmployee()

	println("\nFunction: playWithUser3")
	playWithUser3()

	println("\nFunction: playWithUser4")
	playWithUser4()
	
	// println("\nFunction: ")	
	// println("\nFunction: ")
	// println("\nFunction: ")	
	// println("\nFunction: ")
	// println("\nFunction: ")	
	// println("\nFunction: ")
	// println("\nFunction: ")	
}



