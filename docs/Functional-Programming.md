## Functional Programming (Talk by Adil Akhter)

* Functions should be most preferred implementation
* Function = Total Function (Maths)
	* mapping of elements from Type A to Type B, such that for every element in A has exactly one mapping in set B

```java
@ case class Coordinate(lng: Double, lat: Double)

@ def readCoordinate(): Coordinate = {
	println("Longitue: ")
	val lng = StdIn.readLine().toDouble
	println("Latitude: ")
	val lat = StdIn.readLine().toDouble
	Coordinate(lng, lat)
}
```

> readCoordinate: Unit => Coordinate

* Is `readCoordinate()` a function as per defintion?
	* It's not as we are throwing side-effects
	* If `string` read from input is not a valid `double` then we get an exception thrown
	* Why is this bad?
		* Following the type-signature there's no way of knowing that this might throw an exception
		* Breaks the law of `referential transparency` of FP paradigm

### Functor

* Suppose you have have value of type `A` within a container and you would like to apply function `f: A => B` on the value
	* You would have to do the following in-order to do so
	* `F[A] --unwrap--> A -- f: A => B --> B --wrap--> F[B]`
		* `F[A]` could be anything like List[A], Option[A], Future[A], Task[A], Try[A], etc.

> Functor: Algebrai type that applies a function to every element of a structure
```scala
trait Functor[F[_]] {
	def map[A,B](f: A => B): F[A] => F[B]
}
```
> For `F` to be a functor, needs to implement `map` operation

