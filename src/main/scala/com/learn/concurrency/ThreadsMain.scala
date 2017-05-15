package com.learn.concurrency

/**
  * Created by malam on 5/15/17.
  */
object ThreadsMain extends App {

  val t: Thread = Thread.currentThread()
  val name = t.getName
  println(s"I am the thread $name")

}
