package com.ibm.wala.owl;

import java.util.jar.JarFile;

import com.ibm.wala.classLoader.ClassLoaderFactoryImpl;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.classLoader.Language;
import com.ibm.wala.ipa.callgraph.AnalysisCacheImpl;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.CallGraphBuilder;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.IAnalysisCacheView;
import com.ibm.wala.ipa.callgraph.impl.Everywhere;
import com.ibm.wala.ipa.callgraph.impl.Util;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.properties.WalaProperties;
import com.ibm.wala.shrikeBT.IInstruction;
import com.ibm.wala.ssa.DefaultIRFactory;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.IRFactory;
import com.ibm.wala.ssa.SSAOptions;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.Descriptor;
import com.ibm.wala.types.Selector;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.strings.Atom;

public class OWLTestRunner {

	static IInstruction[] instsToRun;
	
	public static void run(IInstruction[] insts) {
		try {
		instsToRun = insts;
		
	    AnalysisScope scope = new SingleMethodScope();
	    // add standard libraries to scope
	    String[] stdlibs = WalaProperties.getJ2SEJarFiles();
	    for (String stdlib : stdlibs) {
	      scope.addToScope(ClassLoaderReference.Primordial, new JarFile(stdlib));
	    }

	    // build the class hierarchy
	    IClassHierarchy cha =
	        ClassHierarchyFactory.make(scope, new ClassLoaderFactoryImpl(scope.getExclusions()));

	    IClass cls = cha.lookupClass(TypeReference.findOrCreate(ClassLoaderReference.Application, "Loperation"));
		System.err.println(cls);
	    IMethod mth = cls.getMethod(new Selector(Atom.findOrCreateUnicodeAtom("main"), Descriptor.findOrCreateUTF8("([Ljava/lang/String;)V")));
	    System.err.println(mth);
	    
	    System.err.println(cha.resolveMethod(mth.getReference()));
	    
	    IRFactory<IMethod> irf = new DefaultIRFactory();
	    IR ir = irf.makeIR(mth, Everywhere.EVERYWHERE, SSAOptions.defaultOptions());
	    
	    System.err.println(ir);
	    
	    AnalysisOptions options = new AnalysisOptions();
	    Iterable<Entrypoint> entrypoints =
	        Util.makeMainEntrypoints(ClassLoaderReference.Application, cha, new String[] {"Loperation"});
	    options.setEntrypoints(entrypoints);
	    IAnalysisCacheView cache = new AnalysisCacheImpl(irf);
	    CallGraphBuilder<?> builder = com.ibm.wala.ipa.callgraph.impl.Util.makeZeroCFABuilder(Language.JAVA, options, cache, cha, scope);
	    CallGraph cg = builder.makeCallGraph(options, null);
	    
	    System.out.println(cg);

		} catch (Throwable e) {
			System.err.println(e);
		}
	}

}
