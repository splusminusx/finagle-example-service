#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys, glob
from thrift import Thrift
from thrift.transport import THttpClient
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

from example.ttypes import *
from example import Example

try:
  transport = THttpClient.THttpClient('http://localhost:8080/')
  protocol = TBinaryProtocol.TBinaryProtocol(transport)

  client = Example.Client(protocol)

  transport.open()

  client.notify(True, "123123")
  print client.getState("123123")

  transport.close()

except Thrift.TException, tx:
  print '%s' % (tx.message)
