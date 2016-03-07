package com.tasyrkin.scalatest

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

class ThrowsException {
  def invokeAndGetException = {
    throw new RuntimeException("The method is intentionally broken")
  }
}

object ExceptionTest extends App {
  val log = Logger(LoggerFactory.getLogger(ExceptionTest.getClass))

  try {
    new ThrowsException().invokeAndGetException
  } catch {
    case e: RuntimeException => log.error("Exception occured", e)
  } finally {
    log.info("finally block fired")
  }
}
