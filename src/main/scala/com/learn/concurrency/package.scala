package com.learn

/**
  * Created by Mahbub on 5/16/2017.
  */
package object concurrency {

  def thread(body: => Unit): Thread = {
    val t = new Thread {
      override def run() = body
    }
    t
  }

}
