# OWL

# Inorder to run OWL.

STEP 1:  Modify run.sh

 1. change `WALA_HOME` to the url of your WALA project on your machine
  
 2. change the include and link directory (-I and -L) for compilation to your own jdk location if necessary
  
 3. change the `DYLD_LIBRARY_PATH` to your own jdk location if necessary.
  
  
 STEP 2: Modify `OWLDeserializer.cpp` in `src/`
 
  1. change the location of the file `OWL.log` to your own file on line #5
  
 
 STEP 3: Modify `OWLJNIClient.cpp`
 
  1. modify the class path on line #79 if necessary
  
  
 STEP 4: Add `Printer.java` in root directory to `com.ibm.wala.shrikeBT` and rebuild WALA.  
    Comment: Printer.java does nothing but prints all instructions to STDOUT, this should be replaced by the target class where the shrikeBT instructions should be passed into.
  
 STEP 5: run `bash run.sh`

You should see the shrikeBT instructions printed out. 

# Inorder to call a java method. 
  1. write and append the method configuration (`JNIMethodConfig`) in `OWLJNIConfig.hpp / cpp`.
  2. Call the target java method through `jniClient->callMethod`
