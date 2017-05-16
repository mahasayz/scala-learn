package com.learn.concurrency

/**
  * Created by malam on 5/15/17.
  */
object ThreadsCreation extends App {

  def thread(body: => Unit): Thread = {
    val t = new Thread {
      override def run() = body
    }
    t.start()
    t
  }

  val t = thread {
    Thread.sleep(1000)
    println("New thread running")
    Thread.sleep(1000)
    println("Still running")
    Thread.sleep(1000)
    println("Completed running")
  }
  t.join()
  println("New thread joined.")

}
