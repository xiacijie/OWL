package com.ibm.wala.shrikeBT;

public class Printer {
  static void print(Instruction[] instructions){
    for (int i = 0 ; i < instructions.length; i ++){
      System.out.println(i+": " + instructions[i].toString());
    }
  }
}
