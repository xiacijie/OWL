#include "src/OWLTypes.hpp"
#include "src/OWLAnalyser.hpp"
#include "src/OWLJNIClient.hpp"
#include "src/OWLShrikeBTConstructor.hpp"
#include "src/OWLDeserializer.hpp"
#include "src/OWLVerifier.hpp"
#include <vector>

using namespace std;
int main(){

    if (TR_OWLJNIClient::startJVM()){
        TR_OWLJNIClient *jniClient = TR_OWLJNIClient::getInstance();
        
        //deserilize 
        TR_OWLDeserializer* deserializer = new TR_OWLDeserializer();
        deserializer->deserialize("/Users/jackxia/Project/IBM/openj9-openjdk-jdk13/OWL/OWL.log");
        vector<TranslationUnit> translationUnits = deserializer->getTranslationUnits();
        MethodInfo methodInfo = deserializer->getMethodInfo();
        

        //method info
        printf("%s.%s %s\n", methodInfo.className, methodInfo.methodName, methodInfo.signature);
        
        //constructing shrikeBT instructions
        TR_OWLShrikeBTConstructor* constructor = new TR_OWLShrikeBTConstructor(jniClient);
        vector<jobject> shrikeBTInstructions = constructor->constructShrikeBTInstructions(translationUnits);

        //verify the shrikeBT instructions
        printf("========= VERIFY ===========\n");
        TR_OWLVerifier *verifier = new TR_OWLVerifier(jniClient);
        bool status = verifier->verify(&shrikeBTInstructions[0], shrikeBTInstructions.size(), false, true, methodInfo.className, methodInfo.signature);
        
        if (status == true) {
            printf("Successful to verify shrikeBT instructions!\n");
        }
        else{
            printf("Fail to verify shrikeBT instructions!\n");
        }

        //analyse
        printf("======== ANALYSE ========\n");
        TR_OWLAnalyser * analyser = new TR_OWLAnalyser(jniClient);
        analyser->analyse(shrikeBTInstructions);
        
        delete verifier;
        delete deserializer;
        delete analyser;
        delete constructor;
    }
    else{
        printf("JVM cannot be start! Program termniates!\n");
        exit(1);
    }
    TR_OWLJNIClient::destroyJVM();
    TR_OWLJNIClient::destroyInstance();

    return 0;

    
}
