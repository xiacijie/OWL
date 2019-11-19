export WALA_HOME=/Users/jackxia/Project/IBM/WALA

g++ main.cpp -w -I./src/ ./src/OWLAnalyser.cpp ./src/OWLJNIClient.cpp ./src/OWLShrikeBTConstructor.cpp ./src/OWLDeserializer.cpp ./src/OWLTypes.cpp ./src/OWLJNIConfig.cpp  -I/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/include -I/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/include/darwin -L/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/lib -L/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/lib/server -ljli -ljvm

DYLD_LIBRARY_PATH=/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/lib:/Library/Java/JavaVirtualMachines/adoptopenjdk-12.jdk/Contents/Home/lib/server  ./a.out
