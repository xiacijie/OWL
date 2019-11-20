# OWL

Inorder to run OWL.

STEP 1:  Modify run.sh

 1. change `WALA_HOME` to the url of your WALA project on your machine
  
 2. change the include and link directory (-I and -L) for compilation to your own jdk location if necessary
  
 3. change the `DYLD_LIBRARY_PATH` to your own jdk location if necessary.
  
  
 STEP 2: Modify `OWLDeserializer.cpp` in `src/`
 
  1. change the location of the file `OWL.log` to your own on line #5
  
 
 STEP 3: Modify `OWLJNIClient.cpp`
 
  1. modify the class path on line #79 if necessary
  
  
 STEP 4: Add `Printer.java` in root directory to `com.ibm.wala.shrikeBT` and rebuild WALA. 
 
 STEP 5: run `bash run.sh`

You should see the shrikeBT instructions printed out. 
