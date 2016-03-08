package com.tasyrkin.scalatest

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

object ThrowsException {
  def invokeAndGetException = {
    throw new RuntimeException("The method is intentionally broken")
  }
}

object ExceptionTest extends App {
  val log = Logger(LoggerFactory.getLogger(ExceptionTest.getClass))

  try {
    ThrowsException.invokeAndGetException
  } catch {
    case e: RuntimeException => log.error("Exception occurred", e)
  } finally {
    log.info("finally block fired")
  }
}
