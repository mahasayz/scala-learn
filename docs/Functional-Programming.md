## Functional Programming (Talk by Adil Akhter)

* Functions should be most preferred implementation
* Function = Total Function (Maths)
	* mapping of elements from Type A to Type B, such that for every element in A has exactly one mapping in set B

```scala
@ case class Coordinate(lng: Double, lat: Double)

@ def readCoordinate(): Coordinate = {
	println("Longitue: ")
	val lng = StdIn.readLine().toDouble
	println("Latitude: ")
	val lat = StdIn.readLine().toDouble
	Coordinate(lng, lat)
}
```
