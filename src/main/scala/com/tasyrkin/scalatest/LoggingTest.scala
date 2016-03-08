package com.tasyrkin.scalatest

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

object LoggingTest extends App {

  val LOG = Logger(LoggerFactory.getLogger(LoggingTest.getClass))

  LOG.debug(s"debug message at [${System.currentTimeMillis()}]")
  LOG.trace(s"trace message at [${System.currentTimeMillis()}]")
  LOG.info(s"info message at [${System.currentTimeMillis()}]")
  LOG.warn(s"warn message at [${System.currentTimeMillis()}]")
  LOG.error(s"error message at [${System.currentTimeMillis()}]")

}
