#include "OWLAnalyser.hpp"
#include "OWLJNIConfig.hpp"

TR_OWLAnalyser::TR_OWLAnalyser(TR_OWLJNIClient* jniClient){
    _jniClient = jniClient;
}

void TR_OWLAnalyser::analyse(std::vector<jobject> instructions) {
    jobjectArray instructionArray = _jniClient->constructObjectArray(InstructionClassName, instructions);
    _jniClient->callMethod(PrinterConfig, NULL, 1, instructionArray);
}