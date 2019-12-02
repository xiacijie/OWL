# OWL
Evironment: MacOs 10.14

Dependency: WALA https://github.com/wala/WALA

# In order to run OWL.

STEP 1:  Modify Makefile

 1. change `INCLUDE` and `LDFLAGS` to your own jdk path. 
  
STEP 2: Modify `OWLJNIClient.cpp`
 
  1. modify the class path on line #79 if necessary
  
STEP 3: Add `Printer.java` in root directory to `com.ibm.wala.shrikeBT` and rebuild WALA.  
    Comment: Printer.java does nothing but prints all instructions to stdout, this should be replaced by analyser where the shrikeBT instructions should be passed into.
  
STEP 4: set environment variables
  1. export WALA_HOME=/Users/jackxia/Project/IBM/WALA (replace by yours)
  2. export DYLD_LIBRARY_PATH=/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/lib:/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/lib/server ( replace by yours)
  
STEP 5: run `make all`

STEP 6: run `./OWL ./examples/add.log`

You should see the shrikeBT instructions printed out. 

# how to to call a java method. 
  1. write and append the method configuration (`JNIMethodConfig`) in `OWLJNIConfig.hpp / cpp`.
  2. Call the target java method through `jniClient->callMethod`
