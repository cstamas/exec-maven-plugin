/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
File outLog = new File(basedir, 'target/out.log')
assert outLog.exists()

int outIndex = -1
int errIndex = -1


// Sometimes this test fails, because the line and linebreak seem to be separate, unsynchronized calls.
// Would be nice if commons-exec could solve this...
// http://stackoverflow.com/questions/6121786/java-synchronizing-standard-out-and-standard-error
outLog.eachLine { line ->
    int origIndex = line.getAt( 0 ).toInteger();
    if ( origIndex % 2 == 0 )
    {
      assert errIndex < origIndex;
      errIndex = origIndex;
    }
    else
    {
      assert outIndex < origIndex;
      outIndex = origIndex;
    }
}