#ifndef OWL_ANALYSER_HPP
#define OWL_ANALYSER_HPP

#include <stdio.h>
#include <vector>
#include "OWLJNIClient.hpp"

class TR_OWLAnalyser
{
private:
    TR_OWLJNIClient *_jniClient;
public:
    TR_OWLAnalyser(TR_OWLJNIClient *jniClient);
    void analyse(std::vector<jobject> instructions);
};

#endif